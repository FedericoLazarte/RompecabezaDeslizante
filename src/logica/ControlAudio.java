package logica;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.io.InputStream;

public class ControlAudio {
    private static ControlAudio instancia; // Instancia única de la clase
    private Clip musicaFondo;

    // Constructor privado para evitar la creación de múltiples instancias
    private ControlAudio() { }

    // Método para obtener la instancia única
    public static ControlAudio getInstancia() {
        if (instancia == null) {
            instancia = new ControlAudio();
        }
        return instancia;
    }

    public void reproducirSonido(String archivoSonido) {
        try {
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

    public void reproducirMusica(String archivoMusica) {
        try {
            if (musicaFondo != null && musicaFondo.isRunning()) {
                musicaFondo.stop();
            }
            
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

    public void detenerMusica() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
            musicaFondo.stop();
        }
    }
}
