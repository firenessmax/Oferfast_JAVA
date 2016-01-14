package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

@Entity
@Table(name="Usuario_like_Comentario")
@IdClass(UsuarioLikeComentarioPK.class)
@NamedQueries({
	@NamedQuery(name="UsuarioLikeComentario.findAll", query="SELECT ulc FROM UsuarioLikeComentario ulc"),
	@NamedQuery(name="UsuarioLikeComentario.findByUsuario", query="SELECT ulc FROM UsuarioLikeComentario ulc WHERE ulc.usuarioId = :usuarioId"),
	@NamedQuery(name="UsuarioLikeComentario.findByComentario", query="SELECT ulc FROM UsuarioLikeComentario ulc WHERE ulc.comentarioId = :comentarioId"),

	@NamedQuery(name="UsuarioLikeComentario.findByComentarioPositive", query="SELECT ulc FROM UsuarioLikeComentario ulc WHERE ulc.comentarioId = :comentarioId AND ulc.positive = :positive"),
	
	@NamedQuery(name="UsuarioLikeComentario.findByComentarioUsuario", query="SELECT ulc FROM UsuarioLikeComentario ulc WHERE ulc.comentarioId = :comentarioId AND ulc.usuarioId = :usuarioId")
})

public class UsuarioLikeComentario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="usuario_id", nullable=false)
	private int usuarioId;
	
	@Id
	@Column(name="comentario_id", nullable=false)
	private int comentarioId;

	@Column(name="positive", nullable=false)
	private int positive;

	public UsuarioLikeComentario() {
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

	public int getPositive() {
		return positive;
	}

	public void setPositive(int positive) {
		this.positive = positive;
	}
	
	
}
