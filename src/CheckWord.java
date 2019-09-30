//This thread ckecks the word enterd if its correct and then increments number of words caught and the score
import javax.swing.JLabel;
public class CheckWord extends Thread{
    String input;
    WordRecord[] words;
    JLabel matching;
    JLabel score;
    Score scoreClass;

    public CheckWord(String input, WordRecord[] words, JLabel matching, JLabel score, Score scoreClass){
      this.input=input;
      this.words=words;
      this.matching=matching;
      this.score=score;
      this.scoreClass=scoreClass;
    }
    
    public void run(){
      for(int i=0;i<words.length;i++)
        {
           if(words[i].matchWord(input)==true)
           {
               words[i].resetWord();
               scoreClass.caughtWord(input.length());
               matching.setText("Caught: " + scoreClass.getCaught() + "    ");
               score.setText("Score:" + scoreClass.getScore()+ "    "); 
           }
        }

    }
}
