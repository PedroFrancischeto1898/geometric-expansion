import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

public class Placa extends JPanel {
    //Privatizar as variaveis que vou usar nessa bomba de placa para poder pegar direto e não precisar passar por parametro
        private String nome;
        private int nivel;
        private Font customFont;
        private Image imagem;
        private int imgX, imgY, imgWidth, imgHeight;
        private Runnable onClick;

    public Placa(String nome, int nivel, String imagemPath, int imgX, int imgY, int imgWidth, int imgHeight) {
        //Passando os parametros para as variaveis que eu criei
            this.nome = nome;
            this.nivel = nivel;
            this.imgX = imgX;
            this.imgY = imgY;
            this.imgWidth = imgWidth;
            this.imgHeight = imgHeight;
        
        //nessa bomba eu defino a cor da placa 
        setBackground(new Color(0, 0, 0, 0));

        //Essa parte é receita de bolo para caregar a fonte e a imagem , nem precisava de catch mas eu dei ctrl c no código que tinha feito na lista
            try {
                InputStream is = getClass().getResourceAsStream("/Assets/CustomFont.otf");
                if (is != null) {
                    customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(45f);
                }
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
                customFont = new Font("Serif", Font.PLAIN, 24);
            }

            try {
                imagem = new ImageIcon(getClass().getResource(imagemPath)).getImage();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erro: Imagem " + imagemPath + " não pôde ser carregada.");
            }
        //colocando o mouse para jogo

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (onClick != null) {
                        onClick.run();
                    }
                }
            });
    }

    //Essa parte é onde a criança chora e a mãe não vê, essa parte é de gente grande, aqui é onde eu brinco com a parte grafica, onde eu testo pra caramba onde vai ficar as coisas, em que possição, cor (para deixar em a impreção de transparente) e mais uma porrada de coisa (OBS: NÃO TOQUE SE NÃO VAI DA MERDA, E MUITA MERDA!!!!)
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0, 0, 0, 100));

            int arc = 30;
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

            g2.setFont(customFont);
            g2.setColor(Color.WHITE);

            g2.drawString(nome, 160, 90);

            String nivelStr = String.valueOf(nivel);
            g2.drawString(nivelStr, 550, 50);

            if (imagem != null) {
                g2.drawImage(imagem, imgX, imgY, imgWidth, imgHeight, this);
            }
        }
        // Agora essa merda aqui em baixo serve para saber em qual nivel está para que a seta saiba para onde vai
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
