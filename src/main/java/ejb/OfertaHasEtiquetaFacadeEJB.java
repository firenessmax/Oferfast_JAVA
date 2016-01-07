package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.OfertaHasEtiquetaFacade;
import model.Etiqueta;
import model.Oferta;
import model.OfertaHasEtiqueta;

@Stateless
public class OfertaHasEtiquetaFacadeEJB extends AbstractFacade<OfertaHasEtiqueta> implements OfertaHasEtiquetaFacade {

	@PersistenceContext(unitName = "oferfastPU")
	private EntityManager em;
	
	public OfertaHasEtiquetaFacadeEJB() {
		super(OfertaHasEtiqueta.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	@Override
	public List<OfertaHasEtiqueta> createByOfertaEtiqueta(Oferta laOferta, JsonArray lista){
		List<OfertaHasEtiqueta> laLista = new ArrayList<>();
		Etiqueta tagAux = new Etiqueta();
		OfertaHasEtiqueta ohaAux;
		for(int i=0; i<lista.size(); i++){
			ohaAux = new OfertaHasEtiqueta();
			tagAux = em.createNamedQuery("Etiqueta.findByName", Etiqueta.class)
					.setParameter("name", lista.getString(i)).getSingleResult();
			ohaAux.setEtiquetaId(tagAux.getEtiquetaId());
			ohaAux.setOfertaId(laOferta.getOfertaId());
			laLista.add(ohaAux);
		}
		return laLista;
	}

}
