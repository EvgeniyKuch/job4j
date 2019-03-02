package ru.job4j.magnet;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ConvertXSQT {

    public void convert(File source, File dest, File scheme) throws TransformerException {
        StreamSource sourceStream = new StreamSource(source);
        StreamResult destStream = new StreamResult(dest);
        StreamSource schemeStream = new StreamSource(scheme);
        convert(sourceStream, destStream, schemeStream);
    }

    public void convert(StreamSource source, StreamResult dest, StreamSource scheme)
            throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(scheme);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.transform(source, dest);
    }
}
