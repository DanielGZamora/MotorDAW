package com.daw.motor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.motor.model.Equipamiento;

@Repository
public interface EquipamientoRepository extends JpaRepository<Equipamiento, Long> {
}