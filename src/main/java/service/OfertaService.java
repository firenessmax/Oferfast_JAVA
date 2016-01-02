package service;

import java.util.List;
import java.util.logging.Level;
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

import facade.OfertaFacade;
import model.Etiqueta;
import model.ImagenOferta;
import model.Oferta;
import model.OfertaHasEtiqueta;
import model.Usuario;

@Path("/ofertas")
public class OfertaService {

	@EJB 
	OfertaFacade ofertaFacadeEJB;

	@PersistenceContext 
	EntityManager em;
	
	Logger logger = Logger.getLogger(OfertaService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Oferta> findAll(){
		logger.setLevel(Level.ALL);
		logger.entering("OfertaService", "findAll");
		List<Oferta> ofertas = ofertaFacadeEJB.findAll();
		if(ofertas.isEmpty()){
			logger.exiting("OfertaService","findAll","sin elementos");
			return ofertas;
		}
		logger.exiting("OfertaService","findAll", ofertas.size());
		return ofertas;
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
    public List<Etiqueta> findEtiquetas(@PathParam("id") Integer id) {
        return ofertaFacadeEJB.findEtiquetaByID(id);
    }
	
	@GET
    @Path("{id}/imagenOferta")
    @Produces({"application/xml", "application/json"})
    public List<ImagenOferta> findImagenes(@PathParam("id") Integer id) {
        return ofertaFacadeEJB.findImagenByID(id);
    }
	
	@GET
    @Path("{id}/usuario")//JSON -> reputation, type, urlProfilePicture, urlProfileThumbnail, username, usuarioId
    @Produces({"application/xml", "application/json"})
    public Usuario findUsuario(@PathParam("id") Integer id) {
        int usuarioId = ofertaFacadeEJB.find(id).getUsuarioId();
        return ofertaFacadeEJB.findUsuarioByID(usuarioId);
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
    	Oferta aux = ofertaFacadeEJB.find(id);
    	entity = ofertaFacadeEJB.editar(entity, aux);
    	ofertaFacadeEJB.edit(entity);
    }

}
