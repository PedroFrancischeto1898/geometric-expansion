import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class TelaInicial {
    public TelaInicial(JFrame frame) {
        frame.setTitle("Tela Inicial");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(layeredPane);

        PlanoDeFundo planoDeFundo = new PlanoDeFundo(0);
        planoDeFundo.setBounds(0, 0, 800, 450);
        layeredPane.add(planoDeFundo, JLayeredPane.DEFAULT_LAYER);

        Chao chao = new Chao();
        chao.setBounds(0, 450, 800, 150);
        layeredPane.add(chao, JLayeredPane.DEFAULT_LAYER);

        CustomLabel label = new CustomLabel("Expansão Geométrica");
        label.setBounds(60, 120, 710, 50);
        layeredPane.add(label, JLayeredPane.PALETTE_LAYER);

        CustomButton playButton = new CustomButton(frame);
        playButton.setBounds(150, 220, 500, 150);
        layeredPane.add(playButton, JLayeredPane.PALETTE_LAYER);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaInicial(new JFrame());
        });
    }
}
