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

import facade.OfertaHasEtiquetaFacade;
import model.OfertaHasEtiqueta;

public class OfertaHasEtiquetaService {

	@EJB 
	OfertaHasEtiquetaFacade ofertaHasEtiquetaFacadeEJB;
	
	Logger logger = Logger.getLogger(OfertaService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<OfertaHasEtiqueta> findAll(){
		return ofertaHasEtiquetaFacadeEJB.findAll();
	}
	
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public OfertaHasEtiqueta find(@PathParam("id") Integer id) {
        return ofertaHasEtiquetaFacadeEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(OfertaHasEtiqueta entity) {
		ofertaHasEtiquetaFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, OfertaHasEtiqueta entity) {
    	entity.setOfertaId(id.intValue());
    	ofertaHasEtiquetaFacadeEJB.edit(entity);
    }

}
