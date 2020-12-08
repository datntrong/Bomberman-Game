package uet.oop.bomberman;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Explosion;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.level.FileLevel;
import uet.oop.bomberman.level.Level;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board {
    protected Keyboard _input;
    protected Scene _scene;

//    public Entity[] _entities;
    public List<Entity> _entities = new ArrayList<Entity>();
    public List<Entity> _mobs = new ArrayList<Entity>();
    protected List<Bomb> _bombs = new ArrayList<Bomb>();

    //protected List<Bomber> _bombs = new ArrayList<Bomber>();
    //private List<Message> _messages = new ArrayList<Message>();

    protected Level _level;


    public Board(Keyboard input, Scene scene) {
        _input = input;
        _scene = scene;
        changeLevel(1); //start in level 1
    }


//    @Override
//    public void update() {
//        updateEntities();
//        updateMobs();
//        updateBombs();
//
//
////        updateMessages();
////        detectEndGame();
//
////        for (int i = 0; i < _mobs.size(); i++) {
////            Entity a = _mobs.get(i);
////            if(((Entity)a).isRemoved()) _mobs.remove(i);
////        }
//    }

    public void render(GraphicsContext gc) {

    }

//    protected void updateEntities() {
////        if( _game.isPaused() ) return;
//        for (int i = 0; i < _entities.length; i++) {
//            if (_entities[i] != null) {
//                _entities[i].update();
//            }
//
//        }
//    }

    protected void updateMobs() {
        //if( _game.isPaused() ) return;
        Iterator<Entity> itr = _mobs.iterator();

        while (itr.hasNext())
            itr.next().update();
    }

    protected void updateBombs() {
//        if( _game.isPaused() ) return;
        //Iterator<Bomber> itr = _bombs.iterator();

        //while(itr.hasNext())
        //itr.next().update();
    }

    public void addEntitie(int pos,Entity e) {
        _entities.add(pos, e);
    }

    public void changeLevel(int level) {
//        _time = Game.TIME;
//        _screenToShow = 2;
//        _game.resetScreenDelay();
//        _game.pause();
//        _mobs.clear();
//        _bombs.clear();
//        _messages.clear();

        try {
            _level = new FileLevel("levels/Level" + level + ".txt", this);
//            _entities.add(new Entity[_level.getHeight() * _level.getWidth()]);

            _level.createEntities();
        } catch (LoadLevelException e) {
            //endGame(); //failed to load.. so.. no more levels?
        }
    }

    public Entity getEntity(double x, double y, Entity e) {

        Entity res = null;

        res = getExplosionAt((int)x, (int)y);
        if( res != null) return res;
//
        res = getBombAt(x, y);
        if( res != null) return res;
//
//        res = getMobAtExcluding((int)x, (int)y, m);
//        if( res != null) return res;

        res = getEntityAt((int) x, (int) y);

        return res;
    }

    public Entity getEntityAt(double x, double y) {
//        System.out.println((int) x + (int) y * _level.getWidth());
        return _entities.get((int) x + (int) y * _level.getWidth());
    }

    public void addBomber(Bomber e) {
        _mobs.add(e);
    }

    public void addBomb(Bomb e) {
        _bombs.add(e);
    }

    public List<Bomb> getBombs() {
        return _bombs;
    }

    public Bomb getBombAt(double x, double y) {
        Iterator<Bomb> bs = _bombs.iterator();
        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();
            if(b.getX() == (int)x && b.getY() == (int)y)
                return b;
        }

        return null;
    }

    public Explosion getExplosionAt(int x, int y) {
        Iterator<Bomb> bs = _bombs.iterator();
        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();

            Explosion e = b.explosionAt(x, y);
            if(e != null) {
                return e;
            }

        }

        return null;
    }
}