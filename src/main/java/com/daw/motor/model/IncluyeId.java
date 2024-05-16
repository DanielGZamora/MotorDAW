package com.daw.motor.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;

public class IncluyeId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="idmod")
	private Long idModelo;

	@Column(name="ide")
	private Long idEquipamiento;
	
	public IncluyeId() {}
	
	public IncluyeId(Long idModelo, Long idEquipamiento) {
		super();
		this.idModelo = idModelo;
		this.idEquipamiento = idEquipamiento;
	}

	public Long getIdEquipamiento() {
		return idEquipamiento;
	}

	public void setIdEquipamiento(Long idEquipamiento) {
		this.idEquipamiento = idEquipamiento;
	}

	public Long getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Long idModelo) {
		this.idModelo = idModelo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEquipamiento, idModelo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncluyeId other = (IncluyeId) obj;
		return Objects.equals(idEquipamiento, other.idEquipamiento) && Objects.equals(idModelo, other.idModelo);
	}

	@Override
	public String toString() {
		return "IncluyeId [idEquipamiento=" + idEquipamiento + ", idModelo=" + idModelo + "]";
	}
}