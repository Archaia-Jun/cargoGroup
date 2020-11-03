package devlog;

import javax.sound.sampled.*;
import java.io.FileInputStream;

public class Son extends Thread{
String fileName;

  public Son( String fileName  ){
this.fileName = fileName;
    start();
  }
  
public void run(){
 joueSon( fileName );
}

  public void joueSon(String filename){
    try{
      FileInputStream file=new FileInputStream(filename);
      AudioInputStream audioStream=AudioSystem.getAudioInputStream(file);
      AudioFormat audioFormat =audioStream.getFormat();
      int bytesPerFrame =audioFormat.getFrameSize();
      byte[] audioBytes = new byte [1024*bytesPerFrame];
      DataLine.Info info =new DataLine.Info(SourceDataLine.class, audioFormat);
      SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
      line.open();
      line.start();
      int bytesWritten;
      while((bytesWritten = audioStream.read(audioBytes))!=-1)
	{
	      line.write(audioBytes,0,bytesWritten);
	}
      line.close();
      file.close();
    }
      catch(Exception e){
      }
  
  }
}  
