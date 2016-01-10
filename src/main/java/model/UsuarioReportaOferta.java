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
@Table(name="usuario_reporta_oferta")
@IdClass(UsuarioReportaOfertaPK.class)
@NamedQuery(name="UsuarioReportaOferta.findAll", query="SELECT uro FROM UsuarioReportaOferta uro")
public class UsuarioReportaOferta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="usuario_id", nullable=false)
	private int usuarioId;
	
	@Id
	@Column(name="oferta_id", nullable=false)
	private int ofertaId;

	@Column(name="date", nullable=false)
	private Timestamp date;

	@Column(name="description", nullable=false, length=500)
	private String description;

	public UsuarioReportaOferta() {
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
	
}
