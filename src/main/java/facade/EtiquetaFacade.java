package facade;

import java.util.List;
import javax.ejb.Local;
import javax.json.JsonArray;

import model.Etiqueta;
import model.Oferta;

@Local
public interface EtiquetaFacade {

	public void create(Etiqueta entity);

	public void edit(Etiqueta entity);

	public void remove(Etiqueta entity);

	public Etiqueta find(Object id);

	public List<Etiqueta> findAll();

	public List<Etiqueta> findRange(int[] range);
	
	public List<Oferta> findOfertaByID(int id);
	
	public Etiqueta editar(Etiqueta entity, Etiqueta antiguo);
	
	public List<Etiqueta> addPorOferta(JsonArray lista);

	public List<Etiqueta> findAllByName(JsonArray lista);

	public int count();

}
