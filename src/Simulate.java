//This thread starts the words simulation
public class Simulate extends Thread{
    
    WordRecord words;
    private static volatile boolean flag = true;
    public Simulate(WordRecord words)
    {
       this.words=words;
    }
    public void run()
    { 
        
      while(flag){
        words.drop(20*words.getSpeed()/500);
          try {
              Thread.sleep(1000);
          } catch (InterruptedException ex) {}
      }
      flag = false;
    }
    
}
