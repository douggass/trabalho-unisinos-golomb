package golombcoding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

public class GolombCoding {

    static final String PATH_ARQUIVO_CODIFICADO = "C://Golomb//encode";
    static String PATH_ARQUIVO_NAO_CODIFICADO = "C://leia//teste.txt";

    public static void main(String[] args) throws IOException {
        Codificador cod = new Codificador();
        Decodificador decod = new Decodificador();
        //ArrayList<BitSet> codificado;
        BitSet codificado;

        Scanner entrada = new Scanner(System.in);
        System.out.println("Informe o divisor: ");
        int divisor = entrada.nextInt();
        cod.setDivisor(divisor);
        decod.setDivisor(divisor);

        entrada = new Scanner(System.in);
        System.out.println("Informe o caminho completo do arquivo que deve ser codificado: ");
        PATH_ARQUIVO_NAO_CODIFICADO = entrada.nextLine();

        //Le um arquivo localizado em "PATH_ARQUIVO_NAO_CODIFICADO"
        //e o insere em uma String "dadosArquivo"
        ManipulacaoArquivo maniArq = new ManipulacaoArquivo(PATH_ARQUIVO_NAO_CODIFICADO);
        String dadosArquivo = maniArq.leArquivo();

        //Cria um array de char e o converte em um arrayList de inteiros
        //este arrayList ter� a representa��o de ASCII dos chars
        char[] caracteres = dadosArquivo.toCharArray();
        ArrayList<Integer> caracteresDec = new ArrayList<Integer>(caracteres.length);

        for (int i = 0; i < caracteres.length; i++) {
            caracteresDec.add(0);
        }

        for (int i = 0; i < caracteres.length; i++) {
            caracteresDec.set(i, new Integer((int) caracteres[i]));
        }

        //Codifica um arquivo, insere os dados em "codificado"
        //Em seguida decodifica ele e apresenta informa��es na tela
        codificado = cod.inteiroParaColomb(caracteresDec);
        decod.colombParaInteiro(codificado);

        //Escreve em um arquivo o c�digo em Colomb
        ManipulacaoArquivo MA = new ManipulacaoArquivo(PATH_ARQUIVO_CODIFICADO);
        MA.criarDiretorioParaEncodeFiles();
        MA.esreveArquivo(codificado);
        System.out.println("Foi criado o arquivo codificado: " + PATH_ARQUIVO_CODIFICADO);

        //L� o arquivo que foi codificado em Colomb, decodifica
        //e apresenta informa��es dele
        BitSet todosBinarios = MA.leArquivoCodificado();
        decod.colombParaInteiro(todosBinarios);
    }
}
