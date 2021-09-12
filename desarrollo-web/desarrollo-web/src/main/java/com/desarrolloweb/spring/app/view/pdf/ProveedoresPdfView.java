package com.desarrolloweb.spring.app.view.pdf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.desarrolloweb.spring.app.entities.Cliente;
import com.desarrolloweb.spring.app.entities.Proveedor;
import com.desarrolloweb.spring.app.util.PageRender;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.desarrolloweb.spring.app.repositories.ProveedorRepository;

@Component("proveedores.pdf")
public class ProveedoresPdfView extends AbstractPdfView {
	private ProveedorRepository proveedorRepository;
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			  
	
		PdfPTable tabla = new PdfPTable(1);
		tabla.setSpacingAfter(20);
		//List<Proveedor> proveedor=(List<Proveedor>) proveedorRepository.findAll();
		//List<String> integrantes=new ArrayList<>();
		//integrantes.add("wilson chupa pollas");
		//integrantes.add("eddy debora penes");
		//integrantes.add("pablo papito crack");
		//ArrayList proveedores = new ArrayList<>();
		//proveedores=(ArrayList) proveedorRepository.findAll();
		
		List<Proveedor> proveedores =  (List<Proveedor>) model.get("proveedores");
		tabla.addCell(proveedores.get(0).getNombre());
	
		
		
		
		document.add(tabla);
	}

}

