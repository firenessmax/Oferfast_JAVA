package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

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
    	if(entity.getReputation() == 0){
    		entity.setReputation(antiguo.getReputation());
    	}
    	if(entity.getPermission() == 0){
    		entity.setPermission(antiguo.getPermission());
    	}
    	return entity;
	}
	
	@Override
	public Response login(JsonObject datos){
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		String username = datos.getString("username");
		String password = datos.getString("password");
		try{
			Usuario user = em.createNamedQuery("Usuario.findByUsername", Usuario.class)
	        		.setParameter("username", username).getSingleResult();
			if(user.getPassword().equals(password)){
				jsonObjBuilder.add("INFO", "Loggeado")
					.add("usuarioId", user.getUsuarioId())
					.add("username",user.getUsername())
					.add("email",user.getEmail())
					.add("type",user.getType())
					.add("reputation",user.getReputation())
					.add("urlProfilePicture",user.getUrlProfilePicture())
					.add("urlProfileThumbnail",user.getUrlProfileThumbnail());
			} else {
				jsonObjBuilder.add("ERROR", "La constraseña no corresponde, vuelva a intentarlo");
			}
			JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
		} catch(Exception e){
			jsonObjBuilder.add("ERROR", "No existe un usuario con ese username");
			JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
		}
	}

}
