package com.iut.thegameship.model.collider;

import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.EComponementType;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Shoot;

import java.util.Set;
import java.util.UUID;

public class Collider implements ICollider { // Document : http://sdz.tdct.org/sdz/eorie-des-collisions.html

    private final Set<IEntity> entities;
    //Mettre la taille de la fenêtre dans le constructeur

    public Collider(Set<IEntity> entities) {
        this.entities = entities;
    }
    //La collision ne doit pas porter ses propres informations
    @Override
    public ColliderInfo isCollision(double nextX, double nextY, double height, double width, UUID id, double heightWindow, double widthWindow) {
        boolean scene = isCollisionScene(nextX, nextY, height, width, heightWindow, widthWindow);
        IEntity e = isCollisionEntity(nextX, nextY, height, width, id);
        return new ColliderInfo(scene, e);
    }

    protected boolean isCollisionScene(double nextX, double nextY, double height, double width, double heightWindow, double widthWindow) {
        return ((nextY <= 0) || (nextX <= 0) || (nextY + height >= heightWindow) || (nextX + width >= widthWindow)); //trop haut || trop à gauche || trop bas || trop à droite
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
                if (!((x2 >= nextX + width) || (x2 + width2 <= nextX) || (y2 >= nextY + height) || (y2 + height2 <= nextY)))   // trop à droite || trop à gauche || trop en bas || trop en haut
                    return e2;
            }
        }
        return null; //Pas de Collision
    }
}


