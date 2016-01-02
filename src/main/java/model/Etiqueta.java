package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="etiqueta")
@NamedQueries({
	@NamedQuery(name="Etiqueta.findAll", query="SELECT e FROM Etiqueta e"),
	@NamedQuery(name="Etiqueta.findById", query="SELECT e FROM Etiqueta e WHERE e.etiquetaId = :etiquetaId"),
	@NamedQuery(name="Etiqueta.findByVisible", query="SELECT e FROM Etiqueta e WHERE e.visibleEtiqueta = :visibleEtiqueta")
	
})
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
	private transient List<OfertaHasEtiqueta> listaOfertaHasEtiqueta;

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

	public int getVisibleEtiqueta() {
		return visibleEtiqueta;
	}

	public void setVisibleEtiqueta(int visibleEtiqueta) {
		this.visibleEtiqueta = visibleEtiqueta;
	}

	public List<OfertaHasEtiqueta> getListaOfertaHasEtiqueta() {
		return listaOfertaHasEtiqueta;
	}

	public void setListaOfertaHasEtiqueta(List<OfertaHasEtiqueta> listaOfertaHasEtiqueta) {
		this.listaOfertaHasEtiqueta = listaOfertaHasEtiqueta;
	}
	
	
	
}
