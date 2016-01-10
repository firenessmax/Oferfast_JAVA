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
	public Usuario editar(JsonObject datos, Usuario antiguo){
    	try{
    		antiguo.setUsername(datos.getString("username"));
    	} catch(Exception e){}
    	try{
    		antiguo.setPassword(datos.getString("password"));
    	} catch(Exception e){}
    	try{
    		antiguo.setEmail(datos.getString("email"));
    	} catch(Exception e){}
    	try{
    		antiguo.setUrlProfilePicture(datos.getString("urlProfilePicture"));
    	} catch(Exception e){}
    	try{
    		antiguo.setUrlProfileThumbnail(datos.getString("urlProfileThumbnail"));
    	} catch(Exception e){}
    	//el cambio de ambito "social" y visible se hace aparte
    	return antiguo;
	}
	
	@Override
	public Usuario editarSocial(JsonObject datos, Usuario antiguo){
		try{
			antiguo.setType(datos.getInt("type"));
		} catch (Exception e){}
		try{
			antiguo.setReputation(datos.getInt("reputation"));
		} catch (Exception e){}
		try{
			antiguo.setPermission(datos.getInt("permission"));
		} catch (Exception e){}
		return antiguo;
	}
	
	@Override
	public Usuario editarVisible(JsonObject datos, Usuario antiguo){
		antiguo.setVisibleUsuario(datos.getInt("visibleUsuario"));
		return antiguo;
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
				jsonObjBuilder.add("INFO", "La password no corresponde, vuelva a intentarlo");
			}
			JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
		} catch(Exception e){
			jsonObjBuilder.add("INFO", "No existe un usuario con ese username");
			JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
		}
	}
	
	@Override
	public Response crear(JsonObject datos){
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		try{
			Usuario user = em.createNamedQuery("Usuario.findByUsername", Usuario.class)
	        		.setParameter("username", datos.getString("username")).getSingleResult();
			jsonObjBuilder.add("INFO", "Ya existe un usuario con ese username, elija otro username y vuelva a intentarlo");
			JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
		} catch(Exception e){
			Usuario elUsuario = new Usuario();
			elUsuario.setUsername(datos.getString("username"));
			elUsuario.setEmail(datos.getString("email"));
			elUsuario.setPassword(datos.getString("password"));
			elUsuario.setType(1);
			elUsuario.setPermission(1);
			elUsuario.setReputation(1);
			elUsuario.setVisibleUsuario(1);
			try{
				elUsuario.setUrlProfilePicture(datos.getString("urlProfilePicture"));
				elUsuario.setUrlProfileThumbnail(datos.getString("urlProfileThumbnail"));
			} catch (Exception e2){
				elUsuario.setUrlProfilePicture("no hay foto");
				elUsuario.setUrlProfileThumbnail("no hay thumb");
			}
			this.create(elUsuario);
			Usuario user = em.createNamedQuery("Usuario.findByUsername", Usuario.class)
	        		.setParameter("username", elUsuario.getUsername()).getSingleResult();
			jsonObjBuilder.add("INFO", "Usuario creado exitosamente");
			jsonObjBuilder.add("usuarioId",user.getUsuarioId());
			JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
			//return elUsuario;
		}
	}

}
