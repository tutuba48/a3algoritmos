package br.unisul.pizzaria;
package br.unisul.estoque;
import javax.swing.JOptionPane;

/**
 * Movimentação de Estoque — Tela 1.2 e suas telas filhas (1.2.1 e 1.2.2).
 *
 * Implementa as operações de entrada e saída de produtos,
 * atualizando o saldo do estoque.
 */
public class Movimentacao {

    /**
     * Tela 1.2 — menu de Movimentação.
     */
    public static void menu() {
        boolean continuar = true;
        while (continuar) {
            String texto = Util.cabecalho("MOVIMENTAÇÃO")
                    + " 1 — ENTRADA\n"
                    + " 2 — SAÍDA\n"
                    + " 0 — RETORNAR\n\n"
                    + " OPÇÃO :";

            Object resp = JOptionPane.showInputDialog(
                    null,
                    texto,
                    "Pizzaria da Unisul — Movimentação",
                    JOptionPane.PLAIN_MESSAGE,
                    IconePizza.get(),
                    null,
                    "");
            String entrada = (resp == null) ? null : resp.toString();
            if (entrada == null) {
                return;
            }

            switch (entrada.trim()) {
                case "1": entrada();         break;
                case "2": saida();           break;
                case "0": continuar = false; break;
                default:  opcaoInvalidaMovimentacao();
            }
        }
    }

    /** Mensagem mostrada quando o usuário digita uma opção que não existe. */
    private static void opcaoInvalidaMovimentacao() {
        Util.erro("Opção inválida. Digite 0, 1 ou 2.");
    }
