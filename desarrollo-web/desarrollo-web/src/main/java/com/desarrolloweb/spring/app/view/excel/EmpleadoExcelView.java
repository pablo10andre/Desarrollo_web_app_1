package com.desarrolloweb.spring.app.view.excel;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;


import com.desarrolloweb.spring.app.entities.Empleado;

@Component("detalle-empleado-form.xlsx")
public class EmpleadoExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Empleado e = (Empleado) model.get("empleado");
		response.setHeader("Content-Disposition", "attachment; filename=\"reporte_empleados.xlsx\"");
		Sheet sheet = workbook.createSheet("EMPLEADO "+e.getNombre());

		try {
			
			CellStyle ctitulo = workbook.createCellStyle();
			CellStyle catrib = workbook.createCellStyle();
			CellStyle cobj = workbook.createCellStyle();
			Font tfuente = workbook.createFont();
			Font tatrib = workbook.createFont();
			Font tobj = workbook.createFont();
			
			
			ctitulo.setAlignment(HorizontalAlignment.CENTER);
			tfuente.setBold(true);
			tfuente.setFontHeightInPoints((short)14);
			tfuente.setColor(IndexedColors.BLACK.index);
			tfuente.setFontName("Arial");
			ctitulo.setFont(tfuente);
			
			
			catrib.setBorderBottom(BorderStyle.HAIR);
			catrib.setBorderTop(BorderStyle.HAIR);
			catrib.setBorderRight(BorderStyle.HAIR);
			catrib.setBorderLeft(BorderStyle.HAIR);
			catrib.setFillForegroundColor(IndexedColors.AQUA.index);
			catrib.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			catrib.setAlignment(HorizontalAlignment.CENTER);
			
			
			tatrib.setBold(true);
			tatrib.setFontHeightInPoints((short)12);
			tatrib.setColor(IndexedColors.BLACK.index);
			tatrib.setFontName("Arial");
			catrib.setFont(tatrib);
			
			
			cobj.setBorderBottom(BorderStyle.THIN);
			cobj.setBorderTop(BorderStyle.THIN);
			cobj.setBorderRight(BorderStyle.THIN);
			cobj.setBorderLeft(BorderStyle.THIN);
			cobj.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.index);
			cobj.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			
			tobj.setBold(false);
			tobj.setFontHeightInPoints((short)12);
			tobj.setColor(IndexedColors.BLACK.index);
			tobj.setFontName("Times New Roman");
			cobj.setFont(tobj);
			
			
			Row row = sheet.createRow(1);
			Cell cell = row.createCell(1);
			
			
			sheet.addMergedRegion(new CellRangeAddress(1,1,1,5));
			cell.setCellValue(" DATOS EMPLEADO CODIGO: "+e.getId());;
			cell.setCellStyle(ctitulo);
			
			
			row = sheet.createRow(3);
			cell = row.createCell(1);
			cell.setCellValue("DPI");
			cell.setCellStyle(catrib);
			
			
			cell = row.createCell(2);
			cell.setCellValue("NOMBRE");
			cell.setCellStyle(catrib);
			
			
			cell = row.createCell(3);
			cell.setCellValue("APELLIDO");
			cell.setCellStyle(catrib);
			
			cell = row.createCell(4);
			cell.setCellValue("TELEFONO");
			cell.setCellStyle(catrib);
			
			cell = row.createCell(5);
			cell.setCellValue("EMAIL");
			cell.setCellStyle(catrib);
			
			
				
			
				row = sheet.createRow(4);
				cell = row.createCell(1);
				cell.setCellValue(String.valueOf(e.getDpi()));
				cell.setCellStyle(cobj);
				cell = row.createCell(2);
				cell.setCellValue(e.getNombre());
				cell.setCellStyle(cobj);
				cell = row.createCell(3);
				cell.setCellValue(e.getApellido());
				cell.setCellStyle(cobj);
				cell = row.createCell(4);
				cell.setCellValue(e.getTelefono());
				cell.setCellStyle(cobj);
				cell = row.createCell(5);
				cell.setCellValue(e.getEmail());
				cell.setCellStyle(cobj);
			
			
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				sheet.autoSizeColumn(4);
				sheet.autoSizeColumn(5);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		
	}

	
	
}


