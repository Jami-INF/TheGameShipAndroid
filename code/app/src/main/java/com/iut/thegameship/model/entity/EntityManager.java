package com.iut.thegameship.model.entity;

import com.iut.thegameship.model.entity.componement.Sprite;
import com.iut.thegameship.model.game.IEntityCollection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EntityManager implements IEntityCollection {

    private final Set<IEntity> entities;

    @Override
    public Set<IEntity> getEntityCollection() {
        return entities;
    }

    public EntityManager() { entities = new HashSet<>(); }

    //Used Entities
    public void addEntity(IEntity e) {
        Sprite.cast(e).setVisible(true);
        entities.add(e);
    }

    public void removeEntity(IEntity e) {
        Sprite.cast(e).setVisible(false);
        entities.remove(e);
    }

    public void removeEntity(String name) {
        removeEntity(getEntityBy(name));
    }

    private IEntity getEntityBy(String name) {
        Iterator it = entities.iterator();
        while (it.hasNext()) {
            IEntity e = (IEntity) it.next();
            if (e.getName().equals(name)) {
                return e;
            }
        }
        newError(name);
        return null;
    }

    private void newError(String name) {
        System.err.println("Il n'y a pas d'entité de nom : \"" + name + "\"");
    }

    private void newError(EEntityType type) {
        System.err.println("Il n'y a pas d'entité de type : \"" + type + "\"");
    }
}
