package facade;

import java.util.List;

import model.UsuarioHasMedalla;

public interface UsuarioHasMedallaFacade {

	public void create(UsuarioHasMedalla entity);

	public void edit(UsuarioHasMedalla entity);

	public void remove(UsuarioHasMedalla entity);

	public UsuarioHasMedalla find(Object id);

	public List<UsuarioHasMedalla> findAll();

	public List<UsuarioHasMedalla> findRange(int[] range);

	public int count();

}
