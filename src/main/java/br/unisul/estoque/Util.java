package br.unisul.estoque;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Classe utilitaria com constantes e metodos auxiliares usados por todo o sistema.
     *
     * <p>Fornece recursos de entrada de dados via dialogo, formatacao de texto,
     * exibicao de mensagens e relatorios em fonte monoespaca.</p>
     */
public class Util {

    /** Largura padrao (em caracteres) usada na centralizacao de textos. */
    public static final int LARGURA = 50;

    /** Nome da empresa exibido no cabecalho dos relatorios. */
    public static final String EMPRESA = "UNISUL LTDA.";

    /** Nome do sistema exibido no cabecalho dos relatorios. */
    public static final String SISTEMA = "SISTEMA DE CONTROLE DE ESTOQUE";

    /**
     * Centraliza um texto dentro de um campo de largura fixa, preenchendo
         * com espacos a esquerda.
         *
         * @param texto   o texto a ser centralizado; se {@code null}, retorna {@code ""}
         * @param largura a largura total do campo em caracteres
              * @return o texto centralizado com espacos a esquerda
              */
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

    /**
     * Monta o cabecalho padrao dos relatorios, contendo o nome da empresa,
         * o nome do sistema e o titulo da tela.
         *
         * @param titulo o titulo da secao ou tela a ser exibido no cabecalho
         * @return string multilinhas com o cabecalho formatado
         */
    public static String cabecalho(String titulo) {
                return centralizar(EMPRESA, LARGURA) + "\n"
                                    + centralizar(SISTEMA, LARGURA) + "\n\n"
                                    + centralizar(titulo, LARGURA) + "\n\n";
    }

    /**
     * Exibe um dialogo de entrada e solicita que o usuario informe um texto
         * nao vazio.
         *
         * @param prompt mensagem exibida ao usuario descrevendo o campo esperado
         * @return o texto digitado pelo usuario (sem espacos nas extremidades),
         *         ou {@code null} se o usuario cancelar
         */
    public static String lerTexto(String prompt) {
                while (true) {
                                Object resp = JOptionPane.showInputDialog(
                                                        null, prompt, "Entrada de Dados",
                                                        JOptionPane.PLAIN_MESSAGE, IconePizza.get(), null, "");
                                if (resp == null) return null;
                                String entrada = resp.toString().trim();
                                if (!entrada.isEmpty()) return entrada;
                                erro("O campo nao pode ficar em branco.");
                }
    }

    /**
     * Exibe um dialogo de entrada e solicita que o usuario informe um numero
         * inteiro maior ou igual a {@code minimo}.
     *
     * @param prompt  mensagem exibida ao usuario descrevendo o campo esperado
         * @param minimo  valor inteiro minimo aceito (inclusive)
         * @return o inteiro digitado pelo usuario,
         *         ou {@link Integer#MIN_VALUE} se o usuario cancelar
         */
    public static int lerInt(String prompt, int minimo) {
                while (true) {
                                Object resp = JOptionPane.showInputDialog(
                                                        null, prompt, "Entrada de Dados",
                                                        JOptionPane.PLAIN_MESSAGE, IconePizza.get(), null, "");
                                if (resp == null) return Integer.MIN_VALUE;
                                try {
                                                    int valor = Integer.parseInt(resp.toString().trim());
                                                    if (valor < minimo) {
                                                                            erro("Valor invalido. Deve ser maior ou igual a " + minimo + ".");
                                                                            continue;
                                                    }
                                                    return valor;
                                } catch (NumberFormatException e) {
                                                    erro("Valor invalido. Digite um numero inteiro.");
                                }
                }
    }

    /**
     * Exibe um dialogo de entrada e solicita que o usuario informe um numero
         * decimal maior ou igual a {@code minimo}.
     *
              * @param prompt  mensagem exibida ao usuario descrevendo o campo esperado
              * @param minimo  valor decimal minimo aceito (inclusive)
              * @return o double digitado pelo usuario,
              *         ou {@link Double#NaN} se o usuario cancelar
         */
    public static double lerDouble(String prompt, double minimo) {
                while (true) {
                                Object resp = JOptionPane.showInputDialog(
                                                        null, prompt, "Entrada de Dados",
                                                        JOptionPane.PLAIN_MESSAGE, IconePizza.get(), null, "");
                                if (resp == null) return Double.NaN;
                                try {
                                                    double valor = Double.parseDouble(resp.toString().trim().replace(',', '.'));
                                                    if (valor < minimo) {
                                                                            erro("Valor invalido. Deve ser maior ou igual a " + minimo + ".");
                                                                            continue;
                                                    }
                                                    return valor;
                                } catch (NumberFormatException e) {
                                                    erro("Valor invalido. Digite um numero (ex.: 12,50).");
                                }
                }
    }

    /**
     * Exibe um dialogo de entrada e solicita que o usuario informe um numero
         * decimal estritamente positivo (maior que zero).
         *
         * @param prompt mensagem exibida ao usuario descrevendo o campo esperado
         * @return o double positivo digitado pelo usuario,
         *         ou {@link Double#NaN} se o usuario cancelar
         */
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
                                                    erro("Valor invalido. Digite um numero (ex.: 12,50).");
                                }
                }
    }

    /**
     * Exibe um dialogo de confirmacao com opcoes "Sim" e "Nao".
         *
         * @param pergunta a questao a ser apresentada ao usuario
         * @return {@code true} se o usuario escolher "Sim"; {@code false} caso contrario
         */
        public static boolean confirmar(String pergunta) {
            Object[] opcoes = {"Sim", "Nao"};
        int resp = JOptionPane.showOptionDialog(
                            null, pergunta, "Confirmacao",
                            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                            IconePizza.get(), opcoes, opcoes[0]);
        return resp == JOptionPane.YES_OPTION;
}

    /**
     * Exibe uma mensagem informativa ao usuario.
             *
             * @param mensagem o texto da mensagem a ser exibida
             */
            public static void info(String mensagem) {
                JOptionPane.showMessageDialog(null, mensagem, "Informacao",
                                                              JOptionPane.PLAIN_MESSAGE, IconePizza.get());
    }

    /**
     * Exibe uma mensagem de erro ao usuario.
             *
             * @param mensagem o texto da mensagem de erro a ser exibida
             */
            public static void erro(String mensagem) {
                JOptionPane.showMessageDialog(null, mensagem, "Erro",
                                                              JOptionPane.PLAIN_MESSAGE, IconePizza.get());
    }

    /**
     * Exibe uma mensagem generica em um dialogo com titulo personalizado.
             *
             * @param titulo   o titulo da janela do dialogo
             * @param conteudo o texto do conteudo a ser exibido
             */
            public static void mensagem(String titulo, String conteudo) {
                JOptionPane.showMessageDialog(null, conteudo, titulo,
                                                              JOptionPane.PLAIN_MESSAGE, IconePizza.get());
    }

    /**
     * Exibe um relatorio de texto em uma janela com area de rolagem,
             * utilizando fonte monoespaca para alinhamento correto das colunas.
             *
             * @param titulo   o titulo da janela do relatorio
             * @param conteudo o texto completo do relatorio a ser exibido
             */
            public static void exibirRelatorio(String titulo, String conteudo) {
                JTextArea area = new JTextArea(conteudo, 22, 70);
                area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
                area.setEditable(false);
                JScrollPane scroll = new JScrollPane(area);
                JOptionPane.showMessageDialog(null, scroll, titulo,
                                                              JOptionPane.PLAIN_MESSAGE, IconePizza.get());
    }

    /**
     * Formata um valor monetario no padrao brasileiro, com duas casas decimais
             * e separador de milhar.
             *
             * @param valor o valor numerico a ser formatado
             * @return a representacao textual do valor (ex.: {@code "1.234,56"})
     */
    public static String formatarPreco(double valor) {
                return String.format("%,.2f", valor);
    }
                                                              }
