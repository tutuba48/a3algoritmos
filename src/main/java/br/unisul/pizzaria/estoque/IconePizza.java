package br.unisul.pizzaria.estoque;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * Ícone personalizado: uma pizza estilo italiano desenhada
 * programaticamente em Java2D para substituir o ícone padrão
 * de interrogação do JOptionPane no menu principal.
 *
 * O desenho é estático (criado uma única vez na primeira chamada)
 * e reaproveitado em todas as telas que usarem IconePizza.get().
 */
public class IconePizza {

    /** Instância única (cache) do ícone. */
    private static ImageIcon instancia = null;

    /** Tamanho do ícone em pixels (quadrado). */
    private static final int TAMANHO = 64;

    /**
     * Retorna o ícone de pizza, desenhando-o se ainda não existir.
     */
    public static ImageIcon get() {
        if (instancia == null) {
            instancia = new ImageIcon(desenhar());
        }
        return instancia;
    }

    /**
     * Desenha a pizza em uma BufferedImage transparente.
     * Camadas (de trás para frente):
     *  1) sombra sutil
     *  2) borda da massa (mais clara)
     *  3) crosta interna (mais escura)
     *  4) molho de tomate (vermelho)
     *  5) queijo derretido (manchas amarelas)
     *  6) calabresa (círculos vermelho-escuro)
     *  7) folhas de manjericão (verde)
     */
    private static BufferedImage desenhar() {
        BufferedImage img = new BufferedImage(TAMANHO, TAMANHO,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // 1) Sombra sutil deslocada
        g.setColor(new Color(0, 0, 0, 50));
        g.fillOval(6, 8, 54, 54);

        // 2) Borda externa (massa dourada)
        g.setColor(new Color(222, 170, 95));
        g.fillOval(4, 4, 56, 56);

        // 3) Crosta interna (mais escura, dá relevo)
        g.setColor(new Color(190, 130, 65));
        g.fillOval(6, 6, 52, 52);

        // 4) Molho de tomate (vermelho italiano)
        g.setColor(new Color(215, 60, 45));
        g.fillOval(10, 10, 44, 44);

        // 5) Manchas de queijo derretido (amarelo claro)
        g.setColor(new Color(255, 228, 130));
        g.fillOval(14, 14, 16, 13);
        g.fillOval(34, 17, 14, 11);
        g.fillOval(17, 36, 13, 11);
        g.fillOval(36, 37, 14, 11);
        g.fillOval(24, 26, 10, 8);

        // 6) Calabresa (círculos vermelho-escuro)
        g.setColor(new Color(155, 25, 20));
        int[] xs = {18, 38, 27, 42, 22, 36};
        int[] ys = {21, 28, 37, 44, 45, 22};
        for (int i = 0; i < xs.length; i++) {
            g.fillOval(xs[i], ys[i], 7, 7);
        }
        // Brilho da calabresa (pontinhos mais claros no meio)
        g.setColor(new Color(200, 60, 50));
        for (int i = 0; i < xs.length; i++) {
            g.fillOval(xs[i] + 2, ys[i] + 2, 2, 2);
        }

        // 7) Folhas de manjericão (toque italiano)
        g.setColor(new Color(40, 130, 55));
        g.fillOval(30, 24, 5, 8);
        g.fillOval(23, 32, 5, 7);
        g.fillOval(43, 32, 5, 7);

        g.dispose();
        return img;
    }
}
