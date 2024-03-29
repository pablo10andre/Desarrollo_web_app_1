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

import com.desarrolloweb.spring.app.entities.Proveedor;

@Component("detalle-proveedor-form.xlsx")
public class ProveedorExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"reporte_proveedores.xlsx\"");
			Proveedor proveedor = (Proveedor) model.get("proveedor");
			Sheet sheet = workbook.createSheet("Proveedor");
			
			CellStyle header = workbook.createCellStyle();
			header.setBorderBottom(BorderStyle.MEDIUM);
			header.setBorderTop(BorderStyle.MEDIUM);
			header.setBorderRight(BorderStyle.MEDIUM);
			header.setBorderLeft(BorderStyle.MEDIUM);
			header.setFillForegroundColor(IndexedColors.GREEN.index);
			header.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellValue("Datos del Proveedor");
			cell.setCellStyle(header);
			
			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellValue("Nombre");
			cell = row.createCell(1);
			cell.setCellValue(proveedor.getNombre());
			
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
