package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.UsuarioReportaOfertaFacade;
import model.UsuarioReportaOferta;

@Stateless
public class UsuarioReportaOfertaFacadeEJB extends AbstractFacade<UsuarioReportaOferta> implements UsuarioReportaOfertaFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public UsuarioReportaOfertaFacadeEJB() {
		super(UsuarioReportaOferta.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
}
