package model;

import java.io.Serializable;

public class UsuarioReportaOfertaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int usuarioId;
	private int ofertaId;
	
	public UsuarioReportaOfertaPK(int usuarioId, int ofertaId) {
		this.usuarioId = usuarioId;
		this.ofertaId = ofertaId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getOfertaId() {
		return ofertaId;
	}

	public void setOfertaId(int ofertaId) {
		this.ofertaId = ofertaId;
	}
	
	

}
