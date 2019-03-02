package ru.job4j.magnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.magnet.entry.Entry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {
    private Connection connect;
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());

    public StoreSQL(Config config) {
        try {
            this.connect = DriverManager.getConnection(config.get("url"));
            create();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void generate(int size) {
        try (PreparedStatement stmt = connect.prepareStatement(
                "insert into entries(field) values(?);"
        )) {
            connect.setAutoCommit(false);
            for (int i = 1; i <= size; i++) {
                stmt.setInt(1, i);
                stmt.addBatch();
            }
            stmt.executeBatch();
            connect.commit();
        } catch (Exception e) {
            try {
                connect.rollback();
            } catch (Exception e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
        }
    }

    private void create() {
        try (Statement stmt = connect.createStatement()) {
            String sqlCreate =
                    "CREATE TABLE IF NOT EXISTS entries (field integer);";
            String sqlClear =
                    "DELETE FROM entries;";
            stmt.executeUpdate(sqlCreate);
            stmt.executeUpdate(sqlClear);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public List<Entry> load() {
        List<Entry> result = new ArrayList<>();
        try (Statement stmt = connect.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(
                    "SELECT field FROM entries;")) {
                while (rs.next()) {
                    result.add(new Entry(
                                    rs.getInt("field")
                            )
                    );
                }
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
