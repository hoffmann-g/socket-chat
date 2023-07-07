import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    //static cause it's a class variable
    public static ArrayList<ClientHandler> clients = new ArrayList<>();

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));

            clients.add(this);
            System.out.println(socket + " connected");

        } catch (IOException e){
            close(socket, in, out);
        }
        
    }

    public void close(Socket s, BufferedReader br, BufferedWriter bw){
        try{
            s.close();
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){

    }

}