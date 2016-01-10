package facade;

import java.util.List;

import model.UsuarioReportaOferta;

public interface UsuarioReportaOfertaFacade {

	public void create(UsuarioReportaOferta entity);

	public void edit(UsuarioReportaOferta entity);

	public void remove(UsuarioReportaOferta entity);

	public UsuarioReportaOferta find(Object id);

	public List<UsuarioReportaOferta> findAll();

	public List<UsuarioReportaOferta> findRange(int[] range);

	public int count();

}
