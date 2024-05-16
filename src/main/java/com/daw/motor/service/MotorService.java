package com.daw.motor.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.motor.model.Motor;
import com.daw.motor.repository.MotorRepository;

@Service
public class MotorService {
	@Autowired
	MotorRepository motorRepository;

	public Collection<Motor> getAll() {
		return motorRepository.findAll();
	}

	public Motor getById(Long id) {

		final Motor unMotor = motorRepository.findById(id).orElse(null);

		if (unMotor == null) {
			lanzarExcepcionNoExiste();
		}
		return unMotor;
	}

	public void create(Motor item) {
		lanzarExcepcionCuandoYaExiste(item.getId());

		motorRepository.save(item);
	}

	public Motor update(Motor item) {
		lanzarExcepcionCuandoNoExiste(item.getId());

		return motorRepository.save(item);
	}

	public void delete(Motor item) {
		try {
			motorRepository.delete(item);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			lanzarExcepcionNoSePuedeBorrar();
		}
	}

	public void deleteById(Long id) {
		try {
			motorRepository.deleteById(id);
			;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			lanzarExcepcionNoSePuedeBorrar();
		}
	}

	// Métodos auxiliares
	private void lanzarExcepcionNoExiste() {
		throw new MotorException("No existe el motor que busca");
	}

	private void lanzarExcepcionCuandoYaExiste(Long id) {
		if (id != null && motorRepository.existsById(id)) {
			throw new MotorException("Este id ya existe");
		}
	}

	private void lanzarExcepcionCuandoNoExiste(Long id) {
		if (id != null && !motorRepository.existsById(id)) {
			lanzarExcepcionNoExiste();
		}
	}
	private void lanzarExcepcionNoSePuedeBorrar() {
		throw new MotorException("Este motor no puede ser eliminado");
	}
}