package ru.job4j.magnet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXPars extends DefaultHandler {

    private long sum;

    public long getSum() {
        return sum;
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        String field = attributes.getValue("field");
        sum += field != null ? Integer.parseInt(field) : 0;
    }

    public void parser(File source) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(source, this);
    }
}
