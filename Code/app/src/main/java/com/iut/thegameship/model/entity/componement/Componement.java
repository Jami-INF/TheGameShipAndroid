package com.iut.thegameship.model.entity.componement;

public abstract class Componement {

    private EComponementType type;
        public EComponementType getType(){ return type; }

    public Componement(EComponementType type){
        this.type=type;
    }
}
