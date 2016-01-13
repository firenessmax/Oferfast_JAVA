package facade;

import java.util.List;

import javax.json.JsonArray;

import model.Oferta;
import model.OfertaHasEtiqueta;

public interface OfertaHasEtiquetaFacade {

	public void create(OfertaHasEtiqueta entity);

	public void edit(OfertaHasEtiqueta entity);

	public void remove(OfertaHasEtiqueta entity);

	public OfertaHasEtiqueta find(Object id);

	public List<OfertaHasEtiqueta> findAll();

	public List<OfertaHasEtiqueta> findRange(int[] range);

	public List<OfertaHasEtiqueta> createByOfertaEtiqueta(Oferta laOferta, JsonArray lista);
	
	public List<OfertaHasEtiqueta> findByOferta(Oferta laOferta);

	public int count();

}
