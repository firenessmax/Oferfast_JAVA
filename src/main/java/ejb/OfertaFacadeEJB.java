package ejb;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

import facade.AbstractFacade;
import facade.OfertaFacade;
import model.OfertaHasEtiqueta;
import model.Usuario;
import model.UsuarioReportaOferta;
import model.Comentario;
import model.Etiqueta;
import model.Oferta;
import model.ImagenOferta;

@Stateless
public class OfertaFacadeEJB extends AbstractFacade<Oferta> implements OfertaFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public OfertaFacadeEJB() {
		super(Oferta.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	@Override
	public List<Etiqueta> findEtiquetaByID(int id){
		//oferta-etiqueta, que tienen id de la oferta
		List<Etiqueta> lista = new ArrayList<Etiqueta>();
		List<OfertaHasEtiqueta> listaOfertaHasEtiqueta = em.createNamedQuery("OfertaHasEtiqueta.findEtiquetaByOferta", OfertaHasEtiqueta.class)
		.setParameter("ofertaId", id).getResultList();
		List<Etiqueta> listaEtiquetas = em.createNamedQuery("Etiqueta.findAll", Etiqueta.class).getResultList();
		for(int i=0; i<listaOfertaHasEtiqueta.size(); i++){
			for(int j=0; j<listaEtiquetas.size();j++){
				if(listaEtiquetas.get(j).getEtiquetaId()==listaOfertaHasEtiqueta.get(i).getEtiquetaId()){
					lista.add(listaEtiquetas.get(j));
					break;
				}
			}
		}
		
		return lista;
	}
	
	@Override
	public Response findImagenByID(int id){
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		List<ImagenOferta> lista = em.createNamedQuery("ImagenOferta.findByOferta", ImagenOferta.class).setParameter("ofertaId", id).getResultList();
		if(lista.isEmpty()){
			jsonObjBuilder.add("INFO", "La oferta no posee imagenes");
			JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
		} else {
			jsonObjBuilder.add("ofertaId", lista.get(0).getOfertaId());
			JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
			for(int i=0; i<lista.size(); i++){
				JsonObjectBuilder jsonObjBuilder2 = Json.createObjectBuilder();
				jsonObjBuilder2.add("urlNormal", lista.get(i).getUrlNormal());
				jsonObjBuilder2.add("urlThumbnail", lista.get(i).getUrlThumbnail());
				//agregar a un jsonarray
				jsonArrayBuilder.add(jsonObjBuilder2);
			}
			jsonObjBuilder.add("imagenes", jsonArrayBuilder);
			JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
		}
		//return em.createNamedQuery("ImagenOferta.findByOferta", ImagenOferta.class).setParameter("ofertaId", id).getResultList();
	}
	
	@Override
	public Usuario findUsuarioByID(int id){
		Usuario aux = (Usuario) em.createNamedQuery("Usuario.findById", Usuario.class).setParameter("usuarioId", id).getSingleResult();
		Usuario resp = new Usuario(
				aux.getUsuarioId(),
				aux.getUsername(),
				aux.getType(),
        		aux.getReputation(),
        		//aux.getPermission(),
        		aux.getUrlProfilePicture(),
        		aux.getUrlProfileThumbnail()
        		);
        return resp;
	}
	
	@Override
	public Oferta editar(JsonObject entity, Oferta antigua){
		//no se puede cambiar usuarioId, asi q se salta
		try{
			antigua.setTitle(entity.getString("title"));
		} catch(Exception e){}
		try{
			antigua.setDescription(entity.getString("description"));
		} catch(Exception e){}
		try{
			antigua.setPrice(entity.getInt("price"));
		} catch(Exception e){}
		//ubicacion no deberia editarse
		try{
			antigua.setImagesNumber(entity.getInt("imagesNumber"));
		} catch(Exception e){}
		//date siempre coloca la fecha anterior, xq no deberia poder cambiarse
		//visible se cambia en editarVisible
		return antigua;
	}
	
	@Override
	public Oferta crear(JsonObject entity){
		Oferta laOferta = new Oferta();
		//laOferta.setOfertaId(entity.getInt("ofertaId"));
		//String aux = String.valueOf(entity.get("ubicationLon")) ;
		//Timestamp aux = Timestamp.valueOf(String.valueOf(entity.getBoolean("date")));
		laOferta.setTitle(entity.getString("title"));
		laOferta.setDescription(entity.getString("description"));
		laOferta.setPrice(entity.getInt("price"));
		laOferta.setUbicationLat(Double.parseDouble(String.valueOf(entity.get("ubicationLat"))));
		laOferta.setUbicationLon(Double.parseDouble(String.valueOf(entity.get("ubicationLon"))));
		laOferta.setVisibleOferta(entity.getInt("visibleOferta"));
		laOferta.setUsuarioId(entity.getInt("usuarioId"));
		//laOferta.setDate(Timestamp.valueOf(entity.getString("date")));
		laOferta.setImagesNumber(entity.getInt("imagesNumber"));
		
		java.util.Date date= new java.util.Date();
		laOferta.setDate(new Timestamp(date.getTime()));
		
		//trabajando en esta wea
		return laOferta;
		//return aux;
	}
	
	@Override
	public List<Oferta> findAllVisible(int sino){
		return em.createNamedQuery("Oferta.findByVisible", Oferta.class)
			.setParameter("visibleOferta", sino).getResultList();
	}
	
	@Override
	public Oferta findByLonLat(Double Lon, Double Lat){
		return em.createNamedQuery("Oferta.findByUbicationLonLat", Oferta.class)
				.setParameter("ubicationLon", Lon).setParameter("ubicationLat", Lat).getSingleResult();
	}
	
	public Oferta findByOferta(Oferta entity){
		return em.createNamedQuery("Oferta.findByOferta", Oferta.class)
				.setParameter("usuarioId", entity.getUsuarioId())
				.setParameter("title", entity.getTitle())
				.setParameter("price", entity.getPrice())
				.setParameter("description", entity.getDescription())
				.setParameter("ubicationLon", entity.getUbicationLon())
				.setParameter("ubicationLat", entity.getUbicationLat())
				.getSingleResult();
	}

	@Override
	public List<Comentario> findComentarios(int id){
		return em.createNamedQuery("Comentario.findByOfertaVisible", Comentario.class)
        		.setParameter("ofertaId", id).setParameter("visibleComentario", 1).getResultList();
	}
	
	@Override
	public Oferta editarVisible(int numero, Oferta antigua){
		antigua.setVisibleOferta(numero);
		return antigua;
	}
	
	@Override
	public List<UsuarioReportaOferta> findReportes(int id){
		return em.createNamedQuery("UsuarioReportaOferta.findByIdOferta", UsuarioReportaOferta.class)
        		.setParameter("ofertaId", id).getResultList();
	}
}
