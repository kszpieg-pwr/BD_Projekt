import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class Dodawanie extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton dodaj= new JButton("Dodaj");
	private JComboBox<String> opcje;
	private Rysunek rysunek = new Rysunek();
	public Connection conn = null;
	private JTextField t1= new JTextField(10);
	private JTextField t2 = new JTextField(10);
	private JTextField t3 = new JTextField(10);
	private JTextField t4 = new JTextField(10);
	private JTextField t5 = new JTextField(10);
	private JTextField t6 = new JTextField(10);
	private JLabel l1 = new JLabel();
	private JLabel l2 = new JLabel();
	private JLabel l3 = new JLabel();
	private JLabel l4 = new JLabel();
	private JLabel l5 = new JLabel();
	private JLabel l6 = new JLabel();
	//To jest œmietnik ca³a ta klasa nie wyrzuci³em jej jeszcze bo coœ siê da tutaj 
	//zrobiæ szukanie po imieniu czy dodawanie ludzi
	
Dodawanie(){
	super("Dodawanie");
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
	setSize(500,300);
	this.add(rysunek);
	
	
	opcje = Picture.getOpcje();
	
	rysunek.add(opcje);
	rysunek.add(dodaj);
	
	setVisible(true);
	
	opcje.addActionListener(this);
	dodaj.addActionListener(this);
	
	conn = Picture.conn;
}
public void paintForDoc() {
	
	rysunek.remove(l1);
	rysunek.remove(l2);
	rysunek.remove(l3);
	rysunek.remove(l4);
	rysunek.remove(l5);
	rysunek.remove(l6);
	rysunek.remove(t1);
	rysunek.remove(t2);
	rysunek.remove(t3);
	rysunek.remove(t4);
	rysunek.remove(t5);
	rysunek.remove(t6);
	
	l1 = new JLabel("Imie");
	l2 = new JLabel("Nazwisko");
	l3 = new JLabel("Specjalizacja");
	
	rysunek.add(l1);
	rysunek.add(t1);
	rysunek.add(l2);
	rysunek.add(t2);
	rysunek.add(l3);
	rysunek.add(t3);
	
	
	rysunek.revalidate();
	rysunek.repaint();
}
public void paintForClt() {
	
	rysunek.remove(l1);
	rysunek.remove(l2);
	rysunek.remove(l3);
	rysunek.remove(l4);
	rysunek.remove(l5);
	rysunek.remove(l6);
	rysunek.remove(t1);
	rysunek.remove(t2);
	rysunek.remove(t3);
	rysunek.remove(t4);
	rysunek.remove(t5);
	rysunek.remove(t6);
	
	l1 = new JLabel("Imie");
	l2 = new JLabel("Nazwisko");
	l3 = new JLabel("Grupa Krwi");
	l4 = new JLabel("Czy dawca");
	l5 = new JLabel("Czy w szpitalu");
	l6 = new JLabel("Lekarz prowadzacy");
	
	rysunek.add(l1);
	rysunek.add(t1);
	rysunek.add(l2);
	rysunek.add(t2);
	rysunek.add(l3);
	rysunek.add(t3);
	rysunek.add(l4);
	rysunek.add(t4);
	rysunek.add(l5);
	rysunek.add(t5);
	rysunek.add(l6);
	rysunek.add(t6);
	
	
	rysunek.revalidate();
	rysunek.repaint();
}
public void insertLek(String s1, String s2, String s3) {
	
	String query = "INSERT INTO LEKARZE ("
			+ " IMIE,"
			+ " NAZWISKO,"
			+ " SPECJALIZACJA )"
			+ " VALUES ("
			+ " ?, ?, ? )";
	
	
	try {
		
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, s1);
		st.setString(2, s2);
		st.setString(3, s3);
		
		
		st.executeUpdate();
		st.close();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void insertPac(String s1, String s2, String s3, Boolean s4, Boolean s5, Integer s6) {
	
	String query = "INSERT INTO PACJENCI ("
			+ " IMIE,"
			+ " NAZWISKO,"
			+ " GRUPA_KRWI,"
			+ " CZY_JEST_DAWCA,"
			+ " CZY_JEST_W_SZPITALU,"
			+ " LEKARZ_PROWADZACY )"
			+ " VALUES ("
			+ " ?, ?, ?, ?, ?, ? )";
	
	
	try {
		
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, s1);
		st.setString(2, s2);
		st.setString(3, s3);
		st.setBoolean(4, s4);
		st.setBoolean(5, s5);
		st.setInt(6, s6);
		
		st.executeUpdate();
		st.close();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object zr = e.getSource();
		
		if(zr == opcje) {
			if(opcje.getSelectedItem().toString().equalsIgnoreCase("pacjenci")) {
				paintForClt();
				
				//insertPac("Robert", "Solomon", "A+", false, true, 3);
				
			}
			if(opcje.getSelectedItem().toString().equalsIgnoreCase("lekarze")) {
				paintForDoc();
				
			}
		}
		if(zr == dodaj && opcje.getSelectedItem().toString().equalsIgnoreCase("pacjenci")) {
			
			Boolean b1 = Boolean.valueOf(t4.getText());
			Boolean b2 = Boolean.valueOf(t5.getText());
			Integer i1 = Integer.valueOf(t6.getText());
			
			if(!(t1.getText().equals("")) && !(t2.getText().equals("")) 
					&& !(t3.getText().equals("")) && !(t4.getText().equals("")) 
					&& !(t5.getText().equals("")) && !(t6.getText().equals(""))) {
				
				insertPac(t1.getText(), t2.getText(), t3.getText(), b1, b2, i1);
			    JOptionPane.showMessageDialog(null,"Dodano");
			}
			else 
				JOptionPane.showMessageDialog(null,"Pola musza byæ wypelnione");

		}
		if(zr == dodaj && opcje.getSelectedItem().toString().equalsIgnoreCase("lekarze")) {
			
			if(!(t1.getText().equals("")) && !(t2.getText().equals("")) 
					&& !(t3.getText().equals(""))) {
				
				insertLek(t1.getText(), t2.getText(), t3.getText());
				JOptionPane.showMessageDialog(null,"Dodano");
			}
			else 
				JOptionPane.showMessageDialog(null,"Pola musza byæ wypelnione");
		}
	}
}
