package facade;

import java.util.List;

import javax.json.JsonObject;

import model.UsuarioLikeComentario;

public interface UsuarioLikeComentarioFacade {

	public void create(UsuarioLikeComentario entity);

	public void edit(UsuarioLikeComentario entity);

	public void remove(UsuarioLikeComentario entity);

	public UsuarioLikeComentario find(Object id);

	public List<UsuarioLikeComentario> findAll();

	public List<UsuarioLikeComentario> findRange(int[] range);

	public int count();

	public UsuarioLikeComentario crear(JsonObject entity);
	
	public UsuarioLikeComentario crearLike(JsonObject entity);
	
	public UsuarioLikeComentario crearDislike(JsonObject entity);
	
	public UsuarioLikeComentario crearUnlike(JsonObject entity);

}
