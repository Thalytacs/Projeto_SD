package Back_End;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Partida_Publica extends Thread {
    Conexao_Jogador jogador1;
    Conexao_Jogador jogador2;
    String ganhador;

    public Partida_Publica(List<Conexao_Jogador> jogadores) {
        this.jogador1 = jogadores.get(0);
        this.jogador2 = jogadores.get(1);
    }

    public void run() {
        try {
            // Vai iniciar duas threads, uma para cada jogador
            // No final usa join
            String[] gabarito = new String[10];
            String caminho = "C:\\Users\\thaly\\Desktop\\Projeto_SD\\src\\Back_End\\Perguntas.txt";
            BufferedReader lerArquivo = new BufferedReader(new FileReader(caminho));

            Set<Integer> valoresUnicos = new HashSet<>();
            for (int i = 1; i <= 20; i++) {
                int valor = new Random().nextInt(50) + 1;
                valoresUnicos.add(valor);
                if (valoresUnicos.size() == 10) {
                    break;
                }
            }
            List<Integer> listaOrdenada = new ArrayList<>(valoresUnicos);

            Collections.sort(listaOrdenada);
            String[] perguntas = new String[10];

            for (int i = 1, contador = 0; i <= 50 && contador <= 9; i++) {
                String linha = lerArquivo.readLine();

                if (i == listaOrdenada.get(contador)) {
                    perguntas[contador] = linha.split("/")[0];
                    gabarito[contador] = linha.split(":")[1];
                    contador++;
                }
            }

            lerArquivo.close();

            BufferedWriter paraCliente1 = new BufferedWriter(
                    new OutputStreamWriter(jogador1.getSocket().getOutputStream()));
            BufferedWriter paraCliente2 = new BufferedWriter(
                    new OutputStreamWriter(jogador2.getSocket().getOutputStream()));

            for (int i = 0; i < 10; i++) {
                paraCliente1.write(perguntas[i], 0, perguntas[i].length());
                paraCliente2.write(perguntas[i], 0, perguntas[i].length());
            }

            jogador1.start();
            jogador2.start();

            jogador1.join();
            jogador2.join();

            String[] resposta1 = jogador1.getResposta();
            String[] resposta2 = jogador2.getResposta();

            int pontosJog1 = 0;
            int pontosJog2 = 0;
            for (int index = 0; index < 10; index++) {
                if (resposta1[index] == gabarito[1].substring(0, 1)) {
                    pontosJog1 += 1;
                }
                if (resposta2[index] == gabarito[1].substring(0, 1)) {
                    pontosJog2 += 1;
                }
            }

            if (pontosJog1 > pontosJog2) {
                ganhador = jogador1.getNome();
            } else if (pontosJog1 < pontosJog2) {
                ganhador = jogador2.getNome();
            } else {
                ganhador = "Empate";
            }

            paraCliente1.write(ganhador, 0, ganhador.length());
            paraCliente2.write(ganhador, 0, ganhador.length());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
