
import java.awt.*;


public class Plataforma extends Rectangle {
    Color cor = Color.blue;

    Plataforma(int x, int y, int width, int height) {
        super(x, y, width, height);
        
        
    }
    Plataforma(int x, int y, int width, int height, Color cor) {
        super(x, y, width, height);
        this.cor = cor;
        
        
    }
    Plataforma(int x, int y,String tipo,Color cor){
        if(tipo.equals("escadinha")){
            setBounds(x,y,50,15);
            this.cor = cor;
        }
    }
    Plataforma(int x, int y,String tipo){
        if(tipo.equals("escadinha")){
            setBounds(x,y,50,15);
           
        }
    }


    public void setCor(Color cor){
        this.cor = cor;
    }

    public void paint(Graphics g) {
       
        g.setColor(cor);
        g.fillRect(x, y, width, height);

        
    }
}
