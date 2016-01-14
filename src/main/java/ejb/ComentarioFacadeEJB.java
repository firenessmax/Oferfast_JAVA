package ejb;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

import facade.AbstractFacade;
import facade.ComentarioFacade;
import model.Comentario;
import model.Usuario;
import model.UsuarioLikeComentario;

@Stateless
public class ComentarioFacadeEJB extends AbstractFacade<Comentario> implements ComentarioFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public ComentarioFacadeEJB() {
		super(Comentario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	@Override
	public Comentario crear(JsonObject entity){
		Comentario nuevo = new Comentario();
		nuevo.setUsuarioId(entity.getInt("usuarioId"));
		nuevo.setOfertaId(entity.getInt("ofertaId"));
		nuevo.setText(entity.getString("text"));
		
		java.util.Date date= new java.util.Date();
		nuevo.setDate(new Timestamp(date.getTime()));
		nuevo.setVisibleComentario(1);
		
		return nuevo;
	}
	
	@Override
	public Comentario editar(int id, JsonObject entity){
		Comentario comment = this.find(id);
		comment.setText(entity.getString("text"));
		return comment;
	}
	
	@Override
	public Comentario editarVisible(int numero, Comentario antiguo){
		antiguo.setVisibleComentario(numero);
		return antiguo;
	}
	
	@Override
	public List<UsuarioLikeComentario> findLikes(int id){
		return em.createNamedQuery("UsuarioLikeComentario.findByComentarioPositive", UsuarioLikeComentario.class)
        		.setParameter("comentarioId", id).setParameter("positive", 1).getResultList();
	}
	
	@Override
	public List<UsuarioLikeComentario> findDislikes(int id){
		return em.createNamedQuery("UsuarioLikeComentario.findByComentarioPositive", UsuarioLikeComentario.class)
        		.setParameter("comentarioId", id).setParameter("positive", 0).getResultList();
	}
	
	@Override
	public Response findComentario(Comentario comment, List<UsuarioLikeComentario> likes, List<UsuarioLikeComentario> dislikes){
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("text", comment.getText());
		jsonObjBuilder.add("date", comment.getDate().toString());
		jsonObjBuilder.add("usuarioId", comment.getUsuarioId());
		jsonObjBuilder.add("ofertaId", comment.getOfertaId());
		jsonObjBuilder.add("visibleComentario", comment.getVisibleComentario());
		//likes
		jsonObjBuilder.add("cantidadLikes", likes.size());
		JsonArrayBuilder jsonArrayBuilderLikes = Json.createArrayBuilder();
		for(int i=0; i<likes.size(); i++){
			jsonArrayBuilderLikes.add(likes.get(i).getUsuarioId());
		}
		jsonObjBuilder.add("likes", jsonArrayBuilderLikes);
		//dislikes
		jsonObjBuilder.add("cantidadDislikes", dislikes.size());
		JsonArrayBuilder jsonArrayBuilderDislikes = Json.createArrayBuilder();
		for(int i=0; i<dislikes.size(); i++){
			jsonArrayBuilderDislikes.add(dislikes.get(i).getUsuarioId());
		}
		jsonObjBuilder.add("dislikes", jsonArrayBuilderDislikes);
		
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
	}

}
