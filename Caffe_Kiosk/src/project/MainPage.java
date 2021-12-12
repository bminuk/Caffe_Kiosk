package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPage extends JFrame implements MouseListener {
	
	JLabel pos, sale, exit;
	ImageIcon mainPageIcon;
	
	public MainPage() {
		
		//프레임 설정
		setTitle("Main");
		setSize(617, 489);
		setLocationRelativeTo(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		mainPageIcon = new ImageIcon("images/mainPageImage.jpg");
		
		JPanel jp = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(mainPageIcon.getImage(), 0, 0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		jp.setLayout(null);
		
		pos = new JLabel();
		pos.setBounds(168, 332, 66, 60);
//		pos.setOpaque(true);
//		pos.setBackground(Color.black);
		pos.addMouseListener(this);
		
		sale = new JLabel();
		sale.setBounds(270, 332, 60, 50);
//		sale.setOpaque(true);
//		sale.setBackground(Color.blue);
		sale.addMouseListener(this);
		
		exit = new JLabel();
		exit.setBounds(370, 332, 50, 50);
//		exit.setOpaque(true);
//		exit.setBackground(Color.red);
		exit.addMouseListener(this);
		
		//전체 구성
		add(jp);
		jp.add(pos);
		jp.add(sale);
		jp.add(exit);
		
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainPage();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if(obj == pos) {
			new PosPage();
		}else if(obj == sale) {
			new SalesPrice();
		}else if(obj == exit) {
			System.exit(0);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
