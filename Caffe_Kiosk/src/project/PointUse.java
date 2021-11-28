package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PointUse extends JFrame implements ActionListener {
	
	JLabel lbl1, lbl2;
	JLabel userName, userPoint;
	JTextField phoneTf;
	JButton ok,use;
	ResultSet rs;
	String phone;
	public String getPhone() {
		return phone;
	}
	int point;
	
	
	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	private String name;
	private JButton cancle;
	
	public PointUse() {
		
		DB.init();
		setTitle("포인트 사용");
		setSize(400,250);
		setLocationRelativeTo(this);
		setResizable(false);
		setLayout(new BorderLayout());
		
		//north
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		lbl1=new JLabel("전화번호 : ");
		lbl1.setFont(new Font("돋움", FlowLayout.CENTER, 13));
		phoneTf = new JTextField(10);
		
		north.add(lbl1);
		north.add(phoneTf);
		//center
		JPanel center = new JPanel();
		ok = new JButton("조회");
		ok.addActionListener(this);
		cancle = new JButton("취소");
		cancle.addActionListener(this);
		center.add(ok);
		center.add(cancle);
		
		//south
		JPanel south = new JPanel();
		south.setPreferredSize(new Dimension(70,80));
		lbl2 = new JLabel("님의 잔여 포인트는 ");
		lbl2.setFont(new Font("돋움", FlowLayout.CENTER, 15));
		JLabel lbl3 = new JLabel("point 입니다.");
		lbl3.setFont(new Font("돋움", FlowLayout.CENTER, 15));
		userName = new JLabel("");
		userName.setPreferredSize(new Dimension(50,20));
		userName.setFont(new Font("돋움", FlowLayout.CENTER, 15));
		userPoint = new JLabel("");
		userPoint.setPreferredSize(new Dimension(50,20));
		userPoint.setFont(new Font("돋움", FlowLayout.CENTER, 15));
		use = new JButton("확인");
		use.addActionListener(this);
		
		
		south.add(userName);
		south.add(lbl2);
		south.add(userPoint);
		south.add(lbl3);
		south.add(use);
		
		
		//전체 구현
		add(north,BorderLayout.NORTH);
		add(center,BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	public static void main(String[] args) {
	//	new PointUse();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==ok) {
			phone = phoneTf.getText().toString();
			rs = DB.getRs("select name,point from mint.member where phone = '"+phone+"'");
			try {
				rs.next();
				name = rs.getString(1);
				point = rs.getInt(2);
				userName.setText(name);
				userPoint.setText(Integer.toString(point));
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "등록되지 않은 번호입니다.");
			}
		}else if(obj==cancle) {
			dispose();
		}else if(obj==use) {
			dispose();
		}
	}

}
