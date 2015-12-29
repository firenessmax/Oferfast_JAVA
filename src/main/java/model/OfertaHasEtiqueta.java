package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="oferta_has_etiqueta")
@IdClass(OfertaHasEtiquetaPK.class)
@NamedQueries({
	@NamedQuery(name="OfertaHasEtiqueta.findAll", query="SELECT ohe FROM OfertaHasEtiqueta ohe"),
	@NamedQuery(name="OfertaHasEtiqueta.findOfertaByEtiqueta", query="SELECT ohe FROM OfertaHasEtiqueta ohe WHERE ohe.etiquetaId = :etiquetaId"),
	@NamedQuery(name="OfertaHasEtiqueta.findEtiquetaByOferta", query="SELECT ohe FROM OfertaHasEtiqueta ohe WHERE ohe.ofertaId = :ofertaId")
})

public class OfertaHasEtiqueta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="oferta_id", nullable=false)
	private int ofertaId;
	
	@Id
	@Column(name="etiqueta_id", nullable=false)
	private int etiquetaId;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="oferta_id", referencedColumnName = "oferta_id")
	private Oferta oferta;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="etiqueta_id", referencedColumnName = "etiqueta_id")
	private Etiqueta etiqueta;

	public int getOfertaId() {
		return ofertaId;
	}

	public void setOfertaId(int ofertaId) {
		this.ofertaId = ofertaId;
	}

	public int getEtiquetaId() {
		return etiquetaId;
	}

	public void setEtiquetaId(int etiquetaId) {
		this.etiquetaId = etiquetaId;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public Etiqueta getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(Etiqueta etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	
	
	
}
