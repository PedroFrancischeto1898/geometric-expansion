import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    private ImageIcon icon;
    @SuppressWarnings("unused")
    private JFrame parentFrame;

    public CustomButton(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        icon = new ImageIcon(getClass().getResource("/Assets/ButtonPlay.png"));
        setIcon(icon);
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(new ImageIcon(icon.getImage().getScaledInstance(icon.getIconWidth() - 10, icon.getIconHeight() - 10, java.awt.Image.SCALE_SMOOTH)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(icon);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // esse parentFrame Eu não consegui fazer, estava dando erro pois ou eu conseguia abrir o Menu ou eu fechava TelaInicial, mas deu certo
                 
                parentFrame.dispose(); // Fechar TelaInicial.java
                new Menu(); // Abrir Menu.java
            }
        });
    }
}
