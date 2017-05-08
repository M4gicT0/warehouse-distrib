package warehouse.utils;

import warehouse.model.BoxType;
import warehouse.model.Palette;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Created by transpalette on 5/8/17.
 */
public class DatabaseManager {

    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse_db", "warehouse", "warehouse");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertPalette(Palette palette, int aisleNumber, int[][] cell) {
        //insert each box with its palette id
        //insert palette with aisle ID

        return false;
    }

    public boolean fetchPalette(BoxType type) {
        return false;
    }

    public boolean fetchPaletteAt(int aisleNumber, int[][] cell) {
        return false;
    }

    public void createTables() throws SQLException {
        String qry = "CREATE TABLE AISLES(" +
                "id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY);";

        PreparedStatement statement = connection.prepareStatement(qry);
        statement.execute();

        qry = "CREATE TABLE PALETTES(" +
                "id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                "aisle_id INT UNSIGNED NOT NULL," +
                "x INT UNSIGNED NOT NULL," +
                "y INT UNSIGNED NOT NULL," +
                "FOREIGN KEY(aisle_id) REFERENCES AISLES(id) ON DELETE CASCADE);";

        statement = connection.prepareStatement(qry);
        statement.execute();

        qry = "CREATE TABLE BOXES(" +
                "id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                "palette_id INT UNSIGNED NOT NULL," +
                "type ENUM('COTTON', 'FOOD', 'WOOD', 'PAINT') NOT NULL," +
                "FOREIGN KEY(palette_id) REFERENCES PALETTES(id) ON DELETE CASCADE);";

        statement = connection.prepareStatement(qry);
        statement.execute();
    }

    public void dropTables() throws SQLException {
        String qry = "DROP TABLE IF EXISTS BOXES, PALETTES, AISLES CASCADE;";

        PreparedStatement statement = connection.prepareStatement(qry);
        statement.execute();
    }
}
