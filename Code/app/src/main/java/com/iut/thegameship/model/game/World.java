package com.iut.thegameship.model.game;

import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.Life;
import com.iut.thegameship.util.loop.Loop;

import java.util.Set;

public class World implements IEntityCollection, ILifeCycle {

    public final Loop loop;
    private Thread thread;

    Level currentLevel;

    public int getScore() {
        return currentLevel.getScore();
    }
    public void setScore(int score) {
        currentLevel.setScore(score);
    }
    public int getLife() {
        return (int) Life.cast(currentLevel.getPlayer()).getHp();
    }
    public void setLife(int life) {
        Life.cast(currentLevel.getPlayer()).setHp(life);
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
        loop = new Loop(50);
        thread = new Thread(loop);

        currentLevel = new Level(loop, widthWindow, heightWindow); //Mettre le bon monde
    }

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
    public void pause() {
        currentLevel.pause();
        loop.StopLoop();
        thread.interrupt();
    }

    @Override
    public void resume(){
        thread = new Thread(loop);
        thread.start();
        loop.RestartLoop();
    }

    @Override
    public void exit() {
        currentLevel.exit();
        loop.StopLoop();
        thread.interrupt();
    }


}

