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
    MONOMINO(Color.GREEN, new int[][]{{}}),
    DOMINO(Color.GREEN, new int[][]{{0, 1}}),
    TROMINO_L(Color.YELLOW, new int[][]{{1, 0}, {0, 1}}),
    TROMINO_I(Color.YELLOW, new int[][]{{0, 1}, {0, -1}}),
    TETRA_I(new Color(250, 180, 44), new int[][]{{0, 1}, {0, -1}, {0, -2}}),
    TETRA_L(new Color(250, 180, 44), new int[][]{{0, 1}, {0, -1}, {1, -1}}),
    TETRA_T(new Color(250, 180, 44), new int[][]{{-1, 0}, {1, 0}, {0, 1}}),
    TETRA_O(new Color(250, 180, 44), new int[][]{{-1, 1}, {0, 1}, {-1, 0}}),
    TETRA_S(new Color(250, 180, 44), new int[][]{{-1, 0}, {0, 1}, {1, 1}}),
    PENTA_F(Color.RED, new int[][]{{-1,0},{0,1},{0,-1},{1,-1}}),
    PENTA_I(Color.RED, new int[][]{{-2,0},{-1,0},{1,0},{2,0}}),
    PENTA_L(Color.RED, new int[][]{{-1,-1},{-1,0},{1,0},{2,0}}),
    PENTA_N(Color.RED, new int[][]{{-1,1},{0,1},{1,0},{2,0}}),
    PENTA_P(Color.RED, new int[][]{{-1,0},{-1,1},{0,1},{0,-1}}),
    PENTA_T(Color.RED, new int[][]{{-1,-1},{0,-1},{1,-1},{0,1}}),
    PENTA_U(Color.RED, new int[][]{{-1,0},{-1,-1},{1,0},{1,-1}}),
    PENTA_V(Color.RED, new int[][]{{2,0},{1,0},{0,-1},{0,-2}}),
    PENTA_W(Color.RED, new int[][]{{-1,0},{-1,-1},{0,1},{1,1}}),
    PENTA_X(Color.RED, new int[][]{{-1,0},{1,0},{0,-1},{0,1}}),
    PENTA_Y(Color.RED, new int[][]{{-1,0},{0,-1},{1,0},{2,0}}),
    PENTA_Z(Color.RED, new int[][]{{-1,-1},{0,-1},{0,1},{1,1}});

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
        int[][] reversed = this.positions;
        for (int i=0; i< reversed.length; i++) {
            reversed[i][0] *= -1;
        }
        return reversed;
    }
    
    public Color getColor(){
        return this.color;
    }
    
}
