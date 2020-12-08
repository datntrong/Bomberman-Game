package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.bomb.Explosion;
import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity implements IRender {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected boolean _removed = false;

    protected Image img;

    public void remove() {
        _removed = true;
    }

    public boolean isRemoved() {
        return _removed;
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
//        if (this instanceof Explosion) {
//            System.out.println(x + "R" + y);
//        }
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public abstract boolean collide(Entity e);

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
