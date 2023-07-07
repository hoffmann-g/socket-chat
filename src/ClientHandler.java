import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler{
    
    private Socket socket;

    private static List<ClientHandler> clients = new ArrayList<>(1);

    public ClientHandler(Socket socket){
        
        this.socket = socket;
        System.out.println(socket + " connected");
    }


}