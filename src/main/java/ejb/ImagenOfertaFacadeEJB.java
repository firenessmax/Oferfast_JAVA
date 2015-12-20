package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.ImagenOfertaFacade;
import model.ImagenOferta;

@Stateless
public class ImagenOfertaFacadeEJB  extends AbstractFacade<ImagenOferta> implements ImagenOfertaFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public ImagenOfertaFacadeEJB() {
		super(ImagenOferta.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
}
