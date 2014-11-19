package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class DoCommunication implements Runnable {
    
	private Socket server;
    private String input;

    DoCommunication(Socket server) {
      this.server=server;
    }

    @SuppressWarnings("deprecation")
	public void run(){
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

    		String address = resolveAddress(input);
        
        // Now write to the client
    		System.out.println(address);
    		out.println(address);

    		server.close();
    	} catch ( IOException ioe) {
    		System.out.println("IOException : Unable to resolve host" + ioe);    		
    	}
    }
    
    private String resolveAddress(String input) throws UnknownHostException{
    	String IPaddr = null;
    	InetAddress hostAddr = null;
    	try {
			hostAddr = InetAddress.getByName(input);
		} catch (java.net.UnknownHostException e) {
			return "Unable the resolve host";			
		}
		IPaddr = hostAddr.getHostAddress();
		return IPaddr;    	
    }
}
