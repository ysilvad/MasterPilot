/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boundary;

import Control.ToCreateWorld;
import Control.ToShoot;
import Control.ToTurn;
import Entity.Bullet;
import Entity.Planet;
import Entity.SpaceShip;
import fr.umlv.zen3.Application;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

/**
 *
 * @author Maravillas
 */
public class BoundarySpaceShip {

    boolean si = false;
    Bullet bullet;

    public void init(int WIDTH, int HEIGHT) {

        float timeStep = 1.0f / 60f;
        int velocityIteration = 6;
        int positionIteration = 2;
        ToCreateWorld toCreateWorld = new ToCreateWorld();
        World world = toCreateWorld.createWorld();
        ArrayList<Vec2> starts = toCreateWorld.createStarts(WIDTH, HEIGHT);
        ArrayList<Planet> planets = toCreateWorld.createPlanets(WIDTH, HEIGHT);
        SpaceShip spaceShip = toCreateWorld.createSpaceShip(WIDTH, HEIGHT);
        ArrayList<Bullet> bullets;
        ToTurn turn = new ToTurn(spaceShip);
        ToShoot shoot = new ToShoot();

        //Window
        Application.run("Master Pilot", WIDTH, HEIGHT, turn, shoot, context -> {
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
                    graphics.translate(-x + WIDTH / 2, -y + HEIGHT / 2);

                    //Paint starts
                    graphics.setColor(Color.WHITE);
                    for (Vec2 point : starts) {
                        graphics.fill(new Ellipse2D.Float(point.x, point.y, 4, 4));
                    }

                    //Planet
                    for (Planet planet : planets) {                        
                    graphics.setColor(planet.getColor());
                        planet.getBody().setLinearVelocity(new Vec2(0, 0));
                        graphics.fill(new Ellipse2D.Float(planet.getBody().getPosition().x - planet.getCs().m_radius, planet.getBody().getPosition().y - planet.getCs().m_radius, planet.getCs().m_radius * 2, planet.getCs().m_radius * 2));
                    }
                    //Spaceship
                    graphics.setColor(Color.RED);
                    graphics.fill(new Polygon(turn.paintX(turn.getTurn(), x, y, WIDTH, HEIGHT), turn.paintY(turn.getTurn(), x, y, WIDTH, HEIGHT), 3));
                    //Apply Linear Impulse
                    if (turn.getTurn()[0] == 0 & turn.getTurn()[1] == 0) {
                        spaceShip.getBody().setLinearVelocity(new Vec2(0, 0));
                    } else {

                        spaceShip.getBody().applyLinearImpulse(new Vec2(turn.getTurn()[0], turn.getTurn()[1]), spaceShip.getBody().getWorldPoint(spaceShip.getBody().getWorldCenter()));
                    }
                    /*
                     //To Shoot
                     graphics.setColor(Color.YELLOW);
                     if (shoot.isBullet()) {
                     // for (Bullet bullet : bullets) {
                     Bullet bullet = bullets.get(1);
                     graphics.fill(new Ellipse2D.Float(bullet.getBody().getPosition().x * 2, bullet.getBody().getPosition().y - bullet.getBody().getPosition().y, bullet.getCs().m_radius * 2, bullet.getCs().m_radius * 2));
                     shoot.setBullet(true);
                     bullet.getBody().applyLinearImpulse(new Vec2(shoot.getDirection()[0], shoot.getDirection()[1]), bullet.getBody().getWorldPoint(bullet.getBody().getWorldCenter()));
                     // }                       
                     }
                     */
                    if (shoot.isBullet()) {
                        bullet = toCreateWorld.createBullet((int) spaceShip.getBody().getWorldCenter().x, (int) spaceShip.getBody().getWorldCenter().y, new Vec2(shoot.getDirection()[0], shoot.getDirection()[1]));
                        System.out.println(spaceShip.getBody().getPosition());
                        shoot.setBullet(false);
                        si = true;
                    }
                    if (si) {
                        graphics.fill(new Ellipse2D.Float(bullet.getBody().getWorldCenter().x, bullet.getBody().getWorldCenter().y, bullet.getCs().m_radius * 2, bullet.getCs().m_radius * 2));
                        bullet.getBody().applyLinearImpulse(bullet.getDirection(), bullet.getBody().getWorldPoint(bullet.getBody().getWorldCenter()));

                    }

                    //Step and dispose
                    graphics.dispose();
                    world.step(timeStep, velocityIteration, positionIteration);
                });
            }
        });
    }
}
