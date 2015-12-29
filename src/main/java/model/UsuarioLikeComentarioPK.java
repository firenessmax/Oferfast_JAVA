package model;

import java.io.Serializable;

public class UsuarioLikeComentarioPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int usuarioId;
	private int comentarioId;
	
	public UsuarioLikeComentarioPK(int usuarioId, int comentarioId) {
		this.usuarioId = usuarioId;
		this.comentarioId = comentarioId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getComentarioId() {
		return comentarioId;
	}

	public void setComentarioId(int comentarioId) {
		this.comentarioId = comentarioId;
	}
	
}
