package com.iut.thegameship.util.save;/*package com.iut.thegameship.util.save;

import com.iut.thegameship.util.data.HighScore;
import com.iut.thegameship.util.data.Settings;
import java.io.*;

public class PersistenceManager {

    IPersistence ph = new HighScorePersistence();
    private final File highScoreFile = new File("./res/XML/highscore.xml");
    private HighScore highScore;
    public HighScore getHighScore() {
        if (highScore == null){
            loadHighScore();
        }
        return highScore;
    }

    IPersistence ps = new SettingsPersistence();
    private final File settingsFile = new File("./res/XML/settings.xml");
    private Settings settings;
    public Settings getSettings(){
        if(settings == null){
            loadSettings();
        }
        return settings;
    }

    private void loadSettings() {
        SerializeSettings ss = new SerializeSettings();
        try {
            ss = (SerializeSettings) ps.load(settingsFile);
        } catch (Exception err) {
            err.printStackTrace();
        }
        Settings settings = new Settings();
        settings.setDifficulty(ss.getDifficulty());
        settings.setVolume(ss.getVolume());
        settings.setUp(ss.getUp());
        settings.setLeft(ss.getLeft());
        settings.setDown(ss.getDown());
        settings.setRight(ss.getRight());
        settings.setShoot(ss.getShoot());
        this.settings = settings;
    }

    private void loadHighScore() {
        SerializeHighScore sh = new SerializeHighScore();
        try {
            sh = (SerializeHighScore) ph.load(highScoreFile);
        } catch (Exception err) {
            err.printStackTrace();
        }
        this.highScore = new HighScore(sh.getListHighScore());
    }

    public void saveHighScore(HighScore highScore1) {
        try {
            ph.save(highScore1, highScoreFile);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void saveSettings(Settings settings1) {
        try {
            ps.save(settings1, settingsFile);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
*/