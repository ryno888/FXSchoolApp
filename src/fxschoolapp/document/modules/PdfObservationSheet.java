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

import com.itextpdf.tool.xml.XMLWorkerHelper;
import core.Core;
import core.com.pdf.ComPdf;
import static j2html.TagCreator.*;
import j2html.tags.ContainerTag;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
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
                                    td("GRADE REPEATED").withClass("font-weight-bold font10").attr("colspan", 4),
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
                
                table(
                    tr(
                        td(
                            table(
                                tr(
                                    td("INTERVENTION hist.").withClass("font-weight-bold font10").attr("colspan", 3),
                                    td("YR/GR").withClass("font10 text-center").attr("colspan", 1),
                                    td("REMARK").withClass("font10 text-center").attr("colspan", 4)
                                )
                            ).withClass("w-100")
                        ).attr("colspan", 8),
                        td("Current Year/Remark").attr("colspan", 4)
                    )
                ).attr("cellspacing", 0).withClass("main w-100")
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
