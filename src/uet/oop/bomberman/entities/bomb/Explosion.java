package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
//import uet.oop.bomberman.entities.mob.Mob;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends Entity {

	protected boolean _last = false;
	protected Board _board;
	protected Sprite _sprite, _sprite1, _sprite2;
	
	public Explosion(int x, int y, int direction, boolean last, Board board) {
		this.x=x;
		this.y=y;
		this.img=null;
		//super(x, y, null);
		_last = last;
		_board = board;

		switch (direction) {
			case 0:
				if(last == false) {
					_sprite = Sprite.explosion_vertical2;
				} else {
					_sprite = Sprite.explosion_vertical_top_last2;
				}
			break;
			case 1:
				if(last == false) {
					_sprite = Sprite.explosion_horizontal2;
				} else {
					_sprite = Sprite.explosion_horizontal_right_last2;
				}
				break;
			case 2:
				if(last == false) {
					_sprite = Sprite.explosion_vertical2;
				} else {
					_sprite = Sprite.explosion_vertical_down_last2;
				}
				break;
			case 3:
				if(last == false) {
					_sprite = Sprite.explosion_horizontal2;
				} else {
					_sprite = Sprite.explosion_horizontal_left_last2;
				}
				break;
		}
		img = _sprite != null ? _sprite.getFxImage() : null;
	}

//	public void render() {
//		int xt = (int)_x << 4;
//		int yt = (int)_y << 4;
//
//		screen.renderEntity(xt, yt , this);
//	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
	}
	
	@Override
	public void update() {}

	@Override
	public boolean collide(Entity e) {
		
//		if(e instanceof Mob) {
//			((Mob)e).kill();
//		}
		
		return true;
	}
	

}