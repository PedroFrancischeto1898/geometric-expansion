import java.awt.Color;

import javax.swing.*;
public class Fase2 extends painelDeJogo{
  
    private float fimDaMusica; 
    private Concluido concluido;
    Fase2(JFrame frame){
        super(frame);
        fimDaMusica = MusicaTemp.fimDaMusica();
        
       
    }

    @Override
    protected void atualizarJogo() {
        //if(plataformas.get(0).getX() <= -1*calculaX(fimDaMusica)){//checa se a musica acabou
        if(plataformas.get(0).getX() <= -1*calculaX(fimDaMusica-5f)){
            vPlataformas=0;
            velocidadeJogador=10;
            jogador.setX(jogador.getX()+velocidadeJogador);

            if(jogador.getX()>=JOGO.width){
                temporizador.stop();
                concluido=new Concluido(2,frame);
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
        
        
        JOGO.planoDeFundo.setNivel(2);
        JOGO.planoDeFundo.setH(0.915f);

        plataformas.add(new Plataforma(0, alturaChao, calculaX(66f), 300,Color.pink));
        plataformas.add(new Plataforma(calculaX(1.5f), alturaChao-60, calculaX(1.4f), 60,Color.pink));
        obstaculo(2f, alturaChao-60);
        obstaculo(3f, alturaChao-60);

        obstaculo(4f, alturaChao);
        obstaculo(5f, alturaChao);
        obstaculo(6f, alturaChao);

        plataformas.add(new Plataforma(calculaX(7f), alturaChao-40, "escadinha",Color.pink));
        plataformaComObstaculo(7.55f, alturaChao-80, Color.pink);
        plataformas.add(new Plataforma(calculaX(8.1f), alturaChao-120, "escadinha", Color.pink));
        plataformas.add(new Plataforma(calculaX(8.7f), alturaChao-165, baseX, 15,Color.pink));
        obstaculo(8.7f, alturaChao-165);
        obstaculo(11f, alturaChao);
        

        plataformas.add(new Plataforma(calculaX(13f), alturaChao-40, "escadinha", Color.pink));
        plataformas.add(new Plataforma(calculaX(13.5f), alturaChao-90, "escadinha", Color.pink));
        plataformaComObstaculo(14f, alturaChao-130, Color.pink);
        plataformaComObstaculo(14.6f, alturaChao-80, Color.pink);
        obstaculo(15.1f,alturaChao);
        obstaculo(16f, alturaChao);

        obstaculo(19f,alturaChao);
        obstaculo(21.7f,alturaChao);

        plataformas.add(new Plataforma(calculaX(22.5f), alturaChao-50, calculaX(1.4f), 50,Color.pink));
        obstaculo(23f,alturaChao-50);
        obstaculo(23.6f,alturaChao-50);
        plataformas.add(new Plataforma(calculaX(24.8f), alturaChao-90, "escadinha", Color.pink));
        plataformas.add(new Plataforma(calculaX(25.3f), alturaChao-135, "escadinha", Color.pink));
        jumpers.add(new Jumper(calculaX(25.3f), alturaChao-135));

        obstaculo(26.4f,alturaChao);
        obstaculo(25.8f,alturaChao);
        plataformas.add(new Plataforma(calculaX(27.4f), alturaChao-50, 45, 50,Color.pink));
        
        plataformas.add(new Plataforma(calculaX(28f), alturaChao-60, calculaX(1.4f), 60,Color.pink));
        obstaculo(28.5f, alturaChao-60);
        obstaculo(29f, alturaChao-60);
        jumpers.add(new Jumper(calculaX(29.3f),alturaChao-60));

        plataformas.add(new Plataforma(calculaX(31.5f), alturaChao-60, calculaX(1.4f), 60,Color.pink));
        obstaculo(32f, alturaChao-60);
        obstaculo(32.5f, alturaChao-60);
        jumpers.add(new Jumper(calculaX(33f),alturaChao-60));

        plataformas.add(new Plataforma(calculaX(33.5f), alturaChao-180, baseX,15, Color.pink));
        obstaculo(calculaX(33.5f), alturaChao-180);
        obstaculo(calculaX(33.5f),alturaChao);
        jumpers.add(new Jumper(calculaX(35f), alturaChao));

        plataformas.add(new Plataforma(calculaX(35.5f), alturaChao-80, calculaX(1.4f), 80,Color.pink));
        obstaculo(36f, alturaChao-80);
        obstaculo(36.5f, alturaChao-80);
        jumpers.add(new Jumper(calculaX(37f),alturaChao-80));
        jumpers.add(new Jumper(calculaX(38.5f), alturaChao));

        plataformas.add(new Plataforma(calculaX(39f), alturaChao-120, calculaX(1.4f), 120,Color.pink));
        obstaculo(39.5f, alturaChao-120);
        obstaculo(40f, alturaChao-120);
        obstaculo(41.5f,alturaChao);
        jumpers.add(new Jumper(calculaX(42f), alturaChao,0.48f,0.006f));

        plataformas.add(new Plataforma(calculaX(42.6f), alturaChao-80, 150,15, Color.pink));
        for(int i=0;i<3;i++){
            plataformas.add(new Plataforma(calculaX(43.5f+0.5f*i), alturaChao-120-50*i, "escadinha", Color.pink));
        }
        plataformas.add(new Plataforma(calculaX(45f), alturaChao-270, baseX, 15,Color.pink));
        obstaculo(45f,alturaChao-270);

        for(int i=0;i<3;i++){
            obstaculo(46f+0.55f*i, alturaChao);
        }

        plataformas.add(new Plataforma(calculaX(47.5f), alturaChao-60, calculaX(1f), 60,Color.pink));
        obstaculo(48f, alturaChao-60);
        for(int i=0;i<3;i++){
            plataformas.add(new Plataforma(calculaX(49f+0.5f*i), alturaChao-120-40*i, "escadinha", Color.pink));
        }

        for(int i=0;i<4;i++){
            obstaculo(50.5f+0.6f*i,alturaChao);
        }

        jumpers.add(new Jumper(calculaX(52.5f), alturaChao,0f,-0.006f));


        plataformas.add(new Plataforma(calculaX(53f), alturaChao-80, calculaX(3f), 80,Color.pink));
        obstaculo(54f, alturaChao-80);
        obstaculo(55f, alturaChao-80);
        obstaculo(56f, alturaChao-80);
        jumpers.add(new Jumper(calculaX(56.5f), alturaChao-80));
        plataformas.add(new Plataforma(calculaX(57f), alturaChao-80, calculaX(3f), 15,Color.pink));
        obstaculo(58f, alturaChao-80);
        obstaculo(59f, alturaChao-80);
        obstaculo(60f, alturaChao-80);

        plataformas.add(new Plataforma(calculaX(61f), alturaChao-60, "escadinha", Color.pink));
        plataformas.add(new Plataforma(calculaX(61.5f), alturaChao-20, "escadinha", Color.pink));

        jumpers.add(new Jumper(calculaX(65.5f), alturaChao));
        
        for(int i=0;i<5;i++)
        plataformas.add(new Plataforma(calculaX(66f+0.6f*i), alturaChao-120, "escadinha", Color.pink));

        plataformas.add(new Plataforma(calculaX(69f), alturaChao, 7000, 300,Color.pink));

    }

    @Override
    protected int calculaX(float t){
        int x = (int)((xInicial+Jogador.getWidth())+(235*(t)));//235 eh um valor experimental, tem q mudar se trocar vPlataformas
        
        
        return x;
    }
}
