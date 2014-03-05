/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polytetris;

/**
 *
 * @author Ilari
 */
public enum Direction {
    UP(0,-1),DOWN(0,1),LEFT(-1,0),RIGHT(1,0);
    
    private int dx, dy;
    
    private Direction(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }
    
    public int getDx(){
        return dx;
    }
    
    public int getDy(){
        return dy;
    }
    
    public Direction reverse(){
        if (this.equals(Direction.LEFT)){
            return Direction.RIGHT;
        }else if (this.equals(Direction.RIGHT)){
            return Direction.LEFT;
        }else if (this.equals(Direction.UP)){
            return Direction.DOWN;
        }
        return Direction.UP;
        
    }
}
