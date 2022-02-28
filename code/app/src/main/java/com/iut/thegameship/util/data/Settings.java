package com.iut.thegameship.util.data;

public class Settings {

    private double difficulty;
    public double getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    private double volume;
    public double getVolume() {
        return volume;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }

    private String up;
    public String getUp() {
        return up;
    }
    public void setUp(String up) {
        this.up = up;
    }

    private String left;
    public String getLeft() {
        return left;
    }
    public void setLeft(String left) {
        this.left = left;
    }

    private String down;
    public String getDown() {
        return down;
    }
    public void setDown(String down) {
        this.down = down;
    }

    private String right;
    public String getRight() {
        return right;
    }
    public void setRight(String right) {
        this.right = right;
    }

    private String shoot;
    public String getShoot() {
        return shoot;
    }
    public void setShoot(String shoot) {
        this.shoot = shoot;
    }
}
