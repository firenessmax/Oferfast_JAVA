package facade;

import java.util.List;

import javax.json.JsonObject;

import model.Comentario;

public interface ComentarioFacade {

	public void create(Comentario entity);

	public void edit(Comentario entity);

	public void remove(Comentario entity);

	public Comentario find(Object id);

	public List<Comentario> findAll();

	public List<Comentario> findRange(int[] range);
	
	public Comentario crear(JsonObject entity);

	public int count();

}
