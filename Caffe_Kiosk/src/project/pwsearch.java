package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
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
import javax.swing.border.EmptyBorder;

public class pwsearch extends JFrame implements ActionListener{
	
	ImageIcon pwsearchicon, searchicon, searchclickicon;
	private JTextField tfnum;
	private JButton searchbtn;
	EmptyBorder b1 = new EmptyBorder(5,3,5,3);
	private JLabel lb1;
	private ResultSet rs;

	public pwsearch() {
		DB.init();
		setTitle("PASSWORD 찾기");
		setSize(375, 280);
		setResizable(false);
		setLocationRelativeTo(this);
		setLayout(new BorderLayout());
		
		pwsearchicon = new ImageIcon("images/pwsearch.jpg");
		searchicon = new ImageIcon("images/searchicon.jpg");
		searchclickicon = new ImageIcon("images/searchclickicon.jpg");
		
		
		JPanel jp = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(pwsearchicon.getImage(), 0, 0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		jp.setLayout(null);
		add(jp);
		
		tfnum = new JTextField(10);
		tfnum.setBounds(180, 105, 90, 25);
		
		searchbtn = new JButton();
		searchbtn.setIcon(searchicon);
		searchbtn.setPressedIcon(searchclickicon);
		searchbtn.addActionListener(this);
		searchbtn.setBorder(b1);
		searchbtn.setContentAreaFilled(false);		//채우기 없음
		searchbtn.setBorderPainted(false); 		//테두리
		searchbtn.setFocusPainted(false); 		//클릭 시 테두리
		searchbtn.setOpaque(false);
		searchbtn.setBackground( new Color(0, 0, 0, 200));
		searchbtn.setBounds(270, 100, 60, 30);
		
		lb1 = new JLabel("password");
		lb1.setBounds(152, 138, 100, 35);
		
		
		jp.add(tfnum);
		jp.add(searchbtn);
		jp.add(lb1);
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new pwsearch();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == searchbtn) {
			String id = tfnum.getText();
			
			rs = DB.getRs("select password from mint.manager where id = '" + id +"'");
			try {
				rs.next();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				lb1.setText(rs.getString("password"));
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "가입된 아이디가 아닙니다.");
			}
		}
	}


}
