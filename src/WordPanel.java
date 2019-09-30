
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class WordPanel extends JPanel implements Runnable {
   public static volatile boolean done;
   private WordRecord[] words;
   private int noWords;
   private int maxY;
   public static volatile boolean clear;
   public Score score; 
   public JLabel missed;
   private Font font;
		
   public void paintComponent(Graphics g) {
      int width = getWidth();
      int height = getHeight();
      g.clearRect(0,0,width,height);
      g.setColor(Color.red);
      g.fillRect(0,maxY-10,width,height);
      g.setColor(Color.black);
      font = new Font("Helvetica", font.PLAIN, 26);
      g.setFont(font);
   	   //draw the words
   	   //animation must be added
      if(clear==false){ 
         for (int i=0;i<noWords;i++){	    		
            g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words
            if (maxY==words[i].getY()) {
        	   words[i].resetWord();
        	   score.missedWord();
        	   missed.setText("Missed:" + score.getMissed()+ "    ");
            }
         }
      }
      else{
         for (int i=0;i<noWords;i++){
           g.drawString(" ", 0, 0);
         }
      }
   }
   WordPanel(WordRecord[] words, int maxY, Score score, JLabel missed) {
      this.words=words; //will this work?
      noWords = words.length;
      done=false;
      this.maxY=maxY;	
      clear = false;
      this.score=score;
      this.missed=missed;		
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


