/**************************************
/*  Tabuleiro.java
/*
/*  In�cio da Classe Tabuleiro para 
/* a implementação do Jogo 2048.
/*
/*************************************/
/**
 *
 * @author Jefferson Souza Rodrigues
 */
import java.util.*;

public class Tabuleiro{

    int matriz[][]; 
    int pontuacao;
    boolean moveu, winner = false;

    
    Tabuleiro(){
            matriz = new int[4][4];
            pontuacao = 0;
    }

    public void geraNumero() {

        int pos, i, j;
        Random ran = new Random();

        do{
            pos = ran.nextInt(16);;
            i = pos / 4;
            j = pos % 4;
        }while (matriz[i][j] != 0);

        matriz[i][j] = 2;
    }


    public void imprimeTabuleiro(){
        System.out.printf("Pontua��o %d\n", pontuacao);
        for (int i = 0; i < 4; i++) {
            System.out.printf("|");
            for (int j = 0; j < 4; j++) {
                System.out.printf("%d\t", matriz[i][j]);
            }
            System.out.printf("|\n");
        }
    }
    
    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }



    public boolean cheio(){
 
        boolean verf = true;
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if(matriz[i][j] == 0){
                    verf = false;
                    break;
                }

        return verf;
    }
    // Move os valores do tabuleiro para cima
    public void cima(int [][]matriz){
        //verifica se vai mover
        moveu = false;
        //Move para cima se os n�meros s�o iguais e somam na pontua��o
        for(int i = 1; i < 4; i++){
            for (int j = 0; j < 4; j++ ) {
                if(matriz[i][j] == matriz[i-1][j] || matriz[i-1][j] == 0){
                    // verifica se não estamos comparando 0 com 0 
                    if(matriz[i][j] != 0){ 
                        moveu = true; //Se moveu esta verdadeiro
                        int p = i, q = j;
                        boolean zero = false;

                        //percorre a linha quando tiver zeros
                        while( matriz[p-1][q] == 0){
                            matriz[p-1][q] = matriz[p][q];
                            matriz[p][q] = 0;
                            zero = true;
                            p--;
                            if(p == 0) {
                                p += 1;
                                break;
                            }
                        }
                        //Pontuacao 
                        if(!zero ){ 
                            pontuacao += matriz[p][q] + matriz[p-1][q];
                            matriz[p-1][q] = matriz[p][q] + matriz[p-1][q];
                            
                            // Verifica se deu 2048 (y)
                            if(matriz[p-1][q] == 2048){
                                winner = true;
                            }
                            // Passa os valores restantes para frente ( nesse 
//                            caso passa os valores de baixo  para a cima)
                            for(int k = i; k < 4; k++ ){
                                if(k == 3){
                                    matriz[k][j] = 0;
                                }
                                else matriz[k][j] = matriz[k+1][j];
                            }
                        }else {
                            if( matriz[p][q] == matriz[p-1][q] ){
                                pontuacao += matriz[p][q] + matriz[p-1][q];
                                matriz[p-1][q] = matriz[p][q] + matriz[p-1][q];
                                // Verifica se deu 2048 (y)
                                if(matriz[p-1][q] == 2048){
                                    winner = true;
                                }
                                matriz[p][q] = 0;
                                
                            }
                        } 
                    }  
                }
            }
        }
    }

    public void desce(int [][]matriz){
        //verifica se vai mover
        moveu = false;
       
        //Move para baixo se os números são iguais e somam na pontuação
        for(int i = 2; i >= 0; i--){
            for (int j = 0; j < 4; j++ ) {
                if(matriz[i][j] == matriz[i+1][j] || matriz[i+1][j] == 0){
                    // verifica se não estamos comparando 0 com 0 
                    if(matriz[i][j] != 0){ 
                        moveu = true; //Se moveu é verdadeiro
                        int p = i, q = j;
                        boolean zero = false;

                        //percorre a linha quando tiver zeros
                        while( matriz[p+1][q] == 0){
                            matriz[p+1][q] = matriz[p][q];
                            matriz[p][q] = 0;
                            zero = true;
                            p++;
                            if(p == 3) {
                                p -= 1;
                                break;
                            }
                        }

                        //Pontuação 
                        if(!zero ){ 
                            pontuacao += matriz[p][q] + matriz[p+1][q];
                            matriz[p+1][q] = matriz[p][q] + matriz[p+1][q];
                            // Verifica se deu 2048 (y)
                            if(matriz[p+1][q] == 2048){
                                winner = true;
                            }
                            // Passa os valores restantes para frente ( nesse caso passa os valores de cima para a baixo)
                            for(int k = i; k >= 0; k-- ){
                                if(k == 0){
                                    matriz[k][j] = 0;
                                }
                                else matriz[k][j] = matriz[k-1][j];
                            }
                        }else {
                            if( matriz[p][q] == matriz[p+1][q] ){
                                pontuacao += matriz[p][q] + matriz[p+1][q];
                                matriz[p+1][q] = matriz[p][q] + matriz[p+1][q];
                                // Verifica se deu 2048 (y)
                                if(matriz[p+1][q] == 2048){
                                    winner = true;
                                }
                                matriz[p][q] = 0;
                                
                            }
                        }  
                    }  
                }
            }
        }
    }

    public void direita(int [][]matriz){
        //verifica se vai mover
        moveu = false;
        //Move para baixo se os números são iguais e somam na pontuação
        for(int i = 0; i < 4; i++){
            for (int j = 2; j >= 0; j-- ) {
                if(matriz[i][j] == matriz[i][j+1] || matriz[i][j+1] == 0) {
                    // verifica se não estamos comparando 0 com 0 
                    if(matriz[i][j] != 0){    
                        moveu = true; //Se moveu é verdadeiro
                        int p = i, q = j;
                        boolean zero = false;

                        //percorre a linha quando tiver zeros
                        while( matriz[p][q+1] == 0){
                            matriz[p][q+1] = matriz[p][q];
                            matriz[p][q] = 0;
                            zero = true;
                            q++;
                            if(q == 3) {
                                q -= 1;
                                break;
                            }
                        }

                        //Pontua��o 
                        if(!zero ){ 
                            pontuacao += matriz[p][q] + matriz[p][q+1];
                            matriz[p][q+1] = matriz[p][q] + matriz[p][q+1];
                            // Verifica se deu 2048 (y)
                            if(matriz[p][q+1] == 2048){
                                winner = true;
                            }
                            // Passa os valores restantes para frente ( nesse caso passa os valores da esquerda para a direita)
                            for(int k = j; k >= 0; k-- ){
                                if(k == 0){
                                    matriz[i][k] = 0;
                                }
                                else matriz[i][k] = matriz[i][k-1];
                            }
                        }else {
                            if( matriz[p][q] == matriz[p][q+1] ){
                                pontuacao += matriz[p][q] + matriz[p][q+1];
                                matriz[p][q+1] = matriz[p][q] + matriz[p][q+1];
                                // Verifica se deu 2048 (y)
                                if(matriz[p][q+1] == 2048){
                                    winner = true;
                                }
                                matriz[p][q] = 0;
                                
                            }
                        }
                    }
                }
            }
        }
    }

    public void esquerda(int [][]matriz){
        //verifica se vai mover
        moveu = false;
        //Move para esquerda se os números são iguais e somam na pontuação
        for(int i = 0; i < 4; i++){
            for (int j = 1; j < 4; j++ ) {
                if(matriz[i][j] == matriz[i][j-1] || matriz[i][j-1] == 0){
                    // verifica se não estamos comparando 0 com 0 
                    if(matriz[i][j] != 0){ 
                        moveu = true; //Se moveu é verdadeiro
                        int p = i, q = j;
                        boolean zero = false;

                        //percorre a linha quando tiver zeros
                        while( matriz[p][q-1] == 0){
                            matriz[p][q-1] = matriz[p][q];
                            matriz[p][q] = 0;
                            zero = true;
                            q--;
                            if(q == 0) {
                                q += 1;
                                break;
                            }
                        }
                        //Pontuação 
                        if(!zero ){ 
                            pontuacao += matriz[p][q] + matriz[p][q-1];
                            matriz[p][q-1] = matriz[p][q] + matriz[p][q-1];
                            // Verifica se deu 2048 (y)
                            if(matriz[p][q-1] == 2048){
                                winner = true;
                            }
                            // Passa os valores restantes para frente ( nesse caso passa os valores da direita para a esquerda)
                            for(int k = j; k < 4; k++ ){
                                if(k == 3){
                                    matriz[i][k] = 0;
                                }
                                else matriz[i][k] = matriz[i][k+1];
                            }
                        }else {
                            if( matriz[p][q] == matriz[p][q-1] ){
                                pontuacao += matriz[p][q] + matriz[p][q-1];
                                matriz[p][q-1] = matriz[p][q] + matriz[p][q-1];
                                // Verifica se deu 2048 (y)
                                if(matriz[p][q-1] == 2048){
                                    winner = true;
                                }
                                matriz[p][q] = 0;
                                
                            }
                        }
                    }
                }
            }
        }
    }
}