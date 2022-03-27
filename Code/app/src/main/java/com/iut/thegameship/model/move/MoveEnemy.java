package com.iut.thegameship.model.move;

import com.iut.thegameship.model.collider.ColliderInfo;
import com.iut.thegameship.model.collider.ICollider;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Speed;
import com.iut.thegameship.util.input.ECommand;

public class MoveEnemy implements IMove {

    @Override
    public ColliderInfo move(IEntity e, ICollider c, ECommand key, Location l, Speed s) {

        //Location l = Location.cast(e);
        double nextx = l.getX();
        double nexty = l.getX();

        switch (key) {
            case LEFT :
                nextx -= s.getSpeedX();
                break;
            case RIGHT:
                nextx += s.getSpeedX();
                break;
        }

        //Et si ce n'est pas en collision, sa déplace l'entité
        ColliderInfo ci = c.isCollision(nextx, nexty, l.getHeight(), l.getWidth(), e.getId());
        if (!ci.IsCollision()) {
            l.setX(nextx);
            l.setY(nexty);
        }
        return ci;
    }
}
