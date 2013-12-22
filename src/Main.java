
import Boundary.BoundarySpaceShip;
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
        int WIDTH = 800;
        int HEIGHT = 600;

        BoundarySpaceShip boundarySpaceShip = new BoundarySpaceShip();
        boundarySpaceShip.init(WIDTH, HEIGHT);  
    }
}
