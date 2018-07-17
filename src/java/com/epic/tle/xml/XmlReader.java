/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.xml;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
/**
 *
 * @author kreshan
 */
public class XmlReader {

    String xmlFileName;

    public XmlReader(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public String getTleConfigurations(String element) throws Exception { //to get home values
        String value = "";
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(xmlFileName));

            // normalize text representation
            doc.getDocumentElement().normalize();

            NodeList listOfSPRMs = doc.getElementsByTagName("config");
            int totalSPRMs = listOfSPRMs.getLength();

            for (int i = 0; i < totalSPRMs; i++) {
                Node firstSPRMNode = listOfSPRMs.item(i);

                if (firstSPRMNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element firstSPRAMElement = (Element) firstSPRMNode;

                    if (firstSPRAMElement.getElementsByTagName(element) != null) {

                        NodeList hostList = firstSPRAMElement.getElementsByTagName(element);
                        Element hostElement = (Element) hostList.item(0);
                        NodeList textHostList = hostElement.getChildNodes();

                        if ((textHostList.item(0)) != null) {
                            value = ((Node) textHostList.item(0)).getNodeValue().trim();
                        }
                    }
                }//End if

            }//End for 	

        } catch (Exception ex) {            
            throw ex;
        }
        return value;
    }
    
    public void saveTleConfigurations(String element, String value) throws Exception {
        try{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(xmlFileName));

        Element rootElement = doc.getDocumentElement();
        NodeList list = rootElement.getElementsByTagName(element);
        if (list.item(0).getNodeName().equals(element)) {
            NodeList valiveList = list.item(0).getChildNodes();
            ((Text) valiveList.item(0)).setData(value);
        }

        DOMSource source = new DOMSource(doc);
        File file = new File(xmlFileName);
        Result result = new StreamResult(file);
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(source, result);
        
        }catch(Exception ex){
            throw ex;
            //LogFileCreator.writErrorTologs(ex);
        }
    }
}
