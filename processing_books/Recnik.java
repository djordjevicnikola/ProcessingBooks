package zavrsniprojekat;

import java.sql.*;
import java.util.HashMap;

public class Recnik {
	String connectionString;
	Connection con;

	public Recnik(String conStr) {
		connectionString = conStr;
	}

	public void connect() {
		try {
			con = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, String> ispisRecnika() {
		HashMap<String, String> hmRecnik = new HashMap<String, String>();
		try {
			String upit = "SELECT *\r\n" + "FROM entries";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);
			while (rs.next()) {
				String word = rs.getString("word").toLowerCase();
				String definition = rs.getString("definition").toLowerCase();
				hmRecnik.put(word, definition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hmRecnik;
	}

	public void kreirajTabeluNoveReci() {
		String upit = "CREATE TABLE \"NoveReci\" (\r\n" + "	\"ID\"	INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "	\"Ime\"	TEXT\r\n" + ");";
		try {
			Statement stm = con.createStatement();
			stm.executeUpdate(upit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dodajNoveReci(String upit) {
		try {
			Statement stm = con.createStatement();
			stm.executeUpdate(upit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
