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

import facade.ComentarioFacade;
import model.Comentario;
import model.Usuario;
import model.UsuarioLikeComentario;

@Path("/comentarios")
public class ComentarioService {

	@EJB 
	ComentarioFacade comentarioFacadeEJB;
	
	Logger logger = Logger.getLogger(ComentarioService.class.getName());

	@GET
	@Produces({"application/xml", "application/json"})
	public List<Comentario> findAll(){
		return comentarioFacadeEJB.findAll();
	}	

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Response find(@PathParam("id") Integer id) {
    	Comentario comment = comentarioFacadeEJB.find(id);
    	List<UsuarioLikeComentario> likes = comentarioFacadeEJB.findLikes(id);
    	List<UsuarioLikeComentario> dislikes = comentarioFacadeEJB.findDislikes(id);
        //return comentarioFacadeEJB.find(id);
    	return comentarioFacadeEJB.findComentario(comment, likes, dislikes);
    }
    
    @GET
    @Path("{id}/likes")
    @Produces({"application/xml", "application/json"})
    public List<UsuarioLikeComentario> findLikes(@PathParam("id") Integer id) {
        return comentarioFacadeEJB.findLikes(id);
    }
    
    @GET
    @Path("{id}/dislikes")
    @Produces({"application/xml", "application/json"})
    public List<UsuarioLikeComentario> findDislikes(@PathParam("id") Integer id) {
        return comentarioFacadeEJB.findDislikes(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public Response create(JsonObject entity) {
		Comentario comment = comentarioFacadeEJB.crear(entity);
		comentarioFacadeEJB.create(comment);
		//respuesta
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("INFO", "Comentarios creados correctamente");
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public Response edit(@PathParam("id") Integer id, JsonObject entity) {
    	Comentario comment = comentarioFacadeEJB.editar(id.intValue(), entity);
    	comentarioFacadeEJB.edit(comment);
    	//respuesta
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("INFO", "Datos actualizados");
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }

    @PUT
    @Path("{id}/visible")
    @Consumes({"application/xml", "application/json"})
    public Response editVisible(@PathParam("id") Integer id, JsonObject entrada) {
    	int numero = entrada.getInt("visibleComentario");
    	Comentario aux = comentarioFacadeEJB.find(id);
    	aux = comentarioFacadeEJB.editarVisible(numero, aux);
    	comentarioFacadeEJB.edit(aux);
    	//respuesta
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		if(numero==0){
			jsonObjBuilder.add("INFO", "Comentario eliminado");
		} else {
			jsonObjBuilder.add("INFO", "Comentario visible");
		}
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }
}
