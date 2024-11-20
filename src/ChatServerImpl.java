import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
    private final List<ChatClient> clients;

    protected ChatServerImpl() throws RemoteException {
        clients = new ArrayList<>();
    }

    @Override
    public void registerClient(ChatClient client) throws RemoteException {
        clients.add(client);
        System.out.println(client.getName() + " se juntou ao chat!");
        broadcastMessage(client.getName() + " entrou no chat.", null);
    }

    @Override
    public void sendMessage(String message, ChatClient client) throws RemoteException {
        String formattedMessage = client.getName() + ": " + message;
        broadcastMessage(formattedMessage, client);
    }

    private void broadcastMessage(String message, ChatClient sender) throws RemoteException {
        for (ChatClient client : clients) {
            if (client != sender) {
                client.receiveMessage(message);
            }
        }
        System.out.println(message);
    }
}
