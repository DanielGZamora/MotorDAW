package com.daw.motor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.motor.model.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Long>{

}