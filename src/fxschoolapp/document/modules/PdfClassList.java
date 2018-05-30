/*
 * Class 
 * @filename PdfObservationSheet 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 23 May 2018 * 
 */
package fxschoolapp.document.modules;

import app.config.Constants;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import core.com.date.ComDate;
import core.com.pdf.ComPdf;
import static j2html.TagCreator.*;

/**
 *
 * @author Ryno
 */
public class PdfClassList extends ComPdf{
    private String date = null;
    private String className = null;
    //--------------------------------------------------------------------------
    public PdfClassList(){
        super();
        super.setMarginTop(20);
        super.setMarginLeft(20);
        super.setMarginRight(20);
        super.setMarginBottom(20);
        
        date = ComDate.getDate(Constants.DATE);
        className = "Grade R";
    };
    //--------------------------------------------------------------------------
    @Override
    public String getFilename() {
        return "classlist-"+date+".pdf";
    }
    //--------------------------------------------------------------------------
    @Override
    public void createPdf() {
        setHeader();
        addHTML(div().withStyle("height:20px").toString());
        
        setStudentList();
        addHTML(div().withStyle("height:20px").toString());
    }
    //----------------------------------------------------------------------------
    public void setHeader() {
        
        float[] columnWidths = {1,1,1,1,1,1,1,1,1,1,1,1};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        
        PdfPCell cell = getCell(4, 18);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        
        cell.setPhrase(new Phrase("CLASS LIST", font8b));
        table.addCell(cell);
        
        Phrase datePhrase = new Phrase();
        datePhrase.add(new Chunk("DATE: ", font8b));
        datePhrase.add(new Chunk(date, font8).setLineHeight(8));
        cell.setPhrase(datePhrase);
        table.addCell(cell);
        
        Phrase classPhrase = new Phrase();
        classPhrase.add(new Chunk("CLASS: ", font8b));
        classPhrase.add(new Chunk(className, font8).setLineHeight(8));
        cell.setPhrase(classPhrase);
        table.addCell(cell);
        
        addElement(table);
    }
    //----------------------------------------------------------------------------
    public void setStudentList() {
        
        float[] columnWidths = {1,1,1,1,1,1,1,1,1,1,1,1};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        
        //--------------------------------------------------------
        table.addCell(getCell(new Phrase("Malan, Koos", font7), 2, 12));
        for (int i = 0; i < 10; i++) table.addCell(getCell(1, 12));
        
        table.addCell(getCell(new Phrase("Anderson, gabrielskloof", font7), 2, 12));
        for (int i = 0; i < 10; i++) table.addCell(getCell(1, 12));
        
        table.addCell(getCell(new Phrase("Anderson, gabrielskloof", font7), 2, 12));
        for (int i = 0; i < 10; i++) table.addCell(getCell(1, 12));
        
        table.addCell(getCell(new Phrase("Anderson, gabrielskloof", font7), 2, 12));
        for (int i = 0; i < 10; i++) table.addCell(getCell(1, 12));
        
        table.addCell(getCell(new Phrase("Anderson, gabrielskloof", font7), 2, 12));
        for (int i = 0; i < 10; i++) table.addCell(getCell(1, 12));
        
        table.addCell(getCell(new Phrase("Anderson, gabrielskloof", font7), 2, 12));
        for (int i = 0; i < 10; i++) table.addCell(getCell(1, 12));
        
        table.addCell(getCell(new Phrase("Anderson, gabrielskloof", font7), 2, 12));
        for (int i = 0; i < 10; i++) table.addCell(getCell(1, 12));
        
        table.addCell(getCell(new Phrase("Anderson, gabrielskloof", font7), 2, 12));
        for (int i = 0; i < 10; i++) table.addCell(getCell(1, 12));
        
        
        //--------------------------------------------------------
        addElement(table);
        
    }
    //----------------------------------------------------------------------------
}
