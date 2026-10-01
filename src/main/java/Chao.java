import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Chao extends JPanel implements ActionListener {//classe usada no menu inicial
    private Image image;
    private int x = 0;
    private Timer timer;

    public Chao() {
        image = new ImageIcon(getClass().getResource("/Assets/Chao.png")).getImage();
        if (image == null) {
            System.err.println("Erro: A imagem Chao.png não pôde ser carregada.");
        }
        timer = new Timer(10, this);
        timer.start();
        setPreferredSize(new Dimension(800, 150));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.getHSBColor(PlanoDeFundo.h, 1.0f, 1.0f));
        g.fillRect(0, 0, getWidth(), getHeight());

        int width = getWidth();
        int imageWidth = image.getWidth(this);

        for (int i = x; i < width; i += imageWidth) {
            g.drawImage(image, i, 0, this);
        }

        // for (int i = x - imageWidth; i >= -imageWidth; i -= imageWidth) {
        //    g.drawImage(image, x-imageWidth, 0, this);
        // }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x -= 3; // Movimento mais rápido para o Chao
        if (x <= -image.getWidth(this)) {
            x = 0;
        }
        repaint();
    }
}
