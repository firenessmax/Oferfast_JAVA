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

import facade.UsuarioLikeComentarioFacade;
import model.Configuracion;
import model.UsuarioLikeComentario;

@Path("/likes")
public class UsuarioLikeComentarioService {
	
	@EJB
	UsuarioLikeComentarioFacade usuarioLikeComentarioFacadeEJB;
	
	Logger logger = Logger.getLogger(UsuarioLikeComentarioService.class.getName());

	@GET
	@Produces({"application/xml", "application/json"})
	public List<UsuarioLikeComentario> findAll(){
		return usuarioLikeComentarioFacadeEJB.findAll();
	}
	
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public UsuarioLikeComentario find(@PathParam("id") Integer id) {
        return usuarioLikeComentarioFacadeEJB.find(id);
    }
	
	@POST
	@Path("/newAntiguo")
    @Consumes({"application/xml", "application/json"})
    public void create(UsuarioLikeComentario entity) {
		usuarioLikeComentarioFacadeEJB.create(entity);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public Response crear(JsonObject entity) {
		UsuarioLikeComentario ulc = usuarioLikeComentarioFacadeEJB.crear(entity);
		usuarioLikeComentarioFacadeEJB.create(ulc);
		
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		if(ulc.getPositive()==0){
			jsonObjBuilder.add("INFO", "Dislike creado");
		} else {
			jsonObjBuilder.add("INFO", "Like creado");
		}
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }
	
	@POST
	@Path("/like")
    @Consumes({"application/xml", "application/json"})
    public Response createLike(JsonObject entity) {
		UsuarioLikeComentario ulc = usuarioLikeComentarioFacadeEJB.crearLike(entity);
		usuarioLikeComentarioFacadeEJB.create(ulc);
		
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("INFO", "Like creado");
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }
	
	@POST
	@Path("/dislike")
    @Consumes({"application/xml", "application/json"})
    public Response createDislike(JsonObject entity) {
		UsuarioLikeComentario ulc = usuarioLikeComentarioFacadeEJB.crearDislike(entity);
		usuarioLikeComentarioFacadeEJB.create(ulc);
		
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("INFO", "Dislike creado");
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }
	
	@POST
	@Path("/unlike")
    @Consumes({"application/xml", "application/json"})
    public Response unlike(JsonObject entity) {
		UsuarioLikeComentario ulc = usuarioLikeComentarioFacadeEJB.crearUnlike(entity);
		usuarioLikeComentarioFacadeEJB.remove(ulc);
		
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		if(ulc.getPositive()==0){
			jsonObjBuilder.add("INFO", "Dislike eliminado");
		} else {
			jsonObjBuilder.add("INFO", "Like eliminado");
		}
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, UsuarioLikeComentario entity) {
    	entity.setUsuarioId(id.intValue());
    	usuarioLikeComentarioFacadeEJB.edit(entity);
    }
}
