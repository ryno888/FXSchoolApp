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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import j2html.tags.ContainerTag;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    
    //--------------------------------------------------------------------------
    public ComPdf(){
        document = new Document();
    };
    //--------------------------------------------------------------------------
    public abstract void createPdf() throws IOException, DocumentException;
    //--------------------------------------------------------------------------
    public void add(Element element){
        try {
            document.add(element);
        } catch (DocumentException ex) {
            Logger.getLogger(ComPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //--------------------------------------------------------------------------
    public void generate(String f) {
        try {
            setDocumentMargins(marginLeft, marginRight, marginTop, marginBottom);
            writer = PdfWriter.getInstance(document, new FileOutputStream(f));
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
}
