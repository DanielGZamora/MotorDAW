package com.daw.motor.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.motor.model.Equipamiento;
import com.daw.motor.repository.EquipamientoRepository;

@Service
public class EquipamientoService {
	@Autowired
	EquipamientoRepository equipamientoRepository;

	public Collection<Equipamiento> getAll() {
		return equipamientoRepository.findAll();
	}

	public Equipamiento getById(Long id) {

		final Equipamiento unEquipamiento = equipamientoRepository.findById(id).orElse(null);

		if (unEquipamiento == null) {
			lanzarExcepcionNoExiste();
		}
		return unEquipamiento;
	}

	public void create(Equipamiento item) {
		lanzarExcepcionCuandoYaExiste(item.getId());

		equipamientoRepository.save(item);
	}

	public Equipamiento update(Equipamiento item) {
		lanzarExcepcionCuandoNoExiste(item.getId());

		return equipamientoRepository.save(item);
	}

	public void delete(Equipamiento item) {
		try {
			equipamientoRepository.delete(item);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			lanzarExcepcionNoSePuedeBorrar();
		}
	}

	public void deleteById(Long id) {
		try {
			equipamientoRepository.deleteById(id);
			;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			lanzarExcepcionNoSePuedeBorrar();
		}
	}

	// Métodos auxiliares
	private void lanzarExcepcionNoExiste() {
		throw new EquipamientoException("No existe el equipamiento que busca");
	}

	private void lanzarExcepcionCuandoYaExiste(Long id) {
		if (id != null && equipamientoRepository.existsById(id)) {
			throw new EquipamientoException("Este id ya existe");
		}
	}

	private void lanzarExcepcionCuandoNoExiste(Long id) {
		if (id != null && !equipamientoRepository.existsById(id)) {
			lanzarExcepcionNoExiste();
		}
	}
	private void lanzarExcepcionNoSePuedeBorrar() {
		throw new EquipamientoException("Este equipamiento no puede ser eliminado");
	}
}