/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entity.SpaceShip;

/**
 *
 * @author Maravillas
 */
public class ToTurn {

    SpaceShip ss = new SpaceShip();
    private int[] turn = new int[2];
    private int force = 300;
    int[] xCoords = new int[4];
    int[] yCoords = new int[4];

    public ToTurn(SpaceShip ss) {
        this.ss = ss;
        turn[0] = 0;
        turn[1] = 0;
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
        switch (key) {
            case "left":
                turn[0] = -force;
                turn[1] = 0;
                break;
            case "right":
                turn[0] = force;
                turn[1] = 0;
                break;
            case "up":
                turn[0] = 0;
                turn[1] = force;
                break;
            case "down":
                turn[0] = 0;
                turn[1] = -force;
                break;
            case "0":
                turn[0] = 0;
                turn[1] = 0;
                break;

        }
        System.out.println(key);

    }

    public int[] paintX(int[] coord, float x, float y, int WIDTH, int HEIGHT) {

        double side = (Math.sqrt(3) * ss.getCs().m_radius);
        int h = (int) Math.sqrt((side * side) + (side / 2) * (side / 2));
        if (coord[0] == 0) {
            if (coord[1] > 0) { //UP
                xCoords[0] = (int) x + WIDTH / 2;
                xCoords[1] = xCoords[0] - (int) (side / 2);
                xCoords[2] = xCoords[0] + (int) (side / 2);
            } else if (coord[1] < 0) { //DOWN
                xCoords[0] = (int) x + WIDTH / 2;
                xCoords[1] = xCoords[0] + (int) (side / 2);
                xCoords[2] = xCoords[0] - (int) (side / 2);
            } else {
                return xCoords;
            }
        } else if (coord[0] > 0) {//RIGHT
            xCoords[0] = (int) x + WIDTH / 2 + (int) ss.getCs().m_radius;
            xCoords[1] = xCoords[0] - h;
            xCoords[2] = xCoords[0] - h;
        } else if (coord[0] < 0) { //LEFT
            xCoords[0] = (int) x + WIDTH / 2 - (int) ss.getCs().m_radius;
            xCoords[1] = xCoords[0] + h;
            xCoords[2] = xCoords[0] + h;
        }
        return xCoords;

    }

    public int[] paintY(int[] coord, float x, float y, int WIDTH, int HEIGHT) {

        double side = (Math.sqrt(3) * ss.getCs().m_radius);
        int h = (int) Math.sqrt((side * side) + (side / 2) * (side / 2));
        if (coord[0] == 0) {
            if (coord[1] > 0) { //UP
                yCoords[0] = -(int) y + HEIGHT / 2 - (int) ss.getCs().m_radius;
                yCoords[1] = yCoords[0] + h;
                yCoords[2] = yCoords[0] + h;
            } else if (coord[1] < 0) { //DOWN
                yCoords[0] = -(int) y + HEIGHT / 2 + (int) ss.getCs().m_radius;
                yCoords[1] = yCoords[0] - h;
                yCoords[2] = yCoords[0] - h;
            } else {
                return yCoords;
            }
        } else if (coord[0] > 0) {//RIGHT
            yCoords[0] = -(int) y + HEIGHT / 2;
            yCoords[1] = yCoords[0] + (int) side / 2;
            yCoords[2] = yCoords[0] - (int) side / 2;
        } else if (coord[0] < 0) { //LEFT
            yCoords[0] = -(int) y + HEIGHT / 2;
            yCoords[1] = yCoords[0] + (int) side / 2;
            yCoords[2] = yCoords[0] - (int) side / 2;
        }
        return yCoords;
    }

}
