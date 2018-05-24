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

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import core.Core;
import core.com.pdf.ComPdf;
import static j2html.TagCreator.*;
import j2html.tags.ContainerTag;
import java.io.BufferedReader;
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

    //----------------------------------------------------------------------------
    @Override
    public ContainerTag getBuffer() {
        
        return html(
            head(
                style(getStyle())
            ),
            body(
                table(
                    tr(
                        td("OBSERVATION SHEET").withClass("font12 text-center")
                    )
                ).attr("cellspacing", 0).withClass("border-1-solid-dark-grey w-100"),
                table(
                    tr(
                        td("NAME").withClass("w-80"),
                        td("GRADE").withClass("w-10"),
                        td("YEAR").withClass("w-10")
                    )
                ).attr("cellspacing", 0).attr("cellpadding", 2).withClass("border-1-solid-dark-grey w-100")
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
