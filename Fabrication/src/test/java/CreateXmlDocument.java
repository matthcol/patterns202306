import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.io.Writer;

public class CreateXmlDocument {
    @Test
    void createXmlDocument() throws ParserConfigurationException, TransformerException {
        // fabriquer le document XML
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        // fabriquer les noeuds du document xml en utilisant
        // le document comme fabrique
        var rootElement = document.createElement("cities");
        document.appendChild(rootElement);
        // 1st city
        var cityElement = document.createElement("city");
        cityElement.setAttribute("name", "Toulouse");
        rootElement.appendChild(cityElement);
        // 2nd city
        var cityElement2 = document.createElement("city");
        cityElement2.setAttribute("name", "Marseille");
        rootElement.appendChild(cityElement2);
        // un element note avec texte
        var noteElement = document.createElement("note");
        var noteText = document.createTextNode("Cities I like in France");
        noteElement.appendChild(noteText);
        rootElement.appendChild(noteElement);

        // pretty print XML document avec un XML transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", 3);
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(out));
        String xmlPrettyPrinted = out.toString();
        System.out.println(xmlPrettyPrinted);
    }
}
