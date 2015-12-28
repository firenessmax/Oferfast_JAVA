package service;

import java.util.ArrayList;
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

import facade.EtiquetaFacade;
import facade.OfertaHasEtiquetaFacade;
import facade.OfertaFacade;
import model.Etiqueta;
import model.OfertaHasEtiqueta;
import model.Oferta;

@Path("/etiquetas")
public class EtiquetaService {

	@EJB 
	EtiquetaFacade etiquetaFacadeEJB;

	@EJB 
	OfertaHasEtiquetaFacade ofertaHasEtiquetaFacadeEJB;

	@EJB 
	OfertaFacade ofertaFacadeEJB;
	
	Logger logger = Logger.getLogger(EtiquetaService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Etiqueta> findAll(){
		return etiquetaFacadeEJB.findAll();
	}
	
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Etiqueta find(@PathParam("id") Integer id) {
        return etiquetaFacadeEJB.find(id);
    }
    
    @GET
    @Path("{id}/ofertas")
    @Produces({"application/xml", "application/json"})
    public List<Oferta> findOfertas(@PathParam("id") Integer id) {
    	List<OfertaHasEtiqueta> listaOfertaHasEtiqueta = ofertaHasEtiquetaFacadeEJB.findAll(); //relacion oferta-etiqueta
    	List<Oferta> listaOferta = ofertaFacadeEJB.findAll();//se obtienen todas las ofertas
    	List<Oferta> respuesta = new ArrayList<>(); //lista para la respuesta
    	for(int i=0; i<listaOfertaHasEtiqueta.size(); i++){
        	if(listaOfertaHasEtiqueta.get(i).getEtiquetaId() == id ){ //la oferta-etiqueta tiene la misma etiquetaId que la id actual
        		for(int j=0; j<listaOfertaHasEtiqueta.size(); j++){
            		if(listaOferta.get(j).getOfertaId() == listaOfertaHasEtiqueta.get(i).getOfertaId()){
            			respuesta.add(listaOferta.get(j));
            			break;
            		}
            	}
        	}
        }
       return respuesta;
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Etiqueta entity) {
		etiquetaFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Etiqueta entity) {
    	entity.setEtiquetaId(id.intValue());
    	etiquetaFacadeEJB.edit(entity);
    }
}
