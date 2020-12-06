package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.destroyable.DestroyableTile;
import uet.oop.bomberman.graphics.Sprite;

import java.util.LinkedList;

public class LayeredEntity extends Entity {

    protected LinkedList<Entity> _entities = new LinkedList<Entity>();

    public LayeredEntity(int x, int y, Entity ... entities) {
        this.x = x;
        this.y = y;

        for (int i = 0; i < entities.length; i++) {
            _entities.add(entities[i]);

            if(i > 1) { //Add to destroyable tiles the bellow sprite for rendering in explosion
                if(entities[i] instanceof DestroyableTile);
                    //((DestroyableTile)entities[i]).addBelowSprite(entities[i-1].getSprite());
            }
        }
    }

    @Override
    public void update() {
        clearRemoved();
        getTopEntity().update();
    }


    public void render(GraphicsContext gc) {
        getTopEntity().render(gc);
    }

    public Entity getTopEntity() {

        return _entities.getLast();
    }

    private void clearRemoved() {
        Entity top  = getTopEntity();

//        if(top.isRemoved())  {
//            _entities.removeLast();
//        }
    }

    public void addBeforeTop(Entity e) {
        _entities.add(_entities.size() - 1, e);
    }

    @Override
    public boolean collide(Entity e) {
        return getTopEntity().collide(e);
    }

}
