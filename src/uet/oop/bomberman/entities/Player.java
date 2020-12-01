package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.input.Keyboard;

import java.util.List;


public class Player extends Entity {
    private List<Bomber> _bombs;
    protected Keyboard _input ;
    protected boolean _moving = false;
    protected int _direction = -1;


    public Player(int x, int y, Image img) {
        super(x, y, img);
        _input = new Keyboard();
    }

    @Override
    public void update() {
        _input.update();
        calculateMove();
    }

    protected void calculateMove() {
        int xa = 0, ya = 0;
        if(_input.up) y--;
        if(_input.down) y++;
        if(_input.left) x--;
        if(_input.right) x++;

//        if(xa != 0 || ya != 0)  {
//            move(xa * Game.getPlayerSpeed(), ya * Game.getPlayerSpeed());
//            _moving = true;
//        } else {
//            _moving = false;
//        }

    }

    public void move(double xa, double ya) {
        if(xa > 0) _direction = 1;
        if(xa < 0) _direction = 3;
        if(ya > 0) _direction = 2;
        if(ya < 0) _direction = 0;

        if(canMove(0, ya)) { //separate the moves for the player can slide when is colliding
            y += ya;
        }

        if(canMove(xa, 0)) {
            x += xa;
        }
    }
    public boolean canMove(double _x, double _y) {
//        for (int c = 0; c < 4; c++) { //colision detection for each corner of the player
//            double xt = ((_x + x) + c % 2 * 11) / Game.TILES_SIZE; //divide with tiles size to pass to tile coordinate
//            double yt = ((_y + y) + c / 2 * 12 - 13) / Game.TILES_SIZE; //these values are the best from multiple tests
//
//            Entity a = _board.getEntity(xt, yt, this);
//
//            if(!a.collide(this))
//                return false;
//        }

        return true;
    }
}
