package model;

import java.io.Serializable;

public class OfertaHasEtiquetaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int ofertaId;
	private int etiquetaId;
	
	public OfertaHasEtiquetaPK(int ofertaId, int etiquetaId) {
		this.ofertaId = ofertaId;
		this.etiquetaId = etiquetaId;
	}

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
	
	
}
