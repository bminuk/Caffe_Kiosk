package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class SalesPrice extends JFrame implements ActionListener {
	
	String[] day= new String[31];
	String[] month= new String[12];
	String[] day2= new String[31];
	String[] month2= new String[12];
	JButton search, exit;
	JComboBox<String> cb1, cb2, cb3, cb4, cb5, cb6;
	JTable tb;
	DefaultTableModel dm;
	String firstDate;
	String secondDate;
	ResultSet rs;
	private Object[] data;
	JPanel south;
	int sumSalePrice = 0;
	JLabel sumSales;
	
	public SalesPrice() {
		DB.init();
		setTitle("매출액 확인");
		setLayout(new BorderLayout());
		setSize(700,600);
		setLocationRelativeTo(this);
		setResizable(false);
		
		//south
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		
		String year[] = {"2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029"} ;
		
		cb1 = new JComboBox<String>(year);
		north.add(cb1);
		JLabel lbl1 = new JLabel("년");
		north.add(lbl1);
		
		for(int i =0 ; i<9;i++) {
			month[i]="0"+(i+1);
		}
		for(int i = 9 ;i<12;i++) {
			month[i]=Integer.toString(i+1);
		}
		
		cb2 = new JComboBox<String>(month);
		north.add(cb2);
		JLabel lbl2 = new JLabel("월");
		north.add(lbl2);
		for(int i=0;i<9;i++) {
			day[i]="0"+(i+1);
		}
		for(int i=9;i<31;i++) {
			day[i]=Integer.toString(i+1);
		}
		
		cb3 = new JComboBox<String>(day);
		north.add(cb3);
		JLabel lbl3 = new JLabel("일");
		north.add(lbl3);
		
		JLabel between = new JLabel("~");
		north.add(between);
		
		String[] year2 = {"2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029"} ;
		
		cb4 = new JComboBox<String>(year2);
		north.add(cb4);
		JLabel lbl4 = new JLabel("년");
		north.add(lbl4);
		
		
		for(int i =0 ; i<9;i++) {
			month2[i]="0"+(i+1);
		}
		for(int i = 9 ;i<12;i++) {
			month2[i]=Integer.toString(i+1);
		}
		
		cb5 = new JComboBox<String>(month2);
		north.add(cb5);
		JLabel lbl5 = new JLabel("월");
		north.add(lbl5);
		
		for(int i=0;i<9;i++) {
			day2[i]="0"+(i+1);
		}
		for(int i=9;i<31;i++) {
			day2[i]=Integer.toString(i+1);
		}
		
		cb6 = new JComboBox<String>(day2);
		north.add(cb6);
		JLabel lbl6 = new JLabel("일");
		north.add(lbl6);
		
		
		//center
		search = new JButton("조회");
		search.addActionListener(this);
		exit = new JButton("종료");
		exit.addActionListener(this);
		JPanel center = new JPanel();
		center.setLayout(new FlowLayout());
		
		center.add(search);
		center.add(exit);
		
		
		
		//south_north
		south = new JPanel();
		south.setLayout(new BorderLayout());
		JPanel sn = new JPanel();
		sn.setLayout(new FlowLayout());
		String[] title = {"날짜","금액"};
		dm = new DefaultTableModel(title,0);
		tb = new JTable(dm);
		JScrollPane sp = new JScrollPane(tb);
	
		sn.add(sp);
		south.add(sn,BorderLayout.NORTH);
		//south_center
		JPanel sc = new JPanel();
		sc.setLayout(new GridLayout(1,2));
		sc.setPreferredSize(new Dimension(30,60));
		JLabel sumSale = new JLabel("합계 : ",JLabel.RIGHT);
		sumSale.setFont(new Font("돋움", Font.BOLD, 17));
		sumSales = new JLabel();
		sumSales.setFont(new Font("돋움", Font.BOLD, 17));
		sc.add(sumSale);
		sc.add(sumSales);
		south.add(sc,BorderLayout.CENTER);
		
		
		add(north,BorderLayout.NORTH);
		add(center,BorderLayout.CENTER);
		add(south,BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new SalesPrice();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==search) {
			dm.setNumRows(0);
			sumSalePrice = 0;
			firstDate =  cb1.getSelectedItem().toString()+cb2.getSelectedItem().toString()+cb3.getSelectedItem().toString();
			secondDate = cb4.getSelectedItem().toString()+cb5.getSelectedItem().toString()+cb6.getSelectedItem().toString();
			
			
			System.out.println(firstDate);
			System.out.println(secondDate);
			rs=DB.getRs("SELECT * FROM SALES WHERE \"date\" between TO_date('"+firstDate+"','yyyymmdd') and to_date('"+secondDate+"','yyyymmdd')");
			
			try {
				
				
				while(rs.next()) {
					data = new Object[]{rs.getDate("date"),rs.getInt("price")};
					dm.addRow(data);
					sumSalePrice = sumSalePrice + rs.getInt(3);
				}
				sumSales.setText(sumSalePrice+"");
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}else if(obj==exit) {
				dispose();
			}
			
		}
	

}
