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
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import core.Core;
import core.com.pdf.ComPdf;
import static j2html.TagCreator.*;
import j2html.tags.ContainerTag;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
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
    private String file;
    //--------------------------------------------------------------------------
    public PdfObservationSheet(){
        super();
        super.setMarginTop(10);
        super.setMarginBottom(10);
    };
    //--------------------------------------------------------------------------
    @Override
    public void createPdf() {
        try {
            InputStream is = new ByteArrayInputStream(getBuffer().toString().getBytes());
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                        td(
                            div("OBSERVATION SHEET").withClass("font12 text-center p-10")
                        ).attr("colspan", 3).withClass("no-border-left")
                    ).withClass("no-border-left"),
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
