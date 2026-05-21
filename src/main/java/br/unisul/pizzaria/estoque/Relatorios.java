package br.unisul.pizzaria.estoque;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 * Relatórios — Tela 1.4 e suas telas filhas.
 *
 * Implementa os relatórios de Lista de Preços e Balanço Físico-Financeiro,
 * além de relatórios extras (estoque baixo, em falta).
 */
public class Relatorios {

    /**
     * Tela 1.4 — Menu de Relatórios.
     */
    public static void menu() {
        boolean continuar = true;
        while (continuar) {
            String texto = Util.cabecalho("RELATÓRIOS")
                    + " 1 — LISTA DE PREÇOS\n"
                    + " 2 — BALANÇO FÍSICO-FINANCEIRO\n"
                    + " 3 — PRODUTOS EM FALTA\n"
                    + " 4 — PRODUTOS COM ESTOQUE BAIXO\n"
                    + " 0 — RETORNAR\n\n"
                    + " OPÇÃO :";

            Object resp = JOptionPane.showInputDialog(
                    null,
                    texto,
                    "Pizzaria da Unisul — Relatórios",
                    JOptionPane.PLAIN_MESSAGE,
                    IconePizza.get(),
                    null,
                    "");
            String entrada = (resp == null) ? null : resp.toString();
            if (entrada == null) {
                return;
            }

            switch (entrada.trim()) {
                case "1": listaPrecos();             break;
                case "2": balancoFisicoFinanceiro(); break;
                case "3": produtosEmFalta();         break;
                case "4": produtosEstoqueBaixo();    break;
                case "0": continuar = false;         break;
                default:  opcaoInvalidaRelatorios();
            }
        }
    }

    /** Mensagem mostrada quando o usuário digita uma opção que não existe. */
    private static void opcaoInvalidaRelatorios() {
        Util.erro("Opção inválida. Digite um número entre 0 e 4.");
    }

    /**
     * Tela 1.4.1 — Lista de Preços em ordem alfabética.
     */
    public static void listaPrecos() {
        if (Estoque.vazio()) {
            Util.erro("Não há produtos cadastrados.");
            return;
        }

        String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StringBuilder sb = new StringBuilder();
        sb.append(centralizarMono(Util.EMPRESA, 70)).append("\n");
        sb.append(centralizarMono(Util.SISTEMA, 70)).append("\n\n");
        sb.append(dataAtual).append("        ");
        sb.append(centralizarMono("LISTA DE PREÇOS", 36));
        sb.append("        PG 001\n");
        sb.append(linha(70)).append("\n");
        sb.append(String.format("%-40s %-5s %15s%n", "PRODUTO", "UND", "PREÇO"));
        sb.append(linha(70)).append("\n");

        int[] ordem = Estoque.indicesOrdenadosAlfabeticamente();
        for (int i = 0; i < ordem.length; i++) {
            int idx = ordem[i];
            sb.append(String.format(
                    "%-40s %-5s %15s%n",
                    truncar(Estoque.nomes[idx], 40),
                    Estoque.unidades[idx],
                    Util.formatarPreco(Estoque.precos[idx])));
        }
        sb.append(linha(70)).append("\n");
        sb.append("Total de produtos listados: ").append(Estoque.totalProdutos).append("\n");

        Util.exibirRelatorio("Lista de Preços", sb.toString());
    }
