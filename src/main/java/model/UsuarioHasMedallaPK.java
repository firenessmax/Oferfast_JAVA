package model;

import java.io.Serializable;

public class UsuarioHasMedallaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int usuarioId;
	private int medallaId;
	
	public UsuarioHasMedallaPK(int usuarioId, int medallaId) {
		this.usuarioId = usuarioId;
		this.medallaId = medallaId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getMedallaId() {
		return medallaId;
	}

	public void setMedallaId(int medallaId) {
		this.medallaId = medallaId;
	}
	
	
}
