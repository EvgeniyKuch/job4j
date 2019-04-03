package ru.job4j.sqlparse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Parser {
    private final String url;
    private final int offsetBegin;
    private final int offsetEnd;
    private final Map<String, Integer> months;

    public Parser() {
        url = "https://www.sql.ru/forum/job-offers/";
        offsetBegin = 7;
        offsetEnd = 56;
        months = new HashMap<>();
        initMonths();
    }

    private void initMonths() {
        months.put("янв", Calendar.JANUARY);
        months.put("фев", Calendar.FEBRUARY);
        months.put("мар", Calendar.MARCH);
        months.put("апр", Calendar.APRIL);
        months.put("май", Calendar.MAY);
        months.put("июн", Calendar.JUNE);
        months.put("июл", Calendar.JULY);
        months.put("авг", Calendar.AUGUST);
        months.put("сен", Calendar.SEPTEMBER);
        months.put("окт", Calendar.OCTOBER);
        months.put("ноя", Calendar.NOVEMBER);
        months.put("дек", Calendar.DECEMBER);
    }

    public void parse(LinkedHashMap<String, Offer> vacancies, long lastDate) throws IOException {
        boolean nextOffer = true;
        for (int i = 1; nextOffer; i++) {
            Document htmlFile = Jsoup.connect(url + i).get();
            if (htmlFile != null) {
                Elements offers = htmlFile.getElementsByTag("tr");
                for (int j = offsetBegin; nextOffer && j <= offsetEnd; j++) {
                    Elements offer = offers.get(j).getElementsByTag("td");
                    Element nameURLOffer = offer.get(1).selectFirst("a");
                    String nameOffer = nameURLOffer.text();
                    String urlOffer = nameURLOffer.attr("href");
                    long dateOffer = parseDate(offer.get(5).text());
                    nextOffer = dateOffer > lastDate;
                    if (nextOffer && java(nameOffer)) {
                        String textOffer = readTextOffer(urlOffer);
                        Offer vacancy = new Offer(nameOffer, urlOffer, dateOffer, textOffer);
                        vacancies.putIfAbsent(nameOffer, vacancy);
                    }
                }
            }
        }
    }

    private boolean java(String nameOffer) {
        String result = nameOffer.toLowerCase();
        return result.contains("java")
                && !result.contains("javascript")
                && !result.contains("java script");
    }

    private String readTextOffer(String urlOffer) throws IOException {
        Document vacancy = Jsoup.connect(urlOffer).get();
        return vacancy != null ? vacancy.selectFirst(".msgTable")
                .getElementsByClass("msgBody").get(1).text() : "";
    }

    private long parseDate(String date) {
        long result;
        if (date.charAt(0) == 'с') {
            result = parseToday(date);
        } else if (date.charAt(0) == 'в') {
            result = parseYesterday(date);
        } else {
            result = parseNormalDate(date);
        }
        return result;
    }

    private long parseToday(String date) {
        int hour = Integer.parseInt(date.substring(9, 11));
        int minute = Integer.parseInt(date.substring(12));
        Calendar today = new GregorianCalendar();
        today.set(Calendar.HOUR_OF_DAY, hour);
        today.set(Calendar.MINUTE, minute);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today.getTimeInMillis();
    }

    private long parseYesterday(String date) {
        int hour = Integer.parseInt(date.substring(7, 9));
        int minute = Integer.parseInt(date.substring(10));
        Calendar yesterday = new GregorianCalendar();
        int day = yesterday.get(Calendar.DAY_OF_YEAR) - 1;
        yesterday.set(Calendar.DAY_OF_YEAR, day);
        yesterday.set(Calendar.HOUR_OF_DAY, hour);
        yesterday.set(Calendar.MINUTE, minute);
        yesterday.set(Calendar.SECOND, 0);
        yesterday.set(Calendar.MILLISECOND, 0);
        return yesterday.getTimeInMillis();
    }

    private long parseNormalDate(String date) {
        String[] tokens = date.split(" ");
        int day = Integer.parseInt(tokens[0]);
        int month = months.get(tokens[1]);
        int year = Integer.parseInt(tokens[2].substring(0, 2)) + 2000;
        int hour = Integer.parseInt(tokens[3].substring(0, 2));
        int minute = Integer.parseInt(tokens[3].substring(3));
        return new GregorianCalendar(year, month, day, hour, minute)
                .getTimeInMillis();
    }
}
