
import java.awt.*;
public class Jumper extends Plataforma{
    private float hFundo = -1f;
    private float incremento;
    private int nivel=1;
    Jumper(int x, int y){
        super(x,y,40,10,Color.green);
    }
    Jumper(int x, int y, float h, float incremento){
        super(x,y,40,10,Color.green);
        hFundo=h;
        this.incremento=incremento;
    }
    Jumper(int x, int y, int nivelFundo){
        super(x,y,40,10,Color.green);
        nivel = nivelFundo;
    }

    public float getHFundo(){
        return hFundo;
    }
    public float getIncremento(){
        return incremento;
    }
    public int getNivel(){ return nivel;}

}
    


