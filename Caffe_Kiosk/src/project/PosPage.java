package project;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import jdk.jshell.Diag;

public class PosPage extends JFrame implements ActionListener {

	JButton mark;
	JTable saleList;
	JTextField menuTf;
	public JButton[] menuBtn = new JButton[16];
	public JButton[] payBtn = new JButton[8];
	private ResultSet rs;
	int many;
	int btnNum;
	DefaultTableModel model;
	JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10;
	int sumPrice = 0;
	public int discount = 0;

	int finalPrice = 0;
	int point = 0;
	int puPoint;
	String phone;
	int year, month, day;
	PointUse pu;
	PointUse puu;
	private ImageIcon logoicon;
	

	public PosPage(int d) {
		this.discount = d;
	}
	
	public PosPage() {
		
		
		
		DB.init();
		// 프레임 설정
		setTitle("pos");
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
		mark = new JButton();
		mark.setIcon(logoicon);
		mark.setPreferredSize(new Dimension(70, 70));
		mark.setContentAreaFilled(false);		//채우기 없음
		mark.setBorderPainted(false); 		//테두리
		mark.setFocusPainted(false); 		//클릭 시 테두리
		mark.setOpaque(true);
		leftJp_north.add(mark);
		leftJp.add(leftJp_north, BorderLayout.NORTH);

		// 왼쪽 JP_center (Jtable로 계산 목록 만들기)

		String[] title = { "상품명", "수량", "금액" };
		model = new DefaultTableModel(title, 0);
		saleList = new JTable(model);
		JScrollPane sp = new JScrollPane(saleList);
		sp.setPreferredSize(new Dimension(580, 400));

		leftJp.add(sp, BorderLayout.CENTER);

		// 왼쪽 JP_south
		JPanel leftJp_south = new JPanel();
		leftJp_south.setLayout(new GridLayout(3, 2));
		

		leftJp_south.setPreferredSize(new Dimension(500, 250));
		leftJp_south.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
		lbl1 = new JLabel("총 수량 : ", JLabel.CENTER);
		lbl1.setFont(new Font("돋움", Font.BOLD, 16));
		lbl2 = new JLabel("합계 금액 : ", JLabel.CENTER);
		lbl2.setFont(new Font("돋움", Font.BOLD, 16));
		lbl4 = new JLabel("총금액 : ", JLabel.CENTER);
		lbl4.setFont(new Font("돋움", Font.BOLD, 16));
		lbl6 = new JLabel("0000000000");
		lbl6.setFont(new Font("돋움", Font.BOLD, 16));
		lbl7 = new JLabel("0000000000");
		lbl7.setFont(new Font("돋움", Font.BOLD, 16));
		lbl9 = new JLabel("0000000000");
		lbl9.setFont(new Font("돋움", Font.BOLD, 16));
		leftJp_south.add(lbl1);
		leftJp_south.add(lbl6);
		leftJp_south.add(lbl2);
		leftJp_south.add(lbl7);
		leftJp_south.add(lbl4);
		leftJp_south.add(lbl9);
		leftJp.add(leftJp_south, BorderLayout.SOUTH);

		// leftJp_south.setPreferredSize(new Dimension(400,100));

		// 오른쪽 Jp
		JPanel rightJp = new JPanel();
		rightJp.setLayout(new BorderLayout());

		// 오른쪽 Jp_center
		JPanel rightJp_center = new JPanel();
		JPanel rcf = new JPanel();
		rcf.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
		rightJp_center.setLayout(new GridLayout(4, 4, 10, 10));
		rightJp_center.setPreferredSize(new Dimension(600, 450));

		for (int i = 0; i < 16; i++) {//메뉴 버튼 0~15까지 생성
			menuBtn[i] = new JButton(new ImageIcon("images//menuImage//menu"+i+".png"));
			menuBtn[i].addActionListener(this);
			rightJp_center.add(menuBtn[i]);

		}
		rcf.add(rightJp_center);

		rightJp.add(rcf, BorderLayout.CENTER);

		// 오른쪽 Jp_south
		JPanel rightJp_south = new JPanel();
		JPanel rsf = new JPanel();
		rsf.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		rightJp_south.setPreferredSize(new Dimension(480, 220));
		rightJp_south.setLayout(new GridLayout(2, 4, 20, 20));
		payBtn[0] = new JButton("전체 삭제");
		payBtn[0].setFont(new Font("돋움", Font.BOLD, 15));
		payBtn[1] = new JButton("선택 삭제");
		payBtn[1].setFont(new Font("돋움", Font.BOLD, 15));
		payBtn[2] = new JButton("pos종료");
		payBtn[2].setFont(new Font("돋움", Font.BOLD, 15));
		payBtn[3] = new JButton("결제");
		payBtn[3].setFont(new Font("돋움", Font.BOLD, 15));
		payBtn[4] = new JButton("포인트 조회");
		payBtn[4].setFont(new Font("돋움", Font.BOLD, 13));
		payBtn[5] = new JButton("포인트 사용");
		payBtn[5].setFont(new Font("돋움", Font.BOLD, 13));
		payBtn[6] = new JButton("회원 등록");
		payBtn[6].setFont(new Font("돋움", Font.BOLD, 15));
		payBtn[7] = new JButton("매출액");
		payBtn[7].setFont(new Font("돋움", Font.BOLD, 15));
		for (int i = 0; i < 8; i++) {
			rightJp_south.add(payBtn[i]);
			payBtn[i].addActionListener(this);
		}
		rsf.add(rightJp_south);
		rightJp.add(rsf, BorderLayout.SOUTH);

		// 전체 구성
		
		
		add(leftJp, BorderLayout.WEST);
		add(rightJp, BorderLayout.EAST);
		setVisible(true);

	}

	public static void main(String[] args) {
		new PosPage();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if (obj == menuBtn[0]) {
			Object[] r = {"아메리카노 (ICE)","1","2000"};
			model.addRow(r);
			sumPrice = sumPrice + 2000;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
			
		} else if (obj == menuBtn[1]) {
			Object[] r = {"아메리카노 (HOT)","1","2000"};
			model.addRow(r);
			sumPrice = sumPrice + 2000;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[2]) {
			Object[] r = {"카페라떼 (ICE)","1","3000"};
			model.addRow(r);
			sumPrice = sumPrice + 3000;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[3]) {
			Object[] r = {"카페라떼 (HOT)","1","3000"};
			model.addRow(r);
			sumPrice = sumPrice + 3000;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[4]) {
			Object[] r = {"카페모카 (ICE)","1","3000"};
			model.addRow(r);
			sumPrice = sumPrice + 3000;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[5]) {
			Object[] r = {"카페모카 (HOT)","1","3000"};
			model.addRow(r);
			sumPrice = sumPrice + 3000;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[6]) {
			Object[] r = {"연유라떼 (ICE)","1","3000"};
			model.addRow(r);
			sumPrice = sumPrice + 3000;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[7]) {
			Object[] r = {"연유라떼 (HOT)","1","3000"};
			model.addRow(r);
			sumPrice = sumPrice + 3000;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[8]) {
			Object[] r = {"아인슈패너 (ICE)","1","3000"};
			model.addRow(r);
			sumPrice = sumPrice + 3000;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[9]) {
			Object[] r = {"콜드브루 (ICE)","1","2500"};
			model.addRow(r);
			sumPrice = sumPrice + 2500;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[10]) {
			Object[] r = {"바닐라 라떼(ICE)","1","3000"};
			model.addRow(r);
			sumPrice = sumPrice + 3000;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[11]) {
			Object[] r = {"바닐라 라떼(HOT)","1","3000"};
			model.addRow(r);
			sumPrice = sumPrice + 3000;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[12]) {
			Object[] r = {"자몽에이드 (ICE)","1","2500"};
			model.addRow(r);
			sumPrice = sumPrice + 2500;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[13]) {
			Object[] r = {"레몬에이드 (ICE)","1","2500"};
			model.addRow(r);
			sumPrice = sumPrice + 2500;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[14]) {
			Object[] r = {"블루 레몬에이드 (ICE)","1","2800"};
			model.addRow(r);
			sumPrice = sumPrice + 2800;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == menuBtn[15]) {
			Object[] r = {"아이스티 (ICE)","1","2000"};
			model.addRow(r);
			sumPrice = sumPrice + 2000;
			many++;
			lbl6.setText(many + "개");
			lbl7.setText(sumPrice + "원");
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
		} else if (obj == payBtn[0]) {

			model.setNumRows(0);
			many = 0;
			sumPrice = 0;
			point = 0;
			finalPrice = 0;
			lbl7.setText(sumPrice + "원");
			lbl6.setText("0개");
			lbl9.setText(finalPrice + "원");
		} else if (obj == payBtn[1]) {//선택 취소 버튼

			int n = saleList.getSelectedRow();
			if (n >= 0) {
				sumPrice = sumPrice - Integer.parseInt(saleList.getValueAt(n, 2).toString());
				many--;
				model.removeRow(n);
				lbl7.setText(sumPrice + "원");
				lbl6.setText(many + "개");
				finalPrice = sumPrice - discount;
				lbl9.setText(finalPrice + "원");
				point = (int) (finalPrice * 0.05);
			}

		} else if (obj == payBtn[2]) {//포스 종료 버튼
			dispose();
		} else if (obj == payBtn[3]) {//결제 완료 버튼
			if (sumPrice != 0) {
				
					
						JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");
						DB.executeQuery("update mint.member set point=0 where phone ='"+phone+"'");
						model.setNumRows(0);
						many = 0;
						lbl7.setText(sumPrice + "원");
						lbl6.setText("0개");
						finalPrice = sumPrice - discount;
						lbl9.setText(finalPrice + "원");

						//DB.executeQuery("insert into mint.sales values (sysdate," + finalPrice + ")");
						int answer = JOptionPane.showConfirmDialog(null, "포인트를 적립하시겠습니까?","포인트 적립",JOptionPane.YES_NO_OPTION);
						if(answer==JOptionPane.YES_OPTION) {
						puu = new PointUse();
						puu.use.addActionListener(this);
						}
					
			
			}

		} else if (obj == payBtn[4]) {//포인트 검색 버튼 ->바꿀껀데 새로운 창이 떠야해
			
		} else if (obj == payBtn[5]) {//포인트 사용 버튼
			
			pu = new PointUse();
			pu.use.addActionListener(this);
				
			}else if (obj == payBtn[6]) {//멤버 회원 가입 버튼
			new MemberSignIn();
		} else if (obj == payBtn[7]) {
			new SalesPrice();
		}  else if (obj == pu.use) {
			
			discount = pu.getPoint();
			lbl8.setText(Integer.toString(discount));
			finalPrice = sumPrice - discount;
			lbl9.setText(finalPrice + "원");
			point = (int) (finalPrice * 0.05);
			phone = pu.getPhone();
		}  else if (obj==puu.use) {
			phone = puu.getPhone();
			DB.executeQuery("update mint.member set point="+point+" where phone ='"+phone+"'");
		}

	}


	public void getDate() {
		Calendar cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
	}

}
