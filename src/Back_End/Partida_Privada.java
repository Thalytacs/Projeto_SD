package Back_End;

import java.util.Random;

public class Partida_Privada extends Partida_Publica {
    public Integer codigo;

    public Partida_Privada(Conexao_Jogador jogador1){
        super(jogador1);
        this.codigo = new Random().nextInt();
        System.out.println("Código: " + codigo);
    }

    public void setJogador2(Conexao_Jogador jogador2) {
        this.jogador2 = jogador2;
    }
}
