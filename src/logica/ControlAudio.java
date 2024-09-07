package logica;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.io.InputStream;

public class ControlAudio {
    private Clip musicaFondo;

    public void reproducirSonido(String archivoSonido) {
        try {
        	
            // Accede al archivo en el paquete "audio"
            InputStream audioIn = getClass().getResourceAsStream("/audio/" + archivoSonido);
            if (audioIn == null) {
                throw new IOException("No se pudo encontrar el archivo de sonido: " + archivoSonido);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reproducirMusicaDeFondo(String archivoMusica) {
        try {
            if (musicaFondo != null && musicaFondo.isRunning()) {
            	musicaFondo.stop();
            }
            
            // Accede al archivo en el paquete "audio"
            InputStream audioIn = getClass().getResourceAsStream("/audio/" + archivoMusica);
            if (audioIn == null) {
                throw new IOException("No se pudo encontrar el archivo de música: " + archivoMusica);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioIn);
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(audioStream);
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY); // Reproduce en bucle
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detenerMusicaDeFondo() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
        	musicaFondo.stop();
        }
    }
}
