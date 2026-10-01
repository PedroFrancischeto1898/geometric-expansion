import javax.sound.sampled.*;
import java.net.URL;

public class MusicaTemp {//classe da musica
    Long currentFrame;
    static Clip clip;
    String status;
    static AudioInputStream audioInputStream;
    protected static String filePath;
   
     MusicaTemp(){
      carrega(); 
     }
        
    
        
    
    
    protected static float fimDaMusica(){
        return clip == null ? 0f : clip.getMicrosecondLength()/1000000f;
    }

    protected static void carrega(){
        try{
        if (filePath == null) return;
        URL audioUrl = MusicaTemp.class.getResource(filePath);
        if (audioUrl == null) {
            System.err.println("Audio não encontrado: " + filePath + ". O jogo continuará sem música.");
            clip = null;
            return;
        }
        audioInputStream = AudioSystem.getAudioInputStream(audioUrl);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        
        
    
        
        }
        catch (Exception e) {
        System.err.println("The specified audio file is not supported.");
        e.printStackTrace();}

    }
    protected void comecaMusica(){
       
       if (clip == null) return;
       new Thread(() -> {clip.start();}).start();

       while (!clip.isRunning()) {
        try {
            Thread.sleep(10); // Espera 10 milissegundos antes de verificar novamente
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        
           
    }

    public static void reset(){
        
            if (clip != null) {
                clip.stop();
                clip.setFramePosition(0);
            }
        
    }

    public static double segundos(){
        return clip == null ? 0d : clip.getMicrosecondPosition()/1000000f;
    }

    
    
}
