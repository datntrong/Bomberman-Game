package uet.oop.bomberman.sound;

import uet.oop.bomberman.level.FileLevel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class GameSound {
    public static void play(String sound) {
        new Thread(new Runnable() {
                public void run() {
                    try {
                        URL absPath = FileLevel.class.getResource("/sound/" + sound+ ".wav");
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                                new File(absPath.getPath()).getAbsoluteFile());
                        clip.open(inputStream);
                        clip.start();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
        }).start();

    }
    public static void stop(String sound){
        new Thread(new Runnable() {
            public void run() {
                try {
                    URL absPath = FileLevel.class.getResource("/sound/" + sound+ ".wav");
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            new File(absPath.getPath()).getAbsoluteFile());
                    clip.open(inputStream);
                    clip.stop();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

}
