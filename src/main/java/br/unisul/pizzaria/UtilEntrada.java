package br.unisul.pizzaria;

import javax.swing.JOptionPane;

public class UtilEntrada {

    public static String lerTexto(String prompt) {
        while (true) {
            Object resp = JOptionPane.showInputDialog(
                    null, prompt, "Entrada de Dados",
                    JOptionPane.PLAIN_MESSAGE, IconePizza.get(),
                    null, "");
            if (resp == null) {
                return null;
            }
            String entrada = resp.toString().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            UtilMensagem.erro("O campo não pode ficar em branco.");
        }
    }

    public static double lerDouble(String prompt, double minimo) {
        while (true) {
            Object resp = JOptionPane.showInputDialog(
                    null, prompt, "Entrada de Dados",
                    JOptionPane.PLAIN_MESSAGE, IconePizza.get(),
                    null, "");
            if (resp == null) {
                return Double.NaN;
            }
            try {
                double valor = Double.parseDouble(resp.toString().trim().replace(',', '.'));
                if (valor < minimo) {
                    UtilMensagem.erro("Valor inválido. Deve ser maior ou igual a " + minimo + ".");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                UtilMensagem.erro("Valor inválido. Digite um número (ex.: 12,50).");
            }
        }
    }

    public static double lerDoublePositivo(String prompt) {
        while (true) {
            Object resp = JOptionPane.showInputDialog(
                    null, prompt, "Entrada de Dados",
                    JOptionPane.PLAIN_MESSAGE, IconePizza.get(),
                    null, "");
            if (resp == null) {
                return Double.NaN;
            }
            try {
                double valor = Double.parseDouble(resp.toString().trim().replace(',', '.'));
                if (valor <= 0) {
                    UtilMensagem.erro("O valor deve ser maior que zero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                UtilMensagem.erro("Valor inválido. Digite um número (ex.: 12,50).");
            }
        }
    }

    public static int lerInt(String prompt, int minimo) {
        while (true) {
            Object resp = JOptionPane.showInputDialog(
                    null, prompt, "Entrada de Dados",
                    JOptionPane.PLAIN_MESSAGE, IconePizza.get(),
                    null, "");
            if (resp == null) {
                return Integer.MIN_VALUE;
            }
            try {
                int valor = Integer.parseInt(resp.toString().trim());
                if (valor < minimo) {
                    UtilMensagem.erro("Valor inválido. Deve ser maior ou igual a " + minimo + ".");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                UtilMensagem.erro("Valor inválido. Digite um número inteiro.");
            }
        }
    }
}
