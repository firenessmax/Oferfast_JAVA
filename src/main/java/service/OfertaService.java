package service;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.json.JsonObject;
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
import facade.OfertaFacade;
import facade.OfertaHasEtiquetaFacade;
import model.Etiqueta;
import model.ImagenOferta;
import model.Oferta;
import model.OfertaHasEtiqueta;
import model.Usuario;

@Path("/ofertas")
public class OfertaService {

	@EJB 
	OfertaFacade ofertaFacadeEJB;

	@EJB 
	EtiquetaFacade etiquetaFacadeEJB;

	@EJB 
	OfertaHasEtiquetaFacade ofertaHasEtiquetaFacadeEJB;
	
	Logger logger = Logger.getLogger(OfertaService.class.getName());
	
	@GET	//independiente si son visibles o no
	@Path("/all") 
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
	
	@GET	//solo los visibles
    @Produces({"application/xml", "application/json"})
	public List<Oferta> findAllVisible(){
		List<Oferta> lista = ofertaFacadeEJB.findAllVisible(1);
		return lista;
	}
	
	@GET	//los no visibles
	@Path("/notVisible")
    @Produces({"application/xml", "application/json"})
	public List<Oferta> findAllNotVisible(){
		List<Oferta> lista = ofertaFacadeEJB.findAllVisible(0);
		return lista;
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
    @Path("/newAntiguo")
    @Consumes({"application/xml", "application/json"})
	//aceptar de entrada cualquier JSON, y separarlo, verificar las etiquetas, etc etc etc
    public void create(Oferta entity) {
		ofertaFacadeEJB.create(entity);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
	//aceptar de entrada cualquier JSON, y separarlo, verificar las etiquetas, etc etc etc
    public int createOferta(JsonObject entrada) {
		//se crea la oferta
		Oferta laOferta = ofertaFacadeEJB.crear(entrada);
		//se crea la oferta en la BD
		ofertaFacadeEJB.create(laOferta);
		//se obtiene la clase oferta q esta en BD (ahora tiene el valor de la ID)
		laOferta = ofertaFacadeEJB.findByOferta(laOferta);
		//se ven las etiquetas que estan
		try{
			//existen etiquetas
			if(entrada.getJsonArray("tags").size() > 0){
				//verificar si existe el tag
				List<Etiqueta> listaTag = etiquetaFacadeEJB.addPorOferta(entrada.getJsonArray("tags"));
				if(listaTag.isEmpty()){
					for(int i=0; i<listaTag.size(); i++){
						//se guardan las nuevas etiquetas
						etiquetaFacadeEJB.create(listaTag.get(i));
					}
				}
				//una vez creadas las etiquetas q no existen, se crean las "conexiones"
				//para eso, se crean las OfertaHasEtiqueta con la Oferta y los nombres de las etiquetas existentes
				List<OfertaHasEtiqueta> listaOHA = ofertaHasEtiquetaFacadeEJB.createByOfertaEtiqueta(laOferta, entrada.getJsonArray("tags"));
				for(int i=0; i<listaTag.size(); i++){
					//se guardan en BD, todas las OfertaHasEtiqueta
					ofertaHasEtiquetaFacadeEJB.create(listaOHA.get(i));
				}
				return entrada.getJsonArray("tags").size();
			} else {
				return -1;
			}
		} catch(Exception e){
			return -2;
		}
		//return null;
		//falta crear etiquetas y relaciones
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
