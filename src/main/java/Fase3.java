import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import java.awt.Color;
public class Fase3 extends PainelDeJogo {

    private float fimDaMusica; 
    private Concluido concluido;
    Fase3(JFrame frame){
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
                concluido=new Concluido(3,frame);
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

        
        JOGO.planoDeFundo.setNivel(3);
        JOGO.planoDeFundo.setH(0.739f);
        JOGO.planoDeFundo.setB(0.5f);

        plataformas.add(new Plataforma(0, alturaChao, calculaX(80f), 300));//

        obstaculo(1f,alturaChao);
        obstaculo(3f, alturaChao);
        obstaculo(4f,alturaChao);
        obstaculo(5f,alturaChao);

        for(int i=0;i<2;i++){
            plataformas.add(new Plataforma(calculaX(6f+0.5f*i), alturaChao-40*(i+1), "escadinha"));
        }

        plataformas.add(new Plataforma(calculaX(7f), alturaChao-120, calculaX(2f), 120));
        obstaculo(7.5f,alturaChao-120);
        obstaculo(9f,alturaChao-120);
        
        for(int i=0;i<2;i++){
            plataformas.add(new Plataforma(calculaX(9.7f+0.5f*i), alturaChao-120+40*(i+1), "escadinha"));
        }
        obstaculo(10.41f, alturaChao);

        plataformas.add(new Plataforma(calculaX(10.85f), alturaChao-40, "escadinha"));

        plataformas.add(new Plataforma(calculaX(11.5f), alturaChao-80, calculaX(2f),15));
        obstaculo(12f,alturaChao-80);
        obstaculo(13f, alturaChao-80);
        plataformas.add(new Plataforma(calculaX(14.2f), alturaChao-40, "escadinha"));
        obstaculo(14.5f, alturaChao);
        

        plataformas.add(new Plataforma(calculaX(16f), alturaChao-60, 50,60));
        plataformaComObstaculo(16.6f, alturaChao-100, Color.blue);
        plataformaComObstaculo(17.7f, alturaChao-140, Color.blue);
        plataformas.add(new Plataforma(calculaX(18.8f), alturaChao-100, calculaX(1f),100));
        for(int i=0;i<2;i++){
            obstaculo(19.3f+0.5f*i, alturaChao-100);
        }

        obstaculo(21f,alturaChao);
        jumpers.add(new Jumper(calculaX(21.5f), alturaChao));

        plataformas.add(new Plataforma(calculaX(22f), alturaChao-100, 165,100));
        obstaculo(22.3f, alturaChao-100);
        jumpers.add(new Jumper(calculaX(22.5f), alturaChao-100));

        plataformas.add(new Plataforma(calculaX(23.3f), alturaChao-200, 165,200));
        obstaculo(23.6f, alturaChao-200);

        obstaculo(26f, alturaChao);
        obstaculo(26.5f, alturaChao);
        obstaculo(27f, alturaChao);
        jumpers.add(new Jumper(calculaX(27.5f), alturaChao));
        plataformas.add(new Plataforma(calculaX(28f), alturaChao-100, "escadinha"));
        plataformas.add(new Plataforma(calculaX(28.5f), alturaChao-140, 175,15));
        
        plataformas.add(new Plataforma(calculaX(29.5f), alturaChao-100, "escadinha"));
        plataformas.add(new Plataforma(calculaX(30f), alturaChao-60, "escadinha"));

        for(int i=0;i<3;i++){
            obstaculo(31f+1f*i,alturaChao);
        }

        plataformas.add(new Plataforma(calculaX(33.6f), alturaChao-60, calculaX(1f),60));
        obstaculo(34.1f, alturaChao-60);
        obstaculo(34.7f, alturaChao-60);

        obstaculo(35.4f, alturaChao);

        for(int i=1;i<3;i++){
            plataformas.add(new Plataforma(calculaX(36f+0.5f*i), alturaChao-40*(i+1), "escadinha"));
        }

        plataformaComObstaculo(37.5f, alturaChao-160, Color.blue);

        for(int i=0;i<2;i++){
            plataformas.add(new Plataforma(calculaX(38.5f+0.5f*i), alturaChao-160+40*(i+1), "escadinha"));
        }

        obstaculo(40f, alturaChao);
        jumpers.add(new Jumper(calculaX(40.5f), alturaChao, 1/12f,0.005f));
        obstaculo(40.9f,alturaChao);

        for(int i=1;i<3;i++){
            plataformas.add(new Plataforma(calculaX(42f+0.5f*i), alturaChao-40*(i+1), "escadinha"));
        }

        plataformaComObstaculo(43.5f, alturaChao-160, Color.blue);

        for(int i=0;i<3;i++){
            plataformas.add(new Plataforma(calculaX(44.5f+0.5f*i), alturaChao-220+40*(i+1), "escadinha"));
        }
        plataformaComObstaculo(46.1f, alturaChao-100, Color.blue);
        obstaculo(47f, alturaChao);

        for(int i=0;i<3;i++){
            obstaculo(48f+0.5f*i, alturaChao);
        }
        for(int i=0;i<3;i++){
            obstaculo(49.5f+0.66f*i, alturaChao);
        }

        for(int i=0;i<3;i++){
            plataformas.add(new Plataforma(calculaX(51.5f+0.5f*i), alturaChao-40*(i+1), "escadinha"));
        }

        plataformaComObstaculo(53.1f, alturaChao-200, Color.blue);
        plataformas.add(new Plataforma(calculaX(54.1f), alturaChao-200, 150,15));
        obstaculo(54.35f, alturaChao-200);

        for(int i=0;i<3;i++){
            plataformas.add(new Plataforma(calculaX(54.8f+0.5f*i), alturaChao-200+40*(i+1), "escadinha"));
        }
        obstaculo(56.5f, alturaChao);

        jumpers.add(new Jumper(calculaX(57f), alturaChao));

        plataformaComObstaculo(57.6f, alturaChao-120, Color.blue);
        plataformas.add(new Plataforma(calculaX(57.5f)+150, alturaChao-120, 150,120));
        jumpers.add(new Jumper(calculaX(58.5f)+100,alturaChao-120));

        plataformaComObstaculo(59.1f, alturaChao-200, Color.blue);
        plataformas.add(new Plataforma(calculaX(59.5f), alturaChao-200, 150,200));
        


        
        




        
    }

    @Override
    protected int calculaX(float t) {
        int x = (int) ((xInicial + Jogador.getWidth()) + (235 * (t))); // 267 eh um valor experimental, tem q mudar se trocar vPlataformas
        return x;
    }
}
