package com.daw.motor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.motor.model.Motor;

public interface MotorRepository extends JpaRepository<Motor, Long>{
}