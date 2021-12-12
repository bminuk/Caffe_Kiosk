package project.Kiosk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import project.socket.ServerClient;
import project.socket.Client;
import project.socket.MyServer;
import project.socket.OrderVO;

public class KioskMainPage extends JFrame implements ActionListener{
	
	JLabel title= new JLabel("Coffee");//아마 이미지 만들어서 넣는게 더 편할 듯 (추후 이 주석 삭제할 것)
	static KioskMenuFrame kmf;
	static KioskOrderFrame kof;
	JLabel pricelbl = new JLabel("총 결제 금액 : ");
	static JLabel price = new JLabel("");
	static int priceSum = 0;
	JButton takeOutBtn = new JButton("포장");
	JButton useStoreBtn = new JButton("매장");
	Client client;
	public static int orderNumCount=1;
	public KioskMainPage() {
		//기본 Frame 설정
		setTitle("Caffe Kiosk");
		setLayout(null);
		setSize(515, 950);
		setLocationRelativeTo(this);
		//setLocation(1100,100);
		
		
		//MainFrame 관리
		
		//타이틀은 맨 위에 생성
		title.setLocation(230, 0);
		title.setSize(100, 50);
		add(title);
		
		//menu panel
		kmf = new KioskMenuFrame();
		add(kmf);
		
		//order panel
		kof = new KioskOrderFrame();
		add(kof);
		
		//총 결제 금액
		pricelbl.setSize(200, 50);
		pricelbl.setLocation(160, 760);
		add(pricelbl);
		price.setSize(200, 50);
		price.setLocation(260, 760);
		add(price);
		
		
		//매장, 포장 버튼 생성
		
		takeOutBtn.setSize(100, 70);
		takeOutBtn.setLocation(130, 820);
		takeOutBtn.addActionListener(this);
		useStoreBtn.setSize(100, 70);
		useStoreBtn.setLocation(260, 820);
		add(takeOutBtn);
		add(useStoreBtn);
		
		//클라이언트
		
		client = new Client();
		client.startClient("192.168.0.5", 1234);
		
		
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		new KioskMainPage();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==takeOutBtn) {
			ArrayList<String> orderList = new ArrayList<>();
			if(KioskOrderFrame.orderTable.getRowCount()==0) {
				JOptionPane.showMessageDialog(null, "장바구니가 비어있습니다.","장바구니 확인",JOptionPane.ERROR_MESSAGE);
			}else {
			for(int i=0;i<KioskOrderFrame.orderTable.getRowCount();i++) {
				orderList.add(KioskOrderFrame.orderTable.getValueAt(i, 0).toString());//i번째 열의 0번째 컬럼 값을 리스트에 추가
			}
			OrderVO ov= new OrderVO(orderNumCount, orderList, "포장");
			client.send(ov);
			orderNumCount++;
			price.setText("0원");
			KioskOrderFrame.tm.setNumRows(0);
			}
			
		
		
		}else if(obj==useStoreBtn) {
			ArrayList<String> orderList = new ArrayList<>();
			if(KioskOrderFrame.orderTable.getRowCount()==0) {
				JOptionPane.showMessageDialog(null, "장바구니가 비어있습니다.","장바구니 확인",JOptionPane.ERROR_MESSAGE);
			}else {
			for(int i=0;i<KioskOrderFrame.orderTable.getRowCount();i++) {
				orderList.add(KioskOrderFrame.orderTable.getValueAt(i, 0).toString());//i번째 열의 0번째 컬럼 값을 리스트에 추가
			}
			OrderVO ov= new OrderVO(orderNumCount, orderList, "매장");
			client.send(ov);
			orderNumCount++;
			price.setText("0원");
			KioskOrderFrame.tm.setNumRows(0);
			}
			
		}else if (true){
			
		}
	}
	

}

