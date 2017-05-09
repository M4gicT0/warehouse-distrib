package warehouse.utils;

import warehouse.shared.model.RemoteOrder;
import warehouse.shared.model.Box;
import warehouse.shared.model.Palette;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Theo on 5/8/17.
 */
public class DatabaseManager {

	private Connection connection;

	public DatabaseManager() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse_db", "warehouse",
					"warehouse");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean addAisle() throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO AISLES(id) VALUE(NULL);");

		return statement.execute();
	}

	/*
	 * Insert each box of the palette with their palette_id into aisle numbeer
	 * aisleNumber and at cell number cell[x][y].
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
			statement = connection.prepareStatement("INSERT INTO BOXES(id, palette_id, type, order_id) VALUE(?, ?, ?, null);");
			statement.setString(1, box.getId());
			statement.setString(2, palette.getId());
			statement.setString(3, box.getType().toString());

			if (!statement.execute())
				success = false;
		}

		return success;
	}
	
	public boolean insertOrder(RemoteOrder remoteOrder, List<Box> boxes) throws SQLException{
		boolean success = true;
		
		String qry = "INSERT INTO ORDERS(id, created_at, processed_at) VALUE(?, ?, ?);";
		PreparedStatement statement = connection.prepareStatement(qry);
		statement.setString(1, remoteOrder.getId());
		statement.setDate(2, (Date) remoteOrder.created_at());
		statement.setDate(3, (Date) remoteOrder.processed_at());
		
		statement.execute();

		for (Box box : boxes) {
			statement = connection.prepareStatement("UPDATE BOXES SET order_id = ?;");
			statement.setString(1, remoteOrder.getId());

			if (!statement.execute())
				success = false;
		}
		
		return success;
	}

	public HashMap<String, Object> getPaletteById(String id) throws SQLException {

		HashMap<String, Object> hm = new HashMap<String, Object>();

		String qry = "SELECT * FROM PALETTES WHERE id = ?;";
		PreparedStatement statement = connection.prepareStatement(qry);
		statement.setString(1, id.toString());

		ResultSet res = statement.executeQuery();

		if (!res.isBeforeFirst())
			return null;

		while (res.next()) {
			hm.put("id", id);
			hm.put("aisle_id", res.getInt("aisle_id"));
			hm.put("x", res.getInt("x"));
			hm.put("y", res.getInt("y"));
		}
		return hm;
	}

	public ArrayList<HashMap<String, Object>> getAllPalettes() throws SQLException {
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		HashMap<String, Object> hm = new HashMap<>();
		
		String qry = "SELECT * FROM PALETTES;";
		PreparedStatement statement = connection.prepareStatement(qry);
		ResultSet res = statement.executeQuery();
		
		if(!res.isBeforeFirst())
			return null;
		
		while(res.next()){
			hm.put("id", res.getString("id"));
			hm.put("aisle_id", res.getString("aisle_id"));
			hm.put("x", res.getInt("x"));
			hm.put("y", res.getInt("y"));
			
			list.add(hm);
		}
		return list;
	}
	
	public HashMap<String, Object> getBoxById(String id) throws SQLException{
		
		HashMap<String, Object> hm = new HashMap<>();
		
		String qry = "SELECT * FROM BOXES WHERE BOXES.ID = ?;";
		PreparedStatement statement = connection.prepareStatement(qry);
		statement.execute();
		
		ResultSet res = statement.executeQuery();
		
		if(!res.isBeforeFirst())
			return null;
		
		while(res.next()){
			hm.put("id", id);
			hm.put("palette_id", res.getString("palette_id"));
			hm.put("type", res.getString("type"));
		}
		return hm;
	}

	public ArrayList<HashMap<String, Object>> getBoxesByOrderId(String orderId) throws SQLException {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hm = new HashMap<>();

		String qry = "SELECT * FROM BOXES WHERE order_id = ?;";
		PreparedStatement statement = connection.prepareStatement(qry);
		statement.setString(1, orderId);
		statement.execute();

		ResultSet res = statement.executeQuery();

		if (!res.isBeforeFirst())
			return null;

		while (res.next()) {

			hm.put("id", res.getString("id"));
			hm.put("palette_id", res.getString("palette_id"));
			hm.put("type", res.getString("type"));

			list.add(hm);
		}

		return list;
	}

	public ArrayList<HashMap<String, Object>> getAllBoxes() throws SQLException {

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hm = new HashMap<>();

		String qry = "SELECT * FROM BOXES;";
		PreparedStatement statement = connection.prepareStatement(qry);
		ResultSet res = statement.executeQuery();

		if (!res.isBeforeFirst())
			return null;

		while (res.next()) {

			hm.put("id", res.getString("id"));
			hm.put("palette_id", res.getString("palette_id"));
			hm.put("type", res.getString("type"));
			hm.put("items_qty", res.getInt("items_qty"));

			list.add(hm);
		}

		return list;
	}

	public void deletePalette(String id) throws SQLException {
		
		String qry = "DELETE FROM PALETTES WHERE PALETTES.ID = ?;";
		PreparedStatement statement = connection.prepareStatement(qry);
		statement.setString(1, id );
		statement.execute();
		
	}
	
	public void deleteBox(String id) throws SQLException{
		
		String qry = "DELETE FROM BOXES WHERE BOXES.ID = ?;";
		PreparedStatement statement = connection.prepareStatement(qry);
		statement.setString(1, id );
		statement.execute();
		
	}

	public void createTables() throws SQLException {
		String qry = "CREATE TABLE AISLES(" + "id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY);";

		PreparedStatement statement = connection.prepareStatement(qry);
		statement.execute();

		qry = "CREATE TABLE PALETTES(" + "id VARCHAR(15) PRIMARY KEY," + "aisle_id INT UNSIGNED NOT NULL,"
				+ "x INT UNSIGNED NOT NULL," + "y INT UNSIGNED NOT NULL,"
				+ "FOREIGN KEY(aisle_id) REFERENCES AISLES(id) ON DELETE CASCADE);";

		statement = connection.prepareStatement(qry);
		statement.execute();

		qry = "CREATE TABLE ORDERS(" + "id VARCHAR(15) PRIMARY KEY," + "created_at DATE"+ ", proccessed_at DATE);";
		statement = connection.prepareStatement(qry);

		statement.execute();

		qry = "CREATE TABLE BOXES(" + "id VARCHAR(15) PRIMARY KEY," + "palette_id VARCHAR(15) NOT NULL,"
				+ "type ENUM('COTTON', 'FOOD', 'WOOD', 'PAINT') NOT NULL," + "order_id VARCHAR(15) DEFAULT NULL, items_qty INT NOT NULL DEFAULT 1,"
				+ "FOREIGN KEY(palette_id) REFERENCES PALETTES(id) ON DELETE CASCADE,"
				+ "FOREIGN KEY(order_id) REFERENCES ORDERS(ID)  ON DELETE CASCADE);";

		statement = connection.prepareStatement(qry);
		statement.execute();
	}
	
	public HashMap<String, Object> getOrderById(String id) throws SQLException{
		
		HashMap<String, Object> hm = new HashMap<>();
		
		String qry = "SELECT * FROM ORDERS WHERE ORDERS.ID = ?;";
		PreparedStatement statement = connection.prepareStatement(qry);
		statement.execute(qry);
		
		ResultSet res = statement.executeQuery();
		
		if(!res.isBeforeFirst())
			return null;
		
		while(res.next()){
			hm.put("id", id);
			hm.put("created_at", res.getDate("created_at"));
			hm.put("precessed_at", res.getDate("precessed_at"));
		}
		return hm;
		
		
	}
	
	public ArrayList<HashMap<String, Object>> getAllOrders() throws SQLException {

		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		HashMap<String, Object> hm = new HashMap<>();

		String qry = "SELECT * FROM orders;";
		PreparedStatement statement = connection.prepareStatement(qry);
		statement.execute();

		ResultSet res = statement.executeQuery();

		if (!res.isBeforeFirst())
			return null;

		while (res.next()) {

			hm.put("id", res.getString("id"));
			hm.put("created_at", res.getDate("created_at"));
			hm.put("processed_at", res.getDate("processed_at"));

			list.add(hm);
		}

		return list;
	}
	
	public void deleteOrder(String id) throws SQLException{
		
		String qry = "DELETE FROM ORDERS WHERE ORDER.ID = ?;";
		PreparedStatement statement = connection.prepareStatement(qry);
		statement.setString(1, id);
		statement.execute();
	}
	
	public HashMap<String, Object> getPaletteByCoordinates(String aisle_id, int x, int y) throws SQLException{
		
		HashMap<String, Object> hm = new HashMap<>();
		
		String qry = "SELECT * FROM PALETTES WHERE PALETTES.AISLE_ID = ? AND PALETTES.X = ? AND PALETTES.Y = ?;";
		PreparedStatement statement = connection.prepareStatement(qry);
		statement.setString(1, aisle_id);
		statement.setInt(2, x);
		statement.setInt(3, y);
		
		statement.execute();
		
		ResultSet res = statement.executeQuery();
		
		if(!res.isBeforeFirst())
			return null;
		
		while(res.next()){
			hm.put("aisle_id", aisle_id);
			hm.put("x", x);
			hm.put("y", y);
			
		}

		return hm;
	}

	public void dropTables() throws SQLException {
		String qry = "DROP TABLE IF EXISTS BOXES, ORDERS, PALETTES, AISLES CASCADE;";

		PreparedStatement statement = connection.prepareStatement(qry);
		statement.execute();
	}
}
