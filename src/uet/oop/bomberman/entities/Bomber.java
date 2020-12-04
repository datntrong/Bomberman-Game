package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.util.List;


public class Bomber extends Entity {
//    private List<Bomber> _bombs;
    protected int _direction = -1;
    protected boolean _moving = false;
    public static double _x, _y;
    protected Sprite _sprite;

    protected Keyboard _input;

    protected int _animate = 0;
    protected final int MAX_ANIMATE = 60;


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        _input = BombermanGame.getInput();
    }

    @Override
    public void update() {
        animate();

        calculateMove();

        chooseSprite();
        img = _sprite.getFxImage();
    }

    protected void calculateMove() {
        int xa = 0, ya = 0;
        if (_input.up) ya--;
        if (_input.down) ya++;
        if (_input.left) xa--;
        if (_input.right) xa++;

        if (xa != 0 || ya != 0) {
            move(xa * BombermanGame.getPlayerSpeed(), ya * BombermanGame.getPlayerSpeed());
            _moving = true;
        } else {
            _moving = false;
        }

    }

    public boolean canMove(double x, double y) {
//        for (int c = 0; c < 4; c++) { //colision detection for each corner of the player
//            double xt = ((_x + x) + c % 2 * 11) / 16; //divide with tiles size to pass to tile coordinate
//            double yt = ((_y + y) + c / 2 * 12 - 13) / 16; //these values are the best from multiple tests

//            Entity a = _board.getEntity(xt, yt, this);
//
//            if(!a.collide(this))
//                return false;
//        }

        return true;
    }


    public void move(double xa, double ya) {
        if (xa > 0) _direction = 1;
        if (xa < 0) _direction = 3;
        if (ya > 0) _direction = 2;
        if (ya < 0) _direction = 0;

        if (canMove(0, ya)) { //separate the moves for the player can slide when is colliding
            this.y += ya;
        }

        if (canMove(xa, 0)) {
            this.x += xa;
        }
    }

    protected void animate() {
        if (_animate < MAX_ANIMATE) _animate++;
        else _animate = 0; //reset animation
    }

    private void chooseSprite() {
        switch (_direction) {
            case 0:
                _sprite = Sprite.player_up;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case 2:
                _sprite = Sprite.player_down;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                _sprite = Sprite.player_left;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
            case 1:
            default:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
        }
    }
}