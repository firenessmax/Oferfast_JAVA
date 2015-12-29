package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="usuario_sigue_usuario")
@IdClass(UsuarioSigueUsuarioPK.class)
@NamedQuery(name="UsuarioSigueUsuario.findAll", query="SELECT usu FROM UsuarioSigueUsuario usu")
public class UsuarioSigueUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="usuario_id", nullable=false)
	private int usuarioId;
	
	@Id
	@Column(name="usuario_amigo_id", nullable=false)
	private int usuarioAmigoId;

	public UsuarioSigueUsuario() {
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
