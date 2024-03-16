package Back_End;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor_Principal {
    private static List<Conexao_Jogador> esperandoJogadores = new ArrayList<>();
    private static List<Partida_Publica> jogos = new ArrayList<>();
    private static int MAX_PLAYERS = 2;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        //Precisa verificar os códigos, se partida pública ou privada(criar ou existente)
        try {
            ServerSocket servidor = new ServerSocket(12345); // IP? Sim

            System.out.println("Servidor iniciado. \n Aguardando conexões...");
            while (true) {
                Socket socket = servidor.accept();

                System.out.println("Novo jogador conectado: " + socket);
                Conexao_Jogador jogador = new Conexao_Jogador(socket);

                esperandoJogadores.add(jogador);
                if (esperandoJogadores.size() >= MAX_PLAYERS) {
                    comecarJogo();
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void comecarJogo() {
        List<Conexao_Jogador> jogadores = new ArrayList<>();
        synchronized (esperandoJogadores) {
            for (int i = 0; i < MAX_PLAYERS; i++) {
                jogadores.add(esperandoJogadores.remove(0));
            }
        }
        Partida_Publica game = new Partida_Publica(jogadores);
        jogos.add(game);

        game.start();
    }
}