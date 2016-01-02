package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import facade.UsuarioFacade;
import model.Oferta;
import model.Usuario;

@Path("/usuarios")
public class UsuarioService {

	@EJB 
	UsuarioFacade usuarioFacadeEJB;

	@PersistenceContext 
	EntityManager em;
	
	Logger logger = Logger.getLogger(UsuarioService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Usuario> findAll(){
		return usuarioFacadeEJB.findAll();
	}
	
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Usuario find(@PathParam("id") Integer id) {
        return usuarioFacadeEJB.find(id);
    }
	
    @GET
    @Path("{id}/ofertas")
    @Produces({"application/xml", "application/json"})
    public List<Oferta> findOfertas(@PathParam("id") Integer id) {
        return usuarioFacadeEJB.findOfertas(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Usuario entity) {
		usuarioFacadeEJB.create(entity);
    }
	
	@POST
	@Path("login")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response registro(JsonObject entrada){
		return usuarioFacadeEJB.login(entrada);
	}

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    //public void edit(@PathParam("id") Integer id, Usuario entity) {
    public void edit(@PathParam("id") Integer id, Usuario entity) {
		entity.setUsuarioId(id.intValue());
    	Usuario aux = usuarioFacadeEJB.find(id);
    	entity = usuarioFacadeEJB.editar(entity, aux); //editar creado, no el que existe
    	usuarioFacadeEJB.edit(entity);
    	//entity.setUsuarioId(id.intValue());
    	//usuarioFacadeEJB.edit(entity);
    }

}
