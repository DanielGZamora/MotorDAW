package com.daw.motor.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "modelo")
public class Coche {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODELO_SEQ")
	@SequenceGenerator(name = "MODELO_SEQ", allocationSize = 1)
	private Long id;
	
	private String nombre;
	
	private double precioBase;
	
	@ManyToOne
	@JoinColumn(name = "idmot")
	private Motor motor;
	@OneToMany(mappedBy = "coche" ,cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Incluye> listaIncluye = new HashSet<>();
	
	public Coche() {}

	public Coche(Long id, String nombre, double precioBase, Motor motor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.motor = motor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}
	
	public Set<Incluye> getListaIncluye() {
		return listaIncluye;
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
		Coche other = (Coche) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Coche [id=" + id + ", nombre=" + nombre + ", precioBase=" + precioBase + ", motor=" + motor + "]";
	}
}