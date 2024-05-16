package com.daw.motor.model;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Incluye {
	@EmbeddedId
	private IncluyeId id;
	
	@ManyToOne
	@JoinColumn(name="idmod", insertable=false, updatable=false)
	private Coche coche;
	
	@ManyToOne
	@JoinColumn(name="ide", insertable=false, updatable=false)
	private Equipamiento equipamiento;
	
	private double descuento;
	
	public Incluye() {}
	
	public Incluye(Coche coche, Equipamiento equipamiento, double descuento) {
		super();
		this.coche = coche;
		this.equipamiento = equipamiento;
		this.descuento = descuento;
		this.id = new IncluyeId(coche.getId(), equipamiento.getId());
	}

	public IncluyeId getId() {
		return id;
	}

	public void setId(IncluyeId id) {
		this.id = id;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
		this.id.setIdModelo(coche.getId());
	}

	public Equipamiento getEquipamiento() {
		return equipamiento;
	}

	public void setEquipamiento(Equipamiento equipamiento) {
		this.equipamiento = equipamiento;
		this.id.setIdEquipamiento(equipamiento.getId());
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Incluye other = (Incluye) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Incluye [id=" + id + ", coche=" + coche + ", equipamiento=" + equipamiento + ", descuento=" + descuento
				+ "]";
	}
	
	
}