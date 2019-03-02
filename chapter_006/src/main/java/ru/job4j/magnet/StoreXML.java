package ru.job4j.magnet;

import ru.job4j.magnet.entry.Entries;
import ru.job4j.magnet.entry.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class StoreXML {

    private File file;

    public StoreXML(File file) {
        this.file = file;
    }

    public void save(List<Entry> entries) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(
                new Entries(entries), this.file);
    }
}
