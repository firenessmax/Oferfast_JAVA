package facade;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Local;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import model.Comentario;
import model.Etiqueta;
import model.ImagenOferta;
import model.Oferta;
import model.Usuario;
import model.UsuarioLikeComentario;
import model.UsuarioReportaOferta;

@Local
public interface OfertaFacade {

	public void create(Oferta entity);

	public void edit(Oferta entity);

	public void remove(Oferta entity);

	public Oferta find(Object id);

	public List<Oferta> findAll();

	public List<Oferta> findRange(int[] range);
	
	public List<Etiqueta> findEtiquetaByID(int id);
	
	public List<Comentario> findComentarios(int id);
	
	public List<UsuarioReportaOferta> findReportes(int id);

	public Response findImagenByID(int id);
	
	public List<ImagenOferta> findImagenes(int id);
	
	public Response findOferta(Oferta entity, List<ImagenOferta> listaImagenes);
	
	public Usuario findUsuarioByID(int id);
	
	public Oferta editar(JsonObject entity, Oferta antigua);
	
	public Oferta crear(JsonObject entity);

	public Response findAllVisible(int sino);

	public Oferta findByLonLat(Double Lon, Double Lat);

	public Oferta findByOferta(Oferta entity);
	
	public Oferta editarVisible(int numero, Oferta antigua);
	
	public Response findComentariosLikes(int id);

	public int count();
}
