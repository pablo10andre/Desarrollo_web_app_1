package com.desarrolloweb.spring.app.view.pdf;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.desarrolloweb.spring.app.entities.Proveedor;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


@Component("proveedores.pdf")
public class ProveedoresPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		Iterable<Proveedor> pr= (Iterable<Proveedor>) model.get("proveedoresPdf");
		PdfPTable tabla = new PdfPTable(1);
		tabla.setSpacingAfter(20);


		
		
		for(Proveedor proveedor:pr) {
			tabla.addCell(proveedor.getNombre()+"|"+proveedor.getDireccion()+"|"+proveedor.getTelefono()+"|"+proveedor.getEmail());
			
			
		}
		
	
	
		

		document.add(tabla);
	}

}

