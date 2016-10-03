package golombcoding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.stream.Stream;

public class ManipulacaoArquivo {

    //Diret�rio do arquivo que ser� lido/escrito
    private String diretorioArquivo;

    //Construtor
    public ManipulacaoArquivo(String diretorioArquivo) {
        this.diretorioArquivo = diretorioArquivo;
    }

    //Construtor
    public ManipulacaoArquivo() {
    }

    //Faz a leitura de um arquivo de texto comum
    public String leArquivo() throws IOException {
        Path diretorioAbsoluto = Paths.get(new File(this.diretorioArquivo).getAbsolutePath());
        Stream<String> linhasArquivo = Files.lines(diretorioAbsoluto);
        String dadosArquivo = "";
        for (Object linha : linhasArquivo.toArray()) {
            dadosArquivo += linha.toString();
        }
        linhasArquivo.close();
        return dadosArquivo;
    }

    //Faz a leitura de um arquivo codificado em Golumb
    public BitSet leArquivoCodificado() throws IOException {
        File arquivo = new File(this.diretorioArquivo);
        byte[] binario = Files.readAllBytes(Paths.get(arquivo.getAbsolutePath()));
        BitSet bites = BitSet.valueOf(binario);
        return bites;
    }

    //Escreve um BitSet codificado em Golumb
    public void esreveArquivo(BitSet bites) throws IOException {
        File outFile = new File(diretorioArquivo);
        FileOutputStream fos = new FileOutputStream(outFile);
        fos.write(bites.toByteArray());
        fos.close();
    }
    
    public void criarDiretorioParaEncodeFiles() {
        new File("C:" + File.separator + "Golomb").mkdir();
    }
}
