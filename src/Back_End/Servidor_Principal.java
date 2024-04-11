package Back_End;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
                    }else{
                        partida = partidasPublicas.remove(0);
                        partida.setJogador2(jogador);
                        partida.start();
                    }
                    
                }

                else if (resultado.equals("2")) {                	
                	System.out.println("Criando nova partida privada: " + socket);
                    Conexao_Jogador jogador = new Conexao_Jogador(socket);

                    Partida_Privada partida = new Partida_Privada(jogador);
                    
                    try {
            			DataOutputStream paraCliente = new DataOutputStream(jogador.getSocket().getOutputStream());	
            			paraCliente.writeBytes(partida.codigo.toString() + '\n');
            		} catch (IOException e) {
            			e.printStackTrace();
            		}
                    
                    partidasPrivadas.add(partida);
                }

                else {
                	DataOutputStream paraCliente = new DataOutputStream(socket.getOutputStream());
                	String existe = "0";
                	
                    for (Partida_Privada partida : partidasPrivadas) {
                        if (Integer.parseInt(resultado) == partida.codigo) {
                        	existe = "1";
                        	paraCliente.writeBytes(existe + "\n");
                            partida.setJogador2(new Conexao_Jogador(socket));
                            partida.start();
                            
                            break;
                        }
                    }
                    if(!existe.equals("1")) {
                    	paraCliente.writeBytes("0" + '\n');
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}