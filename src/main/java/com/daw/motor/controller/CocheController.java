package com.daw.motor.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daw.motor.model.Coche;
import com.daw.motor.service.CocheService;
import com.daw.motor.service.MotorService;

@Controller
@RequestMapping("/modelo")
public class CocheController {
	@Autowired
	CocheService cocheService;

	@Autowired
	MotorService motorService;
	
	@GetMapping("/")
	public String listarCoches(Model modelo) {
		Collection<Coche> listaCoches = cocheService.getAll(); 
		
		modelo.addAttribute("listaCoches", listaCoches);
		
		return "coche/listar";
	}

	@GetMapping("/{id}")
	public String verCoche(
			@PathVariable(name = "id")
			Long id,
			Model modelo,
			RedirectAttributes redirAttrs) {
		
		try {
			Coche unCoche = cocheService.getById(id);
			modelo.addAttribute("coche", unCoche);
			return "coche/ver";
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
			return "redirect:.";
		}
	}

//	@GetMapping("/borrar/{id}")
//	public String borrarMotor(
//			@PathVariable(name = "id")
//			Long id,
//			Model modelo) {
//		
//		modelo.addAttribute("motor", motorService.getById(id));
//		
//		return "motor/borrar";
//	}

	@GetMapping("/borrar-definitivamente/{id}")
	public String borrarDefinitivamenteMotor(
			@PathVariable(name = "id")
			Long id,
			Model modelo,
			RedirectAttributes redirAttrs) {
		
		try {
			cocheService.deleteById(id);
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
		}
		
		return "redirect:..";
	}

	@GetMapping("/editar/{id}")
	public String editarCoche(
			@PathVariable(name = "id")
			Long id,
			Model modelo,
			RedirectAttributes redirAttrs) {

		try {
			Coche unCoche = cocheService.getById(id);
			modelo.addAttribute("coche", unCoche);
			modelo.addAttribute("listaMotores", motorService.getAll());
			return "coche/formulario";
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
			return "redirect:..";
		}
	}

	@PostMapping("/editar/enviar")
	public String guardarCoche(
			@ModelAttribute(name = "coche")
			Coche coche,
			RedirectAttributes redirAttrs) {
		
		try {
			cocheService.update(coche);
			return "redirect:..";
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
			return "redirect:..";
		}
	}

	/*
	@GetMapping("/nuevo/item")
	public String nuevoMotor(Model modelo) {
		Motor nuevoMotor = new Motor();
		
		nuevoMotor.setTipo("Escriba el tipo");
		
		modelo.addAttribute("motor", nuevoMotor);
		
		return "motor/formulario";
	}

	@PostMapping("/nuevo/enviar")
	public String guardarNuevoMotor(
			@ModelAttribute(name = "motor")
			Motor motor,
			RedirectAttributes redirAttrs) {

		try {
			motorService.create(motor);
			return "redirect:..";
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
			return "redirect:..";
		}
	}
*/
}