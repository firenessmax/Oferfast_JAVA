package model;

import java.io.Serializable;

public class UsuarioSigueUsuarioPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int usuarioId;
	private int usuarioAmigoId;
	
	public UsuarioSigueUsuarioPK(int usuarioId, int usuarioAmigoId) {
		this.usuarioId = usuarioId;
		this.usuarioAmigoId = usuarioAmigoId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getUsuarioAmigoId() {
		return usuarioAmigoId;
	}

	public void setUsuarioAmigoId(int usuarioAmigoId) {
		this.usuarioAmigoId = usuarioAmigoId;
	}
	

}
