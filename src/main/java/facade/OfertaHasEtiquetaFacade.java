package facade;

import java.util.List;

import model.OfertaHasEtiqueta;

public interface OfertaHasEtiquetaFacade {

	public void create(OfertaHasEtiqueta entity);

	public void edit(OfertaHasEtiqueta entity);

	public void remove(OfertaHasEtiqueta entity);

	public OfertaHasEtiqueta find(Object id);

	public List<OfertaHasEtiqueta> findAll();

	public List<OfertaHasEtiqueta> findRange(int[] range);

	public int count();

}
