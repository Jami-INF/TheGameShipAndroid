package com.thegameship.model.move;

import com.thegameship.model.collider.ColliderInfo;
import com.thegameship.model.collider.ICollider;
import com.thegameship.model.entity.IEntity;
import com.thegameship.model.entity.componement.Location;
import com.thegameship.model.entity.componement.Speed;
import com.thegameship.model.util.input.ECommand;

public class MoveEnemy implements IMove {
    @Override
    public ColliderInfo move(IEntity e, ICollider c, ECommand key, Location lPlayer, Speed senemy, double heightWindow, double widthWindow) { //Ici la Location est celle du joueur
        Location lenemy = Location.cast(e);

        // X
        double nextx = lenemy.getX();
        switch (key) {
            case RIGHT :
                nextx += senemy.getSpeedX();
                break;
            default :
                nextx -= senemy.getSpeedX(); //Par défaut c'est vers la gauche
                break;
        }

        // Y
        double nexty = lenemy.getY();
        /* Code qui permet faire à l'ennemie suivre le joueur
        if ((lenemy.getY() - lPlayer.getY()) != 0) { //Si c'est égale alors pas besoin de bouger
            if ((lenemy.getY() - lPlayer.getY()) < 0) { //Si le joueur est plus bas que l'ennemie c'est négatif et inversement
                nexty += senemy.getSpeedY();
            } else {
                nexty -= senemy.getSpeedY();
            }
        }*/

        //Et si ce n'est pas en collision, sa déplace l'entité
        ColliderInfo ci = c.isCollision(nextx, nexty, lenemy.getHeight(), lenemy.getWidth(), e.getId(), heightWindow, widthWindow);
        if (!ci.IsCollision()) {
            lenemy.setX(nextx);
            lenemy.setY(nexty);
        }
        return ci;
    }
}
