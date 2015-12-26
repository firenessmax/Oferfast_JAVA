package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.UsuarioLikeComentarioFacade;
import model.UsuarioLikeComentario;

@Stateless
public class UsuarioLikeComentarioFacadeEJB extends AbstractFacade<UsuarioLikeComentario> implements UsuarioLikeComentarioFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public UsuarioLikeComentarioFacadeEJB() {
		super(UsuarioLikeComentario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
