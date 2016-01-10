package model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity	
@Table(name="Usuario_has_Medalla")
@IdClass(UsuarioHasMedallaPK.class)
@NamedQuery(name="UsuarioHasMedalla.findAll", query="SELECT uhm FROM UsuarioHasMedalla uhm")
public class UsuarioHasMedalla implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="usuario_id", nullable=false)
	private int usuarioId;
	
	@Id
	@Column(name="medalla_id", nullable=false)
	private int medallaId;

	@Column(name="date")
	private Timestamp date;

	public UsuarioHasMedalla() {
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
}
