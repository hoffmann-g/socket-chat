import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable{
    
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    //static cause it's a class variable
    public static List<ClientHandler> clients = new ArrayList<>();

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            clients.add(this);
            System.out.println(socket + " connected");

        } catch (IOException e){

        }
        
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

}