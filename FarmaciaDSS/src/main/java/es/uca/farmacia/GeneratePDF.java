package es.uca.farmacia;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.*;

public class GeneratePDF 
{
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font basicFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	
	private static void addEmptyLine(Paragraph paragraph, int number) 
	{
        for (int i = 0; i < number; i++) 
        {
            paragraph.add(new Paragraph(" "));
        }
    }
	
	// METADATOS
	private static void addMetaData(Document document) 
	{
        document.addTitle("Ticket de compra");
        document.addSubject("Medicamentos");
        document.addKeywords("Java, PDF, Farmacia");
        document.addAuthor("Farmacia");
        document.addCreator("Farmacia");
    }
	 
	private static void addTitlePage(Document document) throws DocumentException 
	{
		Paragraph preface = new Paragraph();
	    addEmptyLine(preface, 1);
	    
	    Paragraph paragraphTitle = new Paragraph("Ticket de compra", catFont);
	    paragraphTitle.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraphTitle);
	    
	    addEmptyLine(preface, 1);
	    
	    LineSeparator objectName = new LineSeparator();              
	    document.add(objectName);
	    
	    addEmptyLine(preface, 1);
	    Paragraph paragraphInfo = new Paragraph("Compra realizada por: " + System.getProperty("user.name") + ", en fecha: " + new Date(), smallBold);
	    paragraphInfo.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraphInfo);
	    addEmptyLine(preface, 3);

	    document.add(preface);
	    addEmptyLine(preface, 8);
	}
	
	private static void addContent(Document document, Medicamentos med) throws DocumentException 
	{
		int precio = 0;
		Paragraph lista = new Paragraph("Lista de medicamentos comprados:", basicFont);
		addEmptyLine(lista, 1);
		
		document.add(lista);
		
		// Imprimir el nombre de los medicamentos comprados.
		for(Medicamento m: med) 
        {
        	document.add(new Paragraph(m.getNombre() + "....................................................................................................." + m.getPrecio() + "e.\n", basicFont));
        	precio = precio + m.getPrecio();
        }
		
		document.add(new Paragraph("Total de la compra = " + precio + "e.", basicFont));
    }
	
	public static void generar(Medicamentos med, int i) 
	{
		 try 
		 { 
			 	Document document = new Document();
			 	PdfWriter.getInstance(document, new FileOutputStream(new File("Ticket" + i + ".pdf")));
			 	document.open();
			 	
			 	addMetaData(document);
			 	addTitlePage(document);
			 	addContent(document, med);		        
		  
		        //Close document and outputStream.
		        document.close();
		        
		        System.out.println("Ticket de la compra generado correctamente.\n");
		 } 
		 catch (Exception e) 
		 {
			e.printStackTrace();
		 }
	}
}
