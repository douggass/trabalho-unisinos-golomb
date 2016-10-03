package golombcoding;

import java.util.BitSet;
import java.util.ArrayList;

public class Decodificador extends Divisor {

    public Decodificador() {
    }

    //Decodifica um BitSet para um ArrayList
    //de inteiros em decimal, c�digo ASCII
    public ArrayList<Integer> colombParaInteiro(BitSet bit) {
        int divisor = getDivisor();
        ArrayList<Integer> inteiros = new ArrayList<Integer>();
        int posicaoBit = bit.size();
        //decodifica o alfabeto, insere os ASCII correspondentes
        //de forma invertida
        while (bit.get(posicaoBit) == false) {
            posicaoBit--;
        }

        while (posicaoBit > 0) {
            int zeros = 0;
            while (bit.get(posicaoBit) == false && posicaoBit > 0) {
                zeros++;
                posicaoBit--;
            }
            int calculoZeros = zeros * divisor;
            inteiros.add(calculoZeros);
            posicaoBit = posicaoBit - 1;
            int ultimo = 0;
            int penultimo = 0;
            if (bit.get(posicaoBit) == true) {
                penultimo = 1;
            } else {
                penultimo = 0;
            }
            if (bit.get(posicaoBit - 1) == true) {
                ultimo = 1;
            } else {
                ultimo = 0;
            }
            int sufixo = this.decodificaSufixo(penultimo, ultimo);
            inteiros.set(inteiros.size() - 1, (inteiros.get((inteiros.size() - 1)) + sufixo));
            posicaoBit = posicaoBit - 2;

        }
        //Inverter posi��es do array que cont�m o c�digo em ASCII
        ArrayList<Integer> inteirosV2 = new ArrayList<Integer>();
        for (int j = 0; j < inteiros.size(); j++) {
            inteirosV2.add(inteiros.get(j));
        }
        for (int j = 0; j < inteirosV2.size(); j++) {
            inteiros.set(inteiros.size() - j - 1, inteirosV2.get(j));
        }

        //Imprime informa��es
        ManipulacaoBits MN = new ManipulacaoBits();
        System.out.println("bitset recebido por parametro: " + MN.bitSetParaString(bit));
        System.out.println("Decodificado em ASCII: " + inteiros.toString());
        System.out.println("posicao do bitset recebido: " + bit.toString());
        return inteiros;
    }

    //Calcula o valor do sufixo com base no BitSet
    //O valor vai de zero a tr�s
    public int decodificaSufixo(int penultimo, int ultimo) {
        int sufixo = 0;
        if (penultimo == 1) {
            if (ultimo == 1) {
                sufixo = 3;
            } else {
                sufixo = 2;
            }
        } else if (ultimo == 1) {
            sufixo = 1;
        } else {
            sufixo = 0;
        }
        return sufixo;
    }
}
