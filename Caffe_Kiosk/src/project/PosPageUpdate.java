package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.sun.net.httpserver.Authenticator.Result;

public class PosPageUpdate extends JFrame implements ActionListener {

	JButton mark;
	JLabel sum;
	JLabel lbl1, lbl2, lbl3, lbl4;
	JTable saleList;
	JTextField menuTf;
	JButton page1Btn, page2Btn;
	JButton[] menuBtn = new JButton[25];
	JButton[] payBtn = new JButton[8];
	//업데이트 부분
	JButton ok, cancle;
	String menuName;
	int menuPrice;
	JTextField nameTf, priceTf;
	JPanel leftJp_center;
	int btnCount;
	//DB
	ResultSet rs;
	private Icon logoicon;
	

	public PosPageUpdate() {
		DB.init();
		
		// 프레임 설정
		setTitle("메뉴 관리");
		setSize(1300, 800);
		setLocationRelativeTo(this);
		setResizable(false);
		setLayout(new BorderLayout());
		

		// 왼쪽 Jp
		JPanel leftJp = new JPanel();
		leftJp.setLayout(new BorderLayout());
		
		logoicon = new ImageIcon("images/poslogo.jpg");

		// 왼쪽 JP_north
		JPanel leftJp_north = new JPanel();
		leftJp_north.setLayout(new FlowLayout());
		leftJp_north.setPreferredSize(new Dimension(400,250));
		mark = new JButton();
		mark.setIcon(logoicon);
		mark.setPreferredSize(new Dimension(70, 70));
		mark.setContentAreaFilled(false);		//채우기 없음
		mark.setBorderPainted(false); 		//테두리
		mark.setFocusPainted(false); 		//클릭 시 테두리
		mark.setOpaque(true);
		leftJp_north.add(mark);
		leftJp.add(leftJp_north,BorderLayout.NORTH);
		
		
		// 왼쪽 JP_center (Jtable로 계산 목록 만들기)
		leftJp_center = new JPanel();
		leftJp_center.setLayout(new BorderLayout());
		JPanel lcbn = new JPanel();
		JPanel lcbc = new JPanel();
		JPanel lcbs = new JPanel();
		lcbn.setLayout(new FlowLayout());
		lcbc.setLayout(new FlowLayout());
		lcbs.setLayout(new FlowLayout());
		lcbs.setPreferredSize(new Dimension(400,170));
		JLabel lbl1 = new JLabel("메뉴명 : ");
		JLabel lbl2 = new JLabel("가    격 : ");
		nameTf = new JTextField(10);
		priceTf = new JTextField(10);
		ok = new JButton("확인");
		ok.addActionListener(this);
		cancle = new JButton("삭제");
		cancle.addActionListener(this);
		lcbn.add(lbl1);
		lcbn.add(nameTf);
		lcbc.add(lbl2);
		lcbc.add(priceTf);
		lcbs.add(ok);
		lcbs.add(cancle);
		leftJp_center.add(lcbn,BorderLayout.NORTH);
		leftJp_center.add(lcbc,BorderLayout.CENTER);
		leftJp_center.add(lcbs,BorderLayout.SOUTH);
		leftJp.add(leftJp_center,BorderLayout.CENTER);
		
		
		leftJp_center.setVisible(false);
		
		
		// 왼쪽 JP_south
		JPanel leftJp_south = new JPanel();
		leftJp_south.setLayout(new GridLayout(2,4));
		leftJp_south.setPreferredSize(new Dimension(500,200));
		leftJp_south.setBorder(BorderFactory.createEmptyBorder(10,10,100,20));
		lbl1 = new JLabel("설정된 메뉴명 : ",JLabel.RIGHT);
		//lbl1.setOpaque(true);
		lbl1.setBackground(Color.blue);
		lbl1.setFont(new Font("돋움", Font.BOLD, 16));
		lbl2 = new JLabel("설정된 가   격  : ",JLabel.RIGHT);
		//lbl2.setOpaque(true);
		lbl2.setBackground(Color.red);
		lbl2.setFont(new Font("돋움", Font.BOLD, 16));
		lbl3 = new JLabel("");
		lbl3.setFont(new Font("돋움", Font.BOLD, 16));
		lbl4 = new JLabel("");
		lbl4.setFont(new Font("돋움", Font.BOLD, 16));
		
		leftJp_south.add(lbl1);
		leftJp_south.add(lbl3);
		leftJp_south.add(lbl2);
		leftJp_south.add(lbl4);
		leftJp.add(leftJp_south, BorderLayout.SOUTH);

		// 오른쪽 Jp
		JPanel rightJp = new JPanel();
		rightJp.setLayout(new BorderLayout());
	
		
		// 오른쪽 Jp_center
		JPanel rightJp_center = new JPanel();
		JPanel rcf = new JPanel();
		rcf.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
		rightJp_center.setLayout(new GridLayout(5,5,10,10));
		rightJp_center.setPreferredSize(new Dimension(600,450));
		
		for(int i=0; i < 25;i++) {
			rs = DB.getRs("select menuname from mint.menu where btnnum = "+i);
			try {
				rs.next();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				menuBtn[i] = new JButton(rs.getString("menuname"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menuBtn[i].setFont(new Font("돋움", Font.BOLD, 11));
			menuBtn[i].addActionListener(this);
			rightJp_center.add(menuBtn[i]);
			
		}
		rcf.add(rightJp_center);
		
		
		rightJp.add(rcf,BorderLayout.CENTER);
		
		
		
		// 오른쪽 Jp_south
		JPanel rightJp_south = new JPanel();
		JPanel rsf = new JPanel();
		rsf.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
		rightJp_south.setPreferredSize(new Dimension(460,220));
		rightJp_south.setLayout(new GridLayout(2,4,20,20));
		payBtn[0] = new JButton("");
		payBtn[0].setFont(new Font("돋움",Font.BOLD,14));
		payBtn[1] = new JButton("");
		payBtn[1].setFont(new Font("돋움",Font.BOLD,14));
		payBtn[2] = new JButton("");
		payBtn[2].setFont(new Font("돋움",Font.BOLD,14));
		payBtn[3] = new JButton("pos종료");
		payBtn[3].setFont(new Font("돋움",Font.BOLD,14));
		payBtn[4] = new JButton("");
		payBtn[4].setFont(new Font("돋움",Font.BOLD,14));
		payBtn[5] = new JButton("");
		payBtn[5].setFont(new Font("돋움",Font.BOLD,12));
		payBtn[6] = new JButton("");
		payBtn[6].setFont(new Font("돋움",Font.BOLD,14));
		payBtn[7] = new JButton("");
		payBtn[7].setFont(new Font("돋움",Font.BOLD,14));
		for(int i=0; i < 8;i++) {
			rightJp_south.add(payBtn[i]);
			payBtn[i].addActionListener(this);
		}
		rsf.add(rightJp_south);
		rightJp.add(rsf,BorderLayout.SOUTH);

		// 전체 구성
		
		add(leftJp, BorderLayout.WEST);
		add(rightJp, BorderLayout.EAST);
		
		setVisible(true);

	}
	

	public static void main(String[] args) {
		
		new PosPageUpdate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		if (obj==menuBtn[0]) {
			
			leftJp_center.setVisible(true);
			btnCount = 0;
			btnSkill();
			
		} else if(obj==menuBtn[1]) {
			leftJp_center.setVisible(true);
			btnCount = 1;
			btnSkill();
		}else if(obj==menuBtn[2]) {
			leftJp_center.setVisible(true);
			btnCount = 2;
			btnSkill();
		}else if(obj==menuBtn[3]) {
			leftJp_center.setVisible(true);
			btnCount = 3;
			btnSkill();
		}else if(obj==menuBtn[4]) {
			leftJp_center.setVisible(true);
			btnCount = 4;
			btnSkill();
		}else if(obj==menuBtn[5]) {
			leftJp_center.setVisible(true);
			btnCount = 5;
			btnSkill();
		}else if(obj==menuBtn[6]) {
			leftJp_center.setVisible(true);
			btnCount = 6;
			btnSkill();
		}else if(obj==menuBtn[7]) {
			leftJp_center.setVisible(true);
			btnCount = 7;
			btnSkill();
		}else if(obj==menuBtn[8]) {
			leftJp_center.setVisible(true);
			btnCount = 8;
			btnSkill();
		}else if(obj==menuBtn[9]) {
			leftJp_center.setVisible(true);
			btnCount = 9;
			btnSkill();
		}else if(obj==menuBtn[10]) {
			leftJp_center.setVisible(true);
			btnCount = 10;
			btnSkill();
		}else if(obj==menuBtn[11]) {
			leftJp_center.setVisible(true);
			btnCount = 11;
			btnSkill();
		}else if(obj==menuBtn[12]) {
			leftJp_center.setVisible(true);
			btnCount = 12;
			btnSkill();
		}else if(obj==menuBtn[13]) {
			leftJp_center.setVisible(true);
			btnCount = 13;
			btnSkill();
		}else if(obj==menuBtn[14]) {
			leftJp_center.setVisible(true);
			btnCount = 14;
			btnSkill();
		}else if(obj==menuBtn[15]) {
			leftJp_center.setVisible(true);
			btnCount = 15;
			btnSkill();
		}else if(obj==menuBtn[16]) {
			leftJp_center.setVisible(true);
			btnCount = 16;
			btnSkill();
		}else if(obj==menuBtn[17]) {
			leftJp_center.setVisible(true);
			btnCount = 17;
			btnSkill();
		}else if(obj==menuBtn[18]) {
			leftJp_center.setVisible(true);
			btnCount = 18;
			btnSkill();
		}else if(obj==menuBtn[19]) {
			leftJp_center.setVisible(true);
			btnCount = 19;
			btnSkill();
		}else if(obj==menuBtn[20]) {
			leftJp_center.setVisible(true);
			btnCount = 20;
			btnSkill();
		}else if(obj==menuBtn[21]) {
			leftJp_center.setVisible(true);
			btnCount = 21;
			btnSkill();
		}else if(obj==menuBtn[22]) {
			leftJp_center.setVisible(true);
			btnCount = 22;
			btnSkill();
		}else if(obj==menuBtn[23]) {
			
			leftJp_center.setVisible(true);
			btnCount = 23;
			btnSkill();
		}else if(obj==menuBtn[24]) {
			leftJp_center.setVisible(true);
			btnCount = 24;
			btnSkill();
		}else if(obj==ok) {
			if(nameTf.getText().toString().equals("")) {
				JOptionPane.showMessageDialog(null,"메뉴명을 입력하세요");
				System.out.println("메뉴명을 입력하세요");
				}else if(priceTf.getText().toString().equals("")){
					JOptionPane.showMessageDialog(null,"가격을 입력하세요");
					System.out.println("가격을 입력하세요");
				}else if(!isInteger(priceTf.getText().toString())) {
					JOptionPane.showMessageDialog(null,"가격은 숫자로만 입력하세요");
					System.out.println("가격은 숫자로만 입력하세요");
				}else {
					menuName = nameTf.getText().toString();
					menuPrice = Integer.parseInt(priceTf.getText());
					System.out.println(menuName);
					System.out.println(menuPrice);
					menuBtn[btnCount].setText(menuName);
					DB.executeQuery("UPDATE MINT.MENU SET MENUNAME='"+menuName+"', PRICE="+menuPrice+" WHERE BTNNUM="+btnCount);
					DB.close();				
					
					leftJp_center.setVisible(false);
					new PosPageUpdate();
					dispose();
				}
			
		}else if(obj==cancle) {
			DB.executeQuery("UPDATE MINT.MENU SET MENUNAME='', PRICE=null WHERE BTNNUM="+btnCount);
			DB.close();
			new PosPageUpdate();
			dispose();
			
		}else if(obj==payBtn[3]) {
			dispose();
		}
		
	}
	public void btnSkill() {
		
		rs= DB.getRs("Select menuname, price from mint.menu where btnnum = "+btnCount);
		
		try {
			rs.next();
			String name = rs.getString("menuname");
			int price = rs.getInt("price");
			if(name!=null) {
			lbl3.setText(name);
			lbl4.setText(price+"원");
			
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}

