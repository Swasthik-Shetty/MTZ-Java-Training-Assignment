package com.example.pdfgen.service;


import org.apache.fop.apps.*;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class PdfGenerationServiceImpl implements PdfGenerationService {
    @Override
    public void createPdf() {
        try  {
        InputStream xmlFile = PdfGenerationServiceImpl.class.getClassLoader().getResourceAsStream("input.xml");
        InputStream xsltFile = PdfGenerationServiceImpl.class.getClassLoader().getResourceAsStream("stylesheet.xsl");

        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();


            OutputStream out = new FileOutputStream("PDF-Generation\\src\\main\\resources\\output.pdf");
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
}
