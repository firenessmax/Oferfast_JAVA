package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.ImagenOfertaFacade;
import model.ImagenOferta;

@Path("/imagenOfertas")
public class ImagenOfertaService {
	
	@EJB 
	ImagenOfertaFacade imagenOfertaFacadeEJB;
	
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
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, ImagenOferta entity) {
    	entity.setImagenOfertaId(id.intValue());
    	imagenOfertaFacadeEJB.edit(entity);
    }
	
	
}
