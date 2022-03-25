package com.iut.thegameship.util.loop;

import static java.lang.Thread.sleep;

import com.iut.thegameship.UI.Views.GameView;

public class Loop extends Observable implements Runnable {

    private final long millis;
    public long getMillis() { return millis; }

    private boolean isRunning = true;

    public Loop(long millis) {
        this.millis = millis;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                sleep(millis);
                notifier();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void StopLoop() { isRunning = false; }
    public void RestartLoop(){
        isRunning = true;
    }
    public void destroyLoop() {
        unsubscribeAll();
    }

    public void setView(GameView gameview) {
        subscribe(gameview);
    }
}