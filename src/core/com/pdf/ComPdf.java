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
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import static j2html.TagCreator.body;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.style;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;
import j2html.tags.ContainerTag;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Ryno
 */
public abstract class ComPdf {
    //--------------------------------------------------------------------------
    public ContainerTag buffer;
    //--------------------------------------------------------------------------
    public abstract ContainerTag getBuffer();
    //--------------------------------------------------------------------------
    public void generate(String f) {
        buffer = getBuffer();
        try {
            System.out.println(buffer.toString());
            try (OutputStream file = new FileOutputStream(new File(f))) {
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, file);
                document.open();
                InputStream is = new ByteArrayInputStream(buffer.toString().getBytes());
                XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
                document.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //--------------------------------------------------------------------------
}
