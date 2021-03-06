package project.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import project.OrderCheck;

public class Client {
Socket socket;
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
				InputStream in = socket.getInputStream();
				byte[] buffer = new byte[512];
				int length = in.read(buffer);
				if(length == -1)throw new IOException();
				String message = new String(buffer,0,length,"UTF-8");
				
				System.out.println(message);
				OrderCheck.setOrderMessage(message);
				
			}catch (Exception e) {
				stopClient();
				break;
			}
			
		}
		
	}
	//서버로 메시지를 전송하는 메소드.
	public void send(OrderVO orderVo) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

					out.writeObject(orderVo);
					out.flush();
					
				}catch (Exception e) {
					stopClient();
				}
			}
		};
		thread.start();
	}
	
	//실제로 프로그램을 동작시키는 메소드.
}
