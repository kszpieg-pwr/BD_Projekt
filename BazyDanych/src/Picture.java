import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Picture extends JFrame implements ActionListener {
	
	private JButton wyslij = new JButton("Wyślij");
	private JTextField baza = new JTextField(10);
	private Rysunek rysunek = new Rysunek();
	
Picture(){
	super("Program");
	setDefaultCloseOperation(EXIT_ON_CLOSE);	
	setSize(500,300);
	this.add(rysunek);
	baza.setEditable(false);
	
	rysunek.add(baza);
	rysunek.add(wyslij);
	wyslij.addActionListener(this);

	
	
	setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
	Object zr = e.getSource();
	if (zr == wyslij) {
		baza.setText("Jii");
	}
	
}

}