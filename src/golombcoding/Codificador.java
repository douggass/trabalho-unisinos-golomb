package golombcoding;

import java.util.ArrayList;
import java.util.BitSet;

public class Codificador extends Divisor{

    //Classe pincipal da codifica��o em Golumb
    //Detalhes est�o dentro do c�digo
    public Codificador() {
    }
    //Converte um array de inteiros (ASCII) para a respectiva codifica��o em Columb

    public BitSet inteiroParaColomb(ArrayList<Integer> decimal) {

        //Instancia��o do BitSet onde todos bites do ArrayList
        //recebido por par�metro ser�o concatenados
        BitSet codigo = new BitSet();
        int posicao = 0;

        //Array que conter� todos BitSets do ArrayList recebido por par�metro,
        //separados, ser� utilizado para exibi��o de informa��es na tela
        ArrayList<BitSet> ALBT = new ArrayList<BitSet>();
        int divisor = getDivisor();
        for (int i = 0; i < decimal.size(); i++) {
            //Informa��es necess�rias para codificia��o em Colomb-Rice
            int prefixo = decimal.get(i) / divisor;
            int sufixo = decimal.get(i) % divisor;
            int tamanhoBinario = prefixo + 4;

            //Seta o bin�rio 1 delimitador de Golumb
            codigo.set(posicao + 3);

            //Define o sufixo da codifica��o
            if (sufixo == 1 || sufixo == 3) {
                codigo.set(posicao + 1);
            }
            if (sufixo == 2 || sufixo == 3) {
                codigo.set(posicao + 2);
            }

            //Array criado para armzenar informa��es temporariamente
            //elas ser�o exibidas ao usu�rio, pois a informa��o final
            //j� est� concatenada
            BitSet temp = new BitSet();
            temp.set(tamanhoBinario - 1);
            temp.set(3);

            //Define o sufixo do arquivo tempor�rio,
            //usado somente para exibi��o na tela
            if (sufixo == 1 || sufixo == 3) {
                temp.set(1);
            }
            if (sufixo == 2 || sufixo == 3) {
                temp.set(2);
            }
            ALBT.add(temp);
            posicao += tamanhoBinario - 1;

        }
        //seta o �ltimo bite do BitSet como 1 afim de saber
        //onde inicia-se a parte que deve ser lida do bitset,
        //pois ele ser� lido de forma inversa
        codigo.set(posicao);

        //mais testes para comparar o n original e o bin�rio
        //em Colomb, j� com o stop bit
        ManipulacaoBits MB = new ManipulacaoBits();

        //imprime Todos c�digos ASCII dos caracteres lidos
        //System.out.println("Inteiro iriginal: "+decimal.toString());
        //imprime o bin�rio concatenado final Golumb codificado
        //System.out.println("Bit codificado em binario: "+MB.bitSetParaString(codigo));
        //imprime o bin�rio Golumb codificado n�o concatenado,
        //ou seja, em diferentes linhas
        //for(int i = 0; i<ALBT.size(); i++)
        //	System.out.println("Array de binarios: "+MB.bitSetParaString(ALBT.get(i)));
        for (int i = 0; i < ALBT.size(); i++) {
            int ASCII = decimal.get(i);
            BitSet temp = ALBT.get(i);

            char[] binarios = MB.bitSetParaString(temp).toCharArray();
            binarios[0] = '0';
            String caracteresTodos = "";
            for (int j = 0; j < binarios.length; j++) {
                caracteresTodos += binarios[j];
            }

            char caractere = (char) ASCII;
            System.out.println("Caractere lido " + caractere + " representado por " + decimal.get(i) + ": "
                    + "(byte)" + caracteresTodos);
            //+"(byte)"+MB.bitSetParaString(temp));
        }

        return codigo;
    }

}
