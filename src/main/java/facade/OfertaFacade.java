package facade;

import java.util.List;
import javax.ejb.Local;
import model.Oferta;

@Local
public interface OfertaFacade {

	public void create(Oferta entity);

	public void edit(Oferta entity);

	public void remove(Oferta entity);

	public Oferta find(Object id);

	public List<Oferta> findAll();

	public List<Oferta> findRange(int[] range);

	public int count();
}
