package ejb;

import java.sql.Timestamp;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.ComentarioFacade;
import model.Comentario;

@Stateless
public class ComentarioFacadeEJB extends AbstractFacade<Comentario> implements ComentarioFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public ComentarioFacadeEJB() {
		super(Comentario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	@Override
	public Comentario crear(JsonObject entity){
		Comentario nuevo = new Comentario();
		nuevo.setUsuarioId(entity.getInt("usuarioId"));
		nuevo.setOfertaId(entity.getInt("ofertaId"));
		nuevo.setText(entity.getString("text"));
		
		java.util.Date date= new java.util.Date();
		nuevo.setDate(new Timestamp(date.getTime()));
		nuevo.setVisibleComentario(1);
		
		return nuevo;
	}

}
