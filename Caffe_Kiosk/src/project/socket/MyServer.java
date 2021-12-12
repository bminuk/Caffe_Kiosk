package project.socket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {
	public static ExecutorService threadPool;
	public static Vector<ServerClient> clients = new Vector<ServerClient>();
	
	ServerSocket serverSocket;
	public static void main(String[] args) {
		
		
	}
	
	public void startSever(String IP,int port) {
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(IP, port));
			System.out.println("서버 열림");
			
		}catch (Exception e) {
			e.printStackTrace();
			if(!serverSocket.isClosed()) {
				stopServer();
			}
			return;
		}
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Socket socket = serverSocket.accept();
						clients.add(new ServerClient(socket));
						System.out.println("클라이언트 접속"+
						socket.getRemoteSocketAddress() +":"+Thread.currentThread().getName());
					}catch (Exception e) {
						if(!serverSocket.isClosed()) {
							stopServer();
						}
					}
				}
				
			}
		};
		threadPool = Executors.newCachedThreadPool();
		threadPool.submit(thread);
	}

	public void stopServer() {
		try {
			Iterator<ServerClient> iterator = clients.iterator();
			while(iterator.hasNext()) {
				ServerClient client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			if(threadPool != null&&threadPool.isShutdown()) {
				threadPool.shutdown();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	

}
