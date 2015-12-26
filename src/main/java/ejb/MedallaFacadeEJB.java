package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.MedallaFacade;
import model.Medalla;

@Stateless
public class MedallaFacadeEJB extends AbstractFacade<Medalla> implements MedallaFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public MedallaFacadeEJB() {
		super(Medalla.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
