import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class Menu extends JFrame {
    private Placa[] placas;
    private int indiceAtual = 0;//mudar depois

    public Menu() {
        setTitle("Tela Inicial");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //parte visual da tela de selecao de fases
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(800, 600));
            setContentPane(layeredPane);

            PlanoDeFundo planoDeFundo = new PlanoDeFundo(0);
            planoDeFundo.setBounds(0, 0, 800, 450);
            layeredPane.add(planoDeFundo, JLayeredPane.DEFAULT_LAYER);

            Chao chao = new Chao();
            chao.setBounds(0, 450, 800, 150);
            layeredPane.add(chao, JLayeredPane.DEFAULT_LAYER);

        //instanciacao das placas
            placas = new Placa[]{
                new Placa("Digital Love", 1, "/Assets/Emoji_1.png", 50, 40, 70, 70),
                new Placa("Remember", 2, "/Assets/Emoji_2.png", 45, 30, 85, 85),
                new Placa("Mantis Lords", 3, "/Assets/Emoji_3.png", 50, 40, 75, 70)
            };

            for (Placa placa : placas) {
                placa.setBounds(100, 125, 600, 150);
                placa.setOnClick(() -> {
                    int nivel = placa.getNivel();
                    iniciarNivel(nivel);
                });
                layeredPane.add(placa, JLayeredPane.PALETTE_LAYER);
                placa.setVisible(false);
            }
            placas[indiceAtual].setVisible(true);


        //setup da selecao de fases
            JButton setaDireita = new JButton(new ImageIcon(getClass().getResource("/Assets/seta-direita.png")));
            setaDireita.setBounds(715, 300, 70, 70);
            setaDireita.setContentAreaFilled(false);
            setaDireita.setBorderPainted(false);
            setaDireita.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    trocarPlaca(1);
                }
            });
            layeredPane.add(setaDireita, JLayeredPane.MODAL_LAYER);

            JButton setaEsquerda = new JButton(new ImageIcon(getClass().getResource("/Assets/seta-esquerda.png")));
            setaEsquerda.setBounds(15, 300, 70, 70);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    trocarPlaca(-1);
                }
            });
            layeredPane.add(setaEsquerda, JLayeredPane.MODAL_LAYER);

            pack();
            setVisible(true);
    }
    //setas
        private void trocarPlaca(int direcao) {
            placas[indiceAtual].setVisible(false);
            indiceAtual = (indiceAtual + direcao + placas.length) % placas.length;
            placas[indiceAtual].setVisible(true);
        }

        //inicia o nivel
        private void iniciarNivel(int nivel) {
            super.dispose();
            new JOGO(nivel);
            
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Menu();
        });
    }
}
