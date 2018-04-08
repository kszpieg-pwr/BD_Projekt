import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Picture extends JFrame implements ActionListener {
	
	
	private static final long serialVersionUID = 1L;
	private JButton wyslij = new JButton("Wyslij");
	private JButton polacz = new JButton("Polacz");
	private JButton rozlacz = new JButton("Rozlacz");
	private JButton dodaj = new JButton("Dodaj");
	private JButton usun = new JButton("Usun");
	private JButton szSzuk = new JButton("Szybkie szukanie");
	private JTextField t1= new JTextField(2);
	private static JComboBox<String> opcje; 
	private Rysunek rysunek = new Rysunek();
	public static Connection conn = null;
	private JTable panel;
	public JScrollPane p = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	
Picture(){
	super("Program");
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);	
	setSize(500,500);
	this.add(rysunek);
	
	
	String[] tablice = {"Lekarze", "Pacjenci", "Sale", "Magazyn", "Zabiegi"};
	//ZOSTAWILEM AWARYJNIE
	
	
	panel = new JTable();
	//panel.setAutoscrolls(true);
	panel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	//panel.setPreferredScrollableViewportSize(new Dimension(400,300));
	panel.setFillsViewportHeight(true);
	
	setOpcje(new JComboBox<>());
	
	//rysunek.add(opcje);
	rysunek.add(t1);
	rysunek.add(polacz);
	rysunek.add(rozlacz);
	rysunek.add(wyslij);
	rysunek.add(dodaj);
	rysunek.add(szSzuk);
	rysunek.add(usun);
	
	wyslij.addActionListener(this);
	polacz.addActionListener(this);
	rozlacz.addActionListener(this);
	dodaj.addActionListener(this);
	szSzuk.addActionListener(this);
	usun.addActionListener(this);
	
	dodaj.setEnabled(false);
	
	setVisible(true);
}

public void addTable(String query) {
	
//	String[] a = Okno.nazwyKol(conn, Okno.queryCreator(opcje.getSelectedItem()));
	//JTable panel =   new JTable(Okno.onKlik(conn, Okno.queryCreator(getOpcje().getSelectedItem()))
	//		, Okno.nazwyKol(conn, Okno.queryCreator(getOpcje().getSelectedItem())));
	JTable panel = new JTable(Okno.onKlik(conn, query), Okno.nazwyKol(conn, query));
	panel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	panel.setPreferredScrollableViewportSize(new Dimension(400,300));
	panel.setFillsViewportHeight(true);
	 p = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	rysunek.add(p, panel);
	

	//Okno.nazwyKol(conn, Okno.queryCreator(opcje.getSelectedItem()))
	//Okno.onKlik(conn);
	rysunek.revalidate();
	rysunek.repaint();
}
public void addCombo(String[] tab) {
	
	setOpcje(new JComboBox<>(tab));
	
	rysunek.add(getOpcje());
	
	rysunek.revalidate();
	rysunek.repaint();
	
}
public static String[]	fillCombo(Connection conn) {
	
	String[] tab = null;
	List<String> temp = new ArrayList<>(); 
	
	try {
		
		DatabaseMetaData md = conn.getMetaData();
		ResultSet rs = md.getTables(null, null, "%", null);
		
		while (rs.next()) {
			
			temp.add(rs.getString(3));
		}
		
	} catch (SQLException e) {

		e.printStackTrace();
	}
	
	tab = new String[temp.size() - 1];
	
	for (int i = 0; i < temp.size() - 1; i++) {
		
		tab[i] = temp.get(i);
		
	}

	return tab;
}

@Override
public void actionPerformed(ActionEvent e) {

	String[] column = {"ID", "Imie", "Nazwisko", "Specjalizacja", "Muje dzikie"};
	Object zr = e.getSource();
	
	if (zr == polacz) {
		
		conn = Okno.lacz();
		if(conn != null) {
			
			dodaj.setEnabled(true);
			JOptionPane.showMessageDialog(null,"Polaczono");
			
			rysunek.remove(getOpcje());
			addCombo(fillCombo(conn));
		}
		else
			JOptionPane.showMessageDialog(null,"Nie udalo sie polaczyc");
	}
	//TU WYWALA NULL POINTER W PROBIE TWORZENIA NOWEJ TABELI Z URZYCIEM Okno.nazwyKOL
	if (zr == wyslij) {
			
		if (conn != null) {
			rysunek.remove(panel);
			rysunek.remove(p);
			addTable(Okno.queryCreator(getOpcje().getSelectedItem()));
			
			//addTable("SELECT * FROM GRUPA_A");
		}
		else
			JOptionPane.showMessageDialog(null,"Nie udalo sie polaaczyc");
	}
	if (zr == rozlacz) {
		
		Okno.rozlacz(conn);
		JOptionPane.showMessageDialog(null,"Rozlaczono");
	}
	if(zr == dodaj) {
		
		new Dodawanie();
		
	}
	if(zr == szSzuk) {
		
		int k = Integer.parseInt(t1.getText());
		addTable("SELECT * FROM PACJENCI WHERE LEKARZ_PROWADZACY = " + k);
		rysunek.remove(t1);
	}
	if(zr == usun) {
		
		JOptionPane.showMessageDialog(null,"Pomyslnie usunieto rekordy");
	}
  }
//Oba mog¹ zostac usuniete to tylko do klasy Dodawanie
public static JComboBox<String> getOpcje() {
	return opcje;
}

public static void setOpcje(JComboBox<String> opcje) {
	Picture.opcje = opcje;
}
}