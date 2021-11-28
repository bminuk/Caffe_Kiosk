package project.Kiosk;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class KioskMenuFrame extends JPanel implements ActionListener{	
	JButton menuJb1,menuJb2,menuJb3,menuJb4,menuJb5,menuJb6,menuJb7,menuJb8,
			menuJb9,menuJb10,menuJb11,menuJb12,menuJb13,menuJb14,menuJb15,menuJb16;
	int sum = 0;
	public KioskMenuFrame() {
		setLayout(new GridLayout(4,4,5,5));
		setBackground(Color.white);
		setLocation(50, 50);
		setSize(400, 550);
		setVisible(true);
		
		menuJb1 = new JButton(new ImageIcon("images//ame.png"));
		
		//	new ImageIcon("images//moca1.png")
		
		
		menuJb2 = new JButton(new ImageIcon("images//ame2.png"));
		menuJb3 = new JButton(new ImageIcon("images//ratte1.png"));
		menuJb4 = new JButton(new ImageIcon("images//ratte2.png"));
		menuJb5 = new JButton(new ImageIcon("images//moca1.png"));
		menuJb6 = new JButton(new ImageIcon("images//moca2.png"));
		menuJb7 = new JButton(new ImageIcon("images//cmratte1.png"));
		menuJb8 = new JButton(new ImageIcon("images//cmratte2.png"));
		menuJb9 = new JButton(new ImageIcon("images//insu.png"));
		menuJb10 = new JButton(new ImageIcon("images//cold.png"));
		menuJb11 = new JButton(new ImageIcon("images//banil1.png"));
		menuJb12 = new JButton(new ImageIcon("images//banil2.png"));
		menuJb13 = new JButton(new ImageIcon("images//jamong.png"));
		menuJb14 = new JButton(new ImageIcon("images//remon.png"));
		menuJb15 = new JButton(new ImageIcon("images//blue.png"));
		menuJb16 = new JButton(new ImageIcon("images//icetea.png"));
		
		
		add(menuJb1);
		add(menuJb2);
		add(menuJb3);
		add(menuJb4);
		add(menuJb5);
		add(menuJb6);
		add(menuJb7);
		add(menuJb8);
		add(menuJb9);
		add(menuJb10);
		add(menuJb11);
		add(menuJb12);
		add(menuJb13);
		add(menuJb14);
		add(menuJb15);
		add(menuJb16);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==menuJb1) {
			String[] record = {"카페모카","5000","2"};
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.price.setText("5000");
		}
		
	}

}
