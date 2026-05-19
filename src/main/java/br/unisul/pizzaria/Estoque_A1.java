package br.unisul.pizzaria;

/**
 * Estoque - armazenamento de produtos em vetores paralelos.
 *
 * Conforme o conteudo da 1a fase (aulas 01-08), os dados ficam em memoria
 * (vetores). Cada posicao i representa um produto cujos atributos estao
 * espalhados pelos quatro vetores paralelos.
 *
 * NAO usa POO (sem classe Produto), nem ArrayList, nem arquivos.
 */
public class Estoque {

    /** Capacidade maxima do estoque. */
    public static final int CAPACIDADE = 100;

    /** Vetor com os nomes dos produtos. */
    public static String[] nomes = new String[CAPACIDADE];

    /** Vetor com os precos unitarios dos produtos. */
    public static double[] precos = new double[CAPACIDADE];

    /** Vetor com as unidades de medida (KG, L, UN, GR). */
    public static String[] unidades = new String[CAPACIDADE];

    /** Vetor com as quantidades em estoque. */
    public static int[] quantidades = new int[CAPACIDADE];

    /** Total de produtos cadastrados (tamanho efetivo dos vetores). */
    public static int totalProdutos = 0;
}
