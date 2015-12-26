package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.UsuarioSigueUsuarioFacade;
import model.UsuarioSigueUsuario;

@Stateless
public class UsuarioSigueUsuarioFacadeEJB extends AbstractFacade<UsuarioSigueUsuario> implements UsuarioSigueUsuarioFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public UsuarioSigueUsuarioFacadeEJB() {
		super(UsuarioSigueUsuario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
