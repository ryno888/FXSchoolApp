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

import core.com.pdf.ComPdf;
import static j2html.TagCreator.*;
import j2html.tags.ContainerTag;

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
                title("Title"),
                style(getStyle())
            ),
            body(
                table(
                    tr(
                        td("OBSERVATION SHEET").withClass("font12 text-center")
                    )
                ).attr("cellspacing", 0).withClass("table-border width-100-perc"),
                table(
                    tr(
                        td("NAME").attr("colspan", 6),
                        td("GRADE").attr("colspan", 1),
                        td("YEAR").attr("colspan", 1)
                    )
                ).attr("cellspacing", 0).attr("cellpadding", 2).withClass("width-100-perc")
            )
        );
    }
    //----------------------------------------------------------------------------
    public String getStyle() {
        return 
            "table { border-collapse:collapse;}" +
            "th{ font-size:12px }" +
            "td, th {" +
            "    border: 1px solid #5f5f5f;" +
            "    text-align: left;" +
            "    font-size:8px;" +
            "    padding: 8px;" +
            "    vertical-align:middle;" +
            "}" +
            "" +
            ".no-border-left{border-left: none !important;}" +
            ".no-border-right{border-right: none !important;}" +
            ".no-border{border: none;}" +
            ".no-border-bottom{border-bottom: none;}" +
            "" +
            ".font12{ font-size: 12px; }" +
            ".font8{ font-size: 8px; }" +
            ".text-center{ text-align: center; }" +
            ".line-heght-14{ line-height: 14px; }" +
            "" +
            ".width-100-perc{ width:100%; }" +
            ".width-64-perc{ width:64%; }" +
            ".width-40-perc{ width:40%; }" +
            ".width-30-perc{ width:30%; }" +
            ".width-25-perc{ width:25%; }" +
            ".width-20-perc{ width:20%; }" +
            ".width-15-perc{ width:15%; }" +
            ".width-12-perc{ width:12%; }" +
            ".width-10-perc{ width:10%; }" +
            ".width-5-perc{ width:5%; }" +
            "" +
            ".border-black {border: 1px solid black;}" +
            ".color-grey {color:#cccccc;}" +
            ".color-green {color:#52da34;}" +
            ".color-red { color: #d40202;}" +
            ".color-black { color: black;}" +
            "" +
            ".text-bold{ font-weight: bold;}" +
            "" +
            ".table-border{ border: 1px solid #5f5f5f; }" +
            ".td-border{ border: 1px solid #5f5f5f; }" +
            ".td-color-grey{ background-color:#5f5f5f; }" +
            ".td-border-left{border-left: 1px solid #5f5f5f;}" +
            ".td-border-right{border-right: 1px solid #5f5f5f;}" +
            ".td-border-bottom{border-bottom: 1px solid #5f5f5f;}" +
            ".td-border-top{border-top: 1px solid #5f5f5f;}";
    }
    //----------------------------------------------------------------------------
}