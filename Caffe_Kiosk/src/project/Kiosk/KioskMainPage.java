package project.Kiosk;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KioskMainPage extends JFrame{
	
	JLabel title= new JLabel("Coffee");//아마 이미지 만들어서 넣는게 더 편할 듯 (추후 이 주석 삭제할 것)
	static KioskMenuFrame kmf;
	static KioskOrderFrame kof;
	public KioskMainPage() {
		//기본 Frame 설정
		setTitle("Caffe Kiosk");
		setLayout(null);
		setSize(600, 800);
		setLocationRelativeTo(this);
		//setLocation(1100,100);
		
		
		//MainFrame 관리
		
		//타이틀은 맨 위에 생성
		title.setLocation(255, 0);
		title.setSize(100, 50);
		add(title);
		
		//menu panel
		kmf = new KioskMenuFrame();
		add(kmf);
		
		//order panel
		kof = new KioskOrderFrame();
		add(kof);
		
		//
		
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	

	public static void main(String[] args) {
		
		new KioskMainPage();
	}

}
