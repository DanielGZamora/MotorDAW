package com.daw.motor.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Equipamiento {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EQUIPAMIENTO_SEQ")
	@SequenceGenerator(name = "EQUIPAMIENTO_SEQ", allocationSize = 1)
	private Long id;
	
	@Column
	private String descripcion;
	
	@Column
	private double precio;
	
	@Column
	private String foto;
	
	@OneToMany(mappedBy="equipamiento", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Incluye> listaIncluye = new HashSet<>();

	public Equipamiento() {}
	
	public Equipamiento(Long id, String descripcion, double precio, String foto) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.precio = precio;
		this.foto = foto;
	}

	public Equipamiento(String descripcion, double precio, String foto) {
		this(null, descripcion, precio, foto);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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
		Equipamiento other = (Equipamiento) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Equipamiento [id=" + id + ", descripcion=" + descripcion + ", precio=" + precio + ", foto=" + foto
				+ "]";
	}

}