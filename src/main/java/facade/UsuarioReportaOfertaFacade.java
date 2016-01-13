package facade;

import java.util.List;

import javax.json.JsonObject;

import model.UsuarioReportaOferta;

public interface UsuarioReportaOfertaFacade {

	public void create(UsuarioReportaOferta entity);

	public void edit(UsuarioReportaOferta entity);

	public void remove(UsuarioReportaOferta entity);

	public UsuarioReportaOferta find(Object id);

	public List<UsuarioReportaOferta> findAll();

	public List<UsuarioReportaOferta> findRange(int[] range);
	
	public UsuarioReportaOferta crear(JsonObject entity);

	public UsuarioReportaOferta editar(int id, JsonObject entity);

	public int count();

}
