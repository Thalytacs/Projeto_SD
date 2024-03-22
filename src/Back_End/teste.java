package Back_End;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class teste {
    public static void main(String[] args) {
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
            String perguntas = new String();

            for (int i = 1, contador = 0; i <= 50 && contador <= 9; i++) {
                String linha = lerArquivo.readLine();

                if (i == listaOrdenada.get(contador)) {
                    perguntas += linha + "\n";
                    gabarito[contador] = linha.split(":")[1];
                    contador++;
                }
            }

            lerArquivo.close();
            for (String letra : gabarito) {
                System.out.println(letra);
                
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
