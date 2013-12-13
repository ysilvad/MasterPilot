
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import org.jbox2d.dynamics.*;

import fr.umlv.zen3.Application;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;

public class Main {

    public static void main(String[] args) {
        int WIDTH = 400;
        int HEIGHT = 300;

        //World
        float timeStep = 2.0f / 60f;
        int velocityIteration = 6;
        int positionIteration = 3;
        World world = new World(new Vec2(0, 0));

        //Starts
        ArrayList<Vec2> starts = new ArrayList<>();
        Random random = new Random(0);
        for (int i = 0; i < 5000; i++) {
            Vec2 start = new Vec2(random.nextInt(WIDTH*10), random.nextInt(HEIGHT*10));
            starts.add(start);
        }

        //Body=SpaceShip
        BodyDef bdSpaceShip = new BodyDef();
        bdSpaceShip.position.set(50, 50);
        bdSpaceShip.type = BodyType.DYNAMIC;

        CircleShape csSpacheShip = new CircleShape();
        csSpacheShip.m_radius = 25;

        FixtureDef fdSpaceShip = new FixtureDef();
        fdSpaceShip.shape = csSpacheShip;
        fdSpaceShip.density = 0.5f;
        fdSpaceShip.friction = 1f;
        fdSpaceShip.restitution = 1f;

        Body spaceShip = world.createBody(bdSpaceShip);
        spaceShip.createFixture(fdSpaceShip);

        int[] xCoords = new int[4];
        int[] yCoords = new int[4];
        int side = (int) (Math.sqrt(3) * csSpacheShip.m_radius);
        int h = (int) Math.sqrt((side * side) + (side / 2) * (side / 2));

        //Body=Planet
        BodyDef bdPlanet = new BodyDef();
        bdPlanet.position.set(WIDTH / 2, HEIGHT / 2);
        bdPlanet.type = BodyType.DYNAMIC;

        CircleShape csPlanet = new CircleShape();
        csPlanet.m_radius = 0.5f;

        FixtureDef fdPlanet = new FixtureDef();
        fdPlanet.shape = csPlanet;
        fdPlanet.density = 0.5f;
        fdPlanet.friction = 1f;
        fdPlanet.restitution = 1f;

        Body planet = world.createBody(bdPlanet);
        planet.createFixture(fdPlanet);

        //Window
        Application.run("Colors", WIDTH, HEIGHT, context -> {

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
                    float x = spaceShip.getPosition().x;
                    float y = spaceShip.getPosition().y;
                    graphics.translate(-x, y);
                    
                    //Paint starts
                    graphics.setColor(Color.WHITE);
                    for (Vec2 point : starts) {
                        graphics.fill(new Ellipse2D.Float(point.x, point.y, 4, 4));
                    }
                    
                    //Planet
                    graphics.setColor(Color.LIGHT_GRAY);
                    graphics.fill(new Ellipse2D.Float(planet.getPosition().x, planet.getPosition().y, 200, 200));
                    
                    //Spaceship
                    graphics.setColor(Color.orange);
                    xCoords[0] = (int)x + WIDTH / 2;
                    yCoords[0] = -(int)y + HEIGHT / 2 - (int) csSpacheShip.m_radius;
                    xCoords[1] = xCoords[0] - (side / 2);
                    yCoords[1] = yCoords[0] + h;
                    xCoords[2] = xCoords[0] + side / 2;
                    yCoords[2] = yCoords[0] + h;
                    graphics.fill(new Polygon(xCoords, yCoords, 3));
                    
                    
                    //Apply Linear Impulse
                    spaceShip.applyLinearImpulse(new Vec2(50f, 0), spaceShip.getWorldPoint(spaceShip.getWorldCenter()));
                    
                    //Step and dispose
                    graphics.dispose();
                    world.step(timeStep, velocityIteration, positionIteration);
                });
            }
        });
    }
}
