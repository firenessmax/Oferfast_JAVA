package facade;

import java.util.List;

import model.Configuracion;

public interface ConfiguracionFacade {


	public void create(Configuracion entity);

	public void edit(Configuracion entity);

	public void remove(Configuracion entity);

	public Configuracion find(Object id);

	public List<Configuracion> findAll();

	public List<Configuracion> findRange(int[] range);

	public int count();
}
