package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
@NamedQueries({
	@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u"),
	@NamedQuery(name="Usuario.findById", query="SELECT u FROM Usuario u WHERE u.usuarioId = :usuarioId"),
	@NamedQuery(name="Usuario.findByUsername", query="SELECT u FROM Usuario u WHERE u.username = :username"),
	@NamedQuery(name="Usuario.findByEmail", query="SELECT u FROM Usuario u WHERE u.email = :email"),
	@NamedQuery(name="Usuario.findByType", query="SELECT u FROM Usuario u WHERE u.type = :type"),
	@NamedQuery(name="Usuario.findByReputation", query="SELECT u FROM Usuario u WHERE u.reputation = :reputation"),
	@NamedQuery(name="Usuario.findByPermission", query="SELECT u FROM Usuario u WHERE u.permission = :permission"),
	@NamedQuery(name="Usuario.findByVisible", query="SELECT u FROM Usuario u WHERE u.visibleUsuario = :visibleUsuario"),
	
	@NamedQuery(name="Usuario.findByIdVisible", query="SELECT u FROM Usuario u WHERE u.usuarioId = :usuarioId AND u.visibleUsuario = :visibleUsuario"),
	@NamedQuery(name="Usuario.findByUsernameVisible", query="SELECT u FROM Usuario u WHERE u.username = :username AND u.visibleUsuario = :visibleUsuario"),
	@NamedQuery(name="Usuario.findByEmailVisible", query="SELECT u FROM Usuario u WHERE u.email = :email AND u.visibleUsuario = :visibleUsuario"),
	@NamedQuery(name="Usuario.findByTypeVisible", query="SELECT u FROM Usuario u WHERE u.type = :type AND u.visibleUsuario = :visibleUsuario"),
	@NamedQuery(name="Usuario.findByReputationVisible", query="SELECT u FROM Usuario u WHERE u.reputation = :reputation AND u.visibleUsuario = :visibleUsuario"),
	@NamedQuery(name="Usuario.findByPermissionVisible", query="SELECT u FROM Usuario u WHERE u.permission = :permission AND u.visibleUsuario = :visibleUsuario"),

})
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
	private String urlProfilePicture;

	@Column(name="url_profile_thumbnail", nullable=false, length=255)
	private String urlProfileThumbnail;

	@Column(name="visible_usuario", nullable=false)
	private int visibleUsuario;
	
	@OneToMany(mappedBy="usuario")
	private transient List<Oferta> listaOfertas;
	
	@OneToMany(mappedBy="usuario")
	private transient List<Comentario> listaComentarios;

	public Usuario() {
	}
	
	public Usuario(int usuarioId, String username, int type, int reputation, String urlProfilePicture,
			String urlProfileThumbnail) {
		super();
		this.usuarioId = usuarioId;
		this.username = username;
		this.type = type;
		this.reputation = reputation;
		this.urlProfilePicture = urlProfilePicture;
		this.urlProfileThumbnail = urlProfileThumbnail;
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

	public String getUrlProfilePicture() {
		return urlProfilePicture;
	}

	public void setUrlProfilePicture(String urlProfilePicture) {
		this.urlProfilePicture = urlProfilePicture;
	}

	public String getUrlProfileThumbnail() {
		return urlProfileThumbnail;
	}

	public void setUrlProfileThumbnail(String urlProfileThumbnail) {
		this.urlProfileThumbnail = urlProfileThumbnail;
	}

	public int getVisibleUsuario() {
		return visibleUsuario;
	}

	public void setVisibleUsuario(int visibleUsuario) {
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
