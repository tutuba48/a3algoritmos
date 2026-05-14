package br.unisul.pizzaria;

import javax.swing.JOptionPane;

/**
 * Main — Ponto de entrada e Menu Principal (Tela 1.0).
 *
 * Trabalho A3 — Algoritmos e Programação — UNISUL.
 * Sistema de Controle de Estoque para Pizzaria com interface JOptionPane.
 */
public class Main {

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(
                null,
                "Bem-vindo ao Sistema de Controle de Estoque\nPizzaria da Unisul Ltda.",
                "Bem-vindo",
                JOptionPane.INFORMATION_MESSAGE);

        boolean continuar = true;
        while (continuar) {
            String texto = "===== MENU PRINCIPAL =====\n"
                    + " 1 — CADASTRO DE PRODUTOS\n"
                    + " 2 — MOVIMENTAÇÃO\n"
                    + " 3 — REAJUSTE DE PREÇOS\n"
                    + " 4 — RELATÓRIOS\n"
                    + " 5 — BUSCAR PRODUTO\n"
                    + " 0 — FINALIZAR\n\n"
                    + " OPÇÃO :";

            String entrada = JOptionPane.showInputDialog(
                    null,
                    texto,
                    "Pizzaria da Unisul — Menu Principal",
                    JOptionPane.PLAIN_MESSAGE);

            if (entrada == null) {
                continuar = false;
                continue;
            }

            switch (entrada.trim()) {
                case "1": Cadastro.menu();       break;
                case "2": Movimentacao.menu();   break;
                case "3": Reajuste.menu();       break;
                case "4": Relatorios.menu();     break;
                case "5": Cadastro.consultar();  break;
                case "0": continuar = false;     break;
                default:
                    JOptionPane.showMessageDialog(
                            null,
                            "Opção inválida. Digite um número entre 0 e 5.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(
                null,
                "Sistema finalizado. Até logo!",
                "Encerrando",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
