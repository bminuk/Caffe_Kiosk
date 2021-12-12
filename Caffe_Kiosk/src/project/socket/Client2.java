package project.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import project.OrderCheck;

public class Client2 {
Socket socket;
ObjectInputStream ois;
OrderVO ov;
	//클라이언트 프로그램 동작 메소드
	public void startClient(String ip,int port) {
		Thread thread = new Thread() {
			public void run() {
				try {
					socket=new Socket(ip,port);
					receive();
				}catch (Exception e) {
					if(!socket.isClosed()) {
						stopClient();
						System.out.println("서버 접속 실패");
						
					}
				}
			}
		};
		thread.start();
	}
	//클라이언트 프로그램 종료 메소드.
	public void stopClient() {
		try {
			if(socket !=null&&!socket.isClosed()) {
				socket.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	//서버로부터 메시지를 전달받는 메소드.
	public void receive() {
		while(true) {
			try {
				
//				InputStream in = socket.getInputStream();
//				byte[] buffer = new byte[512];
//				int length = in.read(buffer);
//				if(length == -1)throw new IOException();
//				String message = new String(buffer,0,length,"UTF-8");
//				
//				System.out.println(message);
//				OrderCheck.setOrderMessage(message);
//				
				
				ois = new ObjectInputStream(socket.getInputStream());
				ov = (OrderVO)ois.readObject();
				OrderCheck.lm.addElement("주문 번호 : "+ov.getOrderNum());
				OrderCheck.orderList.validate();
				OrderCheck.ov=ov;
				
				
			}catch (Exception e) {
				stopClient();
				break;
			}
			
		}
		
	}
	//서버로 메시지를 전송하는 메소드.
//	public void send(String message) {
//		Thread thread = new Thread() {
//			@Override
//			public void run() {
//				try {
//					OutputStream out = socket.getOutputStream();
//					byte[] buffer = message.getBytes("UTF-8");
//					out.write(buffer);
//					out.flush();
//				}catch (Exception e) {
//					stopClient();
//				}
//			}
//		};
//		thread.start();
//	}
	
	//실제로 프로그램을 동작시키는 메소드.
}
