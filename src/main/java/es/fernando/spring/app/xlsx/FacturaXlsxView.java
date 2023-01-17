package es.fernando.spring.app.xlsx;

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

import es.fernando.spring.app.entity.Factura;
import es.fernando.spring.app.entity.ItemFactura;

@Component("factura/ver.xlsx")
public class FacturaXlsxView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"Factura.xlsx\"");
		Factura factura = (Factura) model.get("factura");
		Sheet sheet = workbook.createSheet("Factura Spring");
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		
		cell.setCellValue("Datos del cliente");
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getNombre());
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getEmail());
		
		sheet.createRow(4).createCell(0).setCellValue("Datos de la Factura");
		sheet.createRow(5).createCell(0).setCellValue("Folio: " + factura.getId());;
		sheet.createRow(6).createCell(0).setCellValue("Descripcion: " + factura.getDescripcion());;
		sheet.createRow(7).createCell(0).setCellValue("Fecha: " + factura.getCreateAt());;

		CellStyle theaderstyle = workbook.createCellStyle();
		theaderstyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderstyle.setBorderTop(BorderStyle.MEDIUM);
		theaderstyle.setBorderRight(BorderStyle.MEDIUM);
		theaderstyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderstyle.setFillBackgroundColor(IndexedColors.GOLD.index);
		theaderstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		Row header = sheet.createRow(9);
		header.createCell(0).setCellValue("Producto");
		header.createCell(1).setCellValue("Precio");
		header.createCell(2).setCellValue("Cantidad");
		header.createCell(3).setCellValue("Total");
		
		header.getCell(0).setCellStyle(theaderstyle);
		header.getCell(1).setCellStyle(theaderstyle);
		header.getCell(2).setCellStyle(theaderstyle);
		header.getCell(3).setCellStyle(theaderstyle);
		int rownum = 10;
		for(ItemFactura item: factura.getItems()) {
			CellStyle tbodystyle = workbook.createCellStyle();
			tbodystyle.setBorderBottom(BorderStyle.THIN);
			tbodystyle.setBorderTop(BorderStyle.THIN);
			tbodystyle.setBorderRight(BorderStyle.THIN);
			tbodystyle.setBorderLeft(BorderStyle.THIN);
			Row fila = sheet.createRow(rownum++);

			fila.createCell(0).setCellValue(item.getProducto().getNombre());
			fila.createCell(1).setCellValue(item.getProducto().getPrecio());
			fila.createCell(2).setCellValue(item.getCantidad());
			fila.createCell(3).setCellValue(item.calcularImporte());
			
			fila.getCell(0).setCellStyle(tbodystyle);
			fila.getCell(1).setCellStyle(tbodystyle);
			fila.getCell(2).setCellStyle(tbodystyle);
			fila.getCell(3).setCellStyle(tbodystyle);
		}
		
		Row filaTotal = sheet.createRow(rownum);
		filaTotal.createCell(2).setCellValue("Total factura: ");
		filaTotal.createCell(3).setCellValue(factura.getTotal());

		
	}

}
