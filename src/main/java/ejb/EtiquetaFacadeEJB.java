package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.EtiquetaFacade;
import model.Etiqueta;

@Stateless
public class EtiquetaFacadeEJB extends AbstractFacade<Etiqueta> implements EtiquetaFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public EtiquetaFacadeEJB() {
		super(Etiqueta.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
}
