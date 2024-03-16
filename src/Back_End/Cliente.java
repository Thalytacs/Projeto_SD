package Back_End;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Conectado ao servidor.");

            Scanner scanner = new Scanner(System.in);
            PrintWriter paraServidor = new PrintWriter(socket.getOutputStream(), true);

            // Loop para enviar mensagens para o servidor
            while (true) {
                System.out.print("Digite uma mensagem para enviar ao servidor: ");
                String message = scanner.nextLine();
                paraServidor.println(message);

                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
