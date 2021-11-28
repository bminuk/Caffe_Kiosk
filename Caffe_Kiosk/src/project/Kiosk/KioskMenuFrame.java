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
		
		
		
		
		menuJb2 = new JButton("카페라떼");
		menuJb3 = new JButton("카푸치노");
		menuJb4 = new JButton("카페모카");
		menuJb5 = new JButton("콜드브루");
		menuJb6 = new JButton("아인슈패너");
		menuJb7 = new JButton("연유라떼");
		menuJb8 = new JButton("바닐라라떼");
		menuJb9 = new JButton("카페모카");
		menuJb10 = new JButton("카페모카");
		menuJb11 = new JButton("카페모카");
		menuJb12 = new JButton("카페모카");
		menuJb13 = new JButton("카페모카");
		menuJb14 = new JButton("카페모카");
		menuJb15 = new JButton("카페모카");
		menuJb16 = new JButton("카페모카");
		
		
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
