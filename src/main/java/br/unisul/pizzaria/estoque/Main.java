package br.unisul.pizzaria.estoque;

import java.awt.GraphicsEnvironment;
import javax.swing.JOptionPane;

/**
 * Main — Ponto de entrada e Menu Principal (Tela 1.0).
 *
 * Trabalho A3 — Algoritmos e Programação — UNISUL.
 * Sistema de Controle de Estoque para Pizzaria com interface JOptionPane.
 */
public class Main {

    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            System.err.println("Erro: este aplicativo precisa de ambiente gráfico (GUI).");
            return;
        }

        try {
            // Pré-cadastra insumos típicos de pizzaria para facilitar o uso
            Estoque.preCadastrarInsumos();

            // Mensagem de boas-vindas centralizada (usando HTML para melhor visual)
            String boasVindas =
                    "<html>"
                  + "<div style='text-align: center; padding: 12px; width: 360px;'>"
                  + "<h2 style='margin-bottom: 2px;'>PIZZARIA DA UNISUL LTDA.</h2>"
                  + "<h3 style='margin-top: 0;'>Sistema de Controle de Estoque</h3>"
                  + "<hr>"
                  + "<p><b>Seja bem-vindo!</b></p>"
                  + "<p>O sistema foi iniciado com <b>" + Estoque.totalProdutos
                  + "</b> insumos pré-cadastrados.</p>"
                  + "<br>"
                  + "<p style='font-size: 11px; color: #555;'>"
                  + "Trabalho A3 — Algoritmos e Programação<br>"
                  + "UNISUL — 1ª Fase de Ciências da Computação"
                  + "</p>"
                  + "</div>"
                  + "</html>";
            Util.mensagem("Bem-vindo", boasVindas);

            // Loop do menu principal
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

                // Usa ícone customizado de pizza no lugar da interrogação padrão
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
                    // Usuário apertou Cancelar — confirma se quer sair
                    if (Util.confirmar("Deseja realmente finalizar o sistema?")) {
                        break;
                    } else {
                        continue;
                    }
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

            // Mensagem de despedida centralizada
            String despedida =
                    "<html>"
                  + "<div style='text-align: center; padding: 12px; width: 360px;'>"
                  + "<h2 style='margin-bottom: 2px;'>Até logo!</h2>"
                  + "<hr>"
                  + "<p>Sistema finalizado.</p>"
                  + "<p>Obrigado por utilizar o<br>"
                  + "<b>Controle de Estoque da Pizzaria</b>.</p>"
                  + "<br>"
                  + "<p style='font-size: 11px; color: #555;'>"
                  + "Equipe A3 — UNISUL"
                  + "</p>"
                  + "</div>"
                  + "</html>";
            Util.mensagem("Encerrando", despedida);
            System.exit(0);
        } catch (Throwable t) {
            exibirErroInesperado(t);
        }
    }

    private static void exibirErroInesperado(Throwable t) {
        t.printStackTrace();
        Util.erro("Ocorreu um erro inesperado:\n" + t.getClass().getSimpleName() + ": " + t.getMessage());
        System.exit(1);
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
