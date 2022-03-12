package com.iut.thegameship.model.game;

import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.util.loop.Loop;

import java.util.Set;

public class World implements IEntityCollection, ILifeCycle {

    public final Loop loop;
    private final Thread thread;

    Level currentLevel;

    public int getScore() {
        return currentLevel.getScore();
    }
    public Level getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public Set<IEntity> getEntityCollection() {
        return currentLevel.getEntityCollection();
    }

    public IEntity getPlayer() {
        return currentLevel.getPlayer();
    }

    public World(double widthWindow, double heightWindow) {

        loop = new Loop(20);    //Temps d'attente entre chaque actualisation de sprite du joueur et déplacement joueur
        thread = new Thread(loop);

        currentLevel = new Level(loop, widthWindow, heightWindow);
    }

    //Init, instancie les entité ou tout autre chose
    @Override
    public void init() {
        currentLevel.init();
    }

    @Override
    public void start() {
        thread.start();
        currentLevel.start();
    }

    @Override
    public void exit() {
        currentLevel.exit();
        loop.StopLoop();
        thread.stop();  //TODO: Voir si il n'y a pas un autre moyen car deprecated
    }
}
