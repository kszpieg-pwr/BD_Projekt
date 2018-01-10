import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Okno {

	static String daneZBazy;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Picture();
		
		String polaczenieURL = "jdbc:mysql://64.62.211.131/demmix_BazaProjektowa?user=demmix_Matmix&password=bazydanych";
		//Tworzymy proste zapytanie doa bazy danych
		String query = "Select * FROM LEKARZE";
		
		Connection conn = null;
		
		try {

			//Ustawiamy dane dotyczące podłączenia
			conn = DriverManager.getConnection(polaczenieURL);
			
			//Ustawiamy sterownik MySQL
			Class.forName("com.mysql.jdbc.Driver");
			
			//Uruchamiamy zapytanie do bazy danych
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
		//	System.out.println(rs.getString(0));
		//	System.out.println(rs.getString(2));
		//	System.out.println(rs.getString(3));
			//System.out.println(rs.getString(4));
			
			while (rs.next()) {
				wyswietlDaneZBazy(rs);
				daneZBazy(rs);
			} 
	
			conn.close();
		} 
		//Wyrzuć wyjątki jężeli nastąpią błędy z podłączeniem do bazy danych lub blędy zapytania o dane
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

	} 
	static void wyswietlDaneZBazy(ResultSet rs){
		try{
		daneZBazy = rs.getString(1);
		System.out.println("\n" + daneZBazy + " ");
		daneZBazy = rs.getString(2);
		System.out.println(daneZBazy + " ");
		daneZBazy = rs.getString(3);
		System.out.println(daneZBazy);
		daneZBazy = rs.getString(4);
		System.out.println(daneZBazy);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	static String daneZBazy(ResultSet rs) {
		StringBuilder s = new StringBuilder();
		String d;
		try {
			s.append(rs.getString(1));
			s.append("\n");
			s.append(rs.getString(2));
			s.append("\n");
			s.append(rs.getString(3));
			s.append("\n");
			s.append(rs.getString(4));
		}catch (SQLException e) {
			e.printStackTrace();
		}
		d = s.toString();
		return d;
	}
		
}