import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Okno {

	static String daneZBazy;
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		new Logowanie();
		//Szukanie szukanie = new Szukanie();

	} 
	//TERAZ WYPISZE TYLE DANYCH ILE ZNAJDZIE KOLUMN
	static String[] daneZBazy(ResultSet rs) {
		//StringBuilder s = new StringBuilder();
		String[] d = null;
		
		try {
			
			ResultSetMetaData rsmd = rs.getMetaData();
			d = new String[rsmd.getColumnCount()];
			
			for(int i = 0; i < rsmd.getColumnCount(); i++) {
				
				d[i] = rs.getString(i+1);
				
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		return d;
	}
	//ROZBUDOWANY ONKLIK KORZYSTA TERAZ ZE STRINGA ALE NIE DA SIE TA METODA
	//TWORZYC NOWYCH DANYCH DO BAZY, CHYBA
	public static Object[][] onKlik(Connection conn, String query) {
		
		Object[][] odpowiedz = new Object[20][20];
		int j = 0;
		//Tworzymy proste zapytanie doa bazy danych
		//String query = "Select * FROM LEKARZE";
		try {
			//Uruchamiamy zapytanie do bazy danych
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			//odpowiedz = new String[rsmd.get];
			
			
			Class.forName("com.mysql.jdbc.Driver");
				
			while (rs.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					
					odpowiedz[j][i] = daneZBazy(rs)[i];

				}

				j++;
			} 

		}
		catch(ClassNotFoundException wyjatek) {
			System.out.println("Problem ze sterownikiem");
		}
		catch(SQLException wyjatek) {
			//e.printStackTrace();
			//System.out.println("Problem z logowaniem\nProsze sprawdzic:\n nazwę użytkownika, hasło, nazwę bazy danych lub adres IP serwera");
			System.out.println("SQLException: " + wyjatek.getMessage());
		    System.out.println("SQLState: " + wyjatek.getSQLState());
		    System.out.println("VendorError: " + wyjatek.getErrorCode());
		}
		//wszystkie dane pozyskane z tablicy SQL
		return odpowiedz;
	}
	
	public static Connection lacz() {
		
		String polaczenieURL = "jdbc:mysql://64.62.211.131/demmix_BazaProjektowa?"
				+ "user=demmix_Matmix&password=bazydanych";

		Connection conn = null;
		
		try {

			//Ustawiamy dane dotyczące podłączenia
			conn = DriverManager.getConnection(polaczenieURL);
			
		} 

		catch(SQLException wyjatek) {
			//e.printStackTrace();
			//System.out.println("Problem z logowaniem\nProsze sprawdzic:\n nazwę użytkownika, hasło, nazwę bazy danych lub adres IP serwera");
			System.out.println("SQLException: " + wyjatek.getMessage());
		    System.out.println("SQLState: " + wyjatek.getSQLState());
		    System.out.println("VendorError: " + wyjatek.getErrorCode());
		}
		
		return conn;
	}
	public static void rozlacz(Connection conn) {
		
		try {
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	//TO O WLASNIE NIE DZIALA WYWALA NULL POINTER ALE NIE WIEM W SUMIE DLACZEGO
	//NORMALNIE JEST WYWOLANA PODCZAS KLIKNIECIA ALE JAK TWORZYSZ Z NIEJ
	//TABLICE TO WYWALA
	public static String[] nazwyKol(Connection conn, String query) {
		
		String[] columnNames = null;
		try {
			//Uruchamiamy zapytanie do bazy danych
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			columnNames = new String[rsmd.getColumnCount()];
			
			Class.forName("com.mysql.jdbc.Driver");
			
			for(int i = 0; i < rsmd.getColumnCount(); i++) {
				
				columnNames[i] = rsmd.getColumnName(i + 1);
			//	System.out.println(rsmd.getColumnName(i).toString());
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
		}catch(ClassNotFoundException wyjatek) { 
			
			System.out.println("Problem ze sterownikiem");
		}
		
		return columnNames;	
	}
public static String queryCreator(Object tablica) {
		
		String query = "";
		String nazwaTablicy = "";
		nazwaTablicy = ((String) tablica).toUpperCase();
		query = "SELECT " + "* " + "FROM " + nazwaTablicy;
		
		return query;
	}
}