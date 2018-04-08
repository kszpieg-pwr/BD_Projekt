import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Logowanie extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JButton log = new JButton("Zaloguj");
	private JLabel l = new JLabel("Login");
	private JLabel p = new JLabel("Haslo");
	private JTextField login = new JTextField(10);
	private JTextField password = new JTextField(10);
	private Rysunek rysunek = new Rysunek();
	
	
Logowanie(){
	super("L O G O W A N I E");
	setDefaultCloseOperation(EXIT_ON_CLOSE);	
	setSize(500,200);
	this.add(rysunek);

	
	rysunek.add(l);
	rysunek.add(login);
	rysunek.add(p);
	rysunek.add(password);
	rysunek.add(log);
	
	log.addActionListener(this);
	
	
	setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
	
	Object zr = e.getSource();
	if (zr == log) {
		if (login.getText().toString().equals("") && password.getText().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Zalogowano");
			setVisible(false);
			Picture okno1 = new Picture();
		}	
		else 
			JOptionPane.showMessageDialog(null, "Zly login lub haslo");
	}
}

}