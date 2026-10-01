import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import java.awt.Color;
public class Fase1 extends painelDeJogo {

    private float fimDaMusica; 
    private Concluido concluido;
    private JFrame frame;
    Fase1(JFrame frame){
        super(frame);
        this.frame=frame;
        fimDaMusica = MusicaTemp.fimDaMusica();
        
       
    }


    @Override
    protected void atualizarJogo() {
        //if(plataformas.get(0).getX() <= -1*calculaX(fimDaMusica)){//checa se a musica acabou
        if(plataformas.get(0).getX() <= -1*calculaX(fimDaMusica)){
            vPlataformas=0;
            velocidadeJogador=10;
            jogador.setX(jogador.getX()+velocidadeJogador);

            if(jogador.getX()>=JOGO.width){
                temporizador.stop();
                concluido=new Concluido(1,frame);
                concluido.setBounds(200,150,600,200);
                JOGO.layeredPane.add(concluido, JLayeredPane.POPUP_LAYER);
                JOGO.layeredPane.revalidate(); // Revalida o layout
                JOGO.layeredPane.repaint();
                
               

                
                
            }
        }
        super.atualizarJogo();
    }

    @Override
    protected void gerarObstaculos(){
        obstaculos.clear();
        plataformas.clear();
        jumpers.clear();

        JOGO.planoDeFundo.setNivel(1);
        JOGO.planoDeFundo.setH(0.739f);
        
        plataformas.add(new Plataforma(0, 380, calculaX(21f), 500, Color.magenta));
        plataformas.add(new Plataforma(calculaX(21f), 380,calculaX(16f), 500,Color.white));

        obstaculo(2.5f, alturaChao);
        obstaculo(5f, alturaChao);
        obstaculo(5.6f, alturaChao);
       
        
        obstaculo(6.5f, alturaChao);
        obstaculo(9f, alturaChao);
        obstaculo(9.6f, alturaChao);
        obstaculo(10.5f, alturaChao);
        

        obstaculo(11.5f, alturaChao);
        obstaculo(12.5f, alturaChao);
        

        plataformas.add(new Plataforma(calculaX(14f), alturaChao-60, "escadinha", Color.magenta));
        plataformas.add(new Plataforma(calculaX(14.5f), alturaChao-120, "escadinha", Color.magenta));

        obstaculo(14.9f, alturaChao);
        obstaculo(15.5f, alturaChao);

        obstaculo(16f, alturaChao);
        jumpers.add(new Jumper(calculaX(16.5f),alturaChao));

        plataformas.add(new Plataforma(calculaX(17f), alturaChao-160, "escadinha", Color.magenta));
        obstaculo(17.4f,alturaChao-120);
        plataformas.add(new Plataforma(calculaX(17.4f), alturaChao-120, "escadinha", Color.magenta));
        plataformas.add(new Plataforma(calculaX(17.4f)+45, alturaChao-120, "escadinha", Color.magenta));

        obstaculo(19.5f,alturaChao);
        obstaculo(20f,alturaChao);
        obstaculo(20.5f,alturaChao);
        jumpers.add(new Jumper(calculaX(21f), alturaChao,0.333f,-0.005f));

        obstaculo(23f,alturaChao);
        obstaculo(23.6f, alturaChao);
        obstaculo(24.2f, alturaChao);
        jumpers.add(new Jumper(calculaX(24.2f)+baseX, alturaChao));

        plataformas.add(new Plataforma(calculaX(25f), alturaChao-140, "escadinha", Color.WHITE));
        plataformas.add(new Plataforma(calculaX(25.6f), alturaChao-160, 45*3,15, Color.WHITE));
        obstaculo(25.95f,alturaChao-160);

        for(int i=1;i<4;i++){
            plataformas.add(new Plataforma(calculaX(31f+0.6f*i), alturaChao-40*i, 45*3, 15,Color.white));
            obstaculo(31.2f+0.65f*i, alturaChao-40*i);
        }
        jumpers.add(new Jumper(calculaX(33f), alturaChao-120));

        obstaculo(34f,alturaChao);
        obstaculo(35f, alturaChao);
        plataformas.add(new Plataforma(calculaX(35.3f), alturaChao-50, 45, 60,Color.white));
        plataformaComObstaculo(35.8f, alturaChao-120, Color.white);
        plataformas.add(new Plataforma(calculaX(36.4f), alturaChao-180, "escadinha", Color.white));
        plataformaComObstaculo(36.9f, alturaChao-120,Color.white);

        plataformas.add(new Plataforma(calculaX(39f), alturaChao-50, 45, 60,Color.white));
        plataformas.add(new Plataforma(calculaX(39.4f), alturaChao-120, "escadinha",Color.white));
        plataformas.add(new Plataforma(calculaX(39.8f), alturaChao-170, "escadinha",Color.white));
        jumpers.add(new Jumper(calculaX(39.8f)+5, alturaChao-170,0));
        obstaculo(40f, alturaChao);

        obstaculo(42.01f, alturaChao);
        plataformas.add(new Plataforma(calculaX(42.5f), alturaChao-80, 45, 80,Color.white));
        plataformas.add(new Plataforma(calculaX(43f), alturaChao-150, "escadinha", Color.white));
        plataformaComObstaculo(43.4f, alturaChao-210, Color.white);
        plataformas.add(new Plataforma(calculaX(44f), alturaChao-270, "escadinha", Color.white));
        plataformaComObstaculo(44.5f, alturaChao-330, Color.white);
        plataformas.add(new Plataforma(calculaX(44.3f), alturaChao-210, "escadinha", Color.white));

        plataformas.add(new Plataforma(calculaX(44.6f), alturaChao, 10000, 500,Color.magenta));

        jumpers.add(new Jumper(calculaX(47f), alturaChao));
        plataformas.add(new Plataforma(calculaX(47.3f), alturaChao-60, baseX,15,Color.magenta));
        obstaculo(47.3f,alturaChao-60);

        jumpers.add(new Jumper(calculaX(48f), alturaChao));
        plataformas.add(new Plataforma(calculaX(48.3f), alturaChao-60, baseX,15, Color.magenta));
        obstaculo(48.3f,alturaChao-60);

        jumpers.add(new Jumper(calculaX(49f), alturaChao));
        plataformas.add(new Plataforma(calculaX(49.3f), alturaChao-60, baseX,15, Color.magenta));
        obstaculo(49.3f,alturaChao-60);
        
    }
}
