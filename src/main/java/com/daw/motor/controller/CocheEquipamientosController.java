package com.daw.motor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daw.motor.model.Coche;
import com.daw.motor.service.CocheService;
import com.daw.motor.service.EquipamientoService;
import com.daw.motor.service.MotorService;

@Controller
@RequestMapping("/coche/equipamientos")
public class CocheEquipamientosController {
	@Autowired
	CocheService cocheService;

	@Autowired
	MotorService motorService;

	@Autowired
	EquipamientoService equipamientoService;

	@GetMapping("/{idc}")
	public String editarCocheEquipamiento(
			@PathVariable("idc") Long idCoche,
			Model modelo,
			RedirectAttributes redirAttrs) {
		
		Coche item;
		
		try {
			item = cocheService.getById(idCoche);
		} catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
			return "redirect:..";
		}

		modelo.addAttribute("coche", item);
		modelo.addAttribute("listaMotores", motorService.getAll());
		modelo.addAttribute("listaIncluye", item.getListaIncluye());
		
		return "coche/equipamientos/listar";
	}
}