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

import com.daw.motor.model.Motor;
import com.daw.motor.service.MotorService;

@Controller
@RequestMapping("/motor")
public class MotorController {
	@Autowired
	MotorService motorService;

	@GetMapping("/")
	public String listarMotores(Model modelo) {
		Collection<Motor> listaMotores = motorService.getAll(); 
		
		modelo.addAttribute("listaMotores", listaMotores);
		
		return "motor/listar";
	}

	@GetMapping("/{id}")
	public String verMotor(
			@PathVariable(name = "id") Long id,
			Model modelo,
			RedirectAttributes redirAttrs) {
		
		try {
			Motor unMotor = motorService.getById(id);
			modelo.addAttribute("motor", unMotor);
			return "motor/ver";
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
			return "redirect:.";
		}
	}

	@GetMapping("/borrar/{id}")
	public String borrarMotor(
			@PathVariable(name = "id")
			Long id,
			Model modelo) {
		
		modelo.addAttribute("motor", motorService.getById(id));
		
		return "motor/borrar";
	}

	@GetMapping("/borrar-definitivamente/{id}")
	public String borrarDefinitivamenteMotor(
			@PathVariable(name = "id")
			Long id,
			Model modelo,
			RedirectAttributes redirAttrs) {
		
		try {
			motorService.deleteById(id);
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
		}
		
		return "redirect:..";
	}

	@GetMapping("/editar/{id}")
	public String editarMotor(
			@PathVariable(name = "id")
			Long id,
			Model modelo,
			RedirectAttributes redirAttrs) {

		try {
			Motor unMotor = motorService.getById(id);
			modelo.addAttribute("motor", unMotor);
			return "motor/formulario";
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
			return "redirect:..";
		}
	}

	@PostMapping("/editar/enviar")
	public String guardarMotor(
			@ModelAttribute(name = "motor")
			Motor motor,
			Model modelo,
			RedirectAttributes redirAttrs) {

		try {
			motorService.update(motor);
			return "redirect:..";
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
			return "redirect:..";
		}
	}

	@GetMapping("/nuevo/item")
	public String nuevoMotor(Model modelo) {
		Motor nuevoMotor= new Motor();
		nuevoMotor.setTipo("");
		nuevoMotor.setPotencia(0);
		modelo.addAttribute("motor", nuevoMotor);
		
		return "motor/formulario";
	}

	@PostMapping("/nuevo/enviar")
	public String guardarNuevoEquipamiento(
			@ModelAttribute(name = ",motor")
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
}