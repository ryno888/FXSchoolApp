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

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import core.Core;
import core.com.pdf.ComPdf;
import static j2html.TagCreator.*;
import j2html.tags.ContainerTag;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Ryno
 */
public class PdfObservationSheet extends ComPdf{
    private String studentGrade = "Grade R";
    private String studentBirthday = "23-12-1988";
    private String classYear = "2005";
    private String file;
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
        super.setMarginTop(15);
        super.setMarginLeft(15);
        super.setMarginRight(15);
        super.setMarginBottom(15);
    };
    //--------------------------------------------------------------------------
    @Override
    public void createPdf() {
        try {
            addHTML(getBuffer().toString());
            set_parent_involvement();
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
                //-----------------------------------------------------------------
                table(
                    tr(
                        td(
                            div("OBSERVATION SHEET").withClass("font12 text-center p-5 font-weight-bold")
                        ).attr("colspan", 12)
                    ),
                    tr(
                        td("NAME").withClass("font-weight-bold").attr("colspan", 6),
                        td(p("GRADE").withClass("font-weight-bold"),p(studentGrade)).attr("colspan", 1),
                        td(p("YEAR").withClass("font-weight-bold"),p(classYear)).attr("colspan", 1),
                        td("").attr("colspan", 4)
                    ),
                    tr(
                        td("BIRTH DATE").withClass("font-weight-bold").attr("colspan", 4),
                        td(
                            table(
                                tr(
                                    td("PREV. GRADE").withClass("font-weight-bold font10").attr("colspan", 4),
                                    td("PP").withClass("font10 text-center").attr("colspan", 2),
                                    td("R").withClass("font10 text-center").attr("colspan", 2),
                                    td("1").withClass("font10 text-center").attr("colspan", 2),
                                    td("2").withClass("font10 text-center").attr("colspan", 2),
                                    td("3").withClass("font10 text-center").attr("colspan", 2)
                                )
                            ).withClass("w-100")
                        ).attr("colspan", 4),
                        td(div("PREVIOUS SCHOOL").withClass("fl font-weight-bold")).attr("colspan", 4)
                    ),
                    tr(
                        td(studentBirthday).withClass("font-weight-bold").attr("colspan", 4),
                        td(
                            table(
                                tr(
                                    td("GRADE REPEATED").withClass("font-weight-bold font10 p-5").attr("colspan", 4),
                                    td("1").withClass("font10 text-center").attr("colspan", 2),
                                    td("2").withClass("font10 text-center").attr("colspan", 2),
                                    td("3").withClass("font10 text-center").attr("colspan", 2)
                                )
                            ).withClass("w-100")
                        ).attr("colspan", 4),
                        td().attr("colspan", 4)
                    )
                ).attr("cellspacing", 0).withClass("main w-100"),
                    
                //-----------------------------------------------------------------
                div().withClass("h-20"),
                //-----------------------------------------------------------------
                // intervention hist
                //-----------------------------------------------------------------
                join(get_intervention_history()),
                //-----------------------------------------------------------------
                div().withClass("h-20")
                //-----------------------------------------------------------------
            )
        );
    }
    //----------------------------------------------------------------------------
    public void set_parent_involvement() {
        float[] columnWidths = {1,1,1,1,1,1,1,1,1,1,1,1};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        
        PdfPCell cell = new PdfPCell(new Phrase("TICK CODE"));
        cell.setFixedHeight(20);
        cell.setColspan(12);
        table.addCell(cell);
        
        PdfPCell cell1 = new PdfPCell(new Phrase("Yes/Good"));
        cell1.setFixedHeight(20);
        cell1.setColspan(1);
        table.addCell(cell1);
        
        
        PdfPCell cell2 = new PdfPCell(getSymbol(check));
        cell2.setFixedHeight(20);
        cell2.setColspan(1);
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell(new Phrase("Weak/no"));
        cell3.setFixedHeight(20);
        cell3.setColspan(1);
        table.addCell(cell3);
        
        cell2 = new PdfPCell(getSymbol(times));
        cell2.setFixedHeight(20);
        cell2.setColspan(1);
        table.addCell(cell2);
        
        PdfPCell cell5 = new PdfPCell(new Phrase("Satisfactory"));
        cell5.setFixedHeight(20);
        cell5.setColspan(1);
        table.addCell(cell5);
        
        cell2 = new PdfPCell(getSymbol(bullet));
        cell2.setFixedHeight(20);
        cell2.setColspan(1);
        table.addCell(cell2);
        
        PdfPCell cell7 = new PdfPCell();
        cell7.setFixedHeight(20);
        cell7.setColspan(1);
        table.addCell(cell7);
        
        PdfPCell cell8 = new PdfPCell();
        cell8.setFixedHeight(20);
        cell8.setColspan(1);
        table.addCell(cell8);
        
        PdfPCell cell9 = new PdfPCell();
        cell9.setFixedHeight(20);
        cell9.setColspan(1);
        table.addCell(cell9);
        
        PdfPCell cell10 = new PdfPCell();
        cell10.setFixedHeight(20);
        cell10.setColspan(1);
        table.addCell(cell10);
        
        PdfPCell cell11 = new PdfPCell();
        cell11.setFixedHeight(20);
        cell11.setColspan(1);
        table.addCell(cell11);
        
        PdfPCell cell12 = new PdfPCell();
        cell12.setFixedHeight(20);
        cell12.setColspan(1);
        table.addCell(cell12);
        
        addElement(table);
    }
    //----------------------------------------------------------------------------
    public String get_intervention_history() {
       return table(
            tr(
                td("INTERVENTION hist.").withClass("font-weight-bold w-20 border-y border-left").attr("colspan", 2),
                td("YR/GR").withClass("font-weight-bold w-10 border-y").attr("colspan", 1),
                td("REMARK").withClass("font-weight-bold w-30 border-y").attr("colspan", 4),
                td("Current Year/Remark").withClass("font-weight-bold w-40 border-y border-x").attr("colspan", 5)
            ),
            tr(
                td("CLASS TUTORING").withClass("font-weight-bold border").attr("colspan", 2),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4)
            ),
            tr(
                td("OT").withClass("font-weight-bold border").attr("colspan", 2),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4)
            ),
            tr(
                td("REM").withClass("font-weight-bold border").attr("colspan", 2),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4)
            ),
            tr(
                td("LANGUAGE/SPEECH").withClass("font-weight-bold border").attr("colspan", 2),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4)
            ),
            tr(
                td("PSYCHOLOGIST").withClass("font-weight-bold border").attr("colspan", 2),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4)
            ),
            tr(
                td("SOCIAL/WELFARE").withClass("font-weight-bold border").attr("colspan", 2),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4)
            ),
            tr(
                td("MEDICAL").withClass("font-weight-bold border").attr("colspan", 2),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4)
            ),
            tr(
                td("OTHER").withClass("font-weight-bold border").attr("colspan", 2),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4)
            ),
            tr(
                td().withClass("font-weight-bold border").attr("colspan", 2),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4)
            ),
            tr(
                td("LEARNSUPP. TEAM").withClass("font-weight-bold border").attr("colspan", 2),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4),
                td().withClass("font-weight-bold border").attr("colspan", 1),
                td().withClass("font-weight-bold border").attr("colspan", 4)
            )
        ).attr("cellspacing", 0).withClass("hist-table w-100").toString();
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
    public Paragraph getSymbol(char symbol) {
        Paragraph p = null;
        try {
            BaseFont bf = BaseFont.createFont("assets/fonts/FreeSerif/FreeSerif.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font f = new Font(bf, 12);
            p = new Paragraph(Character.toString(symbol), f);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(PdfObservationSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
    }
    //----------------------------------------------------------------------------
}
