package ru.job4j.sqlparse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class StoreSQL implements AutoCloseable {

    private Connection connection;
    private long lastDate;
    private Set<String> oldsNames;
    private Config config;
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());

    public StoreSQL(Config config) {
        this.config = config;
        this.init();
    }

    private void init() {
        try {
            Class.forName(config.get("jdbc.driver"));
            this.connection = DriverManager.getConnection(
                    config.get("jdbc.url"),
                    config.get("jdbc.username"),
                    config.get("jdbc.password")
            );
            preInit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void preInit() throws SQLException {
        try (Statement st = this.connection.createStatement()) {
            createTableIfNotExist(st);
            setLastDate(st);
            readOlds(st);
        }
    }

    private void createTableIfNotExist(Statement st) throws SQLException {
        st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS vacancy ("
                        + " id      SERIAL PRIMARY KEY,"
                        + " name    VARCHAR(255),"
                        + " text    VARCHAR,"
                        + " link    VARCHAR(255),"
                        + " date    TIMESTAMP"
                        + " );"
        );
    }

    private void setLastDate(Statement st) throws SQLException {
        try (ResultSet rs = st.executeQuery(
                "SELECT date FROM vacancy"
                        + " ORDER BY date DESC"
                        + " LIMIT 1"
                        + " ;"
        )) {
            lastDate = rs.next() ? rs.getTimestamp("date").getTime()
                    : beginOfCurrentYear();
        }
    }

    private long beginOfCurrentYear() {
        Calendar result = new GregorianCalendar();
        result.set(Calendar.MONTH, Calendar.JANUARY);
        result.set(Calendar.DAY_OF_MONTH, 1);
        result.set(Calendar.HOUR_OF_DAY, 0);
        result.set(Calendar.MINUTE, 0);
        result.set(Calendar.SECOND, 0);
        result.set(Calendar.MILLISECOND, 0);
        return result.getTimeInMillis();
    }

    private void readOlds(Statement st) throws SQLException {
        oldsNames = new HashSet<>();
        try (ResultSet rs = st.executeQuery("SELECT name FROM vacancy;")) {
            while (rs.next()) {
                oldsNames.add(rs.getString("name"));
            }
        }
    }

    public long getLastDate() {
        return lastDate;
    }

    public void record(LinkedHashMap<String, Offer> vacancies) {
        try (PreparedStatement st = this.connection.prepareStatement(
                "INSERT INTO vacancy(name, link, text, date) VALUES(?, ?, ?, ?);"
        )) {
            connection.setAutoCommit(false);
            for (Offer offer : vacancies.values()) {
                if (!oldsNames.contains(offer.getName())) {
                    st.setString(1, offer.getName());
                    st.setString(2, offer.getLink());
                    st.setString(3, offer.getText());
                    st.setTimestamp(4, new Timestamp(offer.getDate()));
                    st.addBatch();
                }
            }
            st.executeBatch();
            connection.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            this.connection.close();
        }
    }
}
