package br.unisul.pizzaria;
import javax.swing.JOptionPane;

/**
 * Reajuste de Preços — Tela 1.3.
 *
 * Permite reajustar o preço de um produto específico ou de todos
 * os produtos do estoque por um percentual informado.
 */
public class Reajuste {

    /**
     * Tela 1.3 — Menu de Reajuste de Preços.
     */
    public static void menu() {
        boolean continuar = true;
        while (continuar) {
            if (Estoque.vazio()) {
                Util.erro("Não há produtos cadastrados.");
                return;
            }

            String texto = Util.cabecalho("REAJUSTE DE PREÇOS")
                    + " 1 — REAJUSTE DE UM PRODUTO\n"
                    + " 2 — REAJUSTE GERAL\n"
                    + " 0 — RETORNAR\n\n"
                    + " OPÇÃO :";

            Object resp = JOptionPane.showInputDialog(
                    null,
                    texto,
                    "Pizzaria da Unisul — Reajuste de Preços",
                    JOptionPane.PLAIN_MESSAGE,
                    IconePizza.get(),
                    null,
                    "");
            String entrada = (resp == null) ? null : resp.toString();
            if (entrada == null) {
                return;
            }

            switch (entrada.trim()) {
                case "1": reajusteUm();      break;
                case "2": reajusteGeral();   break;
                case "0": continuar = false; break;
                default:  opcaoInvalidaReajuste();
            }
        }
    }

    /** Mensagem mostrada quando o usuário digita uma opção que não existe. */
    private static void opcaoInvalidaReajuste() {
        Util.erro("Opção inválida. Digite 0, 1 ou 2.");
    }

    /**
     * Reajuste do preço de um único produto.
     */
    public static void reajusteUm() {
        boolean novoReajuste = true;
        while (novoReajuste) {
            String nome = Util.lerTexto("PRODUTO :");
            if (nome == null) return;

            int idx = Estoque.buscar(nome);
            if (idx == -1) {
                Util.erro("Produto não encontrado: " + nome);
                novoReajuste = Util.confirmar("NOVO REAJUSTE ( S/N ) ?");
                continue;
            }

            String dados = Util.cabecalho("REAJUSTE DE PREÇOS")
                    + " PRODUTO     : " + Estoque.nomes[idx] + "\n"
                    + " UNIDADE     : " + Estoque.unidades[idx] + "\n"
                    + " PREÇO ATUAL : " + Util.formatarPreco(Estoque.precos[idx]) + "\n";
            Util.mensagem("Reajuste", dados);

            // Aceita percentuais negativos (descontos)
            double percentual = Util.lerDouble(
                    "PERCENTUAL DE REAJUSTE (use negativo para desconto, ex.: 10 ou -5):",
                    -100.0);
            if (Double.isNaN(percentual)) return;

            double novoPreco = Estoque.precos[idx] * (1 + percentual / 100.0);
            if (novoPreco <= 0) {
                Util.erro("O preço resultante deve ser maior que zero. Operação cancelada.");
                novoReajuste = Util.confirmar("NOVO REAJUSTE ( S/N ) ?");
                continue;
            }

            String resumo = dados
                    + " PERCENTUAL  : " + Util.formatarPreco(percentual) + "%\n"
                    + " NOVO PREÇO  : " + Util.formatarPreco(novoPreco) + "\n";

            if (Util.confirmar(resumo + "\nCONFIRMA REAJUSTE ( S/N ) ?")) {
                Estoque.precos[idx] = novoPreco;
                Util.info("Reajuste aplicado com sucesso!");
            } else {
                Util.info("Reajuste cancelado.");
            }

            novoReajuste = Util.confirmar("NOVO REAJUSTE ( S/N ) ?");
        }
    }

    /**
     * Reajuste de todos os produtos por um mesmo percentual.
     */
