package com.desarrolloweb.spring.app.view.excel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.desarrolloweb.spring.app.entities.Cliente;
import com.desarrolloweb.spring.app.entities.Proveedor;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.GroupLayout.Alignment;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;


@Component("proveedores.xlsx")

public class ProveedorExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		try {

			response.setHeader("Content-Disposition", "attachment; filename=\"reporte_proveedores.xlsx\"");
			
			Sheet sheet = workbook.createSheet("LISTADO-PROVEEDORES");
			response.setHeader("Content-Disposition", "attachment; filename=\"reporte_clientes.xlsx\"");
			Iterable<Proveedor> pr= (Iterable<Proveedor>) model.get("proveedoresPdf");;
			
			//INICIO
			CellStyle ctitulo = workbook.createCellStyle();
			CellStyle catrib = workbook.createCellStyle();
			CellStyle cobj = workbook.createCellStyle();
			Font tfuente = workbook.createFont();
			Font tatrib = workbook.createFont();
			Font tobj = workbook.createFont();
			
			//ESTILO TITULO
			ctitulo.setAlignment(HorizontalAlignment.CENTER);
			tfuente.setBold(true);
			tfuente.setFontHeightInPoints((short)14);
			tfuente.setColor(IndexedColors.BLACK.index);
			tfuente.setFontName("Rockwell Condensed");
			ctitulo.setFont(tfuente);
			
			//ESTILO ATRIBUTOS
			catrib.setBorderBottom(BorderStyle.MEDIUM);
			catrib.setBorderTop(BorderStyle.MEDIUM);
			catrib.setBorderRight(BorderStyle.MEDIUM);
			catrib.setBorderLeft(BorderStyle.MEDIUM);
			catrib.setFillForegroundColor(IndexedColors.CORAL.index);
			catrib.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			catrib.setAlignment(HorizontalAlignment.CENTER);
			
			//FUENTE
			tatrib.setBold(true);
			tatrib.setFontHeightInPoints((short)12);
			tatrib.setColor(IndexedColors.BLACK.index);
			tatrib.setFontName("Times New Roman");
			catrib.setFont(tatrib);
			
			//ESTILO OBJETOS
			cobj.setBorderBottom(BorderStyle.MEDIUM);
			cobj.setBorderTop(BorderStyle.MEDIUM);
			cobj.setBorderRight(BorderStyle.MEDIUM);
			cobj.setBorderLeft(BorderStyle.MEDIUM);
			cobj.setFillForegroundColor(IndexedColors.GOLD.index);
			cobj.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			//FUENTE
			tobj.setBold(false);
			tobj.setFontHeightInPoints((short)12);
			tobj.setColor(IndexedColors.BLACK.index);
			tobj.setFontName("Times New Roman");
			cobj.setFont(tobj);
			
			//SALTOS
			Row row = sheet.createRow(1);
			Cell cell = row.createCell(1);
			
			//TITULO
			sheet.addMergedRegion(new CellRangeAddress(1,1,1,5));
			cell.setCellValue("LISTADO DE PROVEEDORES");
			cell.setCellStyle(ctitulo);
			
			//ID
			row = sheet.createRow(3);
			cell = row.createCell(1);
			cell.setCellValue("ID");
			cell.setCellStyle(catrib);
			
			//NOMBRE
			cell = row.createCell(2);
			cell.setCellValue("NOMBRE");
			cell.setCellStyle(catrib);
			
			//EMAIL
			cell = row.createCell(3);
			cell.setCellValue("EMAIL");
			cell.setCellStyle(catrib);
			
			//DIRECCION
			cell = row.createCell(4);
			cell.setCellValue("DIRECCION");
			cell.setCellStyle(catrib);
			
			//TELEFONO
			cell = row.createCell(5);
			cell.setCellValue("TELEFONO");
			cell.setCellStyle(catrib);
			
			//FIN
				
			int i=4;
			for(Proveedor proveedor:pr) {
				row = sheet.createRow(i);
				cell = row.createCell(1);
				cell.setCellValue(proveedor.getId());
				cell.setCellStyle(cobj);
				cell = row.createCell(2);
				cell.setCellValue(proveedor.getNombre());
				cell.setCellStyle(cobj);
				cell = row.createCell(3);
				cell.setCellValue(proveedor.getEmail());
				cell.setCellStyle(cobj);
				cell = row.createCell(4);
				cell.setCellValue(proveedor.getDireccion());
				cell.setCellStyle(cobj);
				cell = row.createCell(5);
				cell.setCellValue(proveedor.getTelefono());
				cell.setCellStyle(cobj);
				i++;
			}
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
