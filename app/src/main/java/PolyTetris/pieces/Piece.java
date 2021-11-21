/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polytetris.pieces;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import polytetris.Direction;
import polytetris.Peli;

/**
 *
 * @author Ilari
 */
public class Piece {
    private boolean reverse;
    private Block pivot;
    protected ArrayList<Block> parts;
    protected int seedX, seedY;
    private int stage = 0;
    private Type type;
    
    public Piece(ArrayList<Block> parts){
        this.parts = parts;
    }
    
    public Piece clone(int x, int y){
        Piece newPiece = new Piece(x, y, this.type, this.reverse);
        ArrayList<Block> parts = new ArrayList<>();
        for (Block b : this.parts){
            parts.add(b.ghostBlock());
        }
        newPiece.parts = parts;
        return newPiece;
    }
    
    public Piece(int seedX, int seedY, Type t, boolean reversed){
        this.seedX = seedX;
        this.seedY = seedY;
        this.parts = new ArrayList<>();
        this.type = t;
        this.setBlocks(this.type.getPositions(reversed));
    }
    
    public void move(Direction d){        
        for (Block b : this.parts){
            b.move(d);
        }
    }
    
    public void addBlocks(Block b){
        this.parts.add(b);
    } 
    
    public void addBlocks(ArrayList<Block> b){
        this.parts.addAll(b);
    }
    
    public ArrayList<Block> getParts(){
        return this.parts;
    }
    
    public void piirra(Graphics g){
        
        for (Block b : parts){
            b.piirra(g);
        }
    }
    
    public int getY(){
        return this.parts.get(0).getY();
    }
    
    public boolean collide(Piece toinenPala){
        for (Block eka : this.parts){
            for (Block toka: toinenPala.parts){
                if (eka.getX() == toka.getX() && eka.getY() == toka.getY()){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public int getHighY(){
        int palaute = -1;
        for (Block b:parts){
            if (b.getY()>palaute){
                palaute = b.getY();
            }
        }
        return palaute;
    }
    
    public int getLowY(){
        int palaute = 1000; // big enough
        for (Block b:parts){
            if (b.getY()<palaute){
                palaute = b.getY();
            }
        }
        return palaute;
    }
    
    public void checkRows(int alku, int loppu){
        
    }
    
    public boolean isOffscreenX(int x){
        boolean palaute = false;
        for (Block b: parts){
            if (b.isOffscreenX(x)) {
                return true;
            }
        }
        return palaute;
    }
    
    public boolean isOffscreenY(int y){
        boolean palaute = false;
        for (Block b: parts){
            if (b.isOffscreenY(y)) {
                return true;
            }
        }
        return palaute;
    }
    
    private void setBlocks(int[][] palat){
       pivot = new Block(seedX,seedY,Peli.BLOCK_SIZE,this.type.getColor());
       this.parts.add(pivot);

       for (int[] coords : palat){
           this.parts.add(new Block(seedX+coords[0],seedY+coords[1],Peli.BLOCK_SIZE,this.type.getColor()));
       }
    }
    
    public void turn(boolean clockwise){
        if (this.type == Type.TETRA_O) {
            return;
        }

        for (Block block : this.parts) {
            block.rotate(this.pivot.getX(), this.pivot.getY(), clockwise);
        }
    }

    public int getX() {
        return this.pivot.getX();
    }
}
