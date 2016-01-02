package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
		if(entity.getUbicationLon() == 0){
			entity.setUbicationLon(antigua.getUbicationLon());
		}
		if(entity.getUbicationLat() == 0){
			entity.setUbicationLat(antigua.getUbicationLat());
		}
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
}
