package uet.oop.bomberman.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Keyboard {
    public boolean up, down, left, right, space;
    protected Scene scene;

    public Keyboard(Scene scene) {
        this.scene = scene;
    }

    public void update() {
        this.OnKeyPressed();
        this.OnKeyReleased();
//        System.out.println(up + " " + down + " " + left + " " + right);
    }

    private void OnKeyPressed() {
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case UP:
                case W:
                    up = true;
                    break;
                case DOWN:
                case S:
                    down = true;
                    break;
                case LEFT:
                case A:
                    left = true;
                    break;
                case RIGHT:
                case D:
                    right = true;
                    break;
                case SPACE:
                    space = true;
                    break;
            }
        });
    }

    private void OnKeyReleased() {
        scene.setOnKeyReleased(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case UP:
                case W:
                    up = false;
                    break;
                case DOWN:
                case S:
                    down = false;
                    break;
                case LEFT:
                case A:
                    left = false;
                    break;
                case RIGHT:
                case D:
                    right = false;
                    break;
                case SPACE:
                    space = false;
                    break;
            }
        });
    }
}
