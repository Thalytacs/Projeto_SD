package Back_End;

public class teste2 extends Thread{
    public int valor;

    public teste2(int valor){
        this.valor = valor;
    }


    public void run () {
        System.out.println(valor + " Teste 2");
    }
}
