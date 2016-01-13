package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.ImagenOfertaFacade;
import model.ImagenOferta;
import model.Usuario;

@Stateless
public class ImagenOfertaFacadeEJB extends AbstractFacade<ImagenOferta> implements ImagenOfertaFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public ImagenOfertaFacadeEJB() {
		super(ImagenOferta.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	@Override
	public List<ImagenOferta> addPorOferta(int cantidad, JsonObject entrada, int idOferta){
		List<ImagenOferta> lista = new ArrayList<ImagenOferta>();
		JsonArray arreglo = entrada.getJsonArray("imagenes");
		for(int i=0; i<cantidad; i++){
			JsonObject imagen = arreglo.getJsonObject(i);
			ImagenOferta io = new ImagenOferta();
			io.setUrlNormal(imagen.getString("urlNormal"));
			io.setUrlThumbnail(imagen.getString("urlThumbnail"));
			io.setVisibleImagen(1);
			io.setOfertaId(idOferta);
			lista.add(io);
		}
		return lista;
	}
	
	@Override
	public ImagenOferta editarVisible(int numero, ImagenOferta antiguo){
		antiguo.setVisibleImagen(numero);
		return antiguo;
	}
}
