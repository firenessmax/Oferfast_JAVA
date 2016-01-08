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
	@Path("newAntiguo")
    @Consumes({"application/xml", "application/json"})
    public void create(Usuario entity) {
		usuarioFacadeEJB.create(entity);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public Response crear(JsonObject entrada) {
		if(entrada.getString("username").matches("[a-zA-z0-9_]+[a-zA-z0-9_.]*")){
			return usuarioFacadeEJB.crear(entrada);
		} else {
			JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
			jsonObjBuilder.add("ERROR", "El username no es valido");
			JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
		}
    }
	
	@POST
	@Path("login")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response registro(JsonObject entrada){
		return usuarioFacadeEJB.login(entrada);
	}

    @PUT
    @Path("{id}/editAntiguo")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Usuario entity) {
    	entity.setUsuarioId(id.intValue());
    	usuarioFacadeEJB.edit(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public Response editar(@PathParam("id") Integer id, JsonObject entrada) {
    	int bulean=0;//false
    	try{
    		String username = entrada.getString("username");
    		bulean=1;
    	} catch(Exception e){
    		bulean=0;
    	}
    	if(bulean == 1){//se desea cambiar el username
    		if(entrada.getString("username").matches("[a-zA-z0-9_]+[a-zA-z0-9_.]*")){
    			Usuario aux = usuarioFacadeEJB.find(id);
    			aux = usuarioFacadeEJB.editar(entrada, aux); //editar creado, no el que existe
    	    	usuarioFacadeEJB.edit(aux);
    	    	//respuesta
    			JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
    			jsonObjBuilder.add("INFO", "Datos actualizados");
    			JsonObject jsonObj = jsonObjBuilder.build();
    			return Response.status(Response.Status.OK).entity(jsonObj).build();
    		} else {
    			JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
    			jsonObjBuilder.add("INFO", "Username no valido");
    			JsonObject jsonObj = jsonObjBuilder.build();
    			return Response.status(Response.Status.OK).entity(jsonObj).build();
    		}
    	} else {
    		Usuario aux = usuarioFacadeEJB.find(id);
			aux = usuarioFacadeEJB.editar(entrada, aux); //editar creado, no el que existe
	    	usuarioFacadeEJB.edit(aux);
	    	//respuesta
			JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
			jsonObjBuilder.add("INFO", "Datos actualizados");
			JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
    	}
    }

    @PUT
    @Path("{id}/social")
    @Consumes({"application/xml", "application/json"})
    public void editSocial(@PathParam("id") Integer id, JsonObject entrada) {
    	Usuario aux = usuarioFacadeEJB.find(id);
    	aux = usuarioFacadeEJB.editarSocial(entrada, aux);
    	usuarioFacadeEJB.edit(aux);
    }

    @PUT
    @Path("{id}/visible")
    @Consumes({"application/xml", "application/json"})
    public void editVisible(@PathParam("id") Integer id, JsonObject entrada) {
    	Usuario aux = usuarioFacadeEJB.find(id);
    	aux = usuarioFacadeEJB.editarVisible(entrada, aux);
    	usuarioFacadeEJB.edit(aux);
    }

}
