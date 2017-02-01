package org.academiadecodigo.jungleweed.Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;
import java.util.HashMap;


public class NewAudio {

    // The wrapper thread is unnecessary, unless it blocks on the
    // Clip finishing; see comments.
    public static HashMap<String,Clip> soundClips;
    private static AudioInputStream inputStream;

    public static void load(String[] soundNames){

        soundClips = new HashMap<>(soundNames.length);

        for(String soundName:soundNames){
            try {
                soundClips.put(soundName,AudioSystem.getClip());

                // load sound from jar
                String pathStr = "/res/" + soundName.toString() + ".wav";
                URL soundURL = NewAudio.class.getResource(pathStr);


                if(soundURL == null){
                    // load sound from source code
                    File file = new File("res/"+soundName.toString()+".wav");
                    soundURL = file.toURI().toURL();
                }


                inputStream = AudioSystem.getAudioInputStream(soundURL);
                soundClips.get(soundName).open(inputStream);


            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }

    public static void start(String soundName){
        if(!soundClips.get(soundName).isRunning()){
            soundClips.get(soundName).start();
            soundClips.get(soundName).setFramePosition(0);
        }
    }

    public static void loop(String soundName,int time){
        if(!soundClips.get(soundName).isRunning()){
            soundClips.get(soundName).loop(time);
            soundClips.get(soundName).setFramePosition(0);
        }

    }

    public static void stopAll(){
        for(String soundName:soundClips.keySet()){
            soundClips.get(soundName).stop();
        }
    }

    public static void stop(String soundName){
        soundClips.get(soundName).stop();
    }

}