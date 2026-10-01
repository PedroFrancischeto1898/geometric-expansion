import java.awt.*;
import java.util.Iterator;

import javax.imageio.ImageIO;


import java.awt.geom.*;
import java.awt.image.*;
import java.io.IOException;



public class Jogador {
    public final static int width=40, height=40;
    public int x,y;
    public final float gravidade =1.3f;
    public float velocidadePulo=-18f, maxY=0;
    public boolean vivo = true;
    public static boolean pulando = false;
    public static boolean noChao = false;
    public static ThreadColisaoBaixo baixo = null;
    public static ThreadColisoesCimaFrente cimaFrente = null;
    public static BufferedImage skin = null;
    public float velocidadeAtual=0;
    public static double graus = 0;
    public boolean temQRodar = false;

    Jogador(int x, int y){
        try {
            skin = ImageIO.read(Jogador.class.getResource("/Assets/bob.png"));
        } catch (IOException e) {
            e.printStackTrace();
        };
        this.x =x;
        this.y=y;
        
        //matarThreads();
        reiniciarThreads();
        
    }

    public void rotacionaImagem(BufferedImage img, double angulo,Graphics2D g2d){//rotacao da imagem enquanto no ar
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);


        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(angulo), img.getWidth() / 2.0, img.getHeight() / 2.0);

    }
    
    public static void novaImagem(BufferedImage rotateImage, double angle) {// faz a rotacao para cair no chao
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(angle), rotateImage.getWidth() / 2.0, rotateImage.getHeight() / 2.0);
    
        // Rotaciona as coordenadas dos cantos da imagem
        Point2D[] aCorners = new Point2D[4];
        aCorners[0] = tx.transform(new Point2D.Double(0.0, 0.0), null);
        aCorners[1] = tx.transform(new Point2D.Double(rotateImage.getWidth(), 0.0), null);
        aCorners[2] = tx.transform(new Point2D.Double(0.0, rotateImage.getHeight()), null);
        aCorners[3] = tx.transform(new Point2D.Double(rotateImage.getWidth(), rotateImage.getHeight()), null);
    
        // Obtém o valor de translação para cada eixo com um canto "escondido"
        double dTransX = 0;
        double dTransY = 0;
        for(int i = 0; i < 4; i++) {
            if(aCorners[i].getX() < 0 && aCorners[i].getX() < dTransX)
                dTransX = aCorners[i].getX();
            if(aCorners[i].getY() < 0 && aCorners[i].getY() < dTransY)
                dTransY = aCorners[i].getY();
        }
    
        // Aplica a translação para evitar cortes na imagem
        AffineTransform translationTransform = new AffineTransform();
        translationTransform.translate(-dTransX, -dTransY);
        tx.preConcatenate(translationTransform);
    
        skin =  new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR).filter(rotateImage, null);
    }
    public void paint(Graphics2D g){//logica de rotacao
        if(!noChao){
            AffineTransform old = g.getTransform();
            rotacionaImagem(skin,graus, g);
            g.drawImage(skin, 0,0, null);
            g.setTransform(old);
            graus += velocidadeAtual*velocidadeAtual*0.5;
            if(graus>=360) graus =0;
            
        if(!noChao && !pulando && temQRodar){
            novaImagem(skin, 90);
            temQRodar = false;}
    }
        else{
            g.drawImage(skin, x,y, null);
          
        }

    }

    public void pular() {
        
        if (!pulando && noChao) {
            pulando = true;
            velocidadeAtual = velocidadePulo;
            y += velocidadeAtual;
        }
        novaImagem(skin, 90);
    
    }

    public void matarThreads(){//precisamos encerrar as threads ao reiniciar a fase pois elas constantemente leem os vetores que contem os objetos da fase.Toda vez que a fase eh reiniciada, os vetores sao limpos e preenchidos de novo
        try{
            
            if(baixo.isAlive())
                baixo.join();
            if(cimaFrente.isAlive())
                cimaFrente.join();
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }

    public void reiniciarThreads(){
        
            baixo = new ThreadColisaoBaixo(this);
            cimaFrente = new ThreadColisoesCimaFrente(this);
            baixo.start();
            cimaFrente.start();
            
    }
    public void atualizar() {
    
        if(pulando || !noChao){//logica de gravidade
            y += velocidadeAtual;
            velocidadeAtual+=gravidade;
        }
        if(y>JOGO.height) vivo = false;
        
    }

    public int getVelocidadePulo(){return (int)velocidadePulo;}
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public static int getHeight(){return height;}
    public static int getWidth(){return width;}
    public boolean getVivo(){return vivo;}
    public boolean getPulando(){return pulando;}
    public float getGravidade(){return gravidade;}
    public int getVelocidadeAtual(){return (int)velocidadeAtual;}

    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
    public void setVelocidadeAtual(float x){this.velocidadeAtual=x;}
    public void setVivo(boolean x){vivo = x;}
    public void setPulando(boolean x){pulando = x;}
    public void setNoChao(boolean x){noChao = x;}


}

//classes de checagem de colisao.
//todas essas classes desenham hitboxes laterais do quadrado do jogador e verificam insercecao com os objetos. Precisamos de diferentes hitbox pois o lado do quadrado que esta em colisao muitas vezes importa

class ThreadColisoesCimaFrente extends Thread {//thread da colisao frontal/de cima
    Jogador jogador;
    
    ThreadColisoesCimaFrente(Jogador jogador) {
        this.jogador = jogador;
    }
    
    public boolean checaFrente(Jogador jogador, Plataforma obj) {
        Rectangle hitbox = new Rectangle(jogador.getX()-2 + Jogador.getWidth()+1, jogador.getY(), 1, Jogador.getHeight()-Math.abs(jogador.getVelocidadeAtual())*2-1);
        
        Area areaA = new Area(hitbox);
        Area areaB = new Area(obj);
        areaA.intersect(areaB);
        return !areaA.isEmpty();
    }
    
    public boolean checaCima(Jogador jogador, Plataforma obj) {
        Rectangle hitbox = new Rectangle(jogador.getX(), jogador.getY()+1, Jogador.getWidth() - 3, 2);
        Area areaA = new Area(hitbox);
        Area areaB = new Area(obj);
        areaA.intersect(areaB);
        
        return !areaA.isEmpty();
    }

    public boolean checaFrente(Jogador jogador, Obstaculo obj) {
        Rectangle hitbox = new Rectangle(jogador.getX() + Jogador.getWidth()+1, jogador.getY(), 1, Jogador.getHeight());
        Area areaA = new Area(hitbox);
        Area areaB = new Area(obj);
        areaA.intersect(areaB);
        
        return !areaA.isEmpty();
    }
    
    public boolean checaCima(Jogador jogador, Obstaculo obj) {
        Rectangle hitbox = new Rectangle(jogador.getX(), jogador.getY()+1, Jogador.getWidth() - 3, 2);
        Area areaA = new Area(hitbox);
        Area areaB = new Area(obj);
        areaA.intersect(areaB);
        
        return !areaA.isEmpty();
    }
    
    public void run() {
        while (jogador.getVivo()) {
            Iterator<Plataforma> plataformaIterator = PainelDeJogo.plataformas.iterator();
            while(plataformaIterator.hasNext()) {
                Plataforma obj = plataformaIterator.next();
                if (checaCima(jogador, obj) || checaFrente(jogador, obj)) {
                    jogador.setVivo(false);
                    break;
                }
            }
            Iterator<Obstaculo> obstaculoIterator = PainelDeJogo.obstaculos.iterator();
            while(obstaculoIterator.hasNext()) {
                Obstaculo obj = obstaculoIterator.next();
                if (checaCima(jogador, obj) || checaFrente(jogador, obj)) {
                        jogador.setVivo(false);
                        break;
                }

            }
            try {
                Thread.sleep(12);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.interrupt();
    }

    
}


class ThreadColisaoBaixo extends Thread {
    Jogador jogador;
    
    ThreadColisaoBaixo(Jogador jogador) {
        this.jogador = jogador;
    }
    
    public boolean checaBaixo(Jogador jogador, Plataforma obj) {
        Rectangle hitbox = new Rectangle(jogador.getX(), jogador.getY() + Jogador.getHeight() - Math.abs(jogador.getVelocidadeAtual())*2+1, Jogador.getWidth() - 3, Math.abs(jogador.getVelocidadeAtual()) * 2 + 1);
        
        if(hitbox.getY()<jogador.getY()) hitbox.setBounds((int)hitbox.getX(), jogador.getY(), (int)hitbox.getWidth(), (int)hitbox.getHeight());
        
        Area areaA = new Area(hitbox);
        
        Area areaB = new Area(obj);
        areaA.intersect(areaB);
        
        return !areaA.isEmpty();
    }
    public boolean checaBaixo(Jogador jogador, Obstaculo obj) {
        Rectangle hitbox = new Rectangle(jogador.getX(), jogador.getY() + Jogador.getHeight() - Math.abs(jogador.getVelocidadeAtual())*2+1, Jogador.getWidth() - 3, Math.abs(jogador.getVelocidadeAtual()) * 2 + 1);
        
        Area areaA = new Area(hitbox);
        
        Area areaB = new Area(obj);
        areaA.intersect(areaB);
        
        return !areaA.isEmpty();
    }
    
    public void run() {
        while (jogador.getVivo()) {
            boolean colidindo = false;
            Iterator<Plataforma> plataformaIterator = PainelDeJogo.plataformas.iterator();
            while (plataformaIterator.hasNext()) {
                Plataforma obj = plataformaIterator.next();
                if (checaBaixo(jogador, obj)) {
                    jogador.setY((int) (obj.getY() - Jogador.getHeight() - 1));
                    jogador.setNoChao(true);
                    jogador.setPulando(false);
                    jogador.setVelocidadeAtual(0);
                    colidindo = true;
                    jogador.temQRodar=true;
                }
            }
            
            Iterator<Jumper> jumIterator = PainelDeJogo.jumpers.iterator();
            while (jumIterator.hasNext()) {
                Jumper obj = jumIterator.next();
                if (checaBaixo(jogador, obj)) {
                    jogador.setVelocidadeAtual(jogador.getVelocidadePulo()*1.5f);
                    jogador.setNoChao(false);
                    jogador.temQRodar=true;

                    if(obj.getHFundo()!= -1f){
                        new ThreadMudaFundo(obj.getHFundo(), obj.getIncremento()).start();
                    }
                    else if(obj.getNivel() == 0) JOGO.planoDeFundo.setNivel(0);
                }
            }
            if (!colidindo) {jogador.setNoChao(false);}

            Iterator<Obstaculo> obstaculoIterator = PainelDeJogo.obstaculos.iterator();
            while (obstaculoIterator.hasNext()) {
                Obstaculo obj = obstaculoIterator.next();
                if (checaBaixo(jogador, obj)) {
                    jogador.setVivo(false);
                    break;
                }
            }

            try {
                Thread.sleep(12); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.interrupt();
    }
    
    
}

