package facade;

import java.util.List;

import model.UsuarioSigueUsuario;

public interface UsuarioSigueUsuarioFacade {

	public void create(UsuarioSigueUsuario entity);

	public void edit(UsuarioSigueUsuario entity);

	public void remove(UsuarioSigueUsuario entity);

	public UsuarioSigueUsuario find(Object id);

	public List<UsuarioSigueUsuario> findAll();

	public List<UsuarioSigueUsuario> findRange(int[] range);

	public int count();

}
