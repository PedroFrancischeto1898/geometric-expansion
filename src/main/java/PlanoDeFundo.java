import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class PlanoDeFundo extends JPanel implements ActionListener {
    private Image image;
    private int x = 0;
    private static Timer timer;
    private static int nivel;
    public static float h = 0,s=1f,b=1f;

    public PlanoDeFundo(int NIVEL) {
        nivel = NIVEL;
        image = new ImageIcon(getClass().getResource("/Assets/Tela1.png")).getImage();
        if (image == null) {
            System.err.println("Erro: A imagem Tela1.png não pôde ser carregada.");
        }
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.getHSBColor(h, s, b));
        g.fillRect(0, 0, getWidth(), getHeight());

        int width = getWidth();
        int imageWidth = image.getWidth(this);

        for (int i = x; i < width; i += imageWidth) {
            g.drawImage(image, i, 0, this);
        }

        // for (int i = x - imageWidth; i >= -imageWidth; i -= imageWidth) {
        //     g.drawImage(image, i, 0, this);
        // }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        x -= 1;
        if (x <= -image.getWidth(this)) {
            x = 0;
        }
        
        if(nivel == 0){
            h += 0.005f;} // Atualizando a cor gradiente
        if (h > 1) h = 0;
        if(h<0) h=1;
        repaint();
    }

    public void setH(float H){
        h = H;
        if (h > 1) h = 0;
        if(h<0) h=1;
        
    }
    public void setB(float B){
        b=B;
    }
    public void setS(float S){
        s=S;
    }
    public void setNivel(int N){ nivel=N;}

    public int getNivel(){ return nivel;}
    public float getH(){ return h;}
    public float getS(){ return s;}
    public float getB(){ return b;}
    public static void stopTimer(){timer.stop(); timer=null;}
}
