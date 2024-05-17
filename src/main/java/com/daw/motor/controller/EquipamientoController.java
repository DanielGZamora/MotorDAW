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

import com.daw.motor.model.Equipamiento;
import com.daw.motor.service.EquipamientoService;

@Controller
@RequestMapping("/equipamiento")
public class EquipamientoController {
	@Autowired
	EquipamientoService equipamientoService;

	@GetMapping("/")
	public String listarEquipamientos(Model modelo) {
		Collection<Equipamiento> listaEquipamientos = equipamientoService.getAll(); 
		
		modelo.addAttribute("listaEquipamientos", listaEquipamientos);
		
		return "equipamiento/listar";
	}

	@GetMapping("/{id}")
	public String verEquipamiento(
			@PathVariable(name = "id") Long id,
			Model modelo,
			RedirectAttributes redirAttrs) {
		
		try {
			Equipamiento unEquipamiento = equipamientoService.getById(id);
			modelo.addAttribute("equipamiento", unEquipamiento);
			return "equipamiento/ver";
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
			return "redirect:.";
		}
	}

	@GetMapping("/borrar/{id}")
	public String borrarEquipamiento(
			@PathVariable(name = "id")
			Long id,
			Model modelo) {
		
		modelo.addAttribute("equipamiento", equipamientoService.getById(id));
		
		return "equipamiento/borrar";
	}

	@GetMapping("/borrar-definitivamente/{id}")
	public String borrarDefinitivamenteEquipamiento(
			@PathVariable(name = "id")
			Long id,
			Model modelo,
			RedirectAttributes redirAttrs) {
		
		try {
			equipamientoService.deleteById(id);
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
		}
		
		return "redirect:..";
	}

	@GetMapping("/editar/{id}")
	public String editarEquipamiento(
			@PathVariable(name = "id")
			Long id,
			Model modelo,
			RedirectAttributes redirAttrs) {

		try {
			Equipamiento unEquipamiento = equipamientoService.getById(id);
			modelo.addAttribute("equipamiento", unEquipamiento);
			return "equipamiento/formulario";
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
			return "redirect:..";
		}
	}

	@PostMapping("/editar/enviar")
	public String guardarEquipamiento(
			@ModelAttribute(name = "equipamiento")
			Equipamiento equipamiento,
			Model modelo,
			RedirectAttributes redirAttrs) {

		try {
			equipamientoService.update(equipamiento);
			return "redirect:..";
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
			return "redirect:..";
		}
	}

	@GetMapping("/nuevo/item")
	public String nuevoEquipamiento(Model modelo) {
		Equipamiento nuevoEquipamiento = new Equipamiento();
		
		nuevoEquipamiento.setDescripcion("Escriba una descripción");
		
		modelo.addAttribute("equipamiento", nuevoEquipamiento);
		
		return "equipamiento/formulario";
	}

	@PostMapping("/nuevo/enviar")
	public String guardarNuevoEquipamiento(
			@ModelAttribute(name = "equipamiento")
			Equipamiento equipamiento,
			RedirectAttributes redirAttrs) {

		try {
			equipamientoService.create(equipamiento);
			return "redirect:..";
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
			return "redirect:..";
		}
	}
}