package com.thegameship.model.util.save;

import com.thegameship.model.util.data.Settings;

public class SerializeSettings {

    private double difficulty;
    private double volume;

    private String up;
    private String left;
    private String down;
    private String right;
    private String shoot;

    public SerializeSettings() {
        difficulty = 2;
        volume = 50;
        up = "UP";
        left = "LEFT";
        down = "DOWN";
        right = "RIGHT";
        shoot = "SPACE";
    }

    public SerializeSettings(Settings settings) {
        difficulty = settings.getDifficulty();
        volume = settings.getVolume();
        up = settings.getUp();
        left = settings.getLeft();
        down = settings.getDown();
        right = settings.getRight();
        shoot = settings.getShoot();
    }

    public double getDifficulty() { return difficulty; }
    public double getVolume() { return volume; }
    public String getUp() { return up; }
    public String getLeft() { return left; }
    public String getDown() { return down; }
    public String getRight() { return right; }
    public String getShoot() { return shoot; }

    public void setDifficulty(double difficulty) { this.difficulty = difficulty; }
    public void setVolume(double volume) { this.volume = volume; }
    public void setUp(String up) { this.up = up; }
    public void setLeft(String left) { this.left = left; }
    public void setDown(String down) { this.down = down; }
    public void setRight(String right) { this.right = right; }
    public void setShoot(String shoot) { this.shoot = shoot; }

    @Override
    public String toString() {
        return "SerializeSettings{" +
                "difficulty=" + difficulty +
                ", volume=" + volume +
                ", top=" + up +
                ", left=" + left +
                ", down=" + down +
                ", right=" + right +
                ", shoot=" + shoot +
                '}';
    }
}
