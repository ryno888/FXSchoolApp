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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import core.Core;
import core.com.pdf.ComPdf;
import static j2html.TagCreator.*;
import j2html.tags.ContainerTag;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryno
 */
public class PdfObservationSheet extends ComPdf{
    private String studentGrade = "Grade R";
    private String classYear = "2005";
    //--------------------------------------------------------------------------
    public PdfObservationSheet(){
        super();
        super.setMarginTop(10);
        super.setMarginBottom(10);
    };
    //--------------------------------------------------------------------------
    @Override
    public void createPdf() throws IOException, DocumentException {
        document.add(new Paragraph("With 3 columns:"));
            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(5);
            table.setWidths(new int[]{3, 3, 3, 3});
            table.setWidthPercentage(100);
            
            
            PdfPCell cell = new PdfPCell(new Phrase("OBSERVATION SHEET"));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingBottom(5);
            cell.setPaddingTop(5);
            table.addCell(cell);
            
            
            table.getDefaultCell().setColspan(1);
            table.setWidths(new int[]{7, 1, 1, 3});
            
            cell = new PdfPCell(new Phrase("Name"));
            cell.setPaddingBottom(5);
            cell.setPaddingTop(5);
            table.addCell(cell);
            
            table.addCell("Grade");
            table.addCell("Year");
            table.addCell("");
            
            table.getDefaultCell().setColspan(1);
            table.setWidths(new int[]{7, 1, 1, 3});
            table.addCell("");
            table.addCell(studentGrade);
            table.addCell(classYear);
            table.addCell("");
            
        document.add(table);
    }
    //----------------------------------------------------------------------------
    public ContainerTag getBuffer() {
        
        return html(
            head(
                style(getStyle())
            ),
            body(
                table(
                    tr(
                        td("OBSERVATION SHEET").withClass("font12 text-center")
                    ).attr("colspan", 3),
                    tr(
                        td("NAME").withClass("w-80"),
                        td("GRADE").withClass("w-10"),
                        td("YEAR").withClass("w-10")
                    )
                ).attr("cellspacing", 0).attr("cellpadding", 2).withClass("w-100")
            )
        );
    }
    //----------------------------------------------------------------------------
    public String getStyle() {
        StringBuilder cssStr = new StringBuilder();
        try {
            URL css = Core.loadResource("assets/css/onservation.css");

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(css.openStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    cssStr.append(inputLine);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PdfObservationSheet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cssStr.toString();
    }
    //----------------------------------------------------------------------------
}
