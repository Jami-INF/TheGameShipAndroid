package com.iut.thegameship.model.collider;

import com.iut.thegameship.model.entity.IEntity;

import java.util.Set;
import java.util.UUID;

public class ColliderEnemy extends Collider {
    private final double widthWindow;
    private final double heightWindow;

    public ColliderEnemy(Set<IEntity> entities, double widthWindow, double heightWindow) {
        super(entities, widthWindow, heightWindow);
        this.widthWindow = widthWindow;
        this.heightWindow = heightWindow;
    }

    @Override
    public ColliderInfo isCollision(double nextX, double nextY, double height, double width, UUID id) {
        boolean scene = isCollisionScene(nextX, nextY, height, width);
        IEntity e = super.isCollisionEntity(nextX, nextY, height, width, id);
        return new ColliderInfo(scene, e);
    }

    protected boolean isCollisionScene(double nextX, double nextY, double height, double width) {
        double maxY = heightWindow + height*2;
        double minY = -(height*2);
        return ((nextX <= 0) || (nextY <= minY) || (nextY + height >= maxY) || (nextX + width >= widthWindow));
    }
}

