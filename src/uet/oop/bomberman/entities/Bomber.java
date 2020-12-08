package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.util.Iterator;
import java.util.List;


public class Bomber extends Entity {
    private List<Bomb> _bombs;
    protected int _direction = -1;
    protected boolean _moving = false;
    protected Sprite _sprite;

    protected Keyboard _input;
    protected Board _board;
    protected int _animate = 0;
    protected final int MAX_ANIMATE = 60;
    protected int _timeBetweenPutBombs = 0;

    public Bomber(int x, int y, Image img, Board board) {
        super(x, y, img);
        _input = BombermanGame.getInput();
        _board = board;
        _bombs = _board.getBombs();
    }

    @Override
    public void update() {
        clearBombs();

        if(_timeBetweenPutBombs < -7500) _timeBetweenPutBombs = 0; else _timeBetweenPutBombs--;

        animate();

        calculateMove();

        chooseSprite();
        img = _sprite.getFxImage();

        detectPlaceBomb();
    }

    @Override
    public boolean collide(Entity e) {
        return true;
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

    public boolean canMove(double _x, double _y) {
//        System.out.println(x+" "+y+" canmove");
        for (int c = 0; c < 4; c++) { //colision detection for each corner of the player
            double xt = ((_x*2 + x) + c % 2 * 22) / Sprite.SCALED_SIZE; //divide with tiles size to pass to tile coordinate
            double yt = ((_y*2 + y+32) + (c >> 1) * 24-26) / Sprite.SCALED_SIZE; //these values are the best from multiple tests
//            System.out.println(xt+" "+yt);
            Entity a = _board.getEntity(xt, yt, this);
//            System.out.println(a);
//            System.out.println(a.collide(this));
            if(!a.collide(this))
                return false;
        }

        return true;
    }

    private void detectPlaceBomb() {
        if(_input.space && BombermanGame.getBombRate() > 0 && _timeBetweenPutBombs < 0) {
//            System.out.println("OK");
            placeBomb();
            BombermanGame.addBombRate(-1);

            _timeBetweenPutBombs = 30;
        }
    }

    protected void placeBomb() {
//        System.out.println(x+" B "+y);
        double _x = x;
        double _y = y;
        Bomb b = new Bomb((int) Math.round((_x / 32)), (int) Math.round((_y / 32)), _board);
        _board.addBomb(b);
//        System.out.println(_board);
    }

    private void clearBombs() {
        Iterator<Bomb> bs = _bombs.iterator();

        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();
            if(b.isRemoved())  {
                bs.remove();
                BombermanGame.addBombRate(1);
            }
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