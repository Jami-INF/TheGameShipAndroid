package com.iut.thegameship.model.collider;

import java.util.UUID;

public interface ICollider {
    ColliderInfo isCollision(double nextX, double nextY, double height, double width, UUID id);
}

