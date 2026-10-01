import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class Concluido extends JPanel {
    private Font customFont;
    private int nivel;

    public Concluido(int nivel, JFrame frame) {
        this.nivel = nivel;
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);

        try {
            InputStream is = getClass().getResourceAsStream("/Assets/CustomFont.otf");
            if (is != null) {
                customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(35f);
            }
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            customFont = new Font("Serif", Font.PLAIN, 24);
        }

        JLabel labelConcluido = new JLabel("Nível Concluído");
        labelConcluido.setFont(customFont);
        labelConcluido.setForeground(Color.WHITE);
        labelConcluido.setBounds(150, 10, 300, 50);
        add(labelConcluido);

        JButton jogarNovamenteButton = new JButton("Jogar");
        jogarNovamenteButton.setFont(customFont.deriveFont(25f));
        jogarNovamenteButton.setBounds(50, 100, 200, 50);
        jogarNovamenteButton.setContentAreaFilled(true);
        jogarNovamenteButton.setBorderPainted(true);
        jogarNovamenteButton.setEnabled(true);
        jogarNovamenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MusicaTemp.reset();
                painelDeJogo.jogador.setVivo(false);
                painelDeJogo.jogador.matarThreads();
                painelDeJogo.jogador=null;
                painelDeJogo.temporizador=null;
                painelDeJogo.vPlataformas=-4;
                painelDeJogo.velocidadeJogador=0;
                PlanoDeFundo.stopTimer();

                new JOGO(nivel);
            }
        });
        add(jogarNovamenteButton);

        JButton menuButton = new JButton("Menu");
        menuButton.setFont(customFont.deriveFont(25f));
        menuButton.setBounds(350, 100, 200, 50);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
       
                frame.dispose();
                MusicaTemp.reset();
                painelDeJogo.jogador.setVivo(false);
                painelDeJogo.jogador.matarThreads();
                painelDeJogo.jogador=null;
                painelDeJogo.temporizador=null;
                painelDeJogo.vPlataformas=-4;
                painelDeJogo.velocidadeJogador=0;
                PlanoDeFundo.stopTimer();

                
                new TelaInicial(new JFrame()) ;
            }
        });
        add(menuButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 0, 100));

        int arc = 30;
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
    }
}

