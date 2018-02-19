
public class Player {
    private String name;
    private int position;
    private int turn;
    
    public Player(){
        position = 0;
    }
    
    public Player( String n ){
        position = 0;
        setName(n);
    }
    
    public void setName( String n ){
        name = n;
    }
    
    public String getName(){
        return name;
    }
    
    public int getPosition(){
        return position;
    }
    
    public void movement( int x ){
        if( position + x > Run.MIN_CELL - 1 && position + x < Run.MAX_CELL + 1 ){
            position += x;
        }
    }
    
    public void setTurn( int t ){
        if( t > -1 ){
            turn = t;
        }else{
            turn = 0;
        }
    }
    
    public int getTurn(){
        return turn;
    }
}
