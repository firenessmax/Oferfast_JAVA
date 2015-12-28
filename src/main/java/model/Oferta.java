package model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name="oferta")
@NamedQueries({
	@NamedQuery(name="Oferta.findAll", query="SELECT o FROM Oferta o"),
	@NamedQuery(name="Oferta.findByUserId", query="SELECT o FROM Oferta o WHERE o.usuarioId = :usuarioId")
})
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
	
	@Column(name="ubication_lon")
	private Double ubicationLon;
	
	@Column(name="ubication_lat")
	private Double ubicationLat;
	
	@Column(name="images_number", nullable=false)
	private int imagesNumber;

	@Column(name="date", nullable=false)
	private Timestamp date;

	@Column(name="visible_oferta", nullable=false)
	private int visibleOferta;
	
	@OneToMany(mappedBy="oferta")
	private List<OfertaHasEtiqueta> listaOfertaHasEtiqueta;
	
	@OneToMany(mappedBy="oferta")
	private List<ImagenOferta> listaImagenes;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="usuario_id", referencedColumnName = "usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy="oferta")
	private List<Comentario> listaComentarios;

	public Oferta() {
	}

	public int getOfertaId() {
		return ofertaId;
	}

	public void setOfertaId(int ofertaId) {
		this.ofertaId = ofertaId;
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

	public Double getUbicationLon() {
		return ubicationLon;
	}

	public void setUbicationLon(Double ubicacionLon) {
		this.ubicationLon = ubicacionLon;
	}

	public Double getUbicationLat() {
		return ubicationLat;
	}

	public void setUbicationLat(Double ubicacionLat) {
		this.ubicationLat = ubicacionLat;
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

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int isVisibleOferta() {
		return visibleOferta;
	}

	public void setVisibleOferta(int visibleOferta) {
		this.visibleOferta = visibleOferta;
	}

	public List<ImagenOferta> getListaImagenes() {
		return this.listaImagenes;
	}

	public void setListaImagenes(List<ImagenOferta> listaImagenes) {
		this.listaImagenes = listaImagenes;
	}

	public ImagenOferta addListaImagenes(ImagenOferta laImagenOferta) {
		getListaImagenes().add(laImagenOferta);
		laImagenOferta.setOferta(this);

		return laImagenOferta;
	}

	public ImagenOferta removeListaImagenes(ImagenOferta laImagenOferta) {
		getListaImagenes().remove(laImagenOferta);
		laImagenOferta.setOferta(null);

		return laImagenOferta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Comentario> getListaComentarios() {
		return this.listaComentarios;
	}

	public void setListaComentarios(List<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	public Comentario addListaComentarios(Comentario elComentario) {
		getListaComentarios().add(elComentario);
		elComentario.setOferta(this);

		return elComentario;
	}

	public Comentario removeListaComentario(Comentario elComentario) {
		getListaComentarios().remove(elComentario);
		elComentario.setOferta(null);

		return elComentario;
	}	

	public List<OfertaHasEtiqueta> getListaOfertaHasEtiqueta() {
		return listaOfertaHasEtiqueta;
	}

	public void setListaOfertaHasEtiqueta(List<OfertaHasEtiqueta> listaOfertaHasEtiqueta) {
		this.listaOfertaHasEtiqueta = listaOfertaHasEtiqueta;
	}

	public OfertaHasEtiqueta addListaOfertaHasEtiqueta(OfertaHasEtiqueta laOfertaHasEtiqueta) {
		getListaOfertaHasEtiqueta().add(laOfertaHasEtiqueta);
		laOfertaHasEtiqueta.setOferta(this);

		return laOfertaHasEtiqueta;
	}

	public OfertaHasEtiqueta removeListaOfertaHasEtiqueta(OfertaHasEtiqueta laOfertaHasEtiqueta) {
		getListaOfertaHasEtiqueta().remove(laOfertaHasEtiqueta);
		laOfertaHasEtiqueta.setOferta(null);

		return laOfertaHasEtiqueta;
	}
	
	
	
	

	public List<Etiqueta> getListaSoloEtiquetas() {
		List<Etiqueta> listaAux = new ArrayList<Etiqueta>();
		for(int i=0; i<getListaOfertaHasEtiqueta().size(); i++){
			listaAux.add(getListaOfertaHasEtiqueta().get(i).getEtiqueta());
		}
		return listaAux;
	}
	
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
