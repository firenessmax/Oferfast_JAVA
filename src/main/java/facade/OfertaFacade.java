package facade;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Local;
import javax.json.JsonObject;

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
	
	public Oferta editar(Oferta entity, Oferta antigua);
	
	public Oferta crear(JsonObject entity);

	public List<Oferta> findAllVisible(int sino);

	public Oferta findByLonLat(Double Lon, Double Lat);

	public Oferta findByOferta(Oferta entity);

	public int count();
}
