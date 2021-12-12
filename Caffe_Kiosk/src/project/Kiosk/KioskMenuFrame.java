package project.Kiosk;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import project.MainPage;

public class KioskMenuFrame extends JPanel implements ActionListener {
	JButton menuJb1, menuJb2, menuJb3, menuJb4, menuJb5, menuJb6, menuJb7, menuJb8, menuJb9, menuJb10, menuJb11,
			menuJb12, menuJb13, menuJb14, menuJb15, menuJb16;


	public KioskMenuFrame() {
		setLayout(new GridLayout(4, 4, 5, 5));
		setBackground(Color.white);
		setLocation(50, 50);
		setSize(400, 550);
		setVisible(true);

		menuJb1 = new JButton(new ImageIcon("images//ame.png"));
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

		menuJb1.addActionListener(this);
		menuJb2.addActionListener(this);
		menuJb3.addActionListener(this);
		menuJb4.addActionListener(this);
		menuJb5.addActionListener(this);
		menuJb6.addActionListener(this);
		menuJb7.addActionListener(this);
		menuJb8.addActionListener(this);
		menuJb9.addActionListener(this);
		menuJb10.addActionListener(this);
		menuJb11.addActionListener(this);
		menuJb12.addActionListener(this);
		menuJb13.addActionListener(this);
		menuJb14.addActionListener(this);
		menuJb15.addActionListener(this);
		menuJb16.addActionListener(this);

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
		if (obj == menuJb1) {
			String[] record = { "아메리카노 (ICE)", "2000", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 2000;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb2) {
			String[] record = { "아메리카노 (HOT)", "2000", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 2500;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb3) {
			String[] record = { "카페라떼 (ICE)", "2500", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 2500;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb4) {
			String[] record = { "카페라떼 (HOT)", "2500", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 2500;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb5) {
			String[] record = { "카페모카 (ICE)", "3000", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 3000;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb6) {
			String[] record = { "카페모카 (HOT)", "3000", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 3000;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb7) {
			String[] record = { "연유라떼 (ICE)", "3000", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 3000;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb8) {
			String[] record = { "연유라떼 (HOT)", "3000", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 3000;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb9) {
			String[] record = { "아인슈패너 (ICE)", "3000", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 3000;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb10) {
			String[] record = { "콜드브루 (ICE)", "2500", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 2500;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb11) {
			String[] record = { "바닐라 라떼 (ICE)", "3000", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 3000;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb12) {
			String[] record = { "바닐라 라떼 (HOT)", "3000", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 3000;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb13) {
			String[] record = { "자몽에이드", "2500", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 2500;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb14) {
			String[] record = { "레몬에이드", "2500", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 2500;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb15) {
			String[] record = { "블루 레몬에이드", "2800", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 2800;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		} else if (obj == menuJb16) {
			String[] record = { "아이스티", "2000", "1" };
			KioskMainPage.kof.tm.addRow(record);
			KioskMainPage.priceSum = KioskMainPage.priceSum + 2000;
			KioskMainPage.price.setText(KioskMainPage.priceSum + "원");
		}

	}

}
