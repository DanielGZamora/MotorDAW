package com.daw.motor.model;

public class LineaEquipamiento {
	private Long idEquipamientoActual;
	private Long idEquipamientoNuevo;
	private double descuento;
	
	public LineaEquipamiento() {}

	public LineaEquipamiento(Long idEquipamientoActual, Long idEquipamientoNuevo, double descuento) {
		super();
		this.idEquipamientoActual = idEquipamientoActual;
		this.idEquipamientoNuevo = idEquipamientoNuevo;
		this.descuento = descuento;
	}

	public Long getIdEquipamientoActual() {
		return idEquipamientoActual;
	}

	public void setIdEquipamientoActual(Long idEquipamientoActual) {
		this.idEquipamientoActual = idEquipamientoActual;
	}

	public Long getIdEquipamientoNuevo() {
		return idEquipamientoNuevo;
	}

	public void setIdEquipamientoNuevo(Long idEquipamientoNuevo) {
		this.idEquipamientoNuevo = idEquipamientoNuevo;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	@Override
	public String toString() {
		return "LineaEquipamiento [idEquipamientoActual=" + idEquipamientoActual + ", idEquipamientoNuevo="
				+ idEquipamientoNuevo + ", descuento=" + descuento + "]";
	}
	
}