/*
 * Class 
 * @filename ComPdf 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 23 May 2018 * 
 */
package core.com.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import fxschoolapp.document.modules.PdfObservationSheet;
import j2html.tags.ContainerTag;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryno
 */
public abstract class ComPdf {

    public Document document;
    private float marginTop = 15;
    private float marginBottom = 15;
    private float marginLeft = 15;
    private float marginRight = 15;
    public PdfWriter writer;
    public BaseFont symbolFont;
    
    public Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
    public Font font8b = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    public Font font7 = FontFactory.getFont(FontFactory.HELVETICA, 7);
    public Font font7b = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
    public Font font5 = FontFactory.getFont(FontFactory.HELVETICA, 5);
    public Font font5b = FontFactory.getFont(FontFactory.HELVETICA, 5, Font.BOLD);
    
    //--------------------------------------------------------------------------
    public ComPdf(){
        document = new Document();
        try {
            symbolFont = BaseFont.createFont("assets/fonts/FreeSerif/FreeSerif.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(ComPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    //--------------------------------------------------------------------------
    public abstract void createPdf() throws IOException, DocumentException;
    //--------------------------------------------------------------------------
    public abstract String getFilename();
    //--------------------------------------------------------------------------
    public void add(Element element){
        try {
            document.add(element);
        } catch (DocumentException ex) {
            Logger.getLogger(ComPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //--------------------------------------------------------------------------
    public void generate(String path) {
        try {
            setDocumentMargins(marginLeft, marginRight, marginTop, marginBottom);
            writer = PdfWriter.getInstance(document, new FileOutputStream(path+"/"+getFilename().toLowerCase()));
            document.open();
            createPdf();
            document.close();
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(ComPdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ComPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //--------------------------------------------------------------------------
    public PdfPCell createCell(String content, float borderWidth, int colspan, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setBorderWidth(borderWidth);
        cell.setColspan(colspan);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
    //--------------------------------------------------------------------------
    private void setDocumentMargins(float left, float right, float top, float bottom) {
        document.setMargins(left, right, top, bottom);
    }
    //--------------------------------------------------------------------------
    public void setMarginTop(float top) {
        this.marginTop = top;
    }
    //--------------------------------------------------------------------------
    public void setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
    }
    //--------------------------------------------------------------------------
    public void setMarginLeft(float marginLeft) {
        this.marginLeft = marginLeft;
    }
    //--------------------------------------------------------------------------
    public void setMarginRight(float marginRight) {
        this.marginRight = marginRight;
    }
    //--------------------------------------------------------------------------
    public void addHTML(String html) {
        try {
            InputStream is = new ByteArrayInputStream(html.getBytes());
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
        } catch (IOException ex) {
            Logger.getLogger(ComPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //--------------------------------------------------------------------------
    public void addElement(Element element) {
        try {
            document.add(element);
        } catch (DocumentException ex) {
            Logger.getLogger(PdfObservationSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //----------------------------------------------------------------------------
    public Paragraph getSymbol(char symbol) {
        Font f = new Font(symbolFont, 8);
        return new Paragraph(Character.toString(symbol), f);
    }
    //----------------------------------------------------------------------------
    public PdfPCell getCell(int colspan, float height){
        return getCell(null, colspan, height, 0);
    }
    //----------------------------------------------------------------------------
    public PdfPCell getCell(int colspan){
        return getCell(null, colspan, 15, 0);
    }
    //----------------------------------------------------------------------------
    public PdfPCell getCell(Phrase p, int colspan){
        return getCell(p, colspan, 15, 0);
    }
    //----------------------------------------------------------------------------
    public PdfPCell getCell(Phrase p){
        return getCell(p, 1, 15, 0);
    }
    //----------------------------------------------------------------------------
    public PdfPCell getCell(){
        return getCell(null, 1, 15, 0);
    }
    //----------------------------------------------------------------------------
    public PdfPCell getCell(Phrase p, int colspan, float height){
        return getCell(p, colspan, height, 0);
    }
    //----------------------------------------------------------------------------
    public PdfPCell getCell(Phrase p, int colspan, float height, int padding){
        PdfPCell cell = new PdfPCell();
        cell.setFixedHeight(height);
        cell.setPhrase(p);
        cell.setColspan(colspan);
        if(padding > 0) cell.setPadding(padding);
        
        return cell;
    }
    //--------------------------------------------------------------------------
}
