package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.UsuarioFacade;
import model.Oferta;
import model.Usuario;

@Stateless
public class UsuarioFacadeEJB extends AbstractFacade<Usuario> implements UsuarioFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public UsuarioFacadeEJB() {
		super(Usuario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public List<Oferta> findOfertas(int id){
		return em.createNamedQuery("Oferta.findByUsuario", Oferta.class)
        		.setParameter("usuarioId", id).getResultList();
	}
	
	@Override
	public Usuario editar(Usuario entity, Usuario antiguo){
    	if(entity.getUsername() == null){
    		entity.setUsername(antiguo.getUsername());
    	}
    	if(entity.getPassword() == null){
    		entity.setPassword(antiguo.getPassword());
    	}
    	if(entity.getEmail() == null){
    		entity.setEmail(antiguo.getEmail());
    	}
    	if(entity.getType() == 0){
    		entity.setType(antiguo.getType());
    	}
    	if(entity.getReputation() == 0){
    		entity.setReputation(antiguo.getReputation());
    	}
    	if(entity.getPermission() == 0){
    		entity.setPermission(antiguo.getPermission());
    	}
    	if(entity.getUrlProfilePicture() == null){
    		entity.setUrlProfilePicture(antiguo.getUrlProfilePicture());
    	}
    	if(entity.getUrlProfileThumbnail() == null){
    		entity.setUrlProfileThumbnail(antiguo.getUrlProfileThumbnail());
    	}
    	if(entity.getVisibleUsuario() == 0){
    		entity.setVisibleUsuario(antiguo.getVisibleUsuario());
    	}
    	return entity;
	}

}
