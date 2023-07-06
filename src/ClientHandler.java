import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    private Socket socket;
    private String nickname;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public static ArrayList<ClientHandler> clients = new ArrayList<>();

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.nickname = bufferedReader.readLine();
            
            clients.add(this);

            broadcast("[SERVER]: '" + this.nickname + "' has entered the chat.");

        } catch(Exception e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void broadcast(String message){
        for (ClientHandler ch : clients) {
            try{
                if(!ch.nickname.equals(nickname)){ //tweak leater
                    ch.bufferedWriter.write(message);
                    ch.bufferedWriter.newLine();
                    ch.bufferedWriter.flush();
                }
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void removeClient(){
        clients.remove(this);
        broadcast("[SERVER]: '" + nickname + "' has left the server.");
        System.out.println("A client has disconnected");
    }

    public void closeEverything(Socket s, BufferedReader r, BufferedWriter w){
        removeClient();
        try{
            if(s != null){
                s.close();
            }
            if(r != null){
                s.close();
            }
            if(w != null){
                w.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        String message;
        while(socket.isConnected()){
            try{
                message = bufferedReader.readLine();
                broadcast(message);
            } catch(IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
    
}
