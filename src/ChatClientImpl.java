import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    private final String name;

    protected ChatClientImpl(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    public static void main(String[] args) {
        try {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Digite seu nome: ");
                String name = scanner.nextLine();

                ChatClientImpl client = new ChatClientImpl(name);

                // Conecta ao servidor RMI
                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                ChatServer server = (ChatServer) registry.lookup("ChatServer");

                // Registra o cliente no servidor
                server.registerClient(client);

                // Envia mensagens para o servidor
                System.out.println("Bem-vindo ao chat! Digite suas mensagens abaixo:");
                while (true) {
                    String message = scanner.nextLine();
                    server.sendMessage(message, client);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
