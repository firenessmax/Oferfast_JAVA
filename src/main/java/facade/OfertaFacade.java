package facade;

import java.util.List;
import javax.ejb.Local;

import model.Etiqueta;
import model.ImagenOferta;
import model.Oferta;
import model.Usuario;

@Local
public interface OfertaFacade {

	public void create(Oferta entity);

	public void edit(Oferta entity);

	public void remove(Oferta entity);

	public Oferta find(Object id);

	public List<Oferta> findAll();

	public List<Oferta> findRange(int[] range);
	
	public List<Etiqueta> findEtiquetaByID(int id);
	
	public List<ImagenOferta> findImagenByID(int id);
	
	public Usuario findUsuarioByID(int id);

	public int count();
}
