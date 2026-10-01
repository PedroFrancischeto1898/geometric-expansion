import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Placa extends JPanel {

    private String nome;
    private int nivel;
    private Font customFont;
    private Image imagem;
    private int imgX;
    private int imgY;
    private int imgWidth;
    private int imgHeight;
    private Runnable onClick;

    public Placa(
            String nome,
            int nivel,
            String imagemPath,
            int imgX,
            int imgY,
            int imgWidth,
            int imgHeight) {

        this.nome = nome;
        this.nivel = nivel;
        this.imgX = imgX;
        this.imgY = imgY;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;

        setBackground(new Color(0, 0, 0, 0));

        carregarFonte();
        carregarImagem(imagemPath);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (onClick != null) {
                    onClick.run();
                }
            }
        });
    }

    private void carregarFonte() {
        try (InputStream is =
                getClass().getResourceAsStream("/Assets/CustomFont.otf")) {

            if (is != null) {
                customFont = Font
                        .createFont(Font.TRUETYPE_FONT, is)
                        .deriveFont(45f);
            } else {
                System.err.println(
                        "Fonte CustomFont.otf não encontrada. Usando fonte padrão.");
                customFont = new Font("Serif", Font.PLAIN, 24);
            }

        } catch (IOException | FontFormatException e) {
            System.err.println("Erro ao carregar a fonte personalizada.");
            e.printStackTrace();

            customFont = new Font("Serif", Font.PLAIN, 24);
        }
    }

    private void carregarImagem(String imagemPath) {
        URL imagemUrl = getClass().getResource(imagemPath);

        if (imagemUrl != null) {
            imagem = new ImageIcon(imagemUrl).getImage();
        } else {
            System.err.println(
                    "Erro: imagem " + imagemPath + " não pôde ser carregada.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(0, 0, 0, 100));

        int arc = 30;
        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                arc,
                arc);

        g2.setFont(customFont);
        g2.setColor(Color.WHITE);

        g2.drawString(nome, 160, 90);

        String nivelStr = String.valueOf(nivel);
        g2.drawString(nivelStr, 550, 50);

        if (imagem != null) {
            g2.drawImage(
                    imagem,
                    imgX,
                    imgY,
                    imgWidth,
                    imgHeight,
                    this);
        }
    }

    public void atualizarPlaca(String nome, int nivel) {
        this.nome = nome;
        this.nivel = nivel;
        repaint();
    }

    public int getNivel() {
        return nivel;
    }

    public void setOnClick(Runnable onClick) {
        this.onClick = onClick;
    }

}
