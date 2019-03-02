package ru.job4j.magnet.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.magnet.Config;
import ru.job4j.magnet.ConvertXSQT;
import ru.job4j.magnet.StoreSQL;
import ru.job4j.magnet.StoreXML;
import ru.job4j.magnet.SAXPars;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.InputStream;

public class Main {

    private static final int SIZE = 1_000_000;
    private static final Logger LOG = LogManager.getLogger(Main.class.getName());

    private static final File ENTRIES = new File("target/entries.xml");
    private static final File ENTRIES_CONVERT = new File("target/entriesConvert.xml");
    private static final String SCHEME = "scheme.xslt";

    public static void main(String[] args) {
            //0. Сделать install в корне проекта или создать папку target в корне проекта
        long begin = System.currentTimeMillis();
            //1. Создаём базу или подключаемся к существующей
        try (StoreSQL storeSQL = new StoreSQL(new Config());
             InputStream in = Main.class
                     .getClassLoader()
                     .getResourceAsStream(SCHEME)) {

            // 2. Генерируем в базе записи
            storeSQL.generate(SIZE);

            // 3. Генерация XML из данных базы.
            new StoreXML(ENTRIES).save(storeSQL.load());

            // 4. Преобразовать полученный файл из
            //    пункта 3 в файл другого XML формата через XSTL.
            StreamSource scheme = new StreamSource(in);
            StreamSource source = new StreamSource(ENTRIES);
            StreamResult dest = new StreamResult(ENTRIES_CONVERT);
            new ConvertXSQT().convert(source, dest, scheme);

            // 5. Распарсить выходной файл из пункта 4
            //    и вывести арифметическую сумму значений
            //    всех атрибутов field в консоль.
            SAXPars saxPars = new SAXPars();
            saxPars.parser(ENTRIES_CONVERT);
            System.out.println("Result: " + saxPars.getSum());
            System.out.println("Expect: " + (long) (1 + SIZE) * SIZE / 2);
            System.out.println("Duration: " + (System.currentTimeMillis() - begin));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
