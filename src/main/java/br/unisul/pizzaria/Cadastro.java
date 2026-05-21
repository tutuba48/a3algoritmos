package br.unisul.pizzaria;

import javax.swing.JOptionPane;

/**
 * Cadastro de Produtos — Tela 1.1 e suas telas filhas (1.1.1 a 1.1.4).
 *
 * Implementa as operações de inclusão, alteração, consulta e exclusão
 * de produtos do estoque, conforme o roteiro do A3.
 */
public class Cadastro {

    /**
     * Tela 1.1 — menu de Cadastro de Produtos.
     */
    public static void menu() {
        boolean continuar = true;
        while (continuar) {
            String texto = Util.cabecalho("CADASTRO DE PRODUTOS")
                    + " 1 — INCLUSÃO\n"
                    + " 2 — ALTERAÇÃO\n"
                    + " 3 — CONSULTA\n"
                    + " 4 — EXCLUSÃO\n"
                    + " 0 — RETORNAR\n\n"
                    + " OPÇÃO :";

            Object resp = JOptionPane.showInputDialog(
                    null,
                    texto,
                    "Pizzaria da Unisul — Cadastro de Produtos",
                    JOptionPane.PLAIN_MESSAGE,
                    IconePizza.get(),
                    null,
                    "");
            String entrada = (resp == null) ? null : resp.toString();
            if (entrada == null) {
                return;
            }

            switch (entrada.trim()) {
                case "1": incluir();          break;
                case "2": alterar();          break;
                case "3": consultar();        break;
                case "4": excluir();          break;
                case "0": continuar = false;  break;
                default:  opcaoInvalidaCadastro();
            }
        }
    }

    /** Mensagem mostrada quando o usuário digita uma opção que não existe. */
    private static void opcaoInvalidaCadastro() {
        Util.erro("Opção inválida. Digite um número entre 0 e 4.");
    }
