package br.unisul.pizzaria;

import javax.swing.JOptionPane;

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

    public static String lerTexto(String prompt) {
        while (true) {
            Object resp = JOptionPane.showInputDialog(
                    null, prompt, "Entrada de Dados",
                    JOptionPane.PLAIN_MESSAGE, IconePizza.get(), null, "");
            if (resp == null) return null;
            String entrada = resp.toString().trim();
            if (!entrada.isEmpty()) return entrada;
            erro("O campo não pode ficar em branco.");
        }
    }

    public static int lerInt(String prompt, int minimo) {
        while (true) {
            Object resp = JOptionPane.showInputDialog(
                    null, prompt, "Entrada de Dados",
                    JOptionPane.PLAIN_MESSAGE, IconePizza.get(), null, "");
            if (resp == null) return Integer.MIN_VALUE;
            try {
                int valor = Integer.parseInt(resp.toString().trim());
                if (valor < minimo) {
                    erro("Valor inválido. Deve ser maior ou igual a " + minimo + ".");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                erro("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    public static double lerDouble(String prompt, double minimo) {
        while (true) {
            Object resp = JOptionPane.showInputDialog(
                    null, prompt, "Entrada de Dados",
                    JOptionPane.PLAIN_MESSAGE, IconePizza.get(), null, "");
            if (resp == null) return Double.NaN;
            try {
                double valor = Double.parseDouble(resp.toString().trim().replace(',', '.'));
                if (valor < minimo) {
                    erro("Valor inválido. Deve ser maior ou igual a " + minimo + ".");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                erro("Valor inválido. Digite um número (ex.: 12,50).");
            }
        }
    }

    public static double lerDoublePositivo(String prompt) {
        while (true) {
            Object resp = JOptionPane.showInputDialog(
                    null, prompt, "Entrada de Dados",
                    JOptionPane.PLAIN_MESSAGE, IconePizza.get(), null, "");
            if (resp == null) return Double.NaN;
            try {
                double valor = Double.parseDouble(resp.toString().trim().replace(',', '.'));
                if (valor <= 0) {
                    erro("O valor deve ser maior que zero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                erro("Valor inválido. Digite um número (ex.: 12,50).");
            }
        }
    }
}
