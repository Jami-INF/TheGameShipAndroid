package com.iut.thegameship.model.collider;

import com.iut.thegameship.model.entity.IEntity;

import java.util.Set;
import java.util.UUID;

public class ColliderEnemy extends Collider {
    public ColliderEnemy(Set<IEntity> entities) {
        super(entities);
    }

    @Override
    public ColliderInfo isCollision(double nextX, double nextY, double height, double width, UUID id, double heightWindow, double widthWindow) {
        boolean scene = isCollisionScene(nextX, nextY, height, width, heightWindow, widthWindow);
        IEntity e = super.isCollisionEntity(nextX, nextY, height, width, id);
        return new ColliderInfo(scene, e);
    }

    protected boolean isCollisionScene(double nextX, double nextY, double height, double width, double heightWindow, double widthWindow) {
        double maxX = widthWindow + width*2; //Ajoute du vide à droite pour pouvoir faire spawn les entités en dehors de la window
        double minX = -(width*2); //Ajoute du vide à gauche
        return ((nextY <= 0) || (nextX <= minX) || (nextY + height >= heightWindow) || (nextX + width >= maxX)); //trop haut || trop à gauche || trop bas || trop à droite
    }

}
