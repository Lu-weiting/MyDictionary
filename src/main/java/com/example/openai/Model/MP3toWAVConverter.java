package com.example.openai.Model;
//import javax.sound.sampled.*;
//import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
//
//import java.io.File;
//import java.io.IOException;
//
//public class MP3toWAVConverter {
//    public static void convert(String mp3FilePath, String wavFilePath) {
//        try {
//            AudioInputStream in = new MpegAudioFileReader().getAudioInputStream(new File(mp3FilePath));
//            AudioInputStream out = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED, in);
//            AudioSystem.write(out, AudioFileFormat.Type.WAVE, new File(wavFilePath));
//        } catch (UnsupportedAudioFileException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
