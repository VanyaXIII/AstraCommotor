package Engine;

import Graphics.Audio;
import Graphics.RenderEngine;
import PlanetaryEngine.PlanetaryEngine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Engine {
    public PlanetaryEngine planetaryEngine;
    public static boolean timeStopped = false;
    public RenderEngine renderEngine;
    public double startingTime;
    BufferedImage beforeIntro, intro;

    public Engine(){
        try {
            this.beforeIntro = ImageIO.read(new File("C:\\Users\\User\\IdeaProjects\\2D engine\\src\\black.jpg"));
            this.intro = ImageIO.read(new File("C:\\Users\\User\\IdeaProjects\\2D engine\\src\\logoExample.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Audio introSound = new Audio("C:\\Users\\User\\IdeaProjects\\2D engine\\src\\IntroSound.wav", 1);
        introSound.sound();
        startingTime = System.currentTimeMillis();
        planetaryEngine = new PlanetaryEngine();
    }

    public void updateAll(double timePast) {
        if (timeStopped) {
        } else {
            planetaryEngine.updateAll(timePast);
        }
    }

    public void introAnimation(Graphics g){
        if (System.currentTimeMillis() - startingTime > 2300){
            g.drawImage(beforeIntro,0,0,null);
            g.drawImage(intro,19,200, null);
        }else{
            g.drawImage(beforeIntro,0,0,null);
        }
    }
}