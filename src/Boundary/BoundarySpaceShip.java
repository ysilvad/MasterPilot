/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Boundary;

import Control.ToCreateWorld;
import Control.ToTurn;
import Entity.Planet;
import Entity.SpaceShip;
import fr.umlv.zen3.Application;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

/**
 *
 * @author Maravillas
 */
public class BoundarySpaceShip{
    
    public void init(int WIDTH, int HEIGHT){
        
        float timeStep = 1.0f / 60f;
        int velocityIteration = 6;
        int positionIteration = 2;
        ToCreateWorld toCreateWorld = new ToCreateWorld();
        World world = toCreateWorld.createWorld();
        ArrayList<Vec2> starts = toCreateWorld.createStarts(WIDTH, HEIGHT);
        //ArrayList<Planet> planets = toCreateWorld.createPlanets(WIDTH, HEIGHT);
        SpaceShip spaceShip = toCreateWorld.createSpaceShip(WIDTH, HEIGHT);
        int[] xCoords = new int[4];
        int[] yCoords = new int[4];
        double side = (Math.sqrt(3) * spaceShip.getCs().m_radius);
        int h = (int) Math.sqrt((side * side) + (side / 2) * (side / 2));
        ToTurn turn = new ToTurn();

        
                //Window
        Application.run("Master Pilot", WIDTH, HEIGHT, turn, context -> {

            for (;;) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                context.render(graphics -> {
                    //Background
                    graphics.setColor(Color.BLACK);
                    graphics.fill(new Rectangle2D.Float(0, 0, WIDTH, HEIGHT));
                    
                    //Motion effect
                    float x = spaceShip.getBody().getPosition().x;
                    float y = spaceShip.getBody().getPosition().y;
                    graphics.translate(-x, y);
                    
                    //Paint starts
                    graphics.setColor(Color.WHITE);
                    for (Vec2 point : starts) {
                        graphics.fill(new Ellipse2D.Float(point.x, point.y, 4, 4));
                    }
                    
                    //Planet
                  /*  graphics.setColor(Color.GREEN);
                    for (Planet planet : planets) {
                     // graphics.fill(new Ellipse2D.Float(planet.getBody().getPosition().x + (int) Math.sqrt(planet.getCs().m_radius*planet.getCs().m_radius+planet.getCs().m_radius*planet.getCs().m_radius), planet.getBody().getPosition().y + (int) Math.sqrt(planet.getCs().m_radius*planet.getCs().m_radius+planet.getCs().m_radius*planet.getCs().m_radius), planet.getCs().m_radius*2, planet.getCs().m_radius*2));
                        
                        graphics.fill(new Ellipse2D.Float(planet.getBody().getPosition().x, planet.getBody().getPosition().y,  planet.getCs().m_radius*2,  planet.getCs().m_radius*2));
                    }
                    */
                    //Spaceship
                    
                    xCoords[0] = (int)x + WIDTH / 2;
                    yCoords[0] = -(int)y + HEIGHT / 2 - (int) spaceShip.getCs().m_radius;
                    xCoords[1] = xCoords[0] - (int)(side / 2);
                    yCoords[1] = yCoords[0] + h;
                    xCoords[2] = xCoords[0] + (int)(side / 2);
                    yCoords[2] = yCoords[0] + h;
                    graphics.setColor(Color.YELLOW);
                    graphics.fillOval(xCoords[0]-(int)(spaceShip.getCs().m_radius), yCoords[0], h+5, h+5);
                    graphics.setColor(Color.red);
                    graphics.fill(new Polygon(xCoords, yCoords, 3));
                                       
                    
                    //Apply Linear Impulse
                    if(turn.getTurn()[0]==0 & turn.getTurn()[1]==0){
                        spaceShip.getBody().setLinearVelocity(new Vec2(0, 0));
                    }else{
                        spaceShip.getBody().applyLinearImpulse(new Vec2(turn.getTurn()[0], turn.getTurn()[1]), spaceShip.getBody().getWorldPoint(spaceShip.getBody().getWorldCenter()));
                    }
                     //spaceShip.getBody().applyForceToCenter(new Vec2(50f, 50f));
                    //Step and dispose
                    graphics.dispose();
                    world.step(timeStep, velocityIteration, positionIteration);
                });
            }
        });
    }
    
}
