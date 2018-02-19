
public class Ladder {
    private int botton, top;
    
    public Ladder( int b, int t ){
        setBotton(b);
        setTop(t);
    }
    
    public Ladder(){
        
    }
    
    public void setTop( int t ){
        if( t > Run.MIN_CELL - 1 && t < Run.MAX_CELL + 1 ){
            top = t;
        }else{
            top = 100;
        }
    }
    
    public void setBotton( int b ){
        if( b > Run.MIN_CELL - 1 && b < Run.MAX_CELL + 1 ){
            botton = b;
        }else{
            botton = 1;
        }
    }
    
    public int getTop(){
        return top;
    }
    
    public int getBotton(){
        return botton;
    }
}
