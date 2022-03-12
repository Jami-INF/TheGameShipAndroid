package com.iut.thegameship.model.entity;

import com.iut.thegameship.model.entity.componement.Componement;
import com.iut.thegameship.model.entity.componement.EComponementType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Entity extends Componement implements IEntity {

    private UUID id;
        @Override public UUID getId(){ return id; }

    private String name;
        @Override public String getName() { return name; }

    private final Map<EComponementType, Componement> componements = new HashMap<>();
        @Override public void addComponement(Componement c){
            componements.put(c.getType(),c);
        }
        @Override public Componement getComponement(EComponementType type){
            return componements.get(type);
        }
        @Override public boolean isTypeOf(EComponementType type) {
            return componements.keySet().contains(type);
        }

    private EEntityType type;
        @Override public void setEntityType(EEntityType type) { this.type = type; }
        @Override public EEntityType getEntityType() { return type; }
        @Override public boolean isTypeOf(EEntityType type){
            return this.type.equals(type);
        }

    public Entity(String name, EEntityType type) {
        super(EComponementType.Entity);
        this.type = type;
        this.id = UUID.randomUUID();
        this.name = name;
    }

}
