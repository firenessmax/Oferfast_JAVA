package model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Usuario_reporta_Oferta")
@IdClass(UsuarioReportaOfertaPK.class)
@NamedQueries({
	@NamedQuery(name="UsuarioReportaOferta.findAll", query="SELECT uro FROM UsuarioReportaOferta uro"),
	@NamedQuery(name="UsuarioReportaOferta.findByIdUsuario", query="SELECT uro FROM UsuarioReportaOferta uro WHERE uro.usuarioId = :usuarioId"),
	@NamedQuery(name="UsuarioReportaOferta.findByIdOferta", query="SELECT uro FROM UsuarioReportaOferta uro WHERE uro.ofertaId = :ofertaId"),
	@NamedQuery(name="UsuarioReportaOferta.findByIdDate", query="SELECT uro FROM UsuarioReportaOferta uro WHERE uro.date = :date"),
	@NamedQuery(name="UsuarioReportaOferta.findByIdText", query="SELECT uro FROM UsuarioReportaOferta uro WHERE uro.text = :text")
})

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

	@Column(name="text", nullable=false, length=500)
	private String text;

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
	
	
}
