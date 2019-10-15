
package CATChinaRetail.TestAutomation.Core;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ReadXML {

	public static String NodeName(String ElementName)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		String Value = null;
		// XML Read Options
		File fXmlFile = new File("./src/main/resources/Elements.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(fXmlFile);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("//*[local-name()='" + ElementName + "']");
		Value = expr.evaluate(doc);

		return Value;

	}

}
