/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polytetris.pieces;

import java.awt.Color;
import java.awt.Graphics;
import polytetris.Direction;

/**
 *
 * @author Ilari
 */
public class Block {
    
    private int x,y;
    private int length;
    private Color color;
    
    public Block(int x,int y,int length,Color c){
        this.x = x; this.y = y;
        this.length = length;
        this.color =  c;
    }
    
    
    public void move(Direction d){
        this.x += d.getDx();
        this.y += d.getDy();
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public boolean isOffscreenX(int boundaryX){
        return !(0 <= this.x && this.x < boundaryX);
    }
    public boolean isOffscreenY(int boundaryY){
        return !(0 <= this.y && this.y < boundaryY);
    }
    
    public void setPosition(int x, int y){
        this.x= x;
        this.y =y;
    }
    
    public void piirra(Graphics g){
        g.setColor(color);
        g.fillRect(x*(length+1), y*(length+1), length, length);
    }
}
