package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import facade.ImagenOfertaFacade;
import facade.OfertaFacade;
import model.ImagenOferta;
import model.Oferta;

@Path("/imagenes")
public class ImagenOfertaService {
	
	@EJB 
	ImagenOfertaFacade imagenOfertaFacadeEJB;
	
	@EJB 
	OfertaFacade ofertaFacadeEJB;
	
	Logger logger = Logger.getLogger(ImagenOfertaService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<ImagenOferta> findAll(){
		return imagenOfertaFacadeEJB.findAll();
	}
	
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public ImagenOferta find(@PathParam("id") Integer id) {
        return imagenOfertaFacadeEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(ImagenOferta entity) {
		imagenOfertaFacadeEJB.create(entity);
		//modificar cantidadImagenes en la oferta
		Oferta laOferta = ofertaFacadeEJB.find(entity.getOfertaId()) ;
		laOferta.setImagesNumber(laOferta.getImagesNumber()+1);
    	ofertaFacadeEJB.edit(laOferta);
    }
	
	@POST
    @Path("/addMultiple")
    @Consumes({"application/xml", "application/json"})
    public Response createMultiple(JsonObject entrada) {
		int imagesNumber = entrada.getJsonArray("imagenes").size();
		int ofertaId = entrada.getInt("ofertaId");
		List<ImagenOferta> listaIO = imagenOfertaFacadeEJB.addPorOferta(imagesNumber, entrada, ofertaId);
		for(int i=0; i<imagesNumber; i++){
			//se guardan en BD, todas las ImagenOferta
			this.create(listaIO.get(i));
		}
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("INFO", "Imagenes agregadas correctamente");
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, ImagenOferta entity) {
    	entity.setImagenOfertaId(id.intValue());
    	imagenOfertaFacadeEJB.edit(entity);
    }
	
	
}
