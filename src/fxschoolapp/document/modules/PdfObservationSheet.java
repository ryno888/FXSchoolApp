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
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import core.Core;
import core.com.pdf.ComPdf;
import static j2html.TagCreator.*;
import j2html.tags.ContainerTag;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryno
 */
public class PdfObservationSheet extends ComPdf{
    private String studentGrade = "Grade R";
    private String studentBirthday = "23-12-1988";
    private String classYear = "2005";
    private String file;
    private char check = '\u2714';
    private char times = '\u2718';
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
                div().withClass("h-20"),
                //-----------------------------------------------------------------
                // parent invlovement hist
                //-----------------------------------------------------------------
                join(get_parent_involvement())
                //-----------------------------------------------------------------
            )
        );
    }
    //----------------------------------------------------------------------------
    public String get_parent_involvement() {
       return table(
            tr(
                td("TICK CODE").withClass("font-weight-bold w-100 border-top border-left border-right").attr("colspan", 10)
            ),
            tr(
                td("Yes/Good").withClass("font-weight-bold border-bottom border-x").attr("colspan", 1),
                td(div().withClass("w-100 h-10px bg-blue")).withClass("border-x border-y").withStyle("font-size:8px").attr("colspan", 1),
                td("Satisfactory").withClass("font-weight-bold border-bottom border-x").attr("colspan", 1),
                td(div().withClass("w-100 h-10px bg-orange")).withClass("border-x border-y").withStyle("font-size:8px").attr("colspan", 1),
                td("Weak/no").withClass("font-weight-bold border-bottom border-x").attr("colspan", 1),
                td(div().withClass("w-100 h-10px bg-red")).withClass("border-x border-y").withStyle("font-size:8px").attr("colspan", 1),
                td().withClass("font-weight-bold border-bottom border-x").attr("colspan", 1),
                td().withClass("font-weight-bold border-x border-y").attr("colspan", 1),
                td().withClass("font-weight-bold border-bottom border-x").attr("colspan", 1),
                td().withClass("font-weight-bold border-x border-y").attr("colspan", 1)
            )            
        ).attr("cellspacing", 0).withClass("hist-table w-100").toString();
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
}
