/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

/**
 *
 * @author Maravillas
 */
public class ToTurn {
    private int[] turn = new int[2];
    private int force = 300;

    public ToTurn() {    
        turn[0]=0;
        turn[1]=0;
    }

    public int[] getTurn() {
        return turn;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void setTurn(String key) {
    switch (key){        
            case "left":
                turn[0]=-force;
                turn[1]=0;
                break;
            case "right":
                turn[0]=force;
                turn[1]=0;
                break;
            case "up":
                turn[0]=0;
                turn[1]=force;
                break;
            case "down":
                turn[0]=0;
                turn[1]=-force;
                break;
            case "0":
                turn[0]=0;
                turn[1]=0;
                break;
                
    }
                System.out.println(key); 
                
    }
    
}
