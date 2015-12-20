package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="etiqueta")
@NamedQuery(name="Etiqueta.findAll", query="SELECT e FROM Etiqueta e")
public class Etiqueta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="etiqueta_id", unique=true, nullable=false)
	private int etiquetaId;

	@Column(name="name", nullable=false, length=45)
	private String name;

	@Column(name="counter", nullable=false)
	private int counter;

	public Etiqueta() {
	}

	public int getEtiquetaId() {
		return etiquetaId;
	}

	public void setEtiquetaId(int etiquetaId) {
		this.etiquetaId = etiquetaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	
	
}
