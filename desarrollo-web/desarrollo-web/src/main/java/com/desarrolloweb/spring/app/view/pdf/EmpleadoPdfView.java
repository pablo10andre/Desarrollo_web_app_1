package com.desarrolloweb.spring.app.view.pdf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.desarrolloweb.spring.app.entities.Empleado;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("empleados.pdf")
public class EmpleadoPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		Iterable<Empleado> pr= (Iterable<Empleado>) model.get("empleadoDoc");
		PdfPTable tabla = new PdfPTable(1);
		Empleado e = new Empleado();
		List<Empleado> l = new ArrayList<Empleado>();
		List<Empleado> lista = new ArrayList<Empleado>();
		lista.add(e);
		
		PdfPCell ctitulo = new PdfPCell();
		PdfPCell csalto = new PdfPCell();
		Font ftitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		PdfPCell cempleado = new PdfPCell();
		Font fempleado = FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE);
		
		
		ftitulo.setColor(Color.BLACK);
		ftitulo.setSize(27);
		ctitulo.setHorizontalAlignment(ctitulo.ALIGN_CENTER);
		ctitulo.setBorder(ctitulo.NO_BORDER);
		ctitulo.setPhrase(new Phrase("LISTADO DE EMPLEADOS",ftitulo));
	
		fempleado.setColor(Color.BLACK);
		fempleado.setSize(16);
		cempleado.setBackgroundColor(Color.lightGray);
		cempleado.setHorizontalAlignment(ctitulo.ALIGN_LEFT);
		cempleado.setBorderColor(Color.BLUE);
		cempleado.setBorderWidth(2);
		cempleado.setVerticalAlignment(ctitulo.ALIGN_BASELINE);

		csalto.setBorder(ctitulo.NO_BORDER);
		csalto.setPhrase(new Phrase("",ftitulo));		
		
	
		tabla.setSpacingAfter(20);
		tabla.addCell(ctitulo);	
		tabla.addCell(csalto);
		tabla.addCell(csalto);		
		for(Empleado empleado:pr) {
			cempleado.setPhrase(new Phrase(empleado.getId() + ", " + empleado.getNombre() + ", " + empleado.getEmail() + ", " + ", " + empleado.getTelefono(),fempleado));
			
			tabla.addCell(cempleado);
		}
		document.add(tabla);
	}
	}


