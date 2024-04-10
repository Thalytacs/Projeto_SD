package Back_End;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

public class Conexao_Jogador extends Thread {
    private Socket socket;
    private String[] resposta;
    private String nome;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String[] getResposta() {
        return resposta;
    }

    public void setResposta(String[] resposta) {
        this.resposta = resposta;
    }

    public Socket getSocket() {
        return socket;
    }

    public Conexao_Jogador(Socket socket) {
        this.socket = socket;
        this.resposta = new String[10];
    }

    public void run() {
        try {
            BufferedReader doCliente = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //nome = doCliente.readLine();
            
            System.out.println("Entrou no conexão jogador");

            socket.setSoTimeout(300000);
            try {
                for (int i = 0; i < 10; i++) {
                	System.out.println("Entrou no for do conexão servidor");
                    resposta[i] = doCliente.readLine();
                    System.out.println("Chegou uma pergunta: " + resposta[i]);
                }    
            } catch (SocketTimeoutException e) {
                for (int i = 0; i < 10; i++) {
                    resposta[i] = "e";
                }
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
