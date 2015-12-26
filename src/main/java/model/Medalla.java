package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="medalla")
@NamedQuery(name="Medalla.findAll", query="SELECT m FROM Medalla m")
public class Medalla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="medalla_id", unique=true, nullable=false)
	private int medallaId;

	@Column(name="name", nullable=false, length=45)
	private String name;

	@Column(name="description", nullable=false, length=45)
	private String description;

	@Column(name="icon", nullable=false)
	private int icon;

	@Column(name="color", nullable=false)
	private int color;

	@Column(name="backgroud", nullable=false)
	private int backgroud;

	@Column(name="visible_medalla", nullable=false)
	private boolean visibleMedalla;

	public Medalla() {
	}

	public int getMedallaId() {
		return medallaId;
	}

	public void setMedallaId(int medallaId) {
		this.medallaId = medallaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getBackgroud() {
		return backgroud;
	}

	public void setBackgroud(int backgroud) {
		this.backgroud = backgroud;
	}

	public boolean isVisibleMedalla() {
		return visibleMedalla;
	}

	public void setVisibleMedalla(boolean visibleMedalla) {
		this.visibleMedalla = visibleMedalla;
	}
	
	
}
