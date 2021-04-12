package Graphics;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio{
    private final String track;

    public Audio(String track, double wt) {
        this.track = track;
    }

    public void sound(){
        File f = new File(this.track);
        AudioInputStream tr;
        try {
            tr = AudioSystem.getAudioInputStream(f);
            Clip clip = AudioSystem.getClip();
            clip.open(tr);
            FloatControl volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.setFramePosition(0);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

    }
}