package ejb;

import java.sql.Timestamp;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.UsuarioReportaOfertaFacade;
import model.Oferta;
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
	
	@Override
	public UsuarioReportaOferta crear(JsonObject entity){
		UsuarioReportaOferta laURO = new UsuarioReportaOferta();
		laURO.setUsuarioId(entity.getInt("usuarioId"));
		laURO.setOfertaId(entity.getInt("ofertaId"));
		laURO.setText(entity.getString("text"));
		
		java.util.Date date= new java.util.Date();
		laURO.setDate(new Timestamp(date.getTime()));
		return laURO;
	}
	
	@Override
	public UsuarioReportaOferta editar(int id, JsonObject entity){
		UsuarioReportaOferta laURO = this.find(id);
		laURO.setText(entity.getString("text"));
		return laURO;
	}
	
}
