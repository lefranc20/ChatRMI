import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote {
    void registerClient(ChatClient client) throws RemoteException;
    void sendMessage(String message, ChatClient client) throws RemoteException;
}
