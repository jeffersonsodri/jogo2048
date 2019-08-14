/********************************
/*  Jogo.java
/*
/*  Início da Classe Jogo para  
/* a implementacao do Jogo 2048.
/*
/*
/*******************************/
/**
 *
 * @author Jefferson Souza Rodrigues
 */

import java.util.*;
public class Jogo{
       

    Tabuleiro tabuleiro;
    boolean gameover = false;  
    
    
    public Jogo(){
       tabuleiro = new Tabuleiro();
    }
	
    public char leJogada(){

        char jogada;
        Scanner entrada = new Scanner(System.in);

        do{
            System.out.printf("Qual a sua jogada?\n");
            System.out.printf("a - esquerda\n");
            System.out.printf("d - direita\n");
            System.out.printf("s - baixo\n");
            System.out.printf("w - cima\n");
            jogada = entrada.next().charAt(0);
        }while (jogada != 'a' && jogada != 'd' && jogada != 's' && jogada != 'w'); 
        
        return jogada;
    }


    public void mexerTabuleiro(char jogada){
    	if(gameover == false) {
	        if(jogada == 'w'){
	            this.tabuleiro.cima(tabuleiro.matriz);            
	        }
	        if(jogada == 'a'){
	            this.tabuleiro.esquerda(tabuleiro.matriz);
	        }
	        if(jogada == 's'){
	            this.tabuleiro.desce(tabuleiro.matriz);
	        }
	        if(jogada == 'd'){
	            this.tabuleiro.direita(tabuleiro.matriz);
	        }
    	}
    }

    public void verificaGameOver(){
        int m[][] = new int[4][4];

        for (int i = 0;i < 4 ;i++ ) {
        	for(int j = 0;j < 4 ;j++ ) {
        		m[i][j] = tabuleiro.matriz[i][j];
        	}
        }
        
        Tabuleiro tbAux = new Tabuleiro();
        tbAux.matriz = m;

        //Verifica se nao pode mover em nenhuma direcao
        tabuleiro.cima(tbAux.matriz);
        if(tabuleiro.moveu == false){
            tabuleiro.esquerda(tbAux.matriz);
            if(tabuleiro.moveu == false){
                tabuleiro.desce(tbAux.matriz);
                if(tabuleiro.moveu == false){
                    tabuleiro.direita(tbAux.matriz);
                    if(tabuleiro.moveu == false){
                        this.gameover = true;
                    }
                }
            }
        } 
    }
    //Main
    public static void main(String args[])  {
        Jogo jogo = new Jogo();

        jogo.tabuleiro.geraNumero();
        jogo.tabuleiro.geraNumero();

        jogo.tabuleiro.imprimeTabuleiro();

        do{
    	
            char jogada = jogo.leJogada();
            jogo.mexerTabuleiro(jogada);
            if(jogo.tabuleiro.moveu == true)
                jogo.tabuleiro.geraNumero();
            if(jogo.tabuleiro.cheio())
            	jogo.verificaGameOver();
            jogo.tabuleiro.imprimeTabuleiro();
        	
        }while(jogo.gameover == false && jogo.tabuleiro.winner == false );

        if(jogo.gameover == true){
            System.out.println("Game Over. Voce perdeu. :( ");
        }
        if(jogo.tabuleiro.winner == true){
            System.out.println("Voce ganhou o jogo. :) ");
        }
        

    }
}

