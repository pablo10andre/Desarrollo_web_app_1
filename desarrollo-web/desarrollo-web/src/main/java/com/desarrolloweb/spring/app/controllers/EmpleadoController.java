package com.desarrolloweb.spring.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.desarrolloweb.spring.app.repositories.EmpleadoRepository;
import com.desarrolloweb.spring.app.util.PageRender;
import com.desarrolloweb.spring.app.entities.Audit;
import com.desarrolloweb.spring.app.entities.Empleado;
@Controller
public class EmpleadoController {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@RequestMapping(value = "/detalle-empleado/{id}", method = RequestMethod.GET)
	public String detalleEmpleado(@PathVariable(value = "id") Long id, Model model) {

		Empleado empleado= empleadoRepository.findById(id).get();
		if (empleado == null) {
			return "redirect:/listar-empleados";
		}

		model.addAttribute("titulo", "Detalle empleado: " + empleado.getNombre());
		model.addAttribute("empleado", empleado);
		return "detalle-empleado-form";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/listar-empleados", method = RequestMethod.GET)
	public String listarEmpleados(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Empleado> empleados= empleadoRepository.findAll(pageRequest);

		PageRender<Empleado> pageRender = new PageRender<Empleado>("/listar-empleados", empleados);
		model.addAttribute("titulo", "Listado de empleados");
		model.addAttribute("empleados", empleados);
		model.addAttribute("page", pageRender);
		return "empleados";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/nuevo-empleado", method = RequestMethod.GET)
	public String nuevoCliente(Model model) {
		Empleado empleado= new Empleado();
		model.addAttribute("titulo", "Nuevo Cliente");
		model.addAttribute("empleado", empleado);
		return "form-empleado";
	}

	@RequestMapping(value = "/editar-empleado/{id}", method = RequestMethod.GET)
	public String editarEmpleado(@PathVariable(value = "id") Long id, Model model) {
		Empleado empleado = null;
		if (id > 0) {
			empleado = empleadoRepository.findById(id).get();
		} else {
			return "redirect:/listar-empleados";
		}
		model.addAttribute("titulo", "Editar Empleado");
		model.addAttribute("empleado", empleado);
		return "form-empleado";
	}

	@RequestMapping(value = "/eliminar-empleado/{id}", method = RequestMethod.GET)
	public String eliminarEmpleado(@PathVariable(value = "id") Long id, Model model) {
		Empleado empleado= null;
		if (id > 0) {
			empleado = empleadoRepository.findById(id).get();
			empleadoRepository.delete(empleado);
		} else {
			return "redirect:/listar-empleados";
		}

		return "redirect:/listar-empleados";
	}

	@RequestMapping(value = "/nuevo-empleado", method = RequestMethod.POST)
	public String guardarCliente(@RequestParam("file") MultipartFile foto, Empleado empleado) {
		Audit audit = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();



		if (!foto.isEmpty()) {

			try {
				
				String path = "/Users/kc/Desktop/tmp/".concat(foto.getOriginalFilename());

				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(path);
				Files.write(rutaCompleta, bytes);
				empleado.setFoto(foto.getOriginalFilename());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (empleado.getId() != null && empleado.getId() > 0) {
			Empleado empleado2= empleadoRepository.findById(empleado.getId()).get();
			audit = new Audit(auth.getName());
			empleado.setAudit(audit);
			empleado.setId(empleado2.getId());
			empleado.getAudit().setTsCreated(empleado.getAudit().getTsCreated());
			empleado.getAudit().setUsuCreated(empleado2.getAudit().getUsuCreated());
		} else {
			audit = new Audit(auth.getName());
			empleado.setAudit(audit);
		}

		empleadoRepository.save(empleado);
		return "redirect:/listar-empleados";
	}

}
