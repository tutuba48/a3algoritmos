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

}
