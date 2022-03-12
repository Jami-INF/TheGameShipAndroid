package com.iut.thegameship.model.game;

import com.iut.thegameship.model.entity.EntityFabric;
import com.iut.thegameship.model.entity.EntityManager;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.util.loop.*;

import java.util.Set;

public class Level implements IEntityCollection, ILifeCycle, IObserver {

    private final Loop loop;
    private Timer timer1;
    private Timer timer2;
    private Timer timer3;

    private final EntityManager entityManager = new EntityManager();
    private final EntityFabric entityFabric = new EntityFabric();

    private IEntity player;
    public IEntity getPlayer() { return player; }

    @Override public Set<IEntity> getEntityCollection() {
        return entityManager.getEntityCollection();
    }

    private int score ;
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    private double widthWindow;
    private double heightWindow;

    public Level(Loop loop, double widthWindow, double heightWindow) {
        this.loop = loop;
        this.widthWindow = widthWindow;
        this.heightWindow = heightWindow;
    }

    @Override
    public void init() {
        timer1 = new Timer(loop);
        timer2 = new Timer(loop);
        timer3 = new Timer(loop);

        player = (entityFabric.createPlayer("Vaisseau", "spaceship", 60, 60, 3 , 0, 0, 10, 0));
        entityManager.addEntity(player);
    }

    @Override
    public void update() {
    }

    @Override
    public void start() {
        loop.subscribe(timer1);
        loop.subscribe(timer2);
        loop.subscribe(timer3);
        loop.subscribe(this);
    }

    @Override
    public void exit() {
        loop.unsubscribeAll();
    }
}