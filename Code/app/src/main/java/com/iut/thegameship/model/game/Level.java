package com.iut.thegameship.model.game;

import com.iut.thegameship.model.collider.Collider;
import com.iut.thegameship.model.collider.ColliderEnemy;
import com.iut.thegameship.model.collider.ColliderInfo;
import com.iut.thegameship.model.collider.ICollider;
import com.iut.thegameship.model.entity.EEntityType;
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
import com.iut.thegameship.util.input.ECommand;
import com.iut.thegameship.util.input.IInput;
import com.iut.thegameship.util.loop.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Level implements IEntityCollection, ILifeCycle, IObserver {

    private final Loop loop;
    private Timer timer1;
    private Timer timer2;
    private Timer timer3;

    private final IInput input;

    private final EntityManager entityManager = new EntityManager();
    private final EntityFabric entityFabric = new EntityFabric();

    private IEntity player;
    public IEntity getPlayer() { return player; }

    @Override public Set<IEntity> getEntityCollection() {
        return entityManager.getEntityCollection();
    }

    private IMove move = new Move();
    private IMove moveEnemy = new MoveEnemy();

    private final ICollider collider = new Collider(getEntityCollection());
    private final ICollider colliderEnemy = new ColliderEnemy(getEntityCollection());

    private int score ;
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    private double widthWindow;
    private double heightWindow;

    public Level(Loop loop, IInput input, double widthWindow, double heightWindow) {
        this.loop = loop;
        this.input = input;
        this.widthWindow = widthWindow;
        this.heightWindow = heightWindow;
    }

    @Override
    public void init() {
        timer1 = new Timer(loop);
        timer2 = new Timer(loop);
        timer3 = new Timer(loop);

        //ENTITIES
        player = entityFabric.createPlayer("Vaisseau", "spaceship", 90, 90, 6 , 400, 500, 10, 10);
        entityManager.addEntity(player);
        //createNewWave(1,2,0);
    }

    private void updateShoot(IEntity e) {
        UUID ownerId = Shoot.cast(e).getOwnerId();
        for (IEntity e2 : getEntityCollection()) {
            if (e2.getId().equals(ownerId)) {
                ColliderInfo ci = move.move(e, collider, Shoot.cast(e).getDirection(), Location.cast(e), Speed.cast(e), heightWindow, widthWindow);
                if (ci.IsCollision()) {
                    Life.cast(e).setDead(true);
                    if (ci.getEntity() != null) {
                        Life.cast(ci.getEntity()).decreaseHp();
                    }
                }
            }
        }
    }

    private void updatePlayer() {
        IEntity e = getPlayer();
        for (ECommand key : input.getKeyPressed()) {
            if(key!=null)
                move.move(e, collider, key, Location.cast(e), Speed.cast(e), heightWindow, widthWindow);
        }
    }

    private void updateEnemy(IEntity e, long timer) {
        IEntity player = getPlayer();
        Location l = Location.cast(e);
        if(getPlayer() != null){
            l = Location.cast(player);
        }
        ColliderInfo ci = moveEnemy.move(e, colliderEnemy, ECommand.LEFT, l, Speed.cast(e), heightWindow, widthWindow);
        if (timer2.getTimer() >= timer) {
            createShoot(e.getId(), Location.cast(e), ECommand.LEFT, timer);
            timer2.resetTimer();
        }
        if(ci.IsCollision() && ci.getEntity() == null){
            entityManager.removeEntity(e);
        }
    }

    private void createShoot(UUID id, Location l, ECommand key, long timer) {
        if (timer1.getTimer() >= timer) {
            entityManager.addEntity(entityFabric.createShoot(id, l, key));
            timer1.resetTimer();
        }
    }

    private void createNewWave(int min, int max, long timer) {
        double height = 70;
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
        }
    }

    @Override
    public void update() {
        try {
            updatePlayer();
            List<IEntity> listToBurn = new ArrayList<>();       // Création d'une liste temporaire pour stocker les entitées à supprimer

            for (IEntity e : new ArrayList<>(getEntityCollection())) {
                switch (e.getEntityType()) {
                    case Shoot :
                        updateShoot(e);
                        break;
                    case Enemy :
                        updateEnemy(e, 800);
                        break;
                }

                if (e.isTypeOf(EComponementType.Life)) {    //Gestion de la vie
                    if (Life.cast(e).isDead()) {            //Si l'entité a de la vie
                        listToBurn.add(e);
                        if (e.getEntityType().equals(EEntityType.Enemy)) {
                            //setScore(getScore() + (int) Launcher.getPersistenceManager().getSettings().getDifficulty());
                        }
                    }
                }
            }
            for (IEntity e : listToBurn) {
                entityManager.removeEntity(e);
            }
            //createNewWave(1, 2, 10000);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    @Override
    public void start() {
        loop.subscribe(timer1);
        loop.subscribe(timer2);
        loop.subscribe(timer3);
        loop.subscribe(this);
    }

    @Override
    public void pause() {
        return;
    }

    @Override
    public void resume() {
        return;
    }

    @Override
    public void exit() {
        loop.unsubscribeAll();
    }
}
