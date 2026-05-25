package br.unisul.estoque;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 * Relatorios — Tela 1.4 e suas telas filhas.
     *
     * <p>Implementa os relatorios de Lista de Precos e Balanco Fisico-Financeiro,
 * alem de relatorios extras (estoque baixo, em falta).</p>
     */
public class Relatorios {

    /**
     * Tela 1.4 — Menu de Relatorios.
         * Exibe opcoes de relatorios e direciona para o relatorio escolhido.
         */
    public static void menu() {
                boolean continuar = true;
                while (continuar) {
                                String texto = Util.cabecalho("RELATORIOS")
                                                        + " 1 — LISTA DE PRECOS\n"
                                                        + " 2 — BALANCO FISICO-FINANCEIRO\n"
                                                        + " 3 — PRODUTOS EM FALTA\n"
                                                        + " 4 — PRODUTOS COM ESTOQUE BAIXO\n"
                                                        + " 0 — RETORNAR\n\n"
                                                        + " OPCAO :";

                    Object resp = JOptionPane.showInputDialog(
                                            null,
                                            texto,
                                            "Sistema de Estoque — Relatorios",
                                            JOptionPane.PLAIN_MESSAGE,
                                            IconePizza.get(),
                                            null,
                                            "");
                                String entrada = (resp == null) ? null : resp.toString();
                                if (entrada == null) {
                                                    return;
                                }

                    switch (entrada.trim()) {
                        case "1": listaPrecos(); break;
                        case "2": balancoFisicoFinanceiro(); break;
                        case "3": produtosEmFalta(); break;
                        case "4": produtosEstoqueBaixo(); break;
                        case "0": continuar = false; break;
                        default: opcaoInvalidaRelatorios();
                    }
                }
    }

    /**
     * Mensagem mostrada quando o usuario digita uma opcao que nao existe no menu
         * de relatorios.
         */
    private static void opcaoInvalidaRelatorios() {
                Util.erro("Opcao invalida. Digite um numero entre 0 e 4.");
    }

    /**
     * Tela 1.4.1 — Lista de Precos em ordem alfabetica.
         * Exibe todos os produtos cadastrados com seus respectivos precos unitarios.
         */
    public static void listaPrecos() {
                if (Estoque.vazio()) {
                                Util.erro("Nao ha produtos cadastrados.");
                                return;
                }

            String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                StringBuilder sb = new StringBuilder();
                sb.append(centralizarMono(Util.EMPRESA, 70)).append("\n");
                sb.append(centralizarMono(Util.SISTEMA, 70)).append("\n\n");
                sb.append(dataAtual).append(" ");
                sb.append(centralizarMono("LISTA DE PRECOS", 36));
                sb.append(" PG 001\n");
                sb.append(linha(70)).append("\n");
                sb.append(String.format("%-40s %-5s %15s%n", "PRODUTO", "UND", "PRECO"));
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

            Util.exibirRelatorio("Lista de Precos", sb.toString());
    }

    /**
     * Tela 1.4.2 — Balanco Fisico-Financeiro.
         * Exibe todos os produtos com quantidade em estoque, preco unitario e valor total,
         * alem dos totais gerais de itens e valor do estoque.
         */
        public static void balancoFisicoFinanceiro() {
            if (Estoque.vazio()) {
                            Util.erro("Nao ha produtos cadastrados.");
                            return;
            }

            String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    StringBuilder sb = new StringBuilder();
                    sb.append(centralizarMono(Util.EMPRESA, 85)).append("\n");
                    sb.append(centralizarMono(Util.SISTEMA, 85)).append("\n\n");
                    sb.append(dataAtual).append(" ");
                    sb.append(centralizarMono("BALANCO FISICO-FINANCEIRO", 51));
                    sb.append(" PG 001\n");
                    sb.append(linha(85)).append("\n");
                    sb.append(String.format("%-30s %-5s %15s %6s %15s%n",
                                                            "PRODUTO", "UND", "PRECO UNIT.", "QTDE", "PRECO TOTAL"));
                    sb.append(linha(85)).append("\n");

            double valorTotalEstoque = 0.0;
                    int totalItens = 0;
                    int[] ordem = Estoque.indicesOrdenadosAlfabeticamente();
                    for (int i = 0; i < ordem.length; i++) {
                                    int idx = ordem[i];
                                    double subtotal = Estoque.precos[idx] * Estoque.quantidades[idx];
                                    valorTotalEstoque += subtotal;
                                    totalItens += Estoque.quantidades[idx];
                                    sb.append(String.format(
                                                            "%-30s %-5s %15s %6d %15s%n",
                                                            truncar(Estoque.nomes[idx], 30),
                                                            Estoque.unidades[idx],
                                                            Util.formatarPreco(Estoque.precos[idx]),
                                                            Estoque.quantidades[idx],
                                                            Util.formatarPreco(subtotal)));
                    }
                    sb.append(linha(85)).append("\n");
                    sb.append(String.format("TOTAL DE ITENS NO ESTOQUE : %d%n", totalItens));
                    sb.append(String.format("VALOR TOTAL DO ESTOQUE    : %s%n",
                                                            Util.formatarPreco(valorTotalEstoque)));

            Util.exibirRelatorio("Balanco Fisico-Financeiro", sb.toString());
        }

        /**
         * Relatorio extra: produtos com quantidade igual a zero (em falta).
         * Lista todos os produtos sem saldo em estoque.
         */
        public static void produtosEmFalta() {
            if (Estoque.vazio()) {
                            Util.erro("Nao ha produtos cadastrados.");
                            return;
            }

        StringBuilder sb = new StringBuilder();
            sb.append(centralizarMono(Util.EMPRESA, 50)).append("\n");
            sb.append(centralizarMono(Util.SISTEMA, 50)).append("\n\n");
            sb.append(centralizarMono("PRODUTOS EM FALTA (quantidade = 0)", 50)).append("\n");
            sb.append(linha(50)).append("\n");

        int contador = 0;
            int[] ordem = Estoque.indicesOrdenadosAlfabeticamente();
            for (int i = 0; i < ordem.length; i++) {
                            int idx = ordem[i];
                            if (Estoque.quantidades[idx] == 0) {
                                                sb.append(String.format("%-40s %s%n",
                                                                                                Estoque.nomes[idx], Estoque.unidades[idx]));
                                                contador++;
                            }
            }
            sb.append(linha(50)).append("\n");
            sb.append("Total: ").append(contador).append(" produto(s) em falta.\n");

        Util.exibirRelatorio("Produtos em Falta", sb.toString());
}

    /**
     * Relatorio extra: produtos com quantidade abaixo do limiar (5 unidades).
             * Lista todos os produtos com estoque critico.
             */
            public static void produtosEstoqueBaixo() {
                if (Estoque.vazio()) {
                                Util.erro("Nao ha produtos cadastrados.");
                                return;
                }

        final int LIMIAR = 5;
                StringBuilder sb = new StringBuilder();
                sb.append(centralizarMono(Util.EMPRESA, 60)).append("\n");
                sb.append(centralizarMono(Util.SISTEMA, 60)).append("\n\n");
                sb.append(centralizarMono(
                                    "PRODUTOS COM ESTOQUE BAIXO (quantidade < " + LIMIAR + ")", 60))
                                    .append("\n");
                sb.append(linha(60)).append("\n");
                sb.append(String.format("%-40s %-5s %6s%n", "PRODUTO", "UND", "QTDE"));
                sb.append(linha(60)).append("\n");

        int contador = 0;
                int[] ordem = Estoque.indicesOrdenadosAlfabeticamente();
                for (int i = 0; i < ordem.length; i++) {
                                int idx = ordem[i];
                                if (Estoque.quantidades[idx] < LIMIAR) {
                                                    sb.append(String.format("%-40s %-5s %6d%n",
                                                                                                    Estoque.nomes[idx],
                                                                                                    Estoque.unidades[idx],
                                                                                                    Estoque.quantidades[idx]));
                                                    contador++;
                                }
                }
                sb.append(linha(60)).append("\n");
                sb.append("Total: ").append(contador).append(" produto(s) com estoque baixo.\n");

        Util.exibirRelatorio("Produtos com Estoque Baixo", sb.toString());
    }

    /**
     * Gera uma linha horizontal de tracados com o tamanho fornecido.
             *
             * @param tamanho o numero de caracteres '-' que compoem a linha
             * @return uma string com {@code tamanho} caracteres '-'
             */
            private static String linha(int tamanho) {
                StringBuilder sb = new StringBuilder();
                                               for (int i = 0; i < tamanho; i++) {
                                                               sb.append('-');
                                               }
                                               return sb.toString();
                                      }

    /**
     * Trunca um texto se ele ultrapassar o limite de caracteres especificado.
             *
             * @param texto  o texto a ser truncado; se {@code null}, retorna {@code ""}
             * @param limite o numero maximo de caracteres permitido
             * @return o texto original se dentro do limite, ou os primeiros {@code limite}
     *         caracteres caso ultrapasse
              */
             private static String truncar(String texto, int limite) {
                 if (texto == null) return "";
                 if (texto.length() <= limite) return texto;
                 return texto.substring(0, limite);
     }

    /**
     * Centraliza um texto para uso em fonte monoespaca (relatorios), preenchendo
             * com espacos a esquerda para alinhar o texto ao centro dentro da largura dada.
             *
             * @param texto   o texto a ser centralizado; se {@code null}, retorna {@code ""}
             * @param largura a largura total da coluna em caracteres
             * @return o texto com espacos a esquerda para centralizacao
             */
            private static String centralizarMono(String texto, int largura) {
                        if (texto == null) return "";
                        if (texto.length() >= largura) return texto;
                        int espacos = (largura - texto.length()) / 2;
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < espacos; i++) sb.append(' ');
                        sb.append(texto);
                        return sb.toString();
            }
}
