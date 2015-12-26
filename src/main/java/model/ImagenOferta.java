package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="imagenOferta")
@NamedQuery(name="ImagenOferta.findAll", query="SELECT io FROM ImagenOferta io")
public class ImagenOferta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="imagen_oferta_id", unique=true, nullable=false)
	private int imagenOfertaId;
	
	@Column(name="oferta_id", nullable=false)
	private int ofertaId;
	
	@Column(name="url_normal", nullable=false, length=255)
	private String urlNormal;
	
	@Column(name="url_thumbnail", nullable=false, length=255)
	private String urlThumbnail;

	@Column(name="visible_imagen", nullable=false)
	private boolean visibleImagen;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="oferta_id")
	private Oferta oferta;
	
	public ImagenOferta() {
	}

	public int getImagenOfertaId() {
		return imagenOfertaId;
	}

	public void setImagenOfertaId(int imagenOfertaId) {
		this.imagenOfertaId = imagenOfertaId;
	}

	public String getUrlNormal() {
		return urlNormal;
	}

	public void setUrlNormal(String urlNormal) {
		this.urlNormal = urlNormal;
	}

	public String getUrlThumbnail() {
		return urlThumbnail;
	}

	public void setUrlThumbnail(String urlThumbnail) {
		this.urlThumbnail = urlThumbnail;
	}
	
	public boolean isVisibleImagen() {
		return visibleImagen;
	}

	public void setVisibleImagen(boolean visibleImagen) {
		this.visibleImagen = visibleImagen;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getOfertaId() {
		return ofertaId;
	}

	public void setOfertaId(int ofertaId) {
		this.ofertaId = ofertaId;
	}
	
	
}
