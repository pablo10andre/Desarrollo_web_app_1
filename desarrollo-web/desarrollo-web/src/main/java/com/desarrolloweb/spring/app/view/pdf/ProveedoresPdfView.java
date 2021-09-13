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

import com.desarrolloweb.spring.app.entities.Proveedor;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
//import antlr.collections.List;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component("proveedores.pdf")
public class ProveedoresPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		Iterable<Proveedor> pr= (Iterable<Proveedor>) model.get("proveedoresPdf");
		PdfPTable tabla = new PdfPTable(1);
		Proveedor p = new Proveedor();
		List<Proveedor> l = new ArrayList<Proveedor>();
		List<Proveedor> lista = new ArrayList<Proveedor>();
		lista.add(p);
		
		PdfPCell ctitulo = new PdfPCell();
		PdfPCell csalto = new PdfPCell();
		Font ftitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		PdfPCell cproveedor = new PdfPCell();
		Font fproveedor = FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE);
		
		//Color y fuente titulo
		ftitulo.setColor(Color.BLACK);
		ftitulo.setSize(24);
		ctitulo.setHorizontalAlignment(ctitulo.ALIGN_CENTER);
		ctitulo.setBorder(ctitulo.NO_BORDER);
		ctitulo.setPhrase(new Phrase("LISTADO DE PROVEEDORES",ftitulo));
		
		//Color y fuente proveedor
		fproveedor.setColor(Color.BLACK);
		fproveedor.setSize(16);
		cproveedor.setBackgroundColor(Color.CYAN);
		cproveedor.setHorizontalAlignment(ctitulo.ALIGN_LEFT);
		cproveedor.setBorderColor(Color.BLUE);
		cproveedor.setBorderWidth(2);
		cproveedor.setVerticalAlignment(ctitulo.ALIGN_BASELINE);
		//cproveedor.setPhrase(new Phrase(proveedor.getId() + ", " + proveedor.getNombre() + ", " + proveedor.getEmail(),fproveedor));
		csalto.setBorder(ctitulo.NO_BORDER);
		csalto.setPhrase(new Phrase("",ftitulo));		
		
		//Ingreso
		tabla.setSpacingAfter(20);
		tabla.addCell(ctitulo);	
		tabla.addCell(csalto);
		tabla.addCell(csalto);		
		for(Proveedor proveedor:pr) {
			cproveedor.setPhrase(new Phrase(proveedor.getId() + ", " + proveedor.getNombre() + ", " + proveedor.getEmail() + ", " + proveedor.getDireccion() + ", " + proveedor.getTelefono(),fproveedor));
			//tabla.addCell(proveedor.getNombre()+"|"+proveedor.getDireccion()+"|"+proveedor.getTelefono()+"|"+proveedor.getEmail());	
			tabla.addCell(cproveedor);
		}
		document.add(tabla);
	}

}

