package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.EtiquetaFacade;
import model.Etiqueta;
import model.Oferta;
import model.OfertaHasEtiqueta;

@Stateless
public class EtiquetaFacadeEJB extends AbstractFacade<Etiqueta> implements EtiquetaFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public EtiquetaFacadeEJB() {
		super(Etiqueta.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	@Override
	public List<Oferta> findOfertaByID(int id){
		//oferta-etiqueta, que tienen id de la oferta
		List<Oferta> lista = new ArrayList<Oferta>();
		List<OfertaHasEtiqueta> listaOfertaHasEtiqueta = em.createNamedQuery("OfertaHasEtiqueta.findOfertaByEtiqueta", OfertaHasEtiqueta.class)
		.setParameter("etiquetaId", id).getResultList();
		List<Oferta> listaOfertas = em.createNamedQuery("Oferta.findAll", Oferta.class).getResultList();
		for(int i=0; i<listaOfertaHasEtiqueta.size(); i++){
			for(int j=0; j<listaOfertas.size();j++){
				if(listaOfertas.get(j).getOfertaId()==listaOfertaHasEtiqueta.get(i).getOfertaId()){
					lista.add(listaOfertas.get(j));
					break;
				}
			}
		}		
		return lista;
	}
	
	@Override
	public Etiqueta editar(Etiqueta entity, Etiqueta antiguo){
    	if(entity.getName() == null){
    		entity.setName(antiguo.getName());
    	}
    	if(entity.getVisibleEtiqueta() == 0){
    		entity.setVisibleEtiqueta(antiguo.getVisibleEtiqueta());
    	}
    	//no deberia cambiarse por aca el valor de counter, sino por trigger, asi que se obtiene el valor existente
    	entity.setCounter(antiguo.getCounter());
    	return entity;
		
	}
	
	@Override
	public List<Etiqueta> addPorOferta(JsonArray lista){
		List<Etiqueta> laLista = new ArrayList<>();
		Etiqueta aux;
		for(int i=0; i<lista.size(); i++){//revisar, esta devolviendo todo
			try{
				//si la query resulta, es xq existe
				aux = em.createNamedQuery("Etiqueta.findByName", Etiqueta.class)
						.setParameter("name", lista.getString(i)).getSingleResult();
				//aux.setName(aux.getName()+" - existente");
			} catch(Exception e){
				aux = new Etiqueta();
				aux.setName(lista.getString(i));
				aux.setVisibleEtiqueta(1);
				laLista.add(aux);
			}			
			//Etiqueta aux = new Etiqueta();
			//aux.setName(lista.getString(i));
			//hacer la conexion
		}
		//devuelve todas las etiquetas no existen
		return laLista;
	}
	
	@Override
	public List<Etiqueta> findAllByName(JsonArray lista){
		List<Etiqueta> laLista = new ArrayList<>();
		Etiqueta aux;
		for(int i=0; i<lista.size(); i++){
			aux = em.createNamedQuery("Etiqueta.findByName", Etiqueta.class)
					.setParameter("name", lista.getString(i)).getSingleResult();
			laLista.add(aux);
		}
		//devuelve todas las etiquetas no existen
		return laLista;
	}
}
