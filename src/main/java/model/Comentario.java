package model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

@Entity
@Table(name="Comentario")
@NamedQueries({
	@NamedQuery(name="Comentario.findAll", query="SELECT c FROM Comentario c"),
	@NamedQuery(name="Comentario.findById", query="SELECT c FROM Comentario c WHERE c.comentarioId = :comentarioId"),
	@NamedQuery(name="Comentario.findByUsuario", query="SELECT c FROM Comentario c WHERE c.usuarioId = :usuarioId"),
	@NamedQuery(name="Comentario.findByOferta", query="SELECT c FROM Comentario c WHERE c.ofertaId = :ofertaId"),
	@NamedQuery(name="Comentario.findByDate", query="SELECT c FROM Comentario c WHERE c.date like :date"),
	@NamedQuery(name="Comentario.findByVisible", query="SELECT c FROM Comentario c WHERE c.visibleComentario = :visibleComentario"),
	
	@NamedQuery(name="Comentario.findByIdVisible", query="SELECT c FROM Comentario c WHERE c.comentarioId = :comentarioId AND c.visibleComentario = :visibleComentario"),
	@NamedQuery(name="Comentario.findByUsuarioVisible", query="SELECT c FROM Comentario c WHERE c.usuarioId = :usuarioId AND c.visibleComentario = :visibleComentario"),
	@NamedQuery(name="Comentario.findByOfertaVisible", query="SELECT c FROM Comentario c WHERE c.ofertaId = :ofertaId AND c.visibleComentario = :visibleComentario"),
	@NamedQuery(name="Comentario.findByDateVisible", query="SELECT c FROM Comentario c WHERE c.date like :date AND c.visibleComentario = :visibleComentario")
	
})
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="comentario_id", unique=true, nullable=false)
	private int comentarioId;
	
	@Column(name="usuario_id", nullable=false)
	private int usuarioId;
	
	@Column(name="oferta_id", nullable=false)
	private int ofertaId;

	@Column(name="text", nullable=false, length=45)
	private String text;

	@Column(name="date", nullable=false)
	private Timestamp date;

	@Column(name="visible_comentario", nullable=false)
	private int visibleComentario;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="oferta_id")
	private Oferta oferta;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="usuario_id")
	private Usuario usuario;

	public Comentario() {
	}

	public int getComentarioId() {
		return comentarioId;
	}

	public void setComentarioId(int comentarioId) {
		this.comentarioId = comentarioId;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getVisibleComentario() {
		return visibleComentario;
	}

	public void setVisibleComentario(int visibleComentario) {
		this.visibleComentario = visibleComentario;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
