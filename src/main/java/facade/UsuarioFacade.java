package facade;

import java.util.List;

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

	public int count();

}
