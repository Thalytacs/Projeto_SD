package Back_End;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
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

    public Partida_Publica(Conexao_Jogador jogador) {
        this.jogador1 = jogador;
    }

    public void setJogador2(Conexao_Jogador jogador2) {
        this.jogador2 = jogador2;
    }


    public void run() {
    	
    	try {
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
                paraCliente1.write(perguntas[i] + '\n');
                paraCliente2.write(perguntas[i] + '\n');
                
                paraCliente1.flush();
                paraCliente2.flush();
                
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
                if (resposta1[index].equals(gabarito[index].substring(1, 2))) {
                    pontosJog1 += 1;
                }
                if (resposta2[index].equals(gabarito[index].substring(1, 2))) {
                    pontosJog2 += 1;
                }
            }
            
            DataOutputStream respCliente1 = new DataOutputStream(jogador1.getSocket().getOutputStream());
            DataOutputStream respCliente2 = new DataOutputStream(jogador2.getSocket().getOutputStream());
            
            if (pontosJog1 > pontosJog2) {
            	respCliente1.writeBytes("Parabens! Voce ganhou. Pontos: " + pontosJog1 + "\n");
            	respCliente2.writeBytes("Que pena! Voce perdeu. Pontos: " + pontosJog2 + "\n");
                
            } else if (pontosJog1 < pontosJog2) {
            	respCliente2.writeBytes("Parabens! Voce ganhou. Pontos: "  + pontosJog1 + "\n");
            	respCliente1.writeBytes("Que pena! Voce perdeu. Pontos: "  + pontosJog2 + "\n");
                
            } else {
            	respCliente1.writeBytes("Empate. Pontos: " + pontosJog1 + "\n");
            	respCliente2.writeBytes("Empate. Pontos: " + pontosJog2 + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
