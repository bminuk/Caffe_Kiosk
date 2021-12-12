package project.Kiosk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class KioskOrderFrame extends JPanel implements MouseListener {
	
	public JScrollPane jp;
	public JTable orderTable;
	DefaultTableModel tm;
	
	public KioskOrderFrame() {
		//패널 설정
		//setSize(500, 150);
		setLocation(50, 610);
		setBackground(Color.white);
		setLayout(new BorderLayout());
		setSize(400, 150);
		
		//orderTable 설정
		String header[]={"주문 상품","가격","수량"};
		tm = new DefaultTableModel(header,0); //테이블 모델 생성
		
		orderTable = new JTable(tm);//모델로 테이블 객체 생성
		
		jp = new JScrollPane(orderTable); //스크롤팬에 테이블 넣기
		
		orderTable.addMouseListener(this);
		
		add(jp,BorderLayout.CENTER);//팬에 스크롤팬 추가
		
		
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = orderTable.getSelectedRow();
		String menuName = (String) orderTable.getValueAt(row, 0);
		int result = JOptionPane.showConfirmDialog(null, menuName+" 메뉴를 삭제하시겠습니까?", "메뉴 취소",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION) {
			tm.removeRow(row);
			KioskMainPage.priceSum = KioskMainPage.priceSum - Integer.parseInt(orderTable.getValueAt(row, 1).toString());
			KioskMainPage.price.setText(KioskMainPage.priceSum+"원");
			//sum= sum-
		}else {
			
		}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
