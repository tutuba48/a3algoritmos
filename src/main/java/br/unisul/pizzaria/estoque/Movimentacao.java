package br.unisul.pizzaria.estoque;

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

    /**
     * Tela 1.2.1 — Entrada de Produto.
     */
    public static void entrada() {
        boolean novaEntrada = true;
        while (novaEntrada) {
            if (Estoque.vazio()) {
                Util.erro("Não há produtos cadastrados.");
                return;
            }

            String nome = Util.lerTexto("PRODUTO :");
            if (nome == null) return;

            int idx = Estoque.buscar(nome);
            if (idx == -1) {
                Util.erro("Produto não encontrado: " + nome);
                novaEntrada = Util.confirmar("NOVA ENTRADA ( S/N ) ?");
                continue;
            }

            int qtdAtual = Estoque.quantidades[idx];
            int qtdEntrada = Util.lerInt("QTDE ENTRADA :", 1);
            if (qtdEntrada == Integer.MIN_VALUE) return;

            int qtdFinal = qtdAtual + qtdEntrada;

            String resumo = Util.cabecalho("MOVIMENTAÇÃO — ENTRADA DE PRODUTO")
                    + " PRODUTO      : " + Estoque.nomes[idx] + "\n"
                    + " QTDE ATUAL   : " + qtdAtual + "\n"
                    + " QTDE ENTRADA : " + qtdEntrada + "\n"
                    + " QTDE FINAL   : " + qtdFinal + "\n";

            if (Util.confirmar(resumo + "\nCONFIRMA ENTRADA ( S/N ) ?")) {
                Estoque.quantidades[idx] = qtdFinal;
                Util.info("Entrada registrada com sucesso!");
            } else {
                Util.info("Entrada cancelada.");
            }

            novaEntrada = Util.confirmar("NOVA ENTRADA ( S/N ) ?");
        }
    }

    /**
     * Tela 1.2.2 — Saída de Produto.
     */
    public static void saida() {
        boolean novaSaida = true;
        while (novaSaida) {
            if (Estoque.vazio()) {
                Util.erro("Não há produtos cadastrados.");
                return;
            }

            String nome = Util.lerTexto("PRODUTO :");
            if (nome == null) return;

            int idx = Estoque.buscar(nome);
            if (idx == -1) {
                Util.erro("Produto não encontrado: " + nome);
                novaSaida = Util.confirmar("NOVA SAÍDA ( S/N ) ?");
                continue;
            }

            int qtdAtual = Estoque.quantidades[idx];

            if (qtdAtual == 0) {
                Util.erro("Produto sem saldo em estoque. Saída não permitida.");
                novaSaida = Util.confirmar("NOVA SAÍDA ( S/N ) ?");
                continue;
            }

            int qtdSaida;
            while (true) {
                qtdSaida = Util.lerInt("QTDE SAÍDA :", 1);
                if (qtdSaida == Integer.MIN_VALUE) return;
                if (qtdSaida > qtdAtual) {
                    Util.erro("Saldo insuficiente. Estoque atual: " + qtdAtual);
                    continue;
                }
                break;
            }

            int qtdFinal = qtdAtual - qtdSaida;

            String resumo = Util.cabecalho("MOVIMENTAÇÃO — SAÍDA DE PRODUTO")
                    + " PRODUTO    : " + Estoque.nomes[idx] + "\n"
                    + " QTDE ATUAL : " + qtdAtual + "\n"
                    + " QTDE SAÍDA : " + qtdSaida + "\n"
                    + " QTDE FINAL : " + qtdFinal + "\n";

            if (Util.confirmar(resumo + "\nCONFIRMA SAÍDA ( S/N ) ?")) {
                Estoque.quantidades[idx] = qtdFinal;
                Util.info("Saída registrada com sucesso!");
            } else {
                Util.info("Saída cancelada.");
            }

            novaSaida = Util.confirmar("NOVA SAÍDA ( S/N ) ?");
        }
    }
}
