package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class SignUpPage extends JFrame implements ActionListener {
	
	JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7;
	JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
	EmptyBorder b1 = new EmptyBorder(5,3,5,3);
	JButton jb1, jb2;
	ImageIcon icon;
	private ImageIcon signUpicon, signUpClickicon;
	private ImageIcon backicon, overicon, overclickicon, conicon, conclickicon;
	private ResultSet rs;
	private JButton idbtn;
	private JButton pwbtn;
	private JButton bnbtn;
	
	
	
	public SignUpPage() {
	//StartPage
			DB.init();
			setTitle("Manager SingUp");
			setSize(416, 539);
			setLocationRelativeTo(this);
			setResizable(false);
			setLayout(new BorderLayout());
			
			icon = new ImageIcon("images/signUp.jpg");
			signUpicon = new ImageIcon("images/signUpbtnNomal.jpg");
			signUpClickicon = new ImageIcon("images/signUpbtnClicked.jpg");
			backicon = new ImageIcon("images/back.jpg");
			overicon = new ImageIcon("images/over.jpg");
			overclickicon = new ImageIcon("images/overclick.jpg");
			conicon = new ImageIcon("images/conbtn.jpg");
			conclickicon = new ImageIcon("images/conclickbtn.jpg");
			
			JPanel jp = new JPanel() {
				public void paintComponent(Graphics g) {
					g.drawImage(icon.getImage(), 0, 0,null);
					setOpaque(false);
					super.paintComponent(g);
				}
			};
			jp.setLayout(null);
			add(jp);
			
			tf1= new JTextField();
			tf1.setBounds(176, 153, 130, 25);
			jp.add(tf1);
			
			tf2= new JTextField();
			tf2.setBounds(176, 183, 130, 25);
			jp.add(tf2);
			
			tf3= new JTextField();
			tf3.setBounds(176, 218, 130, 25);
			jp.add(tf3);
			
			tf4= new JTextField();
			tf4.setBounds(176, 248, 130, 25);
			jp.add(tf4);
			
			tf5= new JTextField();
			tf5.setBounds(176, 280, 130, 25);
			jp.add(tf5);
			
			tf6= new JTextField();
			tf6.setBounds(176, 310, 130, 25);
			jp.add(tf6);
			
			tf7= new JTextField();
			tf7.setBounds(176, 340, 130, 25);
			jp.add(tf7);
			
			jb1 = new JButton();
			jb1.setIcon(signUpicon);
			jb1.setPressedIcon(signUpClickicon);
			jb1.addActionListener(this);
			jp.add(jb1);
			jb1.setBorder(b1);
			jb1.setOpaque(false);
			jb1.setBackground( new Color(0, 0, 0, 200));
			jb1.setBounds(164, 377, 74, 27);
			
			jb2 = new JButton();
			jb2.setIcon(backicon);
			jp.add(jb2);
			jb2.addActionListener(this);
			jb2.setOpaque(false);
			jb2.setContentAreaFilled(false);		//채우기 없음
			jb2.setBorderPainted(false); 		//테두리
			jb2.setFocusPainted(false); 		//클릭 시 테두리
			jb2.setBorder(b1);
			jb2.setBackground(new Color(0, 0, 0, 200));
			jb2.setBounds(362, 435, 27, 27);
			
			
			idbtn = new JButton();
			idbtn.setIcon(overicon);
			idbtn.setPressedIcon(overclickicon);
			idbtn.addActionListener(this);
			idbtn.setOpaque(false);
			idbtn.setContentAreaFilled(false);		//채우기 없음
			idbtn.setBorderPainted(false); 		//테두리
			idbtn.setBackground(new Color(0, 0, 0, 200));
			idbtn.setBounds(306, 150, 50, 30);
			
			pwbtn = new JButton();
			pwbtn.setIcon(conicon);
			pwbtn.setPressedIcon(conclickicon);
			pwbtn.addActionListener(this);
			pwbtn.setOpaque(false);
			pwbtn.setContentAreaFilled(false);		//채우기 없음
			pwbtn.setBorderPainted(false); 		//테두리
			pwbtn.setBackground(new Color(0, 0, 0, 200));
			pwbtn.setBounds(306, 215, 50, 30);
			
			bnbtn = new JButton();
			bnbtn.setIcon(overicon);
			bnbtn.setPressedIcon(overclickicon);
			bnbtn.addActionListener(this);
			bnbtn.setOpaque(false);
			bnbtn.setContentAreaFilled(false);		//채우기 없음
			bnbtn.setBorderPainted(false); 		//테두리
			bnbtn.setBackground(new Color(0, 0, 0, 200));
			bnbtn.setBounds(306, 308, 50, 30);
			
			
			jp.add(idbtn);
			jp.add(pwbtn);
			jp.add(bnbtn);
			
			setVisible(true);
			}

	public static void main(String[] args) {
		new SignUpPage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String managerID = tf1.getText();
		String managerPassword = tf2.getText();
		String managerName = tf4.getText();
		String managerPhone = tf5.getText();
		String BusinessNumber = tf6.getText();
		String managerBirthday = tf7.getText();
		
		boolean check = checkID(managerID);
		boolean check2 = checkBN(BusinessNumber);
		
		if(obj == idbtn) {
			
			
			
			if(tf1.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요.");
			} else if(tf1.getText().length() < 5) {
				JOptionPane.showMessageDialog(null, "5자 이상 입력해 주세요.");
			} else if(tf1.getText().length() > 15) {
				JOptionPane.showMessageDialog(null, "15자 이하로 입력해 주세요.");
			} 
			
			
			else if(check) {
				JOptionPane.showMessageDialog(null, "이미 가입된 아이디입니다.");
			} else {
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
			}
			
			
		} else if(obj == pwbtn) {
			if(tf2.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요.");
			} else if(tf2.getText().length() < 8) {
				JOptionPane.showMessageDialog(null, "8자 이상 입력해 주세요.");
			} else if(tf2.getText().length() > 20) {
				JOptionPane.showMessageDialog(null, "20자 이하로 입력해 주세요.");
			} else if(tf3.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "확인할 비밀번호를 입력해 주세요.");
			} else if(!tf2.getText().toString().equals(tf3.getText().toString())) {		//대상이 같지 않다
				JOptionPane.showMessageDialog(null, "비밀번호가 같지 않습니다.");
			} else if(tf2.getText().toString().equals(tf3.getText().toString())) { 		//대상이 같다(==는 주소값 비교이기 때문에 equals를 사용)
				JOptionPane.showMessageDialog(null, "비밀번호가 일치합니다.");
			} 
			
		} else if(obj == bnbtn) {
			
			
			if(tf6.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "사업자 번호를 입력해 주세요.");
			} else if(tf6.getText().length() < 5) {
				JOptionPane.showMessageDialog(null, "사업자 번호가 짧습니다. 다시 입력해 주세요.");
			} 
			
			
			else if(check2) {
				JOptionPane.showMessageDialog(null, "이미 가입된 사업자 번호입니다.");
			} else {
				JOptionPane.showMessageDialog(null, "사용 가능한 사업자 번호입니다.");
			}
		}
		
		
		else if(obj == jb2) {
			dispose();
		} else if(obj == jb1) {
			if(managerID.length() < 5) {
				JOptionPane.showMessageDialog(null, "아이디를 입력하고 중복을 확인해 주세요.");
			} else if(tf1.getText().length() > 15) {
				JOptionPane.showMessageDialog(null, "아이디를 입력하고 중복을 확인해 주세요.");
			} else if(managerPassword.length() < 8) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하고 비밀번호 확인을 해 주세요.");
			} else if(tf3.getText().length() < 8) {
				JOptionPane.showMessageDialog(null, "확인 비밀번호를 입력하고 비밀번호 확인을 해 주세요.");
			} else if(!tf2.getText().toString().equals(tf3.getText().toString())) {		//대상이 같지 않다
				JOptionPane.showMessageDialog(null, "비밀번호가 같지 않습니다. 비밀번호 확인을 해 주세요.");
			} else if(tf2.getText().length() > 20) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하고 비밀번호 확인을 해 주세요.");
			} else if(BusinessNumber.length() < 5) {
				JOptionPane.showMessageDialog(null, "사업자 번호를 입력하고 중복을 확인해 주세요.");
			} else if(check) {
				JOptionPane.showMessageDialog(null, "이미 가입된 아이디입니다. 아이디 중복을 확인해 주세요.");
			} else if(check2) {
				JOptionPane.showMessageDialog(null, "이미 가입된 사업자 번호입니다. 사업자 번호 중복을 확인해 주세요.");
			} else if(managerName.length() == 0) {
				JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.");
			} else if(managerName.length() > 20) {
				JOptionPane.showMessageDialog(null, "이름이 길어 다시 입력해 주세요.");
			} else if(managerPhone.length() != 13) {
				JOptionPane.showMessageDialog(null, "휴대폰 번호를 입력해 주세요. (ex. 010-****-****)");
			} else if(managerBirthday.length() != 8) {
				JOptionPane.showMessageDialog(null, "생일을 8자로 입력해 주세요. (ex.19940205)");
			}
			
			else {
			
			DB.executeQuery( "INSERT INTO MANAGER " + "VALUES"
					+ "('" + managerID + "', '" + managerPassword + "', '" + managerName + "', '" + 
					managerPhone + "', '" + BusinessNumber + "', '" + managerBirthday + "')");
			
			
			JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.");
			
			dispose();
			}
			
		}
		
	}

	private boolean checkBN(String businessNumber) {
		boolean check2 = false;
		String sql = "select id from manager where businessnum = '" + businessNumber +"'";
		ResultSet rs = DB.getRs(sql);
		try {
			if(rs.next()) {
				check2 = true;
			} else {
				check2 = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check2;
	}

	private boolean checkID(String managerID) {
		boolean check = false;
		String sql = "select id from manager where id = '" + managerID +"'";
		ResultSet rs = DB.getRs(sql);
		try {
			if(rs.next()) {
				check = true;
			} else {
				check = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	
}
