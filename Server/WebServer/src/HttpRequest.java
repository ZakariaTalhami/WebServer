//package WebServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpRequest implements Runnable {
	final String CRLF = "\r\n";
	Socket socket;
	public HttpRequest(Socket socket) throws Exception
	{
		this.socket = socket;
	}
        
	@Override
	public void run() {
            
            try
            {
                processRequest();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

	}

        
        private void processRequest() throws Exception
        {
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());

            String requestLine = is.readLine();
            
            System.out.println(requestLine);
            
            StringTokenizer tokens = new StringTokenizer(requestLine);
            tokens.nextToken();
            String fileName = tokens.nextToken();
            fileName = fileName.substring(1);
            /*
             * 	this is for the Site folder 
             */
            	fileName = "../../Site/"+fileName;
            /*
             * 
             */
            System.out.println(fileName);
            
            
            
            FileInputStream fis = null;
            boolean fileExists = true;
            try
            {
                fis = new FileInputStream(fileName);
            }
            catch (FileNotFoundException e)
            {
                fileExists = false;
                System.out.println("FILE DOESNT EXIST");
            }
            
            
            
            String statusLine = null;
            String contentTypeLine = null;
            String entityBody = null;
            if (fileExists)
            {
                statusLine = "HTTP/1.1 200 OK" + CRLF;
                contentTypeLine = "Content-Type: " +
                contentType( fileName ) + CRLF;
            }
            else
            {
                statusLine= "HTTP/1.1 404 Not Found" + CRLF;
                contentTypeLine= "Content-Type: " + "text/html" + CRLF;
                entityBody = "<html>" +
                "<head><title>Not Found</title></head>" +
                "<body>Not Found</body></html>";
            }
            
            
            os.writeBytes(statusLine);
            os.writeBytes(contentTypeLine);
            os.writeBytes(CRLF);

            // now we send the body 

            // send the entity body:
            if (fileExists)
            {
                sendBytes(fis, os);
                fis.close();
            }
            else
            {
                os.writeBytes(entityBody);
            }
            
            os.close();
            is.close();
            socket.close();
        }
        
private static String contentType(String fileName)
	{
		if(fileName.endsWith(".htm") || fileName.endsWith(".html"))
		{
			return "text/html";
		}
		if(fileName.endsWith(".txt"))
		{
			return "text/plain";
		}
		if(fileName.endsWith(".css"))
		{
			return "text/css";
		}
		if(fileName.endsWith(".js"))
		{
			return "text/javascript";
		}
		if(fileName.endsWith(".png"))
		{
			return "image/png";
		}
		if(fileName.endsWith(".jpeg"))
		{
			return "image/jpeg";
		}
		if(fileName.endsWith(".wav"))
		{
			return "audio/wav";
		}
		return "application/octet-stream";
	}

        
        private static void sendBytes(FileInputStream fis, DataOutputStream os) throws Exception {
            
        byte[] buffer = new byte[1024];
        int bytes = 0;
        
            while((bytes = fis.read(buffer)) != -1 ){
                os.write(buffer,0,bytes);
            }
        }
}