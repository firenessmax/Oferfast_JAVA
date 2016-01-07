package ejb;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.OfertaFacade;
import model.OfertaHasEtiqueta;
import model.Usuario;
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
	public List<ImagenOferta> findImagenByID(int id){
		return em.createNamedQuery("ImagenOferta.findByOferta", ImagenOferta.class).setParameter("ofertaId", id).getResultList();
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
	public Oferta editar(Oferta entity, Oferta antigua){
		if(entity.getUsuarioId() == 0){
			entity.setUsuarioId(antigua.getUsuarioId());
		}
		if(entity.getTitle() == null){
			entity.setTitle(antigua.getTitle());			
		}
		if(entity.getDescription() == null){
			entity.setDescription(antigua.getDescription());			
		}
		if(entity.getPrice() == 0){
			entity.setPrice(antigua.getPrice());
		}
		//ubicacion no deberia editarse, para que asi no puedan publicar ofertas malas
		entity.setUbicationLon(antigua.getUbicationLon());
		entity.setUbicationLat(antigua.getUbicationLat());
		if(entity.getImagesNumber() == 0){
			entity.setImagesNumber(antigua.getImagesNumber());
		}
		//date siempre coloca la fecha anterior, xq no deberia poder cambiarse
		entity.setDate(antigua.getDate());
		if(entity.getVisibleOferta() == 0){
			entity.setVisibleOferta(antigua.getVisibleOferta());
		}
		return entity;
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
}
