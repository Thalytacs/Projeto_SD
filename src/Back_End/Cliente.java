package Back_End;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            String[] perguntas = new String[10];
            String[] respostas = new String[10];

            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Conectado ao servidor.");

            Scanner scanner = new Scanner(System.in);
            PrintWriter paraServidor = new PrintWriter(socket.getOutputStream(), true);

            // BufferReader para comunicação com a partida (Publica e Privada), não implementado certo ainda
            BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            for (int i = 0; i < 10; i++) {
                perguntas[i] = r.readLine();
            }
            int cont = 0;
            for (String pergunta : perguntas) {
                System.out.println(pergunta);
                respostas[cont] = scanner.nextLine();
                cont++;
            }
            
            // Loop para enviar mensagens para o servidor
            for (String resposta : respostas) {
                paraServidor.println(resposta);
            }

           System.out.println(r.readLine());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
