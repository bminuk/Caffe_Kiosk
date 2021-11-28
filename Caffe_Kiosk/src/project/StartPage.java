package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class StartPage extends JFrame implements ActionListener  {

	JTextField tfId;
	JPasswordField tfPw;
	JButton signUpbtn, logInBtn, exitBtn;
	ImageIcon managerLoginIcon, logInNomalIcon, logInClickedIcon, exitIcon, exitClickedIcon;
	EmptyBorder b1 = new EmptyBorder(5,3,5,3);
	private JButton idsbtn;
	private JButton pwsbtn;
	private JButton joinbtn;

	public StartPage() {
		
		// StartPage
		DB.init();
		setTitle("Caffe pos");
		setSize(616, 489);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setLayout(new BorderLayout());
		
		
		
		managerLoginIcon = new ImageIcon("images/managerLoginPage.jpg");
		logInNomalIcon = new ImageIcon("images/logInbtnNomal.jpg");
		logInClickedIcon = new ImageIcon("images/logInbtnClicked.jpg");
		exitIcon = new ImageIcon("images/exitbtn.jpg");
		exitClickedIcon = new ImageIcon("images/exitClicked.jpg");
		
		
		
		JPanel jp = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(managerLoginIcon.getImage(), 0, 0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		add(jp);
		
		jp.setLayout(null);
		
		tfId = new JTextField(10);
	
		tfId.setBounds(285, 192, 130, 30);
		
		tfPw = new JPasswordField(10);
		tfPw.setBounds(285, 222, 130, 30);
		
		
		idsbtn = new JButton();
		idsbtn.addActionListener(this);
		idsbtn.setContentAreaFilled(false);		//채우기 없음
		idsbtn.setBorderPainted(false); 		//테두리
		idsbtn.setBounds(220, 300, 44, 17);
		
		pwsbtn = new JButton();
		pwsbtn.addActionListener(this);
		pwsbtn.setContentAreaFilled(false);		//채우기 없음
		pwsbtn.setBorderPainted(false); 		//테두리
		pwsbtn.setBounds(278, 300, 44, 17);
		
		joinbtn = new JButton();
		joinbtn.addActionListener(this);
		joinbtn.setContentAreaFilled(false);		//채우기 없음
		joinbtn.setBorderPainted(false); 		//테두리
		joinbtn.setBounds(332, 300, 44, 17);
		
		
		logInBtn = new JButton();
		logInBtn.setIcon(logInNomalIcon);
		logInBtn.setPressedIcon(logInClickedIcon);
		logInBtn.addActionListener(this);
		logInBtn.setBorder(b1);
		logInBtn.setOpaque(false);
		logInBtn.setBounds(267, 262, 74, 28);
		
		exitBtn = new JButton();
		exitBtn.setIcon(exitIcon);
		exitBtn.setPressedIcon(exitClickedIcon);
		exitBtn.addActionListener(this);
		exitBtn.setBorder(b1);
		exitBtn.setOpaque(false);
		exitBtn.setBackground( new Color(0, 0, 0, 200));
		exitBtn.setBounds(284, 362, 32, 32);
		
		//프레임 구성;
		
		
		jp.add(logInBtn);
		jp.add(exitBtn);
		jp.add(tfId);
		jp.add(tfPw);
		jp.add(idsbtn);
		jp.add(pwsbtn);
		jp.add(joinbtn);
		
		
		setVisible(true);
	}
	

	public static void main(String[] args) {
		new StartPage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == logInBtn) {
			String id = tfId.getText();
			String pw = tfPw.getText();
			
			boolean check = checkIDPW(id, pw);
			if(check) {
				JOptionPane.showMessageDialog(null, "접속해 주셔서 감사합니다.");
				System.out.println("로그인 성공");
				new MainPage();
			} else {
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 잘못되었습니다.");
				System.out.println("로그인 실패");
			}
			
			
		}else if(obj == exitBtn) {
			System.exit(0);
		}else if(obj == idsbtn) {
			new idsearch();
		}else if(obj == pwsbtn) {
			new pwsearch();
		}else if(obj == joinbtn) {
			new SignUpPage();
		}
	}


	private boolean checkIDPW(String id, String pw) {
		boolean check = false;
		String sql = "SELECT * FROM MIN.MANAGER WHERE ID = '" + id + "' AND PW = '" + pw + "'";
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
