package warehouse.utils;

import warehouse.shared.model.Box;
import warehouse.shared.model.BoxType;
import warehouse.shared.model.Palette;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Created by Theo on 5/8/17.
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

    public boolean addAisle() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO AISLES(id) VALUE(NULL);");

        return statement.execute();
    }

    /* Insert each box of the palette with their palette_id into aisle number aisleNumber
     * and at cell number cell[x][y].
     */
    public boolean insertPalette(Palette palette, int aisleNumber, int x, int y) throws RemoteException, SQLException {
        boolean success = true;
        String qry = "INSERT INTO PALETTES(id, aisle_id, x, y) VALUE(?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(qry);
        statement.setString(1, palette.getId());
        statement.setInt(2, aisleNumber);
        statement.setInt(3, x);
        statement.setInt(4, y);

        statement.execute();

        for (Box box : palette.getBoxes()) {
            statement = connection.prepareStatement("INSERT INTO BOXES(id, palette_id, type) VALUE(?, ?, ?);");
            statement.setString(1, box.getId());
            statement.setString(2, palette.getId());
            statement.setString(3, box.getType().toString());

            if (!statement.execute())
                success = false;
        }

        return success;
    }


    public boolean fetchPalette(BoxType type) {
        return false;
    }

    public boolean fetchPaletteAt(int aisleNumber, int[][] cell) {
        return false;
    }

    public boolean getPaletteCount(int aisleNumber) {
        return false;
    }

    public boolean fetchPaletteById(String id) {
        return false;
    }

    public void createTables() throws SQLException {
        String qry = "CREATE TABLE AISLES(" +
                "id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY);";

        PreparedStatement statement = connection.prepareStatement(qry);
        statement.execute();

        qry = "CREATE TABLE PALETTES(" +
                "id VARCHAR(15) PRIMARY KEY," +
                "aisle_id INT UNSIGNED NOT NULL," +
                "x INT UNSIGNED NOT NULL," +
                "y INT UNSIGNED NOT NULL," +
                "FOREIGN KEY(aisle_id) REFERENCES AISLES(id) ON DELETE CASCADE);";

        statement = connection.prepareStatement(qry);
        statement.execute();

        qry = "CREATE TABLE BOXES(" +
                "id VARCHAR(15) PRIMARY KEY," +
                "palette_id VARCHAR(15) NOT NULL," +
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
