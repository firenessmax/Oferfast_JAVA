package ejb;

import javax.ejb.Stateless;
import javax.json.JsonObject;
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
	
	@Override
	public UsuarioLikeComentario crear(JsonObject entity){
		UsuarioLikeComentario ulc = new UsuarioLikeComentario();
		ulc.setUsuarioId(entity.getInt("usuarioId"));
		ulc.setComentarioId(entity.getInt("comentarioId"));
		ulc.setPositive(entity.getInt("positive"));
		return ulc;		
	}
	
	@Override
	public UsuarioLikeComentario crearLike(JsonObject entity){
		UsuarioLikeComentario ulc = new UsuarioLikeComentario();
		ulc.setUsuarioId(entity.getInt("usuarioId"));
		ulc.setComentarioId(entity.getInt("comentarioId"));
		ulc.setPositive(1);
		return ulc;		
	}
	
	@Override
	public UsuarioLikeComentario crearDislike(JsonObject entity){
		UsuarioLikeComentario ulc = new UsuarioLikeComentario();
		ulc.setUsuarioId(entity.getInt("usuarioId"));
		ulc.setComentarioId(entity.getInt("comentarioId"));
		ulc.setPositive(0);
		return ulc;		
	}
	
	@Override
	public UsuarioLikeComentario crearUnlike(JsonObject entity){
		return em.createNamedQuery("UsuarioLikeComentario.findByComentarioUsuario", UsuarioLikeComentario.class)
        		.setParameter("comentarioId", entity.getInt("comentarioId"))
        		.setParameter("usuarioId", entity.getInt("usuarioId"))
        		.getSingleResult();
	}

}
