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
