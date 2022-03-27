package com.iut.thegameship.model.game;

import com.iut.thegameship.UI.Activities.GameActivity;
import com.iut.thegameship.model.collider.Collider;
import com.iut.thegameship.model.collider.ColliderEnemy;
import com.iut.thegameship.model.collider.ColliderInfo;
import com.iut.thegameship.model.collider.ICollider;
import com.iut.thegameship.model.entity.EEntityType;
import com.iut.thegameship.model.entity.EntityFabric;
import com.iut.thegameship.model.entity.EntityManager;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.Life;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Shoot;
import com.iut.thegameship.model.entity.componement.Speed;
import com.iut.thegameship.model.move.IMove;
import com.iut.thegameship.model.move.Move;
import com.iut.thegameship.model.move.MoveEnemy;
import com.iut.thegameship.model.move.MoveShoot;
import com.iut.thegameship.util.input.ECommand;
import com.iut.thegameship.util.loop.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Level implements IEntityCollection, ILifeCycle, IObserver {

    private final Loop loop;
    private Timer timer;
    private Timer timer2;
    private Timer timer3;

    private final EntityManager entityManager = new EntityManager();
    private final EntityFabric entityFabric = new EntityFabric();

    private IEntity player;
    public IEntity getPlayer() { return player; }

    private IEntity enemy;
    public IEntity getEnemy() { return enemy; }

    @Override public Set<IEntity> getEntityCollection() {
        return entityManager.getEntityCollection();
    }

    List<IEntity> entitiesToRemove = new ArrayList<>();

    private IMove move = new Move();
    private IMove moveShoot = new MoveShoot();
    private IMove moveEnemy = new MoveEnemy();

    private final ICollider collider;
    private final ICollider colliderEnemy;

    private int score ;
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    private double widthWindow;
    private double heightWindow;

    public Level(Loop loop, double widthWindow, double heightWindow) {
        this.loop = loop;
        this.widthWindow = widthWindow;
        this.heightWindow = heightWindow;

        collider = new Collider(getEntityCollection(), widthWindow, heightWindow);
        colliderEnemy = new ColliderEnemy(getEntityCollection(), widthWindow, heightWindow);
    }

    @Override
    public void init() {
        timer = new Timer(loop);
        timer2 = new Timer(loop);
        timer3 = new Timer(loop);

        player = entityFabric.createPlayer("Vaisseau", "spaceship", widthWindow/6, widthWindow/6, 3 , widthWindow/2 - widthWindow/12, heightWindow - 3*(widthWindow/6), 10, 0);
        entityManager.addEntity(player);

        enemy = entityFabric.createEnemy("enemy", "enemy", widthWindow/6, widthWindow/6, 5, widthWindow/2 - widthWindow/12, heightWindow - 3.5*(widthWindow/2));
        entityManager.addEntity(enemy);
    }

    public void updatePlayer(ECommand key) {
        IEntity e = getPlayer();
        move.move(e, collider, key, Location.cast(e), Speed.cast(e));
    }

    private void createShoot(UUID id, Location l, ECommand key) {
        entityManager.addEntity(entityFabric.createShoot(id, l, key));
    }

    private void updateShoot(IEntity e) {
        for (IEntity e2 : getEntityCollection()) {
            if (e2.getId().equals(e.getId())) {
                ColliderInfo ci = move.move(e, colliderEnemy, Shoot.cast(e).getDirection(), Location.cast(e), Speed.cast(e));
                if (ci.IsCollision()) {

                    if (ci.getEntity() != null) {
                        Life.cast(ci.getEntity()).decreaseHp();
                        setScore(getScore() + 1);
                    }
                    entitiesToRemove.add(e);
                    //System.out.println(ci.toString());
                }
            }
        }
    }

    private void updateEnemy(IEntity e) {
        if (timer2.getTimer() <= 5000) {
            moveEnemy.move(e, colliderEnemy, ECommand.LEFT, Location.cast(e), Speed.cast(e));
        } else if (timer2.getTimer() >= 5000 && timer2.getTimer() <= 15000) {
            moveEnemy.move(e, colliderEnemy, ECommand.RIGHT, Location.cast(e), Speed.cast(e));
        } else if (timer2.getTimer() >= 15000 && timer2.getTimer() <= 25000) {
            moveEnemy.move(e, colliderEnemy, ECommand.LEFT, Location.cast(e), Speed.cast(e));
        } else {
            timer2.setTimer(5000);
        }
        timer2.update();
        if (timer3.getTimer() >= 3000) {
            createShoot(e.getId(), Location.cast(e), ECommand.DOWN);
            timer3.resetTimer();
        }
        timer3.update();
    }

    @Override
    public void update() {
        entitiesToRemove.clear();
        try {
            if (timer.getTimer() >= 2000) {
                createShoot(player.getId(), Location.cast(player), ECommand.UP);
                timer.resetTimer();
            }
            for (IEntity e : new ArrayList<>(getEntityCollection())) {
                switch (e.getEntityType()) {
                    case Shoot:
                        updateShoot(e);
                        break;
                    case Enemy:
                        updateEnemy(e);
                        break;
                    case Player:
                        if (Life.cast(e).getHp() <= 0) {

                        }

                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }

        try {
            for (IEntity e : entitiesToRemove) {
                if (entitiesToRemove.contains(e)) {
                    entityManager.removeEntity(e);
                    //System.out.println("remove"+e.getName());
                    entitiesToRemove.remove(e);
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }


    @Override
    public void start() {
        loop.subscribe(timer);
        loop.subscribe(this);
    }

    @Override
    public void pause() {
        loop.StopLoop();
    }

    @Override
    public void resume() {
        loop.RestartLoop();
    }

    @Override
    public void exit() {
        loop.unsubscribeAll();
    }
}
