/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import Entity.Planet;
import Entity.SpaceShip;
import java.util.ArrayList;
import java.util.Random;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 *
 * @author Maravillas
 */
public class ToCreateWorld {
    
        World world;
    //World
    public World createWorld(){
        world = new World(new Vec2(0, 0));
        return world;
    }
    
    public SpaceShip createSpaceShip(int WIGTH,int HEIGHT){
        SpaceShip spaceShip = new SpaceShip();
        BodyDef bd = spaceShip.createBodyDef(WIGTH / 2, HEIGHT / 2, BodyType.DYNAMIC, 15);
        FixtureDef fd = spaceShip.createBodyFixture(1, 1, 1);
        spaceShip.createBody(bd, fd, world);
        return spaceShip;
    }
    
    public ArrayList createStarts(int WIDTH, int HEIGHT){
        ArrayList<Vec2> starts = new ArrayList<>();
        Random random = new Random(0);
        for (int i = 0; i < 5000; i++) {
            Vec2 start = new Vec2(random.nextInt(WIDTH*10), random.nextInt(HEIGHT*10));
            starts.add(start);
        }        
        return starts;
    }
    
    public ArrayList createPlanets(int WIDTH, int HEIGHT){
        ArrayList<Planet> planets = new ArrayList<>();
        Random random = new Random(0);
        for (int i = 0; i < 500; i++) {
            Planet planet = new Planet();                
            BodyDef bd = planet.createBodyDef(random.nextInt(WIDTH*10), random.nextInt(HEIGHT*10), BodyType.DYNAMIC, random.nextInt(200));
            FixtureDef fd = planet.createBodyFixture(1, 1, 1); 
            planet.createBody(bd, fd, world);
            planets.add(planet);
            
        }
        return planets;
    }
        
}
