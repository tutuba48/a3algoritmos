package br.unisul.pizzaria;

import javax.swing.JOptionPane;

public class UtilMensagem {

    public static boolean confirmar(String pergunta) {
        Object[] opcoes = {"Sim", "Não"};
        int resp = JOptionPane.showOptionDialog(
                null,
                pergunta,
                "Confirmação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                IconePizza.get(),
                opcoes,
                opcoes[0]);
        return resp == JOptionPane.YES_OPTION;
    }

    public static void erro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro",
                JOptionPane.PLAIN_MESSAGE, IconePizza.get());
    }

    public static void info(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Informação",
                JOptionPane.PLAIN_MESSAGE, IconePizza.get());
    }

    public static void mensagem(String titulo, String conteudo) {
        JOptionPane.showMessageDialog(null, conteudo, titulo,
                JOptionPane.PLAIN_MESSAGE, IconePizza.get());
    }
}
