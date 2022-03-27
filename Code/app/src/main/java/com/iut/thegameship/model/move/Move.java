package com.iut.thegameship.model.move;

import com.iut.thegameship.model.entity.componement.EComponementType;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Shoot;
import com.iut.thegameship.model.entity.componement.Speed;
import com.iut.thegameship.model.collider.ColliderInfo;
import com.iut.thegameship.model.collider.ICollider;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.util.input.ECommand;

import java.util.UUID;

public class Move implements IMove {

    @Override
    public ColliderInfo move(IEntity e, ICollider c, ECommand key, Location l, Speed s) {

        double nextx = l.getX();
        double nexty = l.getY();

        switch (key) {
            case LEFT:
                nextx -= s.getSpeedX();
                break;
            case RIGHT:
                nextx += s.getSpeedX();
                break;
            case UP:
                nexty -= s.getSpeedY();
                break;
            case DOWN:
                nexty += s.getSpeedY();
                break;
        }

        //Vérifie la collision
        UUID id = e.getId();
        /*if (e.isTypeOf(EComponementType.Shoot)) {
            id = Shoot.cast(e).getOwnerId();
        }*/

        //Et si ce n'est pas en collision, sa déplace l'entité
        ColliderInfo ci = c.isCollision(nextx, nexty, l.getHeight(), l.getWidth(), id, heightWindow, widthWindow);
        //if (!ci.IsCollision()) {
            l.setX(nextx);
            l.setY(nexty);
        //}
        return ci;
    }
}
