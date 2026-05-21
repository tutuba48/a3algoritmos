package br.unisul.pizzaria;

public class Util {

    public static final int LARGURA = 50;
    public static final String EMPRESA = "PIZZARIA DA UNISUL LTDA.";
    public static final String SISTEMA = "SISTEMA DE CONTROLE DE ESTOQUE";

    public static String centralizar(String texto, int largura) {
        if (texto == null) return "";
        int comprimento = texto.length();
        if (comprimento >= largura) return texto;
        int espacos = (largura - comprimento) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < espacos; i++) sb.append(' ');
        sb.append(texto);
        return sb.toString();
    }

    public static String cabecalho(String titulo) {
        return centralizar(EMPRESA, LARGURA) + "\n"
             + centralizar(SISTEMA, LARGURA) + "\n\n"
             + centralizar(titulo, LARGURA) + "\n\n";
    }
}
