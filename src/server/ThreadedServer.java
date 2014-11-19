package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer {
	
	private static int port=6052, maxConnections=0;
	
	// Listen for incoming connections and handle them
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int i=0;

		try{
			ServerSocket listener = new ServerSocket(port);
			Socket server;

			while((i++ < maxConnections) || (maxConnections == 0)){
				server = listener.accept();
				DoCommunication connection= new DoCommunication(server);
				Thread thread = new Thread(connection);
				thread.start();
			}
		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}
	}
}
