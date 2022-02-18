package com.iut.thegameship.util.input;/*
package com.iut.thegameship.util.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Keyboard implements IInput, EventHandler<KeyEvent> {

    private Map<ECommand, Boolean> keyPressed = new HashMap<>();

    @Override public ArrayList<ECommand> getKeyPressed() {
        ArrayList<ECommand> list = new ArrayList<>();
        for (Map.Entry m: keyPressed.entrySet()) {
            if ((Boolean)m.getValue()) {
                list.add((ECommand) m.getKey());
            }
        }
        return list;
    }

    //Correspondance entre les commandes disponible et les touche du clavier
    private Map<KeyCode, ECommand> matchKey = new HashMap<>()       //TODO: L'adapter aux StringProperty, afin de spécifie les touche dynamiquement avec les settings
        {
            {
                put(KeyCode.UP, ECommand.UP);
                put(KeyCode.Z, ECommand.UP);

                put(KeyCode.DOWN, ECommand.DOWN);
                put(KeyCode.S, ECommand.DOWN);

                put(KeyCode.RIGHT, ECommand.RIGHT);
                put(KeyCode.D, ECommand.RIGHT);

                put(KeyCode.LEFT, ECommand.LEFT);
                put(KeyCode.Q, ECommand.LEFT);

                put(KeyCode.SPACE, ECommand.SHOOT);
            }
        };

    public Keyboard() {
        for (ECommand e : ECommand.values()) {
            keyPressed.put(e, false);
        }
    }

    @Override
    public void handle(KeyEvent event) {
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            KeyCode key = event.getCode();
            if (matchKey.containsKey(key)) {
                keyPressed.replace(matchKey.get(key), true);
            }
        }
        else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            KeyCode key = event.getCode();
            if (matchKey.containsKey(key)) {
                keyPressed.replace(matchKey.get(key), false);
            }
        }
    }
}
*/