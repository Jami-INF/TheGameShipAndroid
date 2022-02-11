package com.iut.thegameship.model.entity;

import com.iut.thegameship.model.entity.componement.*;
import com.iut.thegameship.model.util.input.ECommand;

import java.util.UUID;

public class EntityFabric {

    private long numberShoot = 0;

    private long getShootNumber() {
        numberShoot++;
        return numberShoot;
    }

    private long numberEnemy = 0;

    private long getEnemyNumber() {
        numberEnemy++;
        return numberEnemy;
    }

    public Entity createPlayer(String name, String sprite, double height, double width, double hp, double x, double y, float speedX, float speedY) {
        Entity e = new Entity(name, EEntityType.Player);
        e.addComponement(new Sprite(sprite));
        e.addComponement(new Location(x, y, height, width));
        e.addComponement(new Life(hp));
        e.addComponement(new Speed(speedX, speedY));
        return e;
    }

    public Entity createEnemy(String name, String sprite, double height, double width, double hp, double x, double y) {
        Entity e = createPlayer(name, sprite, height, width, hp, x, y, 3, 3);
        e.setEntityType(EEntityType.Enemy);
        return e;
    }

    public Entity createEnemy(double x, double y, double height, double width) {
        String name = "Shoot" + getEnemyNumber();
        return createEnemy(name, "/Sprites/Enemy.png", height, width, 5, x, y);
    }

    public IEntity createShoot(UUID ownerId, Location l, ECommand direction) {
        //Pour la direction du tir
        double heightShoot = 10;
        double widthShoot = 30;
        double xShoot = l.getX();
        double yShoot = l.getY() + l.getHeight() / 2 - heightShoot / 2;
        switch (direction) {
            case RIGHT :
                xShoot += l.getWidth() + 5;
                break;
            case LEFT :
                xShoot -= l.getWidth() - 5;
                break;
        }

        String name = "Shoot" + getShootNumber() + "_" + ownerId.toString();
        Entity e = new Entity(name, EEntityType.Shoot);
        e.addComponement(new Sprite(null));
        e.addComponement(new Location(xShoot, yShoot, heightShoot, widthShoot));
        e.addComponement(new Life(1));
        e.addComponement(new Speed(15, 15));
        e.addComponement(new Shoot(ownerId, direction));

        return e;
    }
}
