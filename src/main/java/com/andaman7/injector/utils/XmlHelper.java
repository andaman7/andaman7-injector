package com.andaman7.injector.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;

/**
 * A utility class to deal with XML files.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 09/02/2015.
 */
public class XmlHelper {

    /**
     * Returns a {@link org.w3c.dom.Document} from an XML file.
     * @param file the XML file
     * @return the XML document
     * @throws IOException if there was an error with the file
     * @throws SAXException if there was an error while parsing the XML document
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    public static Document getDocument(File file)
            throws IOException, SAXException, ParserConfigurationException {

        return getDocument(new FileInputStream(file));
    }

    /**
     * Returns a {@link org.w3c.dom.Document} from an {@link java.io.InputStream}.
     * @param inputStream the stream from which getting the XML content
     * @return the XML document or null if 
     * @throws IOException if there was an error while getting the XML from the stream
     * @throws SAXException if there was an error while parsing the XML document
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    public static Document getDocument(InputStream inputStream)
            throws IOException, SAXException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        return builder.parse(inputStream);
    }

    /**
     * Converts an XML {@link org.w3c.dom.Document} to a string.
     *
     * @param doc the XML document
     * @return the string representation of the XML document
     */
    public static String documentToString(Document doc) {
        
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));

            return writer.getBuffer().toString();

        } catch(Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }

        return "";
    }

    /**
     * Returns an {@link javax.xml.xpath.XPathExpression} from a string.
     *
     * @param xpathString the XPath string
     * @return the {@link javax.xml.xpath.XPathExpression}
     * @throws XPathExpressionException if the given XPath expression is incorrect
     */
    public static XPathExpression getXPathExpression(String xpathString)
            throws XPathExpressionException {

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        return xpath.compile(xpathString);
    }

    /**
     * Writes an XML document to a file.
     *
     * @param doc the XML document
     * @param file the destination file
     */
    public static void writeDocumentToFile(Document doc, File file) {

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);

            transformer.transform(source, result);

        } catch(TransformerException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
