package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemberSignIn extends JFrame implements ActionListener {

	JLabel lbl1, lbl2;
	JLabel userName, point;
	JTextField phoneTf, nameTf;
	JButton ok, cancle;
	ResultSet rs;
	String name, phone;

	public MemberSignIn() {
		DB.init();
		setTitle("회원 등록");
		setSize(400, 250);
		setLocationRelativeTo(this);
		setLayout(new BorderLayout());
		setResizable(false);

		// north
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(2, 2, 0, 5));
		north.setPreferredSize(new Dimension(200, 120));
		north.setBorder(BorderFactory.createEmptyBorder(40, 10, 10, 95));
		lbl1 = new JLabel("이름 : ", JLabel.RIGHT);
		lbl1.setFont(new Font("돋움", FlowLayout.CENTER, 13));
		lbl2 = new JLabel("전화번호 : ", JLabel.RIGHT);
		lbl2.setFont(new Font("돋움", FlowLayout.CENTER, 13));
		nameTf = new JTextField(10);
		phoneTf = new JTextField(10);
		north.add(lbl1);
		north.add(nameTf);
		north.add(lbl2);
		north.add(phoneTf);
		// center
		JPanel center = new JPanel();
		ok = new JButton("등록");
		ok.addActionListener(this);
		cancle = new JButton("취소");
		cancle.addActionListener(this);
		center.add(ok);
		center.add(cancle);

		// 전체 구현
		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args) {
		new MemberSignIn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == ok) {

			name = nameTf.getText();
			phone = phoneTf.getText();
			if (name.equals("")||phone.equals("")) {
				JOptionPane.showMessageDialog(null, "이름과 전화번호를 모두 입력해주세요");
			}else {
				if (isInteger(phone)) {
					
					rs = DB.getRs("select name from \"member\" where phone = " + phone + "");
					

					try {
						
						rs.next();
						String n = rs.getString(1).toString();
						JOptionPane.showMessageDialog(null, "이미 등록되어 있는 번호입니다.");
						nameTf.setText("");
						phoneTf.setText("");
					} catch (SQLException e1) {
						System.out.println("등록되지 않은 번호");
						 DB.executeQuery("insert into \"member\"(name, phone) values('"+name+"',"+phone+")");
						 JOptionPane.showMessageDialog(null, "회원등록이 완료되었습니다.");
					}
					
			}else {
				JOptionPane.showMessageDialog(null, "전화번호는 숫자로만 입력하세요");
			}
			}
	} else if(obj==cancle) {
		dispose();
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
