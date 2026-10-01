import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class CustomLabel extends JLabel {
    private static Font customFont;

    static {
        try {
            InputStream is = CustomLabel.class.getResourceAsStream("/Assets/CustomFont.otf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(60f); 
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Serif", Font.PLAIN, 24); 
        }
    }

    public CustomLabel(String text) {
        super(text);
        setFont(customFont);
        setForeground(Color.decode("#10c400"));
    }
}
