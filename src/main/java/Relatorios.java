package br.unisul.pizzaria;

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
