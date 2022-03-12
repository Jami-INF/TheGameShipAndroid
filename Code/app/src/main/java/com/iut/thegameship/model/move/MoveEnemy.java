package com.iut.thegameship.model.move;

import com.iut.thegameship.model.collider.ColliderInfo;
import com.iut.thegameship.model.collider.ICollider;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Speed;
import com.iut.thegameship.util.input.ECommand;

public class MoveEnemy implements IMove {

    @Override
    public ColliderInfo move(IEntity e, ICollider c, ECommand key, Location lPlayer, Speed senemy, double heightWindow, double widthWindow) {

        Location lenemy = Location.cast(e);

        double nexty = lenemy.getX();
        switch (key) {
            case DOWN :
                nexty += senemy.getSpeedY();
                break;
        }

        double nextx = lenemy.getX();

        //Et si ce n'est pas en collision, sa déplace l'entité
        ColliderInfo ci = c.isCollision(nextx, nexty, lenemy.getHeight(), lenemy.getWidth(), e.getId(), heightWindow, widthWindow);
        if (!ci.IsCollision()) {
            lenemy.setX(nextx);
            lenemy.setY(nexty);
        }
        return ci;
    }
}
