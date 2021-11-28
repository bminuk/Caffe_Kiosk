package project.Kiosk;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class KioskOrderFrame extends JPanel {
	
	public JScrollPane jp;
	public JTable orderTable;
	DefaultTableModel tm;
	
	public KioskOrderFrame() {
		//패널 설정
		setSize(500, 200);
		setLocation(50, 410);
		setBackground(Color.white);
		setLayout(new GridLayout());
		
		//orderTable 설정
		String header[]={"주문 상품","가격","수량"};
		tm = new DefaultTableModel(header,0); //테이블 모델 생성
		
		orderTable = new JTable(tm);//모델로 테이블 객체 생성
		
		jp = new JScrollPane(orderTable); //스크롤팬에 테이블 넣기
		
		add(jp);//팬에 스크롤팬 추가
		
		
		
		
		
	}

}
