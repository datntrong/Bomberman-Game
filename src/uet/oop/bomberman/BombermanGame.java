package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.sound.GameSound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final double PLAYERSPEED = 1.0;

    //can be modified with bonus
    protected static int bombRate = BOMBRATE;
    protected static int bombRadius = BOMBRADIUS;
    protected static double playerSpeed = PLAYERSPEED;

    private static Keyboard _input;
    public static Board board;
    private static GameSound gameSound;
    private static GraphicsContext gc;
    private Canvas canvas;

    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static void main(String[] args) {
        String s = "soundtrack";
        gameSound.play(s);
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
        _input = new Keyboard(scene);
        board = new Board(_input, scene);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                update();
                render();
            }
        };
        timer.start();


    }

    public void update() {
        _input.update();
        board._mobs.forEach(Entity::update);
        if(board._bombs != null) {
            board._bombs.forEach(Bomb::update);
        }
        board.updateMobs();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        board._entities.forEach(g -> {
            if (g != null) {
                g.render(gc);
            }
        });
        board._mobs.forEach(g -> {
            if (g != null) {
                g.render(gc);
            }
        });
        board._bombs.forEach(g -> g.render(gc));
    }

    public static double getPlayerSpeed() {
        return playerSpeed;
    }

    public static Keyboard getInput() {
        return _input;
    }

    public static Board getBoard() {
        return board;
    }

    public static int getBombRate() {
        return bombRate;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static void addBombRate(int i) {
        bombRate += i;
    }

    public static GraphicsContext getGc() {
        return gc;
    }
}