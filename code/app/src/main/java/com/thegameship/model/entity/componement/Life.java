package com.thegameship.model.entity.componement;

public class Life extends Componement {

    private double hp;

    public double getHp() {
        return hp;
    }
    private void setHp(double hp) {
        this.hp = hp;
        if (getHp() <= 0) {
            setDead(true);
        }
    }
    public void decreaseHp() {
        setHp(getHp() - 1);
    }

    private boolean isDead ;
    public boolean isDead() {
        return isDead;
    }
    public void setDead(boolean dead) {
        isDead = dead;
    }

    public Life(double hp) {
        super(EComponementType.Life);
        setHp(hp);
        setDead(false);
    }

    public static Life cast(IHasComponements e) {
        return (Life) e.getComponement(EComponementType.Life);
    }
}
