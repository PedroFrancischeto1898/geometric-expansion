import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public abstract class painelDeJogo extends JPanel implements ActionListener, KeyListener, MouseListener {// o "esqueleto das fases" 
    protected JFrame frame;
    public static Jogador jogador= null;

    public static List<Obstaculo> obstaculos = new ArrayList<>();//esses tres vetores guardam todos os objetos da fase e sao sempre analisados pelo Jogador
    public static List<Plataforma> plataformas = new ArrayList<>();
    public static List<Jumper> jumpers = new ArrayList<>();

    public static MusicaTemp musica = new MusicaTemp();
    public static int velocidadeJogador=0;
    protected static final int baseX = 35, alturaChao = 380;


    protected int xInicial = 100, yInicial = 320;
    static protected int vPlataformas = -4;
    protected static int[] xpoints = {0, 0, 0}, ypoints = {0, 0, 0};
    
    public static Timer temporizador = null;

    public painelDeJogo(JFrame frame) {
        inicializarJogo(frame);
    }

    private void inicializarJogo(JFrame frame) {
        setup(frame);
        gerarObstaculos();
        preparaJogador();
        musica.comecaMusica();
        // try{Thread.sleep(100);}catch(Exception e){}
        timer();
        
        

        
    }

    protected void timer() {
        temporizador = new Timer(10, this);
        temporizador.start();
        
    }

    protected void setup(JFrame frame) {
        this.setOpaque(false);
        this.frame = frame;
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        setVisible(true);
    }

    protected void preparaJogador() {
        jogador = new Jogador(xInicial, yInicial);
        

    }

   

    protected void obstaculo(float t, int y) {
        int x = calculaX(t);
        xpoints[0] = x;
        xpoints[1] = x + baseX / 2;
        xpoints[2] = x + baseX;

        ypoints[0] = y;
        ypoints[1] = y - Jogador.getHeight() + 5;
        ypoints[2] = y;

        obstaculos.add(new Obstaculo(xpoints, ypoints));
    }

    protected void plataformaComObstaculo(float t, int y, Color cor) {
        plataformas.add(new Plataforma(calculaX(t), y, 50 * 3, 15, cor));
        obstaculo(t + 0.25f, y);
    }

    protected abstract void gerarObstaculos();//classe vai ser definida para cada fase

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Plataforma plat : plataformas) {
            plat.paint(g);
        }
        for (Jumper j : jumpers) j.paint(g);

        g.setColor(Color.RED);
        for (Polygon triangulo : obstaculos) {
            g.fillPolygon(triangulo);
        }

        Graphics2D g2d = (Graphics2D) g;
        jogador.paint(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        atualizarJogo();
        repaint();
    }

    protected int calculaX(float t) {
        int x = (int) ((xInicial + Jogador.getWidth()) + (235 * (t + 6f))); // 267 eh um valor experimental, tem q mudar se trocar vPlataformas
        return x;
    }

    protected void atualizarJogo() {
        // if((int)JOGO.musica.segundos()==1f)System.out.println(plataformas.get(0));
        // System.out.println(musica.segundos());
        jogador.atualizar();
        if (!jogador.getVivo()) {
            System.out.println(MusicaTemp.segundos());
            reiniciarJogo();
        }

        for (Polygon obstaculo : obstaculos) {
            obstaculo.translate(vPlataformas, 0);
        }
        for (Plataforma plat : plataformas) {
            plat.setBounds((int) (plat.getX() + vPlataformas), (int) plat.getY(), (int) plat.getWidth(), (int) plat.getHeight());
        }
        for (Jumper j : jumpers) j.setBounds((int) j.getX() + vPlataformas, (int) j.getY(), (int) j.getWidth(), (int) j.getHeight());
    }

    public synchronized void reiniciarJogo() {
        temporizador.stop();
        MusicaTemp.reset();


        jogador.matarThreads();

        jogador.setVivo(true);
        jogador.setY(320);
        jogador.setPulando(false);
        jogador.setNoChao(true);
        
        System.out.println("dsfsdfsf");
        
       
        
        
        //try{Thread.sleep(300);}catch(Exception e){}
        gerarObstaculos();
       
        jogador.reiniciarThreads();
        
        try{Thread.sleep(600);}catch(Exception e){}
        musica.comecaMusica();

        
        temporizador.start();
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !jogador.getPulando()) {
            jogador.pular();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && !jogador.getPulando()) {
            jogador.pular();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

class ThreadMudaFundo extends Thread {
    protected float h;
    protected float incremento;

    ThreadMudaFundo(float h, float incremento) {
        this.h = h;
        this.incremento = incremento;
    }

    public void run() {
        while (!((JOGO.planoDeFundo.getH() >=( h- Math.abs(2f*incremento))) && (JOGO.planoDeFundo.getH() <=(h + Math.abs(2f*incremento)))) && painelDeJogo.jogador.getVivo()) {
            JOGO.planoDeFundo.setH(JOGO.planoDeFundo.getH() + incremento);
            try {
                Thread.sleep(30);
            } catch (Exception e) {}
        }
        this.interrupt();
    }
}
