package SoundPack;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.*;

import javazoom.jl.player.Player;

/**
 * This class creates a thread that will stream audio from a URL. It can handle
 * all uncompressed streams that Java can natively handle (.wav, .aiff, .au,
 * etc.). It also uses the JavaZoom library to decode and play mp3 files as
 * well. When the thread should be stopped, a request must be sent in.
 * @author dan
 */
public class StreamThread extends Thread{

    //The audio's URL
    private URL url = null;
    //The mp3 sound player
    private Player mp3_player;
    //The built in sound player
    private  Clip clip;
    private FloatControl gainControl;
    SourceDataLine line;

    BooleanControl muteControl;
    public double gain = .5;

    static boolean muted = false;
    static boolean paused = false;
    static boolean stop = false;
    
    /**
     * This is an optional constructor that takes in the source URL for the
     * thread. 
     * @param source_url
     */
    public StreamThread( URL source_url ){
        
        url = source_url;
    }
    
    /**
     * The constructor can also be called with no arguments. If this is the
     * case, the method setStream() must be called to set the stream URL. If it
     * is not and the StreamThread is run, it will throw an exception.
     */
    public StreamThread(){}
    
    /**
     * This method is used to set the source URL of the StreamThread.
     * @param source_url
     */
    public void setStream( URL source_url ){
        url = source_url;
    }
    
    /**
     * Because there is no way of knowing how long certain file types may play,
     * the Thread uses a loop when the sound is beign streamed. This loop
     * continues until the sound is finished or a stop is requested. This is
     * intended to be used by the user to inplement a manual stop.
     */
    public void requestStop(){
        if( mp3_player != null ){
           // SourceDataLine line = getLine(targetFormat);
           // line.stop();
            mp3_player.close();
        }

        if( clip != null && clip.isRunning() ){
            clip.close();
            clip.drain();
        }
    }





    public void testPlay(URL filename)
{
  try {
    //File file = new File(filename);
    //URI hi = file.toURI();
//     DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat(), ((int)stream.getFrameLength()*format.getFrameSize()));
//        Clip clip = (Clip) AudioSystem.getLine(info);
//
//
//
//    FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
//    double gain = .5D;    // number between 0 and 1 (loudest)
//    float dB = (float)(Math.log(gain)/Math.log(10.0)*20.0);
//    gainControl.setValue(dB);

     // double durationInSecs = clip.getBufferSize() ;/// (clip.getFormat().getFrameSize() * clip.getFormat().getFrameRate());
     // System.err.println(durationInSecs);
    //stream_thread = new StreamThread( filename );
    //stream_thread.start();
    URL file = filename;


    //AudioInputStream in= AudioSystem.getAudioInputStream(file);
    AudioInputStream in= AudioSystem.getAudioInputStream(file);
    AudioInputStream din = null;
    AudioFormat baseFormat = in.getFormat();
    AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                                                                                  baseFormat.getSampleRate(),
                                                                                  16,
                                                                                  baseFormat.getChannels(),
                                                                                  baseFormat.getChannels() * 2,
                                                                                  baseFormat.getSampleRate(),
                                                                                  false);

    din = AudioSystem.getAudioInputStream(decodedFormat, in);

 




    //clip
//    DataLine.Info info = new DataLine.Info(Clip.class, decodedFormat);
//
 //   clip = (Clip)AudioSystem.getLine(info);
//    clip.open(in);

//    double durationInSecs = clip.getBufferSize() / (clip.getFormat().getFrameSize() * clip.getFormat().getFrameRate());
 //   System.err.println(" HEYYY " + durationInSecs);


    // Play now.
    rawplay(decodedFormat, din);

 

    in.close();

    

  } catch (Exception e)
    {
        //Handle exception.
    }
}


//number between 0 and 1 (loudest)
public  void setGain(double gain)
{
  this.gain = gain;
  //gainControl = (FloatControl)line.getControl(FloatControl.Type.MASTER_GAIN);
  //float dB = (float)(Math.log(gain)/Math.log(10.0)*20.0);
  //gainControl.setValue(dB);
}

public void setMuted(boolean value){
    muted = value;
}



private void rawplay(AudioFormat targetFormat, AudioInputStream din) throws IOException,                                                                                                LineUnavailableException,                                                                                                 IOException
{
 
  byte[] data = new byte[4096];
   line = getLine(targetFormat);

  //private SourceDataLine source;
  if (line != null)
  {
    // Start
    line.start();


     muteControl = (BooleanControl)line.getControl(BooleanControl.Type.MUTE);
    //setting the volume - help from.. http://www.exampledepot.com/egs/javax.sound.sampled/Volume.html


    //BooleanControl muteControl = (BooleanControl)clip.getControl(BooleanControl.Type.MUTE);
    //muteControl.setValue(true);
    
     
    int nBytesRead = 0, nBytesWritten = 0;
    while (nBytesRead != -1)
    {
       

        //nBytesRead = din.read(data, 0, data.length);
      // if (nBytesRead != -1) nBytesWritten = line.write(data, 0, nBytesRead);

      synchronized (lock) {
      while ((nBytesRead = din.read(data, 0, data.length)) != -1) {

        //setting the volume - help from.. http://www.exampledepot.com/egs/javax.sound.sampled/Volume.html
        gainControl = (FloatControl)line.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float)(Math.log(gain)/Math.log(10.0)*20.0);
        gainControl.setValue(dB);

        if(muted){
            mute();
        }
        else{
           unMute();
        }
        while (paused) {
          if(line.isRunning()) {
            line.stop();
          }
          try {
            lock.wait();
          }
          catch(InterruptedException e) {
          }
        }

        if(stop){
            line.drain();
            line.stop();
            line.close();
            din.close();
        }

        if(!line.isRunning()) {
          line.start();
        }
        line.write(data, 0, nBytesRead);

      }
      

}
    }
    // Stop
    line.drain();
    line.stop();
    line.close();
    din.close();
  }
}




private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException
{
  SourceDataLine res = null;
  DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
  res = (SourceDataLine) AudioSystem.getLine(info);
  res.open(audioFormat);
  return res;
}

public void mute(){
    muteControl.setValue(true);

}
public void unMute(){
    muteControl.setValue(false);

}

static Object lock = new Object();
// some paused variable


// on the user thread:
public static void userPressedPause() {
 paused = true;
}

public static void userPressedPlay() {
    if(paused == true){
        synchronized(lock) {
            paused = false;
            lock.notifyAll();
        }
    }
}







    /**
     * This is the main guts of the thread. It first resets the stop_requested
     * variable. Then it checks to see if the thread has been given a URL
     * (throwing an exception if it has not). Then it decides if the URL given
     * corresponds to a supported file type. If it is, then it streams that
     * sound and waits until the sound is finished or a stop is requested.
     */
    public void run(){
        
        try{    
            if( url == null ) throw new Exception( "URL uninitialized." ); 
            
            if( url.getFile().endsWith( ".mp3" ) ){

                testPlay(url);
//                url.openConnection();
//                BufferedInputStream input_stream = new BufferedInputStream( url.openStream() );
//                mp3_player = new Player( input_stream );
//                mp3_player.play();
//                while( !mp3_player.isComplete() ){
//                    Thread.yield();
//                }
            }
            else if( url.getFile().endsWith( ".wav" ) ||
                url.getFile().endsWith( ".aiff" ) ||
                url.getFile().endsWith( ".au" ) ){
                url.openConnection();
                AudioInputStream soundIn = AudioSystem.getAudioInputStream( new BufferedInputStream( url.openStream() ) );
                AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                           AudioSystem.NOT_SPECIFIED,
                           16, 2, 4,
                           AudioSystem.NOT_SPECIFIED, true);
                DataLine.Info info = new DataLine.Info(Clip.class, format);

                clip = (Clip)AudioSystem.getLine(info);
                clip.open(soundIn);
                clip.start();
//                while( clip.isRunning() ){
//                    Thread.yield();
//                }
            }
            else{
                throw new Exception( "Unsupported File Type" );
            }
        }
        catch( Exception e ){
            System.err.println( e );
        }
        
    }
    
}
