package service;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.EtiquetaFacade;
import model.Etiqueta;
import model.Oferta;


@Path("/etiquetas")
public class EtiquetaService {

	@EJB 
	EtiquetaFacade etiquetaFacadeEJB;

	@PersistenceContext 
	EntityManager em;
	
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
    	return etiquetaFacadeEJB.findOfertaByID(id);
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
