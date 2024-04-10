package Back_End;

import Front_End.*;
import Front_End.gui.Tela_Principal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private String[] respostas = new String[10];
    private int cont = 0;
    public Socket socket;

    public void criarPartida(String codigo) {
        try {
            // Solicita conexao com o servidor
            this.socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            
            PrintWriter paraServidor = new PrintWriter(socket.getOutputStream(), true);
            paraServidor.println(codigo+'\n');

            //Mudar para dialogo do Jpanel/frame
            System.out.println("Conectado ao servidor.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Método para receber as perguntas do servidor
    public String[] receberPerguntas() {
    	String[] perguntas = new String[10];
    	try {
    		BufferedReader doServidor = new BufferedReader(
    				new InputStreamReader(socket.getInputStream()));
            for (int i = 0; i < 10; i++) {
            	System.out.println("Pegou a pergunta");
            	
                perguntas[i] = doServidor.readLine();
                
                System.out.println("Leu "+ perguntas[i]);
            }
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	return perguntas;
    }
    
    //Método para guardar as respostas do cliente
    public void addRespostas(String resposta) {
    	respostas[cont] = resposta;
    	cont++;
    }
    
    //Método para enviar as respostas para a conexão jogador
    public void enviarRespostas() {
    	try {
    		DataOutputStream paraServidor = new DataOutputStream(socket.getOutputStream());
            for(int i = 0; i < 10; i++) {
            	paraServidor.writeBytes(respostas[i] + "\n");
            	
            	System.out.println("Enviou resposta para o servidor");
            }
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    }
    
    public String existePartidaPrivada() {
    	String resp = "0";
    	
    	try {
    		BufferedReader doServidor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		
    		System.out.println("Chegou na função");
    		resp = doServidor.readLine();
    		
    		System.out.println(resp);
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	return resp;
    }
    
    public String getGanhador() {
    	String resp = "Erro";
    	
    	try {
    		BufferedReader doServidor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		System.out.println("Chegou para receber a resposta");
    		
			resp = doServidor.readLine();
			
			System.out.println("Resultado da partida: " + resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return resp;
    }
}