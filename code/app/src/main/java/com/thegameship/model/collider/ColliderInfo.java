package com.thegameship.model.collider;

import com.thegameship.model.entity.IEntity;

public class ColliderInfo {

    private final boolean isCollision;
    public boolean IsCollision() {
        return isCollision;
    }

    private final IEntity e;
    public IEntity getEntity() {
        return e;
    }

    public ColliderInfo(boolean isCollison, IEntity e) {
        this.e = e;
        this.isCollision = e != null || isCollison;  //Si e est attribué alors c'est en collision
    }

    @Override
    public String toString() {
        String entityName = (e != null) ? "null" : e.getName();
        return "isCollison : " + isCollision + "\nAvec l'entité : " + entityName + "\n";
    }
}
