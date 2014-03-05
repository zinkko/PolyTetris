/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polytetris.pieces;

import java.util.ArrayList;
import java.util.Iterator;
import polytetris.Direction;
import polytetris.Peli;

/**
 *
 * @author Ilari
 */
public class Pohja extends Piece{
    private Peli peli;
    
    public Pohja(Peli peli, ArrayList<Block> b){
        super(b);
        this.peli = peli;
    }
    
    @Override
    public void turn(boolean clockwise){
        
    }
    
    @Override
    public void checkRows(int alku, int loppu){
        
        for (int i=alku-1; i<=loppu;i++){
            int counter = 0;
            for (Block b : this.parts){
                if (b.getY()==i){
                    counter++;
                }
            }
            if (counter>=peli.getMaxX()){
                boolean b = deleteRow(i);
            }else{
            }
        }
    }
    
    private boolean deleteRow(int r){
        Iterator<Block> iter = this.parts.iterator();
        while (iter.hasNext()){
            Block b = iter.next();
            if (b.getY() == r){
                iter.remove();
            }
        }
        for (Block b : this.parts){
            if (b.getY()<r){
                //for (int i=0; i < r-b.getY();i++){
                b.move(Direction.DOWN); 
                //}
            }
        }
        peli.addLine();
        return true;
    }
}
