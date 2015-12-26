package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.ConfiguracionFacade;
import model.Configuracion;

@Stateless
public class ConfiguracionFacadeEJB extends AbstractFacade<Configuracion> implements ConfiguracionFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public ConfiguracionFacadeEJB() {
		super(Configuracion.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
