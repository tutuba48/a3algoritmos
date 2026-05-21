package br.unisul.pizzaria.estoque;

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

    /**
     * Pre-cadastra 10 insumos tipicos de uma pizzaria para facilitar
     * a demonstracao do sistema.
     */
    public static void preCadastrarInsumos() {
        adicionar("Mussarela",       "KG", 45.00, 10);
        adicionar("Molho de Tomate", "L",  18.00,  8);
        adicionar("Farinha de Trigo","KG",  6.00, 50);
        adicionar("Calabresa",       "KG", 32.00, 12);
        adicionar("Azeitona Preta",  "KG", 28.00,  5);
        adicionar("Orégano",         "GR",  0.50,200);
        adicionar("Refrigerante 2L", "UN",  8.00, 30);
        adicionar("Catupiry",        "KG", 38.00,  7);
        adicionar("Presunto",        "KG", 25.00,  9);
        adicionar("Cebola",          "KG",  4.00, 15);
    }

    /**
     * Adiciona um produto ao final dos vetores. Uso interno.
     */
    public static void adicionar(String nome, String unidade, double preco, int quantidade) {
        if (totalProdutos >= CAPACIDADE) {
            return;
        }
        nomes[totalProdutos] = nome;
        unidades[totalProdutos] = unidade;
        precos[totalProdutos] = preco;
        quantidades[totalProdutos] = quantidade;
        totalProdutos++;
    }

    /**
     * Remove o produto na posicao indicada deslocando os elementos
     * seguintes uma posicao a esquerda.
     */
    public static void removerNaPosicao(int indice) {
        if (indice < 0 || indice >= totalProdutos) {
            return;
        }
        for (int i = indice; i < totalProdutos - 1; i++) {
            nomes[i] = nomes[i + 1];
            precos[i] = precos[i + 1];
            unidades[i] = unidades[i + 1];
            quantidades[i] = quantidades[i + 1];
        }
        totalProdutos--;
        nomes[totalProdutos] = null;
        precos[totalProdutos] = 0.0;
        unidades[totalProdutos] = null;
        quantidades[totalProdutos] = 0;
    }

    /**
     * Busca um produto pelo nome (case-insensitive, sem espacos nas pontas).
     *
     * @param nome nome a procurar
     * @return indice do produto ou -1 se nao encontrado
     */
    public static int buscar(String nome) {
        if (nome == null) {
            return -1;
        }
        String chave = nome.trim();
        for (int i = 0; i < totalProdutos; i++) {
            if (nomes[i].equalsIgnoreCase(chave)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna um vetor de indices ordenados em ordem alfabetica
     * (sem alterar os vetores originais). Usa bubble sort, que e o
     * algoritmo visto no exercicio 26 da aula de vetores.
     */
    public static int[] indicesOrdenadosAlfabeticamente() {
        int[] ordem = new int[totalProdutos];
        for (int i = 0; i < totalProdutos; i++) {
            ordem[i] = i;
        }

        for (int i = 0; i < totalProdutos - 1; i++) {
            for (int j = i + 1; j < totalProdutos; j++) {
                if (nomes[ordem[j]].compareToIgnoreCase(nomes[ordem[i]]) < 0) {
                    int aux = ordem[i];
                    ordem[i] = ordem[j];
                    ordem[j] = aux;
                }
            }
        }
        return ordem;
    }

    /** Retorna true se o estoque esta cheio. */
    public static boolean cheio() {
        return totalProdutos >= CAPACIDADE;
    }

    /** Retorna true se nao ha nenhum produto cadastrado. */
    public static boolean vazio() {
        return totalProdutos == 0;
    }
}
