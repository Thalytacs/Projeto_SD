package Back_End;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

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
    }

    public void run() {
        try {

            BufferedReader doCliente = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            nome = doCliente.readLine();

            for (int i = 0; i < 10; i++) {
                resposta[i] = doCliente.readLine();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
