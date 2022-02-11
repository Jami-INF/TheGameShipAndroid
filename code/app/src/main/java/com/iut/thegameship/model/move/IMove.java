package com.iut.thegameship.model.move;

import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Speed;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.util.input.ECommand;
import com.iut.thegameship.model.collider.ColliderInfo;
import com.iut.thegameship.model.collider.ICollider;

public interface IMove {

    ColliderInfo move(IEntity e, ICollider c, ECommand key, Location l, Speed s, double heightWindow, double widthWindow) ;
}
