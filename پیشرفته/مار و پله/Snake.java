
public class Snake {
    private int head, tail;
    
    public  Snake( int h, int t ){
        setHead(h);
        setTail(t);
    }
    
    public  Snake(){
        
    }
    
    public void setHead( int h ){
        if( h > Run.MIN_CELL - 1 && h < Run.MAX_CELL + 1 ){
            head = h;
        }else{
            head = 100;
        }
    }
    
    public void setTail( int t ){
        if( t > Run.MIN_CELL - 1 && t < Run.MAX_CELL + 1 ){
            tail = t;
        }else{
            tail = 1;
        }
    }
    
    public int getHead(){
        return head;
    }
    
    public int getTail(){
        return tail;
    }
}
