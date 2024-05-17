package com.daw.motor.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daw.motor.model.Coche;
import com.daw.motor.model.Incluye;
import com.daw.motor.service.CocheService;
import com.daw.motor.service.EquipamientoService;
import com.daw.motor.service.MotorService;

@Controller
@RequestMapping("/modelo/equipamientos")
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
		
		return "coche/equipamientos/listar";
	}

	@GetMapping("{idc}/borrar/{ide}")
	public String borrarMotor(
			@PathVariable(name = "idc")
			Long idc,
			@PathVariable(name = "ide")
			Long ide,
			Model modelo) {
		
		modelo.addAttribute("coche", cocheService.getById(idc));
		modelo.addAttribute("equipamiento", equipamientoService.getById(ide));
		
		return "coche/equipamientos/borrar";
	}

	@GetMapping("/{idc}/borrar-definitivamente/{ide}")
	public String borrarDefinitivamenteMotor(
			@PathVariable(name = "idc")
			Long idc,
			@PathVariable(name = "ide")
			Long ide,
			Model modelo,
			RedirectAttributes redirAttrs) {
		
		try {
			Coche unCoche = cocheService.getById(idc);
			Set<Incluye> listaIncluye = unCoche.getListaIncluye(); 
			
			for (Incluye i : listaIncluye) {
				if (i.getId().getIdEquipamiento() == ide) {
					listaIncluye.remove(i);
					break;
				}
			}
			
			cocheService.update(unCoche);
		}
		catch (Exception e) {
			redirAttrs.addFlashAttribute("error", true);
			redirAttrs.addFlashAttribute("mensaje", e.getMessage());
		}
		
		return "redirect:/modelo/equipamientos/"+idc;
	}
}