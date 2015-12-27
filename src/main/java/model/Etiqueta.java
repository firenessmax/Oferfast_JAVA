package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="etiqueta")
@NamedQuery(name="Etiqueta.findAll", query="SELECT e FROM Etiqueta e")
public class Etiqueta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="etiqueta_id", unique=true, nullable=false)
	private int etiquetaId;

	@Column(name="name", nullable=false, length=45)
	private String name;

	@Column(name="counter", nullable=false)
	private int counter;

	@Column(name="visible_etiqueta", nullable=false)
	private int visibleEtiqueta;
	
	@OneToMany(mappedBy="etiqueta")
	private List<OfertaHasEtiqueta> listaOfertas;

	public Etiqueta() {
	}

	public int getEtiquetaId() {
		return etiquetaId;
	}

	public void setEtiquetaId(int etiquetaId) {
		this.etiquetaId = etiquetaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int isVisibleEtiqueta() {
		return visibleEtiqueta;
	}

	public void setVisibleEtiqueta(int visibleEtiqueta) {
		this.visibleEtiqueta = visibleEtiqueta;
	}

	public List<OfertaHasEtiqueta> getListaOfertas() {
		return listaOfertas;
	}

	public void setListaOfertas(List<OfertaHasEtiqueta> listaOfertas) {
		this.listaOfertas = listaOfertas;
	}

	public OfertaHasEtiqueta addListaEtiquetas(OfertaHasEtiqueta laOfertaHasEtiqueta) {
		getListaOfertas().add(laOfertaHasEtiqueta);
		laOfertaHasEtiqueta.setEtiqueta(this);

		return laOfertaHasEtiqueta;
	}

	public OfertaHasEtiqueta removeListaEtiquetas(OfertaHasEtiqueta laOfertaHasEtiqueta) {
		getListaOfertas().remove(laOfertaHasEtiqueta);
		laOfertaHasEtiqueta.setEtiqueta(null);

		return laOfertaHasEtiqueta;
	}
	

	public List<Oferta> getListaSoloOfertas() {
		List<Oferta> listaAux = new ArrayList<Oferta>();
		for(int i=0; i<getListaOfertas().size(); i++){
			listaAux.add(getListaOfertas().get(i).getOferta());
		}
		return listaAux;
	}
	
	
	
}
