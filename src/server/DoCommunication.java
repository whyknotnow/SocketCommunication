package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class DoCommunication implements Runnable {
    
	private Socket server;
    private String input;

    DoCommunication(Socket server) {
      this.server=server;
    }

    @SuppressWarnings("deprecation")
	public void run() {
    	input="";

    	try {
        // Get input from the client
    		DataInputStream in = new DataInputStream (server.getInputStream());
    		PrintStream out = new PrintStream(server.getOutputStream());
    		
    	//"telnet" application has no "cls" (clear screen) command, so
    	//i will clear the screen with send this code to telnet application
    		out.print("\u001B[2J");
    		out.flush();
    		
    	//get address value
    		input=in.readLine();    		

    		InetAddress hostAddr = InetAddress.getByName(input);
    		String IPaddr = hostAddr.getHostAddress();
        
        // Now write to the client
    		System.out.println("IP address is:" + IPaddr);
    		out.println("IP address is:" + IPaddr);

    		server.close();
    	} catch (IOException ioe) {
    		System.out.println("IOException on socket listen: " + ioe);
    		ioe.printStackTrace();
    	}
    }
}
