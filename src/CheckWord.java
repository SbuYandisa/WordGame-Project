//This thread ckecks the word enterd if its correct and then increments number of words caught and the score
public class CheckWord extends Thread{
    String input;
    WordRecord[] words;
    
    public CheckWord(String input, WordRecord[] words){
      this.input=input;
      this.words=words;
    }
    
    public void run(){
      for(int i=0;i<words.length;i++)
        {
           if(words[i].matchWord(input)==true)
           {
               words[i].resetWord();
               WordApp.score.caughtWord(input.length());
               WordApp.setTextCaught("Caught: " + WordApp.score.getCaught() + "    ");
               WordApp.setTextScore("Score:" + WordApp.score.getScore()+ "    "); 
           }
        }

    }
}
