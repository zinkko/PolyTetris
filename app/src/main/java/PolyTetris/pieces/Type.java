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
    MONOMINO(Type.color(0f), new int[][]{}),
    DOMINO(Type.color(0.5f), new int[][]{{0, 1}}),
    TROMINO_L(Type.color(0.25f), new int[][]{{1, 0}, {0, 1}}),
    TROMINO_I(Type.color(0.75f), new int[][]{{0, 1}, {0, -1}}),

    TETRA_I(Type.color(0.05f), new int[][]{{0, 1}, {0, -1}, {0, -2}}),
    TETRA_L(Type.color(0.15f), new int[][]{{0, 1}, {0, -1}, {1, -1}}),
    TETRA_T(Type.color(0.35f), new int[][]{{-1, 0}, {1, 0}, {0, 1}}),
    TETRA_O(Type.color(0.55f), new int[][]{{-1, 1}, {0, 1}, {-1, 0}}),
    TETRA_S(Type.color(0.85f), new int[][]{{-1, 0}, {0, 1}, {1, 1}}),

    PENTA_F(Type.color(0.1f), new int[][]{{-1,0},{0,1},{0,-1},{1,-1}}),
    PENTA_I(Type.color(0.2f), new int[][]{{-2,0},{-1,0},{1,0},{2,0}}),
    PENTA_L(Type.color(0.3f), new int[][]{{-1,-1},{-1,0},{1,0},{2,0}}),
    PENTA_N(Type.color(0.4f), new int[][]{{-1,1},{0,1},{1,0},{2,0}}),
    PENTA_P(Type.color(0.45f), new int[][]{{-1,0},{-1,1},{0,1},{0,-1}}),
    PENTA_T(Type.color(0.5f), new int[][]{{-1,-1},{0,-1},{1,-1},{0,1}}),
    PENTA_U(Type.color(0.6f), new int[][]{{-1,0},{-1,-1},{1,0},{1,-1}}),
    PENTA_V(Type.color(0.65f), new int[][]{{2,0},{1,0},{0,-1},{0,-2}}),
    PENTA_W(Type.color(0.7f), new int[][]{{-1,0},{-1,-1},{0,1},{1,1}}),
    PENTA_X(Type.color(0.8f), new int[][]{{-1,0},{1,0},{0,-1},{0,1}}),
    PENTA_Y(Type.color(0.9f), new int[][]{{-1,0},{0,-1},{1,0},{2,0}}),
    PENTA_Z(Type.color(0.95f), new int[][]{{-1,-1},{0,-1},{0,1},{1,1}});

    private int[][] positions;
    private Color color;
    
    private Type(Color c, int [][] positions){
        this.color = c;
        this.positions = positions;
    }

    private static Color color(float hue) {
        return new Color(Color.HSBtoRGB(hue, 1f, 0.9f));
    }
    
    public int[][] getPositions(boolean reverse){ 
        if (reverse) {
            return mirror();
        }
        return this.positions;
    }
    
    private int[][] mirror() {
        int[][] reversed = this.positions;
        for (int i=0; i < reversed.length; i++) {
            reversed[i][0] *= -1;
        }
        return reversed;
    }
    
    public Color getColor(){
        return this.color;
    }
    
}
