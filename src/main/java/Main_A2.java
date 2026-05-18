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
        Estoque.preCadastrarInsumos();

        JOptionPane.showMessageDialog(
                null,
                "Bem-vindo ao Sistema de Controle de Estoque\nPizzaria da Unisul Ltda.",
                "Bem-vindo",
                JOptionPane.INFORMATION_MESSAGE);

        boolean continuar = true;
        while (continuar) {
            String texto = Util.cabecalho("MENU PRINCIPAL")
                    + " 1 — CADASTRO DE PRODUTOS\n"
                    + " 2 — MOVIMENTAÇÃO\n"
                    + " 3 — REAJUSTE DE PREÇOS\n"
                    + " 4 — RELATÓRIOS\n"
                    + " 5 — BUSCAR PRODUTO\n"
                    + " 0 — FINALIZAR\n\n"
                    + " OPÇÃO :";

            Object resp = JOptionPane.showInputDialog(
                    null,
                    texto,
                    "Pizzaria da Unisul — Menu Principal",
                    JOptionPane.PLAIN_MESSAGE,
                    IconePizza.get(),
                    null,
                    "");
            String entrada = (resp == null) ? null : resp.toString();
            if (entrada == null) {
                continuar = false;
                continue;
            }

            switch (entrada.trim()) {
                case "1": abrirCadastro();        break;
                case "2": abrirMovimentacao();    break;
                case "3": abrirReajuste();        break;
                case "4": abrirRelatorios();      break;
                case "5": buscarProdutoRapido();  break;
                case "0": continuar = false;      break;
                default:  opcaoInvalidaPrincipal();
            }
        }

        JOptionPane.showMessageDialog(
                null,
                "Sistema finalizado. Até logo!",
                "Encerrando",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    // ============================================================
    // Sub-rotinas do menu principal (Tela 1.0)
    // Cada opção do switch chama uma destas funções.
    // ============================================================

    /** Opção 1 — abre o menu de cadastro de produtos. */
    private static void abrirCadastro() {
        Cadastro.menu();
    }

    /** Opção 2 — abre o menu de movimentação. */
    private static void abrirMovimentacao() {
        Movimentacao.menu();
    }

    /** Opção 3 — abre o menu de reajuste de preços. */
    private static void abrirReajuste() {
        Reajuste.menu();
    }

    /** Opção 4 — abre o menu de relatórios. */
    private static void abrirRelatorios() {
        Relatorios.menu();
    }

    /** Opção 5 — atalho para a consulta rápida de produto. */
    private static void buscarProdutoRapido() {
        Cadastro.consultar();
    }

    /** Caso o usuário digite uma opção que não existe no menu principal. */
    private static void opcaoInvalidaPrincipal() {
        Util.erro("Opção inválida. Digite um número entre 0 e 5.");
    }
}
