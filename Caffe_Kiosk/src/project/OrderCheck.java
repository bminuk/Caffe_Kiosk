package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteOrder;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import project.socket.ServerClient;
import project.Kiosk.KioskMainPage;
import project.socket.Client;
import project.socket.Client2;
import project.socket.MyServer;
import project.socket.OrderVO;

public class OrderCheck extends JFrame implements MouseListener, ActionListener {
	
	Client2 client;
	static String orderMessage;
	public static DefaultListModel<String> lm;
	public static JList<String> orderList;
	public static OrderVO ov;
	JButton clear;
	public static String getOrderMessage() {
		return orderMessage;
	}

	public static void setOrderMessage(String orderMessage) {
		OrderCheck.orderMessage = orderMessage;
	}

	public OrderCheck(){
		setLayout(new BorderLayout());
		setSize(300, 500);
		setResizable(false);
		setTitle("주문내역 확인");
		setLocationRelativeTo(this);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.white);
		
		//클라이언트
				client = new Client2();
				client.startClient("127.0.0.1", 1234);
				client.receive();
		
		JPanel jpNorth = new JPanel();//제목 부분
		jpNorth.setLayout(new FlowLayout());
		JLabel orderCheckTitle = new JLabel("주문 내역 확인");
		jpNorth.add(orderCheckTitle);
		
		
		JPanel jpCenter = new JPanel();// JList 부분
		jpCenter.setLayout(new FlowLayout());	
		lm = new DefaultListModel<>();//리스트 모델
		orderList = new JList<>(lm);//리스트 모델로 리스트 생성
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//한번에 하나의 리스트만 선택 가능
		orderList.setBorder(new EmptyBorder(0, 20, 0, 0));
		JScrollPane scrollPane = new JScrollPane(orderList);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jpCenter.add(scrollPane);
		
		//리스트 액션 이벤트
		orderList.addMouseListener(this);;
		
		//리스트 사이즈 설정
		orderList.setFixedCellHeight(50);
		orderList.setFixedCellWidth(250);
		orderList.setFont(new Font("돋움",Font.BOLD,17));
	
		
		clear = new JButton("주문 내역 클리어");
		clear.addActionListener(this);
		
		
		//전체 구성
		add(jpNorth,BorderLayout.NORTH);
		add(jpCenter,BorderLayout.CENTER);
		add(clear,BorderLayout.SOUTH);
		
		
				
		
	}
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("메뉴");
		OrderVO ov = new OrderVO(1, list, "매장");
		new OrderCheckDetail(ov);
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("리스너 클림됨"+orderList.getSelectedIndex());
		try {
			new OrderCheckDetail(ov);
		} catch (Exception e2) {
			System.out.println("주문확인서 안열고 메세지 보낸경우");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==clear) {
			lm.removeAllElements();
			
		}
		
	}
	

}

class OrderCheckDetail extends JFrame{//리스트를 클릭하면 생성되는 메뉴 자세한 페이지
	public OrderCheckDetail(OrderVO ov) {
		setLayout(new BorderLayout());
		setSize(300, 300);
		setTitle("주문 내역 확인");
		setResizable(false);
		
		setLocationRelativeTo(this);
		
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.setSize(300, 500);
		jp.setBackground(Color.white);
		
		
		
		JTextArea jta = new JTextArea();
		jta.setEditable(false);
		jta.setFont(new Font("돋움", Font.BOLD, 15));
		jta.setLineWrap(true);
		jta.setSize(280, 500);
		jta.append("주문 번호 : "+ov.getOrderNum()+"\n\n");
		jta.append(ov.getWhereUse()+" 주문\n\n");
		jta.append("주문 메뉴 목록 : \n");
		ArrayList<String> orderList = ov.getOrderList();
		for(int i=0;i<orderList.size();i++) {
		jta.append(orderList.get(i)+"\n");
		}
		
		JScrollPane jp2 = new JScrollPane(jta);
		
		jp.add(jp2,BorderLayout.CENTER);
		
		add(jp,BorderLayout.CENTER);
		
		setVisible(true);

	}
}
