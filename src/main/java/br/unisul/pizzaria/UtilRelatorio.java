package br.unisul.pizzaria;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UtilRelatorio {

    public static void exibirRelatorio(String titulo, String conteudo) {
        JTextArea area = new JTextArea(conteudo, 22, 70);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(null, scroll, titulo,
                JOptionPane.PLAIN_MESSAGE, IconePizza.get());
    }
}
