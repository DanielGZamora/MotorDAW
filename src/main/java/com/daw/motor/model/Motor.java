package com.daw.motor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Motor {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOTOR_SEQ")
	@SequenceGenerator(name = "MOTOR_SEQ", allocationSize = 1)
	private Long id;
	
	@Column
	private String tipo;

	@Column
	private double potencia;

	@Column
	private double precio;
	
	@OneToMany(mappedBy = "motor",
			cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Coche> coches = new ArrayList<>();
	
	public Motor() {}

	public Motor(Long id, String tipo, double potencia, double precio) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.potencia = potencia;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPotencia() {
		return potencia;
	}

	public void setPotencia(double potencia) {
		this.potencia = potencia;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Motor [id=" + id + ", tipo=" + tipo + ", potencia=" + potencia + ", precio=" + precio + "]";
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
		Motor other = (Motor) obj;
		return Objects.equals(id, other.id);
	}

	public List<Coche> getCoches() {
		return coches;
	}

	public List<String> getCochesBonito() {
		List<String> listaCoches = new ArrayList<>();
		
		for (Coche coche : coches) {
			listaCoches.add(coche.getNombre());
		}
		
		return listaCoches;
	}

	public void addCoche(Coche unCoche) {
		coches.add(unCoche);
		unCoche.setMotor(this);
	}

	public void removeCoche(Coche unCoche) {
		coches.remove(unCoche);
		unCoche.setMotor(null);
	}
	
	public String getNombreBonito() {
		return String.format("%s de %.2f CV, PVP: %.2f",
				tipo, potencia, precio);
	}
}