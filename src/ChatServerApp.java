import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServerApp {
    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServerImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatServer", server);
            System.out.println("Servidor de chat pronto para conexões!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
