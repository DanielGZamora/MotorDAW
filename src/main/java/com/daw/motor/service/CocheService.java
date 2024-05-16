package com.daw.motor.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.motor.model.Coche;
import com.daw.motor.repository.CocheRepository;

@Service
public class CocheService {
	@Autowired
	CocheRepository cocheRepository;
	
	public Collection<Coche> getAll() {
		return cocheRepository.findAll();
	}
	
	public Coche getById(Long id) {
		Coche unCoche = cocheRepository.findById(id).orElse(null);
		
		if (unCoche == null)
			throw new RuntimeException("El elemento no existe");
		
		return unCoche; 
	}
	
	public void create(Coche item) {
		Long id = item.getId();
		
		if (id != null && cocheRepository.findById(id) != null)
			throw new RuntimeException("Ya existe ese elemento");

		cocheRepository.save(item);
	}
	
	public Coche update(Coche item) {
		Long id = item.getId();

		if (id == null || cocheRepository.findById(id) == null)
			throw new RuntimeException("El elemento no existe");

		return cocheRepository.save(item);
	}
	
	public void delete(Coche item) {
		try {
			cocheRepository.delete(item);
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("No se ha podido borrar el elemento");
		}
	}
	
	public void deleteById(Long id) {
		try {
			cocheRepository.deleteById(id);
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("No se ha podido borrar el elemento");
		}
	}
}