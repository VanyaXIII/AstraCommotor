package Graphics;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio{
    private String track;
    private Clip clip = null;
    private double wt;
    private FloatControl volumeC = null;

    public Audio(String track, double wt) {
        this.track = track;
        this.wt = wt;
    }

    public void sound(){
        File f = new File(this.track);
        AudioInputStream tr = null;
        try {
            tr = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(tr);
            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.setFramePosition(0);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

    }
}