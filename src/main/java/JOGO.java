import javax.swing.*;
import java.awt.*;


public class JOGO extends JFrame {//aqui sao feitas as configuracoes do frame e do plano de fundo da fase
    static int width = 1000, height = 600;
    public static PlanoDeFundo planoDeFundo;
    public static MusicaTemp musica;
    public static JLayeredPane layeredPane;
    
    public JOGO(int nivel) {
        setTitle("Expansão Geométrica");
        setSize(width, height);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height));
        setContentPane(layeredPane);

        planoDeFundo = new PlanoDeFundo(nivel);
        planoDeFundo.setBounds(0, 0, width, height);
        layeredPane.add(planoDeFundo, JLayeredPane.DEFAULT_LAYER);

        

        if (nivel == 1) {//carregamento da musica da fase
            MusicaTemp.filePath = "/Assets/DigitalLove2.wav";
        } else if (nivel == 2) {
            MusicaTemp.filePath = "/Assets/Remember.wav";
        }
        else MusicaTemp.filePath = "/Assets/Hk.wav";

       MusicaTemp.carrega();
        
        
        
        PainelDeJogo painel;//instanciando a fase
        if (nivel == 1) {
            painel = new Fase1(this);
        } else if(nivel ==2){
            painel = new Fase2(this);
        }else{
            painel =new Fase3(this);
        }

        painel.setBounds(0, 0, width, height);
        layeredPane.add(painel, JLayeredPane.MODAL_LAYER);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
