import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args){
        try{
            System.out.println("[Client]: Started.");

            //socket
            Socket clientSocket = new Socket("localhost", 9999);

            //creates an output stream to send a bytestream to the server
            OutputStream outputStream = clientSocket.getOutputStream();
            //creates a writable obj to socket's inputstream be able to use
            ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("[CLIENT]: Enter a string: ");
            String str = userInput.readLine();

            //writes an object in bjOutputStream
            objOutputStream.writeObject(str);
            //forces it to send the stream
            objOutputStream.flush();

        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
