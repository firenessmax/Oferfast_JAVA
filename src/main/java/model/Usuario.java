package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usuario_id", unique=true, nullable=false)
	private int usuarioId;

	@Column(name="username", nullable=false, length=45)
	private String username;

	@Column(name="password", nullable=false, length=45)
	private String password;

	@Column(name="email", nullable=false, length=45)
	private String email;

	@Column(name="type", nullable=false)
	private int type;

	@Column(name="reputation", nullable=false)
	private int reputation;

	@Column(name="permission", nullable=false)
	private int permission;

	@Column(name="url_profile_picture", nullable=false, length=255)
	private String url_profile_picture;

	@Column(name="url_profile_thumbnail", nullable=false, length=255)
	private String url_profile_thumbnail;

	@Column(name="visible_usuario", nullable=false)
	private boolean visibleUsuario;
	
	@OneToMany(mappedBy="usuario")
	private List<Oferta> listaOfertas;
	
	@OneToMany(mappedBy="usuario")
	private List<Comentario> listaComentarios;

	public Usuario() {
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String getUrl_profile_picture() {
		return url_profile_picture;
	}

	public void setUrl_profile_picture(String url_profile_picture) {
		this.url_profile_picture = url_profile_picture;
	}

	public String getUrl_profile_thumbnail() {
		return url_profile_thumbnail;
	}

	public void setUrl_profile_thumbnail(String url_profile_thumbnail) {
		this.url_profile_thumbnail = url_profile_thumbnail;
	}

	public boolean isVisibleUsuario() {
		return visibleUsuario;
	}

	public void setVisibleUsuario(boolean visibleUsuario) {
		this.visibleUsuario = visibleUsuario;
	}

	public void setListaOfertas(List<Oferta> listaOfertas) {
		this.listaOfertas = listaOfertas;
	}

	public List<Oferta> getListaOfertas() {
		return this.listaOfertas;
	}

	public Oferta addListaOfertas(Oferta laOferta) {
		getListaOfertas().add(laOferta);
		laOferta.setUsuario(this);

		return laOferta;
	}

	public Oferta removeListaOfertas(Oferta laOferta) {
		getListaOfertas().remove(laOferta);
		laOferta.setUsuario(null);

		return laOferta;
	}

	public List<Comentario> getListaComentarios() {
		return this.listaComentarios;
	}

	public void setListaComentarios(List<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	public Comentario addListaComentarios(Comentario elComentario) {
		getListaComentarios().add(elComentario);
		elComentario.setUsuario(this);

		return elComentario;
	}

	public Comentario removeListaComentario(Comentario elComentario) {
		getListaComentarios().remove(elComentario);
		elComentario.setUsuario(null);

		return elComentario;
	}

	
}
