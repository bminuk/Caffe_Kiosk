package project;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KioskMenuFrame extends JPanel{	
	
	public KioskMenuFrame() {
		setLayout(new GridLayout(2,2));
		setBackground(Color.white);
		setLocation(50, 100);
		setSize(500, 300);
		setVisible(true);
		
		JButton jb = new JButton("카페모카");
		JButton jb2 = new JButton("카푸치노");
		JButton jb3 = new JButton("아메리카노");
		JButton jb4 = new JButton("카페라떼");
		add(jb);
		add(jb2);
		add(jb3);
		add(jb4);
	}

}
