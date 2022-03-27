package com.iut.thegameship.model.move;

import com.iut.thegameship.model.collider.ColliderInfo;
import com.iut.thegameship.model.collider.ICollider;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Speed;
import com.iut.thegameship.util.input.ECommand;

import java.util.UUID;

public class MoveShoot implements IMove {

    @Override
    public ColliderInfo move(IEntity e, ICollider c, ECommand key, Location l, Speed s, double heightWindow, double widthWindow) {

        //Location l = Location.cast(e);
        double nexty = l.getX();
        double nextx = l.getX();

        switch (key) {
            case UP:
                nexty -= s.getSpeedY();
                break;
            case DOWN:
                nexty += s.getSpeedY();
                break;
        }

        UUID id = e.getId();

        //Et si ce n'est pas en collision, sa déplace l'entité
        ColliderInfo ci = c.isCollision(nextx, nexty, l.getHeight(), l.getWidth(), e.getId(), heightWindow, widthWindow);
        if (!ci.IsCollision()) {
            l.setX(nextx);
            l.setY(nexty);
        }
        return ci;
    }
}
