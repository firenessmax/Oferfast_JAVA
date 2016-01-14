package facade;

import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import model.Comentario;
import model.Oferta;
import model.UsuarioLikeComentario;

public interface ComentarioFacade {

	public void create(Comentario entity);

	public void edit(Comentario entity);

	public void remove(Comentario entity);

	public Comentario find(Object id);

	public List<Comentario> findAll();

	public List<Comentario> findRange(int[] range);
	
	public Comentario crear(JsonObject entity);
	
	public Comentario editar(int id, JsonObject entity);
	
	public Comentario editarVisible(int numero, Comentario antiguo);
	
	public List<UsuarioLikeComentario> findLikes(int id);
	
	public List<UsuarioLikeComentario> findDislikes(int id);
	
	public Response findComentario(Comentario comment, List<UsuarioLikeComentario> likes, List<UsuarioLikeComentario> dislikes);

	public int count();

}
