package com.thegameship.model.move;

import com.thegameship.model.entity.componement.Location;
import com.thegameship.model.entity.componement.Speed;
import com.thegameship.model.entity.IEntity;
import com.thegameship.model.util.input.ECommand;
import com.thegameship.model.collider.ColliderInfo;
import com.thegameship.model.collider.ICollider;

public interface IMove {

    ColliderInfo move(IEntity e, ICollider c, ECommand key, Location l, Speed s, double heightWindow, double widthWindow) ;
}
