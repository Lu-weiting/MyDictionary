package com.example.openai;

import com.baidu.aip.speech.AipSpeech;
import com.example.openai.Model.AudioDownloader;
//import com.example.openai.Model.AudioPlayer;
//import com.example.openai.Model.AudioPlayer;
//import com.example.openai.Model.MP3ToWav;
//import com.example.openai.Model.MP3toWAVConverter;
import com.example.openai.Model.MP3Player;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


@SpringBootApplication
public class OpenAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenAiApplication.class, args);
//同名直接取代
//        AudioDownloader.downloadAudio("https://s.yimg.com/bg/dict/dreye/live/m/movie.mp3", "/Users/waiting/tryAudio/movie.mp3");
//        AudioDownloader.downloadAudio("http://dict.youdao.com/dictvoice?type=2&audio=you%20are%20nice", "/Users/waiting/openAI/src/main/resources/static/mp3/n1.mp3");

//        String filename = "/Users/waiting/tryAudio/movie.mp3";
//        MP3Player mp3Player = new MP3Player(filename);
//        mp3Player.play();
//        http://dict.youdao.com/dictvoice?type=2&audio=forklift
//          MP3ToWav mp3ToWav=new MP3ToWav();
//        MP3toWAVConverter.convert("/Users/waiting/tryAudio/pen1.mp3","/Users/waiting/tryAudio/pen1.wav");
//        AudioPlayer player = new AudioPlayer();
//        player.loadAudio("/Users/waiting/tryAudio/pen1.wav");
//        player.play();
//        URL url;
//        try {
//            url = new URL("http://dict.youdao.com/dictvoice?type=2&audio=must");
//            URLConnection conn = url.openConnection();
//            AudioPlayer player = AudioPlayer.createPlayer(url);
//
//            player.setPlayCount(2);
//            player.setAutoClose(true);
//            player.play();
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        try
//        {
//            //add the path to the audio file
//            SoundPlayer.thePath = "/Users/waiting/Downloads/d.wav";
////            /Users/waiting/Downloads/d.wav http://dict.youdao.com/dictvoice?type=2&audio=must
//
//            SoundPlayer simpleSoundPlayer =
//                    new SoundPlayer();
//
//            simpleSoundPlayer.play();
//            Scanner scanned = new Scanner(System.in);
//
//            //show the options
//            while (true)
//            {
//                System.out.println("1. pause");
//                System.out.println("2. resume");
//                System.out.println("3. restart");
//                System.out.println("4. stop");
//                System.out.println("5. Jump to specific time");
//                int a = scanned.nextInt();
//                simpleSoundPlayer.gotoChoice(a);
//                if (a == 4)
//                    break;
//            }
//            scanned.close();
//        }
//
//        catch (Exception e)
//        {
//            System.out.println("Experienced an error while playing sound.");
//            e.printStackTrace();
//
//        }
    }

}
