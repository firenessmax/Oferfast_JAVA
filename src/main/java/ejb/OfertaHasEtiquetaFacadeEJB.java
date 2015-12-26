package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.OfertaHasEtiquetaFacade;
import model.OfertaHasEtiqueta;

@Stateless
public class OfertaHasEtiquetaFacadeEJB extends AbstractFacade<OfertaHasEtiqueta> implements OfertaHasEtiquetaFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public OfertaHasEtiquetaFacadeEJB() {
		super(OfertaHasEtiqueta.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
