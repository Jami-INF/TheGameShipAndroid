package com.iut.thegameship.model.collider;

import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.EComponementType;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Shoot;

import java.util.Set;
import java.util.UUID;

public class Collider implements ICollider {
    private final Set<IEntity> entities;
    private final double widthWindow;
    private final double heightWindow;

    public Collider(Set<IEntity> entities, double widthWindow, double heightWindow) {
        this.entities = entities;
        this.widthWindow = widthWindow;
        this.heightWindow = heightWindow;
    }

    @Override
    public ColliderInfo isCollision(double nextX, double nextY, double height, double width, UUID id) {
        boolean scene = isCollisionScene(nextX, nextY, height, width);
        IEntity e = isCollisionEntity(nextX, nextY, height, width, id);
        return new ColliderInfo(scene, e);
    }

    protected boolean isCollisionScene(double nextX, double nextY, double height, double width) {
        return ((nextY <= 0) || (nextX <= 0) || (nextY + height >= heightWindow) || (nextX + width >= widthWindow));    //trop haut || trop à gauche || trop bas || trop à droite
    }

    protected IEntity isCollisionEntity(double nextX, double nextY, double height, double width, UUID id) {
        for (IEntity e2 : entities) {
            //Empêche que le joueur soit bloquer par ces propre tir
            boolean isAtOriginOfShoot = false;
            if (e2.isTypeOf(EComponementType.Shoot)) {
                isAtOriginOfShoot = id.equals(Shoot.cast(e2).getOwnerId());
            }
            if (!id.equals(e2.getId()) && !isAtOriginOfShoot) {
                Location l = Location.cast(e2);
                double x2 = l.getX();
                double y2 = l.getY();
                double height2 = l.getHeight();
                double width2 = l.getWidth();
                if (!((x2 >= nextX + width) || (x2 + width2 <= nextX) || (y2 >= nextY + height) || (y2 + height2 <= nextY))) {  //trop haut || trop à gauche || trop bas || trop à droite
                    return e2;
                }
            }
        }
        return null;
    }
}


