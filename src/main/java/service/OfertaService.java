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

import facade.OfertaFacade;
import model.Etiqueta;
import model.Oferta;

@Path("/ofertas")
public class OfertaService {

	@EJB 
	OfertaFacade ofertaFacadeEJB;
	
	Logger logger = Logger.getLogger(OfertaService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Oferta> findAll(){
		return ofertaFacadeEJB.findAll();
	}
	
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Oferta find(@PathParam("id") Integer id) {
        return ofertaFacadeEJB.find(id);
    }
    
    @GET
    @Path("{id}/etiquetas")
    @Produces({"application/xml", "application/json"})
    public List<Etiqueta> find_etiquetas(@PathParam("id") Integer id) {
        return ofertaFacadeEJB.find(id).getListaSoloEtiquetas();
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Oferta entity) {
		ofertaFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Oferta entity) {
    	entity.setOfertaId(id.intValue());
    	ofertaFacadeEJB.edit(entity);
    }

}
