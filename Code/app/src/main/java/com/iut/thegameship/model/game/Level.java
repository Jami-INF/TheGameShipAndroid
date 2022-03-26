package com.iut.thegameship.model.game;

import com.iut.thegameship.model.collider.Collider;
import com.iut.thegameship.model.collider.ColliderEnemy;
import com.iut.thegameship.model.collider.ColliderInfo;
import com.iut.thegameship.model.collider.ICollider;
import com.iut.thegameship.model.entity.EntityFabric;
import com.iut.thegameship.model.entity.EntityManager;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.EComponementType;
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

    private final EntityManager entityManager = new EntityManager();
    private final EntityFabric entityFabric = new EntityFabric();

    private IEntity player;
    public IEntity getPlayer() { return player; }

    @Override public Set<IEntity> getEntityCollection() {
        return entityManager.getEntityCollection();
    }

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
        player = entityFabric.createPlayer("Vaisseau", "spaceship", widthWindow/6, widthWindow/6, 3 , widthWindow/2 - widthWindow/12, heightWindow - 3*(widthWindow/6), 20, 0);
        entityManager.addEntity(player);
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
                    Life.cast(e).setDead(true);
                    if (ci.getEntity() != null) {
                        Life.cast(ci.getEntity()).decreaseHp();
                    }
                }
            }
        }
    }

    @Override
    public void update() {
        try {
            List<IEntity> entitiesToBurn = new ArrayList<>();

            if (timer.getTimer() >= 10000) {
                System.out.println("Shoot");
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
                }

                if (e.isTypeOf(EComponementType.Life)) {
                    if (Life.cast(e).isDead()) {
                        entitiesToBurn.add(e);
                    }
                }
            }
            for (IEntity e : entitiesToBurn) {
                entityManager.removeEntity(e);
            }
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }

    private void updateEnemy(IEntity e) {
        /*IEntity player = getPlayer();
        Location l = Location.cast(e);
        if(getPlayer() != null){
            l = Location.cast(player);
        }
        ColliderInfo ci = moveEnemy.move(e, colliderEnemy, ECommand.LEFT, l, Speed.cast(e), heightWindow, widthWindow);
        if (timer2.getTimer() >= timer) {
            //createShoot(e.getId(), Location.cast(e), ECommand.LEFT, timer);
            timer2.resetTimer();
        }
        if(ci.IsCollision() && ci.getEntity() == null){
            entityManager.removeEntity(e);
        }*/
    }

    private void createNewWave(int min, int max, long timer) {
        /*double height = 70;
        double width = height;
        if (timer3.getTimer() >= timer) {
            double nbEnemy = Math.random() * (max - min + 1) + min;     //Math.random() * (max - min + 1) + min
            double ydiff = heightWindow / nbEnemy;

            for (int i = 0; i < nbEnemy; i++) {
                double x = (widthWindow + width - 20);
                double y = ((ydiff * i) - height);
                entityManager.addEntity(entityFabric.createEnemy(x, y, height, width));
            }
            timer3.resetTimer();
        }*/
    }

    @Override
    public void start() {
        loop.subscribe(timer);
        loop.subscribe(this);
    }

    @Override
    public void pause() {
        loop.StopLoop();
        return;
    }

    @Override
    public void resume() {
        loop.RestartLoop();
        return;
    }

    @Override
    public void exit() {
        loop.unsubscribeAll();
    }
}
