import java.awt.Polygon;

public class Obstaculo extends Polygon{

    Obstaculo(int[] xpoints, int[] ypoints){
        super(xpoints, ypoints, 3);

    }
    
    
    public int calculaLargura(){
        return xpoints[2]-xpoints[0];
    }
}
