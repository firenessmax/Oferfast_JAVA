package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.UsuarioHasMedallaFacade;
import model.UsuarioHasMedalla;

@Stateless
public class UsuarioHasMedallaFacadeEJB extends AbstractFacade<UsuarioHasMedalla> implements UsuarioHasMedallaFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public UsuarioHasMedallaFacadeEJB() {
		super(UsuarioHasMedalla.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
