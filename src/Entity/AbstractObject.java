/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import org.jbox2d.dynamics.*;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Maravillas
 */
public abstract class AbstractObject {

    private BodyDef bd = new BodyDef();
    private CircleShape cs = new CircleShape();
    private FixtureDef fd = new FixtureDef();
    private Body body;

    public abstract void move();

    public BodyDef createBodyDef(float x, float y, BodyType type, float radius) {
        bd = new BodyDef();
        setPosition(x, y);
        setType(type);
        setRadius(radius);
        return bd;
    }

    public FixtureDef createBodyFixture(float density, float friction, float restitution) {
        fd = new FixtureDef();
        setFixture(density, friction, restitution);
        return fd;
    }

    public void createBody(BodyDef bd, FixtureDef fd, World world) {
        body = world.createBody(bd);
        body.createFixture(fd);
    }

    public void setPosition(float x, float y) {
        bd.position.set(x, y);
    }

    public void setType(BodyType type) {
        bd.type = type;
    }

    public void setRadius(float radius) {
        cs.m_radius = radius * 1f;
    }

    public void setFixture(float density, float friction, float restitution) {
        fd.shape = cs;
        fd.density = density * 1f;
        fd.friction = friction * 1f;
        fd.restitution = restitution * 1f;
    }

    public void setDensity(int density) {
        fd.density = density * 1f;
    }

    public void setFriction(int friction) {
        fd.friction = friction * 1f;
    }

    public void setRestitution(int restitution) {
        fd.restitution = restitution * 1f;
    }

    public BodyDef getBd() {
        return bd;
    }

    public void setBd(BodyDef bd) {
        this.bd = bd;
    }

    public CircleShape getCs() {
        return cs;
    }

    public FixtureDef getFd() {
        return fd;
    }

    public Body getBody() {
        return body;
    }
    

}
