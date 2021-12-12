package project.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import project.PosPage;
import project.Kiosk.KioskMainPage;

public class ServerClient {
	Socket socket;
	
	public ServerClient(Socket socket) {
		this.socket = socket;
		receive();
	}

	public void receive() {
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true) {
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
						OrderVO ov = (OrderVO)ois.readObject();
//						InputStream in = socket.getInputStream();
//						byte[] buffer = new byte[512];
//						int length = in.read(buffer);
//						while(length==-1) throw new IOException();
						System.out.println("메시지 수신 성공"+socket.getRemoteSocketAddress()+" : "+Thread.currentThread().getName());
//						String message = new String(buffer,0,length,"UTF-8");
						for(ServerClient client : MyServer.clients) {
							client.send(ov);
						}
						
					}
				}catch (Exception e) {
					System.out.println("메세지 수신 오류");
				}
				
			}
		};
		MyServer.threadPool.submit(thread);
	}
	
	public void send(OrderVO ovSend) {
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				try {
					
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					OrderVO ov = ovSend;
					oos.writeObject(ov);
					oos.flush();
//					OutputStream out = socket.getOutputStream();
//					byte[] buffer = message.getBytes("UTF-8");
//					out.write(buffer);
//					out.flush();
					System.out.println("메세지 잘 보냈음");
				}catch (Exception e) {
					System.out.println("메세지 송신 오류");
				}
				
			}
		};
		MyServer.threadPool.submit(thread);
	}
}
