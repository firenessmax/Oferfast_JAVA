package model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Configuracion")
@NamedQueries({
	@NamedQuery(name="Configuracion.findAll", query="SELECT c FROM Configuracion c"),
	@NamedQuery(name="Configuracion.findById", query="SELECT c FROM Configuracion c WHERE c.configuracionId = :configuracionId"),
	@NamedQuery(name="Configuracion.findByUsuario", query="SELECT c FROM Configuracion c WHERE c.usuarioId = :usuarioId"),
	@NamedQuery(name="Configuracion.findByLanguage", query="SELECT c FROM Configuracion c WHERE c.language = :language"),
	@NamedQuery(name="Configuracion.findByCountry", query="SELECT c FROM Configuracion c WHERE c.country = :country"),
	@NamedQuery(name="Configuracion.findByNotification", query="SELECT c FROM Configuracion c WHERE c.notification = :notification"),
	@NamedQuery(name="Configuracion.findByDate", query="SELECT c FROM Configuracion c WHERE c.date = :date"),
	@NamedQuery(name="Configuracion.findByVisible", query="SELECT c FROM Configuracion c WHERE c.visibleConfiguracion = :visibleConfiguracion"),
	
	@NamedQuery(name="Configuracion.findByIdVisible", query="SELECT c FROM Configuracion c WHERE c.configuracionId = :configuracionId AND c.visibleConfiguracion = :visibleConfiguracion"),
	@NamedQuery(name="Configuracion.findByUsuarioVisible", query="SELECT c FROM Configuracion c WHERE c.usuarioId = :usuarioId AND c.visibleConfiguracion = :visibleConfiguracion"),
	@NamedQuery(name="Configuracion.findByLanguageVisible", query="SELECT c FROM Configuracion c WHERE c.language = :language AND c.visibleConfiguracion = :visibleConfiguracion"),
	@NamedQuery(name="Configuracion.findByCountryVisible", query="SELECT c FROM Configuracion c WHERE c.country = :country AND c.visibleConfiguracion = :visibleConfiguracion"),
	@NamedQuery(name="Configuracion.findByNotificationVisible", query="SELECT c FROM Configuracion c WHERE c.notification = :notification AND c.visibleConfiguracion = :visibleConfiguracion"),
	@NamedQuery(name="Configuracion.findByDateVisible", query="SELECT c FROM Configuracion c WHERE c.date = :date AND c.visibleConfiguracion = :visibleConfiguracion")
})

public class Configuracion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="configuracion_id", unique=true, nullable=false)
	private int configuracionId;
	
	@Column(name="usuario_id", nullable=false)
	private int usuarioId;

	@Column(name="language", nullable=false, length=45)
	private String language;

	@Column(name="country", nullable=false, length=45)
	private String country;

	@Column(name="notification", nullable=false, length=45)
	private String notification;

	@Column(name="timezone", nullable=false)
	private Time date;

	@Column(name="visible_configuracion", nullable=false)
	private int visibleConfiguracion;

	public Configuracion() {
	}

	public int getConfiguracionId() {
		return configuracionId;
	}

	public void setConfiguracionId(int configuracionId) {
		this.configuracionId = configuracionId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public Time getDate() {
		return date;
	}

	public void setDate(Time date) {
		this.date = date;
	}

	public int isVisibleConfiguracion() {
		return visibleConfiguracion;
	}

	public void setVisibleConfiguracion(int visibleConfiguracion) {
		this.visibleConfiguracion = visibleConfiguracion;
	}
	
	
}
