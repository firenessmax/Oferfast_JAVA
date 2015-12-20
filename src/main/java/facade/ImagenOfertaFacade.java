package facade;

import java.util.List;
import javax.ejb.Local;
import model.ImagenOferta;

@Local
public interface ImagenOfertaFacade {

	public void create(ImagenOferta entity);

	public void edit(ImagenOferta entity);

	public void remove(ImagenOferta entity);

	public ImagenOferta find(Object id);

	public List<ImagenOferta> findAll();

	public List<ImagenOferta> findRange(int[] range);

	public int count();

}
