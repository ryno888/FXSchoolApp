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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import core.com.pdf.ComPdf;
import static j2html.TagCreator.*;

/**
 *
 * @author Ryno
 */
public class PdfObservationSheet extends ComPdf{
    private String studentName = "Ryno van Zyl";
    private String studentGrade = "Grade R";
    private String studentBirthday = "23-12-1988";
    private String studentPreviousSchool = "Hermanus High School";
    private String classYear = "2005";
    private char check = '\u2713';
    private char times = '\u2717';
    private char bullet = '\u2022';
    
    private String temp_remark = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
                                + "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                                + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    private String temp_year = "2018";
    //--------------------------------------------------------------------------
    public PdfObservationSheet(){
        super();
        super.setMarginTop(20);
        super.setMarginLeft(20);
        super.setMarginRight(20);
        super.setMarginBottom(20);
    };
    //--------------------------------------------------------------------------
    @Override
    public String getFilename() {
        return "observation-"+studentName+".pdf";
    }
    //--------------------------------------------------------------------------
    @Override
    public void createPdf() {
        getHeader();
        addHTML(div().withStyle("height:20px").toString());
        
        setParentInvolvement();
        addHTML(div().withStyle("height:20px").toString());
        
        setDiciplineRating();
        addHTML(div().withStyle("height:20px").toString());
        
        setDiciplineObservation();
        addHTML(div().withStyle("height:20px").toString());
        
        setInterventionTermDetails();
        addHTML(div().withStyle("height:20px").toString());
    }
    //----------------------------------------------------------------------------
    public void getHeader() {
        
        float[] columnWidths = {1,1,1,1,1,1,1,1,1,1};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        
        PdfPCell cell = getCell(new Phrase("OBSERVATION SHEET", font8b), 10, 18);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        table.addCell(cell);
        
        //----------------------------------------------------------------------
        Phrase namePhrase = new Phrase();
        namePhrase.add(new Chunk("NAME: ", font8b));
        namePhrase.add(new Chunk(studentName, font8).setLineHeight(8));
        
        cell = getCell(namePhrase, 5);
        cell.setPadding(5);
        table.addCell(cell);
        
        
        Phrase grade = new Phrase();
        grade.add(new Chunk("GRADE", font8b));
        grade.add(Chunk.NEWLINE); 
        grade.add(new Chunk(studentGrade, font5).setLineHeight(8));
        
        cell = new PdfPCell(grade);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(1);
        cell.setPadding(5);
        table.addCell(cell);
        
        
        Phrase year = new Phrase();
        year.add(new Chunk("YEAR", font8b));
        year.add(Chunk.NEWLINE); 
        year.add(new Chunk(classYear, font5).setLineHeight(8));
        cell = new PdfPCell(year);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(1);
        cell.setPadding(5);
        table.addCell(cell);
        
        cell.setPhrase(null);
        cell.setColspan(3);
        table.addCell(cell);
        
        //----------------------------------------------------------------------
        
        Phrase birthdayPhrase = new Phrase();
        birthdayPhrase.add(new Chunk("BIRTH DATE: ", font8b));
        birthdayPhrase.add(new Chunk(studentBirthday, font8).setLineHeight(8));
        
        cell = new PdfPCell();
        cell.setPhrase(birthdayPhrase);
        cell.setPadding(5);
        cell.setColspan(4);
        table.addCell(cell);
        
        Phrase pervGrade = new Phrase();
        pervGrade.add(new Chunk("PREV. GRADE:  ", font8b));
        pervGrade.add(new Chunk("3", font8));
        cell = new PdfPCell();
        cell.setPhrase(pervGrade);
        cell.setPadding(5);
        cell.setColspan(3);
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setPhrase(new Phrase("PREVIOUS SCHOOL", font8b));
        cell.setColspan(3);
        cell.setPadding(5);
        table.addCell(cell);
        
        //----------------------------------------------------------------------
        
        cell = new PdfPCell();
        cell.setPhrase(null);
        cell.setColspan(4);
        cell.setPadding(5);
        table.addCell(cell);
        
        Phrase repGrade = new Phrase();
        repGrade.add(new Chunk("GRADE REPEATED:  ", font8b));
        repGrade.add(new Chunk("2, 3", font8));
        cell = new PdfPCell();
        cell.setPhrase(repGrade);
        cell.setColspan(3);
        cell.setPadding(5);
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setPhrase(new Phrase(studentPreviousSchool, font8));
        cell.setColspan(3);
        cell.setPadding(5);
        table.addCell(cell);
        
        //----------------------------------------------------------------------
        
        addElement(table);
    }
    //----------------------------------------------------------------------------
    public void setParentInvolvement() {
        
        float[] columnWidths = {1,1,1,1,1,1,1,1,1,1};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        
        PdfPCell cell = new PdfPCell();
        
        cell.setPhrase(new Phrase("TICK CODE", font8b));
        cell.setColspan(10);
        cell.setFixedHeight(15);
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Yes/Good", font8b));
        cell.setColspan(1);
        table.addCell(cell);
        
        
        PdfPCell checkSymbol = new PdfPCell(getSymbol(check));
        checkSymbol.setHorizontalAlignment(Element.ALIGN_CENTER);
        checkSymbol.setVerticalAlignment(Element.ALIGN_MIDDLE);
        checkSymbol.setColspan(1);
        table.addCell(checkSymbol);

        cell.setPhrase(new Phrase("Weak/no", font8b));
        cell.setColspan(1);
        table.addCell(cell);
        
        PdfPCell timesSymbol = new PdfPCell(getSymbol(times));
        timesSymbol.setHorizontalAlignment(Element.ALIGN_CENTER);
        timesSymbol.setVerticalAlignment(Element.ALIGN_MIDDLE);
        timesSymbol.setColspan(1);
        table.addCell(timesSymbol);
        
        cell.setPhrase(new Phrase("Satisfactory", font8b));
        cell.setColspan(1);
        table.addCell(cell);
        
        PdfPCell bulletSymbol = new PdfPCell(getSymbol(bullet));
        bulletSymbol.setHorizontalAlignment(Element.ALIGN_CENTER);
        bulletSymbol.setVerticalAlignment(Element.ALIGN_MIDDLE);
        bulletSymbol.setColspan(1);
        table.addCell(bulletSymbol);
        
        cell.setPhrase(null);
        cell.setColspan(4);
        table.addCell(cell);
        
        //-------------------------------------------------------------------
        PdfPCell cell2 = new PdfPCell();
        cell2.setUseVariableBorders(true);
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setFixedHeight(15);
        
        cell2 = new PdfPCell();
        cell2.setPadding(5);
        cell2.setPhrase(new Phrase("PARENT INVOLVE", font8b));
        cell2.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
        cell2.setColspan(2);
        table.addCell(cell2);
        
        cell2 = new PdfPCell();
        cell2.setPadding(5);
        cell2.setPhrase(new Phrase("TERM 1", font8b));
        cell2.setBorder(Rectangle.BOTTOM);
        cell2.setColspan(2);
        table.addCell(cell2);
        
        cell2 = new PdfPCell();
        cell2.setPadding(5);
        cell2.setPhrase(new Phrase("TERM 2", font8b));
        cell2.setBorder(Rectangle.BOTTOM);
        cell2.setColspan(2);
        table.addCell(cell2);
        
        cell2 = new PdfPCell();
        cell2.setPadding(5);
        cell2.setPhrase(new Phrase("TERM 3", font8b));
        cell2.setBorder(Rectangle.BOTTOM);
        cell2.setColspan(2);
        table.addCell(cell2);
        
        cell2 = new PdfPCell();
        cell2.setPadding(5);
        cell2.setPhrase(new Phrase("TERM 4", font8b));
        cell2.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
        cell2.setColspan(2);
        table.addCell(cell2);
        //-------------------------------------------------------------------
        PdfPCell cell3 = new PdfPCell();
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell3.setFixedHeight(15);
        
        cell3.setPhrase(new Phrase("INFO EVENING", font8b));
        cell3.setColspan(2);
        table.addCell(cell3);
        
        cell3.setPhrase(null);
        cell3.setColspan(2);
        table.addCell(cell3);
        
        cell3.setPhrase(null);
        cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell3.setBorderColorTop(BaseColor.LIGHT_GRAY);
        cell3.setBorderColorLeft(BaseColor.LIGHT_GRAY);
        cell3.setColspan(6);
        table.addCell(cell3);
        //-------------------------------------------------------------------
        PdfPCell cell4 = new PdfPCell();
        cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell4.setFixedHeight(15);
        
        cell4.setPhrase(new Phrase("REPORT DISCUSS", font8b));
        cell4.setColspan(2);
        table.addCell(cell4);
        
        for (int i = 0; i < 4; i++) {
            cell4.setPhrase(null);
            cell4.setColspan(2);
            table.addCell(cell4);
        }
        //-------------------------------------------------------------------
        PdfPCell cell5 = new PdfPCell();
        cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell5.setFixedHeight(15);
        
        cell5.setPhrase(new Phrase("OTHER MEETINGS", font8b));
        cell5.setColspan(2);
        table.addCell(cell5);
        
        for (int i = 0; i < 8; i++) {
            cell5.setPhrase(null);
            cell5.setColspan(1);
            table.addCell(cell5);
        }
        
        //-------------------------------------------------------------------
        PdfPCell cell6 = getCell(2);
        cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
        
        cell6.setPhrase(new Phrase("MESSAGE BOOK SIGNED", font8b));
        table.addCell(cell6);
        for (int i = 0; i < 4; i++) table.addCell(getCell(2));
        
        cell6.setPhrase(new Phrase("WORK BOOK SIGNED", font8b));
        table.addCell(cell6);
        for (int i = 0; i < 4; i++) table.addCell(getCell(2));
        
        cell6.setPhrase(new Phrase("HOMEWORK", font8b));
        table.addCell(cell6);
        for (int i = 0; i < 4; i++) table.addCell(getCell(2));
        
        cell6.setPhrase(new Phrase("OTHER INFO", font8b));
        table.addCell(cell6);
        for (int i = 0; i < 4; i++) table.addCell(getCell(2));
        
        for (int i = 0; i < 10; i++) table.addCell(getCell(2));
        
        addElement(table);
        
    }
    //----------------------------------------------------------------------------

    private void setDiciplineRating() {
        
        float[] columnWidths = {1,1,1,1,1,1,1,1,1,1};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        
        table.addCell(getCell(new Phrase("DISCIPLINE", font8b), 2, 15));
        table.addCell(getCell(new Phrase("TERM 1", font8b), 2));
        table.addCell(getCell(new Phrase("TERM 2", font8b), 2));
        table.addCell(getCell(new Phrase("TERM 3", font8b), 2));
        table.addCell(getCell(new Phrase("TERM 4", font8b), 2));
        
        table.addCell(getCell(new Phrase("GOOD", font8b), 2, 15));
        for (int i = 0; i < 4; i++) {
            table.addCell(getCell(2, 15));
        }
        
        table.addCell(getCell(new Phrase("SATISFACTORY", font8b), 2, 15));
        for (int i = 0; i < 4; i++) {
            table.addCell(getCell(2, 15));
        }
        
        table.addCell(getCell(new Phrase("WEAK", font8b), 2, 15));
        for (int i = 0; i < 4; i++) {
            table.addCell(getCell(2, 15));
        }
        
        table.addCell(getCell(new Phrase("ADSEMT(Days)", font8b), 2, 15));
        for (int i = 0; i < 4; i++) {
            table.addCell(getCell(2, 15));
        }
        
        addElement(table);
    }

    //----------------------------------------------------------------------------
    
    private void setDiciplineObservation() {
        float[] columnWidths = {1,1,1,1,1,1,1,1,1,1,1,1};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        
        //--------------------------------------------------------
        table.addCell(getCell(new Phrase("DISCIPLINE", font8b), 2, 15));
        table.addCell(getCell(new Phrase("TERM 1", font8b), 10));
        //--------------------------------------------------------
        table.addCell(getCell(2, 15));
        table.addCell(getCell(new Phrase("ADJUSTMENT:", font8b), 2, 15));
        table.addCell(getCell(new Phrase("Good", font8b), 8));
        //--------------------------------------------------------
        table.addCell(getCell(2, 15));
        table.addCell(getCell(new Phrase("Neatness/Care:", font8b), 2, 15));
        table.addCell(getCell(new Phrase("Good", font8b), 8));
        //--------------------------------------------------------
        table.addCell(getCell(2, 15));
        table.addCell(getCell(10, 150));
        //--------------------------------------------------------
        addElement(table);
    }
    //----------------------------------------------------------------------------  
    private void setInterventionTermDetails() {
        float[] columnWidths = {1,1,1,1,1,1,1,1,1,1,1,1};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        
        //--------------------------------------------------------
        table.addCell(getCell(new Phrase("Intervention", font8b), 2));
        table.addCell(getCell(new Phrase("YES/NO", font8b), 10, 150));
        
        //--------------------------------------------------------
        table.addCell(getCell(new Phrase("Progress", font7b), 2));
        table.addCell(getCell(new Phrase("Good", font7b), 1));
        table.addCell(getCell(new Phrase("Satisfactory", font7b)));
        table.addCell(getCell());
        table.addCell(getCell(new Phrase("Weak", font7b)));
        table.addCell(getCell(new Phrase("Literacy", font7b)));
        table.addCell(getCell(new Phrase("Numeracy", font7b)));
        table.addCell(getCell(new Phrase("LS", font7b)));
        table.addCell(getCell(new Phrase("FAL", font7b), 3));
        //--------------------------------------------------------
        addElement(table);
    }
    //----------------------------------------------------------------------------
}
