package Back_End;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;

public class Partida_Publica extends Thread {
    Conexao_Jogador jogador1;
    Conexao_Jogador jogador2;

    public Partida_Publica(List<Conexao_Jogador> jogadores){
        this.jogador1 = jogadores.get(0);
        this.jogador2 = jogadores.get(1);
    }

    public void run() {
        try {
            //Vai iniciar duas threads, uma para cada jogador
            //No final usa join

            byte[] dados = "Aguardando outro jogador".getBytes();

            DatagramPacket pkg = new DatagramPacket(dados, 0, jogador1.getSocket().getInetAddress(), jogador1.getSocket().getPort());

            BufferedReader doCliente1 = new BufferedReader(new InputStreamReader(jogador1.getSocket().getInputStream()));
            BufferedReader doCliente2 = new BufferedReader(new InputStreamReader(jogador2.getSocket().getInputStream()));
            
            doCliente1.read();
            
            DatagramSocket enviar = new DatagramSocket();

            enviar.send(pkg);
            // BufferedReader buffer = new BufferedReader(new FileReader("Perguntas.txt"));
            // buffer.read();
            // buffer.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
