package model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name="oferta")
@NamedQuery(name="Oferta.findAll", query="SELECT o FROM Oferta o")
public class Oferta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="oferta_id", unique=true, nullable=false)
	private int ofertaId;
	
	@Column(name="usuario_id", nullable=false)
	private int usuarioId;

	@Column(name="title", nullable=false, length=100)
	private String title;

	@Column(name="description", nullable=false, length=500)
	private String description;
	
	@Column(name="price", nullable=false)
	private int price;
	
	@Column(name="ubicacion_lon", nullable=false)
	private Double ubicacionLon;
	
	@Column(name="ubicacion_lat", nullable=false)
	private Double ubicacionLat;
	
	@Column(name="images_number", nullable=false)
	private int imagesNumber;

	@Column(name="date", nullable=false)
	private Timestamp date;

	public Oferta() {
	}

	public int getOfertaId() {
		return ofertaId;
	}

	public void setOfertaId(int ofertaId) {
		this.ofertaId = ofertaId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Double getUbicacionLon() {
		return ubicacionLon;
	}

	public void setUbicacionLon(Double ubicacionLon) {
		this.ubicacionLon = ubicacionLon;
	}

	public Double getUbicacionLat() {
		return ubicacionLat;
	}

	public void setUbicacionLat(Double ubicacionLat) {
		this.ubicacionLat = ubicacionLat;
	}

	public int getImagesNumber() {
		return imagesNumber;
	}

	public void setImagesNumber(int imagesNumber) {
		this.imagesNumber = imagesNumber;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
