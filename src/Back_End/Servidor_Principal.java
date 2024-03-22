package Back_End;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor_Principal {
    private static List<Conexao_Jogador> esperandoJogadores = new ArrayList<>();
    private static List<Partida_Privada> partidasPrivadas = new ArrayList<>();
    private static int MAX_PLAYERS = 2;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // Precisa verificar os códigos, se partida pública ou privada(criar ou
        // existente)
        try {
            ServerSocket servidor = new ServerSocket(12345);
            List<Conexao_Jogador> jogadores = new ArrayList<>();
            byte[] dado = new byte[256];

            DatagramPacket pkg = new DatagramPacket(dado, dado.length);

            DatagramSocket receber = new DatagramSocket();

            System.out.println("Servidor iniciado. \n Aguardando conexões...");
            while (true) {
                Socket socket = servidor.accept();
                receber.receive(pkg);

                String resultado = String.valueOf(pkg.getData());

                if (resultado.equals("1")) {
                    System.out.println("Novo jogador conectado: " + socket);
                    Conexao_Jogador jogador = new Conexao_Jogador(socket);

                    esperandoJogadores.add(jogador);
                    if (esperandoJogadores.size() >= MAX_PLAYERS) {

                        for (int i = 0; i < MAX_PLAYERS; i++) {
                            jogadores.add(esperandoJogadores.remove(0));
                        }
                        Partida_Publica partida = new Partida_Publica(jogadores);
                        partida.start();
                    }
                }

                else if (resultado.equals("2")) {
                    System.out.println("Criando nova partida privada: " + socket);
                    Conexao_Jogador jogador = new Conexao_Jogador(socket);

                    Partida_Privada partida = new Partida_Privada(jogador);
                    partidasPrivadas.add(partida);                    
                }
                
                else {
                    for (Partida_Privada partida : partidasPrivadas) {
                        if (Integer.parseInt(resultado) == partida.codigo) {
                            partida.setJogador2(new Conexao_Jogador(socket));
                            partida.start();
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}