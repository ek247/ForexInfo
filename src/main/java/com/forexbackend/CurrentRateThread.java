package com.forexbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by worri on 6/20/2016.
 * Scrapes current forex rates from fxcm every minute and stores it in a database.
 */
public class CurrentRateThread implements Runnable {

    private CurrentRateRepository repo;
    private long systime;

    //Take in a repository that is autowired in later. Set systime to be negative to ensure it runs immediately
    public CurrentRateThread(CurrentRateRepository repo)
    {
        this.repo=repo;
        systime = -60000;
    }

    public void run() {

        while (true) {
            //Run every minute or so
            if (System.currentTimeMillis() - systime >= 60000) {
                systime = System.currentTimeMillis();

                try {
                    //Get url from
                    URL url = new URL("http://rates.fxcm.com/RatesXML");
                    URLConnection conn = url.openConnection();
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.parse(conn.getInputStream());

                    NodeList nList = doc.getElementsByTagName("Rate");
                    ArrayList<Float> ask = new ArrayList<Float>();
                    ArrayList<Float> bid = new ArrayList<Float>();
                    ArrayList<Integer> direction = new ArrayList<Integer>();

                    for (int i = 0; i < 12; i++) {
                        Node node = nList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            bid.add(new Float(element.getElementsByTagName("Bid").item(0).getTextContent()));
                            ask.add(new Float(element.getElementsByTagName("Ask").item(0).getTextContent()));
                            direction.add(new Integer(element.getElementsByTagName("Direction").item(0).getTextContent()));
                        }
                    }

                    CurrentRates rates = new CurrentRates(bid, ask, direction, new Timestamp(Calendar.getInstance().getTimeInMillis()));
                    System.out.println("Saving!");
                    repo.save(rates);
                    System.out.println(rates);


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void printDoc(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = tf.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        try {
            transformer.transform(new DOMSource(doc),
                    new StreamResult(new OutputStreamWriter(System.out, "UTF-8")));
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}


