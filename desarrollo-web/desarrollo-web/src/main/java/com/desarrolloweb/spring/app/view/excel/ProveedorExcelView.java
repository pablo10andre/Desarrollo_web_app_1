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


@Component("proveedores.xlsx")

public class ProveedorExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		try {

			response.setHeader("Content-Disposition", "attachment; filename=\"reporte_proveedores.xlsx\"");
			
			Sheet sheet = workbook.createSheet("Proveedor");
			response.setHeader("Content-Disposition", "attachment; filename=\"reporte_clientes.xlsx\"");
			Iterable<Proveedor> pr= (Iterable<Proveedor>) model.get("proveedoresPdf");;
		
			
			CellStyle header = workbook.createCellStyle();
			header.setBorderBottom(BorderStyle.MEDIUM);
			header.setBorderTop(BorderStyle.MEDIUM);
			header.setBorderRight(BorderStyle.MEDIUM);
			header.setBorderLeft(BorderStyle.MEDIUM);
			header.setFillForegroundColor(IndexedColors.GREEN.index);
			header.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellValue("Datos de los Proveedores");
			cell.setCellStyle(header);
			int i=2;
			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellValue("Nombre Proveedor");
			cell = row.createCell(1);
			cell.setCellValue("Email");
			cell = row.createCell(2);
			cell.setCellValue("Direccion");
			cell = row.createCell(3);
			cell.setCellValue("Telefono");
			for(Proveedor proveedor:pr) {
				row = sheet.createRow(i);
				cell = row.createCell(0);
				cell.setCellValue(proveedor.getNombre());
				cell = row.createCell(1);
				cell.setCellValue(proveedor.getEmail());
				cell = row.createCell(2);
				cell.setCellValue(proveedor.getDireccion());
				cell = row.createCell(3);
				cell.setCellValue(proveedor.getTelefono());
				i++;
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
