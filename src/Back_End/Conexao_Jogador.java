package Back_End;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

public class Conexao_Jogador extends Thread {
    private Socket socket;
    //private int pontos;


    public Socket getSocket() {
        return socket;
    }

    public Conexao_Jogador(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        byte[] dados = new byte[256];
        
        try {
            BufferedReader doCliente = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
