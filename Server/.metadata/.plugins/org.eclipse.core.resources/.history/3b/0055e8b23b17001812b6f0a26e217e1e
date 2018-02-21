package WebServer;

import java.io.* ;
import java.net.* ;
import java.util.* ;
public final class WebServer {

	public static void main(String[] args)  throws Exception{
		// TODO Auto-generated method stub
		//set the port number to 8899 instead of 80
		int port = 8899;
		//creating a socket to listen
		ServerSocket ServerEar = new ServerSocket(port);
		//loop for infinity
		//when a request comes, create a new thread
		//and socket to handle the request
		while (true) {
			Socket connectionSocket = ServerEar.accept(); 
			HttpRequest Handler = new HttpRequest(connectionSocket);
			Thread thread = new Thread(Handler);
			thread.start();
		}
		
	}

}
