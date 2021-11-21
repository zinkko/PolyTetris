/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polytetris.pieces;

import java.awt.Color;

/**
 *
 * @author Ilari
 */
public enum Type {
    MONOMINO(Color.PINK, new int[][]{{}}),
    DOMINO(Color.PINK, new int[][]{{0,1}}),
    TROMINO_L(Color.PINK, new int[][]{{1,0},{0,1}}),
    TROMINO_I(Color.PINK, new int[][]{{0,1},{0,-1}}),
    //TETRA_I,TETRA_L,TETRA_T,TETRA_O,TETRA_S,
    PENTA_F(Color.CYAN,new int[][]{{-1,0},{0,1},{0,-1},{1,-1}}),
    PENTA_I(Color.RED,new int[][]{{-2,0},{-1,0},{1,0},{2,0}}),
    PENTA_L(Color.BLUE,new int[][]{{-1,-1},{-1,0},{1,0},{2,0}}),
    PENTA_N(Color.GREEN,new int[][]{{-1,1},{0,1},{1,0},{2,0}}),
    PENTA_P(Color.MAGENTA,new int[][]{{-1,0},{-1,1},{0,1},{0,-1}}),
    PENTA_T(Color.YELLOW,new int[][]{{-1,-1},{0,-1},{1,-1},{0,1}}),
    PENTA_U(Color.WHITE,new int[][]{{-1,0},{-1,-1},{1,0},{1,-1}}),
    PENTA_V(Color.BLUE,new int[][]{{2,0},{1,0},{0,-1},{0,-2}}),
    PENTA_W(Color.GREEN,new int[][]{{-1,0},{-1,-1},{0,1},{1,1}}),
    PENTA_X(Color.RED,new int[][]{{-1,0},{1,0},{0,-1},{0,1}}),
    PENTA_Y(Color.ORANGE,new int[][]{{-1,0},{0,-1},{1,0},{2,0}}),
    PENTA_Z(Color.CYAN,new int[][]{{-1,-1},{0,-1},{0,1},{1,1}});

    private int[][] positions;
    private Color color;
    
    private Type(Color c, int [][] positions){
        this.color = c;
        this.positions = positions;
    }
    
    public int[][] getPositions(boolean reverse){ 
        if (reverse) {
            return mirror();
        }
        return this.positions;
    }
    
    private int[][] mirror() {
        int[][] reversed = positions;
        for (int i=0; i< positions.length; i++) {
            reversed[i][0] *= -1;
        }
        return reversed;
    }
    
    public Color getColor(){
        return this.color;
    }
    
}
