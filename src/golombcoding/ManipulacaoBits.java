package golombcoding;

import java.util.BitSet;

public class ManipulacaoBits {

    //Convert um BitSet para um String que cont�m a
    //representa��o dos zeros e uns. � utilizado
    //Somente para exibi��o em tela
    public String bitSetParaString(BitSet bit) {
        String binario = "";
        for (int i = bit.length() - 1; i > 0; i--) {
            if (bit.get(i) == true) {
                binario += 1;
            } else {
                binario += 0;
            }
        }
        return binario;
    }
}
