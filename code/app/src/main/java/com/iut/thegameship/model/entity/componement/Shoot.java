package com.iut.thegameship.model.entity.componement;

import com.iut.thegameship.util.input.ECommand;
import java.util.UUID;

public class Shoot extends Componement {

    private UUID ownerId;
        public UUID getOwnerId() { return ownerId; }
        public void setOwnerId(UUID ownerId) { this.ownerId = ownerId; }

    private final ECommand direction;
        public ECommand getDirection() { return direction; }

    public Shoot(UUID ownerId, ECommand direction){
        super(EComponementType.Shoot);
        setOwnerId(ownerId);
        this.direction = direction;
    }

    public static Shoot cast(IHasComponements e){
            return (Shoot) e.getComponement(EComponementType.Shoot);
    }
}
