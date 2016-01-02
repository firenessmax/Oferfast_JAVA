package facade;

import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import model.Oferta;
import model.Usuario;

public interface UsuarioFacade {

	public void create(Usuario entity);

	public void edit(Usuario entity);

	public void remove(Usuario entity);

	public Usuario find(Object id);

	public List<Usuario> findAll();

	public List<Usuario> findRange(int[] range);

	public List<Oferta> findOfertas(int id);

	public Usuario editar(Usuario entity, Usuario antiguo);
	
	public Response login(JsonObject datos);

	public int count();

}
