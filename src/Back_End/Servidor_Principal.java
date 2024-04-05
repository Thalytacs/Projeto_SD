package Back_End;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor_Principal {
    private static List<Partida_Privada> partidasPrivadas = new ArrayList<>();
    private static List<Partida_Publica> partidasPublicas = new ArrayList<>();

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // Precisa verificar os códigos, se partida pública ou privada(criar ou
        // existente)
        try {
            ServerSocket servidor = new ServerSocket(12345);

            System.out.println("Servidor iniciado. \nAguardando conexões...");
            while (true) {
                Socket socket = servidor.accept();

                BufferedReader ler = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String resultado = ler.readLine();

                if (resultado.equals("1")) {
                    System.out.println("Novo jogador conectado: " + socket);
                    Conexao_Jogador jogador = new Conexao_Jogador(socket);
                    Partida_Publica partida;

                    if (partidasPublicas.isEmpty()) {
                        partida = new Partida_Publica(jogador);
                        partidasPublicas.add(partida);
                        System.out.println("Criou partida publica");
                    }else{
                        partida = partidasPublicas.remove(0);
                        partida.setJogador2(jogador);
                        partida.start();
                        System.out.println("Startou partida publica");
                    }
                    
                }

                else if (resultado.equals("2")) {
                    System.out.println("Criando nova partida privada: " + socket);
                    Conexao_Jogador jogador = new Conexao_Jogador(socket);

                    Partida_Privada partida = new Partida_Privada(jogador);
                    partidasPrivadas.add(partida);
                }

                else {
                	Boolean flag = false;
                	
                    for (Partida_Privada partida : partidasPrivadas) {
                        if (Integer.parseInt(resultado) == partida.codigo) {
                            partida.setJogador2(new Conexao_Jogador(socket));
                            partida.start();
                            flag = true;
                            break;
                        }
                    }
                    
                    if(!flag) {
                    	PrintWriter paraCliente = new PrintWriter(socket.getOutputStream(), true);
                    	
                    	paraCliente.print("0");
                    	
                    	paraCliente.close();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}