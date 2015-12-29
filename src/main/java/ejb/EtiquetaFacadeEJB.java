package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
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
}
