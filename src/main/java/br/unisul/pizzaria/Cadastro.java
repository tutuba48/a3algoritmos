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
    
    /**
     * Tela 1.1.1 — Inclusão de Produto.
     */
    public static void incluir() {
        boolean novaInclusao = true;
        while (novaInclusao) {
            if (Estoque.cheio()) {
                Util.erro("Estoque cheio! Capacidade máxima de "
                        + Estoque.CAPACIDADE + " produtos atingida.");
                return;
            }

            Util.mensagem("Inclusão", Util.cabecalho("INCLUSÃO DE PRODUTO"));

            // Nome (único)
            String nome;
            while (true) {
                nome = Util.lerTexto("NOME :");
                if (nome == null) return;
                if (Estoque.buscar(nome) != -1) {
                    Util.erro("Já existe um produto com este nome. Digite outro.");
                    continue;
                }
                break;
            }

            // Preço > 0
            double preco = Util.lerDoublePositivo("PREÇO :");
            if (Double.isNaN(preco)) return;

            // Unidade
            String unidade = Util.lerTexto("UNIDADE (KG, L, UN, GR) :");
            if (unidade == null) return;
            unidade = unidade.toUpperCase();

            // Quantidade >= 0
            int quantidade = Util.lerInt("QUANTIDADE :", 0);
            if (quantidade == Integer.MIN_VALUE) return;

            String resumo = Util.cabecalho("INCLUSÃO DE PRODUTO")
                    + " NOME       : " + nome + "\n"
                    + " PREÇO      : " + Util.formatarPreco(preco) + "\n"
                    + " UNIDADE    : " + unidade + "\n"
                    + " QUANTIDADE : " + quantidade + "\n";

            if (Util.confirmar(resumo + "\nCONFIRMA INCLUSÃO ( S/N ) ?")) {
                Estoque.adicionar(nome, unidade, preco, quantidade);
                Util.info("Produto cadastrado com sucesso!");
            } else {
                Util.info("Inclusão cancelada.");
            }

            novaInclusao = Util.confirmar("NOVA INCLUSÃO ( S/N ) ?");
        }
    }

    /**
     * Tela 1.1.2 — Alteração de Produto.
     */
    public static void alterar() {
        boolean novaAlteracao = true;
        while (novaAlteracao) {
            if (Estoque.vazio()) {
                Util.erro("Não há produtos cadastrados.");
                return;
            }

            Util.mensagem("Alteração", Util.cabecalho("ALTERAÇÃO DE PRODUTO"));

            String nome = Util.lerTexto("NOME do produto a alterar :");
            if (nome == null) return;

            int idx = Estoque.buscar(nome);
            if (idx == -1) {
                Util.erro("Produto não encontrado: " + nome);
                novaAlteracao = Util.confirmar("NOVA ALTERAÇÃO ( S/N ) ?");
                continue;
            }

            String dadosAtuais = Util.cabecalho("ALTERAÇÃO DE PRODUTO")
                    + " NOME       : " + Estoque.nomes[idx] + "\n"
                    + " PREÇO      : " + Util.formatarPreco(Estoque.precos[idx]) + "\n"
                    + " UNIDADE    : " + Estoque.unidades[idx] + "\n"
                    + " QUANTIDADE : " + Estoque.quantidades[idx] + "\n";
            Util.mensagem("Dados Atuais", dadosAtuais
                    + "\n(O nome NÃO pode ser alterado. Para isso, exclua e recadastre.)");

            double novoPreco = Util.lerDoublePositivo("Novo PREÇO :");
            if (Double.isNaN(novoPreco)) return;

            String novaUnidade = Util.lerTexto("Nova UNIDADE :");
            if (novaUnidade == null) return;
            novaUnidade = novaUnidade.toUpperCase();

            int novaQtd = Util.lerInt("Nova QUANTIDADE :", 0);
            if (novaQtd == Integer.MIN_VALUE) return;

            String resumo = Util.cabecalho("ALTERAÇÃO DE PRODUTO")
                    + " NOME       : " + Estoque.nomes[idx] + "\n"
                    + " PREÇO      : " + Util.formatarPreco(novoPreco) + "\n"
                    + " UNIDADE    : " + novaUnidade + "\n"
                    + " QUANTIDADE : " + novaQtd + "\n";

            if (Util.confirmar(resumo + "\nCONFIRMA ALTERAÇÃO ( S/N ) ?")) {
                Estoque.precos[idx] = novoPreco;
                Estoque.unidades[idx] = novaUnidade;
                Estoque.quantidades[idx] = novaQtd;
                Util.info("Produto alterado com sucesso!");
            } else {
                Util.info("Alteração cancelada.");
            }

            novaAlteracao = Util.confirmar("NOVA ALTERAÇÃO ( S/N ) ?");
        }
    }
