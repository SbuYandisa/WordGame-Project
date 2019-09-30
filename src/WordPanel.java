
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable {
   public static volatile boolean done;
   private WordRecord[] words;
   private int noWords;
   private int maxY;
   public static volatile boolean clear;
		
   public void paintComponent(Graphics g) {
      int width = getWidth();
      int height = getHeight();
      g.clearRect(0,0,width,height);
      g.setColor(Color.red);
      g.fillRect(0,maxY-10,width,height);
   
      g.setColor(Color.black);
      g.setFont(new Font("Helvetica", Font.PLAIN, 26));
   	   //draw the words
   	   //animation must be added
      if(clear==false){ 
         for (int i=0;i<noWords;i++){	    		
            g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words
            if (maxY==words[i].getY()) {
        	   words[i].resetWord();
        	   WordApp.score.missedWord();
        	   WordApp.setTextMissed("Missed:" + WordApp.score.getMissed()+ "    ");
            }
         }
      }
      else{
         for (int i=0;i<noWords;i++){
           g.drawString(" ", 0, 0);
         }
      }
   }
		
   WordPanel(WordRecord[] words, int maxY) {
      this.words=words; //will this work?
      noWords = words.length;
      done=false;
      this.maxY=maxY;	
      clear = false;	
   }
		
   public void run() {
         for(int i =0;i<noWords;i++){
        	 Thread thread = new Thread(new Simulate(words[i]));
        	 thread.start();
         }
         while (!done) {
         repaint();
         }
      }
}


