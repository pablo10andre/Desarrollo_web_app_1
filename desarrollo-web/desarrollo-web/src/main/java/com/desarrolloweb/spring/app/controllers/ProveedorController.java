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

import com.desarrolloweb.spring.app.repositories.ProveedorRepository;
import com.desarrolloweb.spring.app.util.PageRender;
import com.desarrolloweb.spring.app.entities.Audit;
import com.desarrolloweb.spring.app.entities.Proveedor;

@Controller
public class ProveedorController {

	@Autowired
	private ProveedorRepository proveedorRepository;

	@RequestMapping(value = "/detalle-proveedor/{id}", method = RequestMethod.GET)
	public String detalleProveedor(@PathVariable(value = "id") Long id, Model model) {

		Proveedor proveedor = proveedorRepository.findById(id).get();
		if (proveedor == null) {
			return "redirect:/listar-proveedores";
		}

		model.addAttribute("titulo", "Detalle Proveedor: " + proveedor.getNombre());
		model.addAttribute("proveedor", proveedor);
		return "detalle-proveedor-form";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/listar-proveedores", method = RequestMethod.GET)
	public String listarClientes(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Proveedor> proveedores = proveedorRepository.findAll(pageRequest);

		PageRender<Proveedor> pageRender = new PageRender<Proveedor>("/listar-proveedores", proveedores);
		model.addAttribute("titulo", "Listado de proveedores");
		model.addAttribute("proveedores", proveedores);
		model.addAttribute("page", pageRender);
		return "proveedores";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/nuevo-proveedor", method = RequestMethod.GET)
	public String nuevoProveedor(Model model) {
		Proveedor proveedor = new Proveedor();
		model.addAttribute("titulo", "Nuevo Proveedor");
		model.addAttribute("proveedor", proveedor);
		return "form-proveedor";
	}

	@RequestMapping(value = "/editar-proveedor/{id}", method = RequestMethod.GET)
	public String editarProveedor(@PathVariable(value = "id") Long id, Model model) {
		Proveedor proveedor = null;
		if (id > 0) {
			proveedor = proveedorRepository.findById(id).get();
		} else {
			return "redirect:/listar-proveedores";
		}
		model.addAttribute("titulo", "Editar proveedor");
		model.addAttribute("proveedor", proveedor);
		return "form-proveedor";
	}

	@RequestMapping(value = "/eliminar-proveedor/{id}", method = RequestMethod.GET)
	public String eliminarProveedor(@PathVariable(value = "id") Long id, Model model) {
		Proveedor proveedor = null;
		if (id > 0) {
			proveedor = proveedorRepository.findById(id).get();
			proveedorRepository.delete(proveedor);
		} else {
			return "redirect:/listar-proveedores";
		}

		return "redirect:/listar-proveedores";
	}

	@RequestMapping(value = "/nuevo-proveedor", method = RequestMethod.POST)
	public String guardarproveedor(@RequestParam("file") MultipartFile foto, Proveedor proveedor) {
		Audit audit = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();



		if (!foto.isEmpty()) {

			try {
				
				String path = "/Users/kc/Desktop/tmp/".concat(foto.getOriginalFilename());

				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(path);
				Files.write(rutaCompleta, bytes);
				proveedor.setFoto(foto.getOriginalFilename());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (proveedor.getId() != null && proveedor.getId() > 0) {
			Proveedor proveedor2 = proveedorRepository.findById(proveedor.getId()).get();
			audit = new Audit(auth.getName());
			proveedor.setAudit(audit);
			proveedor.setId(proveedor2.getId());
			proveedor.getAudit().setTsCreated(proveedor2.getAudit().getTsCreated());
			proveedor.getAudit().setUsuCreated(proveedor2.getAudit().getUsuCreated());
		} else {
			audit = new Audit(auth.getName());
			proveedor.setAudit(audit);
		}

		proveedorRepository.save(proveedor);
		return "redirect:/listar-proveedores";
	}

}
