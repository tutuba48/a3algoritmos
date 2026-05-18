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
