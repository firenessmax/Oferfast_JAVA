package facade;

import java.util.List;
import javax.ejb.Local;
import model.Etiqueta;

@Local
public interface EtiquetaFacade {

	public void create(Etiqueta entity);

	public void edit(Etiqueta entity);

	public void remove(Etiqueta entity);

	public Etiqueta find(Object id);

	public List<Etiqueta> findAll();

	public List<Etiqueta> findRange(int[] range);

	public int count();

}
