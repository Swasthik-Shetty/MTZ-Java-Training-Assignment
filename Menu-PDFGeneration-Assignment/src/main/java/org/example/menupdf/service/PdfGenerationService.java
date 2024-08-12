package org.example.menupdf.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.fop.apps.*;
import org.example.menupdf.dto.OrderInfoDTO;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class PdfGenerationService {
    public void createPdf() {
        try {
            InputStream xmlFile = new FileInputStream("Menu-PDFGeneration-Assignment/src/main/resources/order.xml");
            InputStream xsltFile = PdfGenerationService.class.getClassLoader().getResourceAsStream("stylesheet.xsl");

            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();


            OutputStream out = new FileOutputStream("Menu-PDFGeneration-Assignment/src/main/resources/order_details.pdf");
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            Source src = new StreamSource(xmlFile);
            Result res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(src, res);
        } catch (TransformerException | IOException | FOPException e) {
            System.err.println("Error generating PDF: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void DTOToXml(OrderInfoDTO orderInfoDTO) {


        try {
            XmlMapper xmlMapper = new XmlMapper();
            OutputStream file = new FileOutputStream("Menu-PDFGeneration-Assignment/src/main/resources/order.xml");
            xmlMapper.writeValue(file, orderInfoDTO);

            // Output XML string

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
