import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ClientHandler implements Runnable{
    
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private String username;

    //static cause it's a class variable
    public static Set<ClientHandler> clients = new HashSet<>();
    public static int clientLimit = 2;

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.username = in.readLine();

            clients.add(this);
            System.out.println("client[" + socket.getPort() + "] connected.");
            broadcast("'" + username + "' has entered the chat.", true);
            
            //clients.forEach(ch -> System.out.println(ch.username + " "));

        } catch (IOException e){
            closeAll(socket, in, out);
        }
        
    }

    public void kickUser(){
        clients.remove(this);
        broadcast("'" + username + "' has left the chat.", true);
        System.out.println("client[" + socket.getPort() + "] disconnected.");
    }

    public void closeAll(Socket s, BufferedReader br, BufferedWriter bw){
        kickUser();
        try{
            if (s != null){
                s.close();
            }
            if (br != null){
                br.close();
            }
            if (bw != null){
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message, boolean global){
        for (ClientHandler ch : clients) {
            try{
                if (global){
                    ch.out.write(message);
                    ch.out.newLine();
                    ch.out.flush();
                } else {
                    if (!ch.username.equals(username)){
                        ch.out.write(username + ": "+ message);
                        ch.out.newLine();
                        ch.out.flush();
                    }
                }
            } catch (IOException e){
    
            }
        }
    }

    //chatting responsible method
    @Override
    public void run(){
        String message;
        while(socket.isConnected()){
            try{
                message = in.readLine();
                System.out.println(message);
                broadcast(message, false);
            } catch (IOException e){
                closeAll(socket, in, out);
                break;
            }
        }
    }

}