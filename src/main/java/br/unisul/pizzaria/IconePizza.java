package br.unisul.pizzaria;

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
    }
}
