package uet.oop.bomberman.entities.mob;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Board _board;
	protected int _direction = -1;
	protected boolean _alive = true;
	protected boolean _moving = false;
	public int _timeAfter = 80;
	protected Sprite _sprite;

	protected int _animate = 0;
	protected final int MAX_ANIMATE = 7500; //save the animation status and dont let this get too big


	
	public Mob(int x, int y, Board board) {
		this.x = x;
		this.y = y;
		_board = board;
		//img=sprite.getFxImage();
	}
	
	@Override
	public abstract void update();
	
	@Override
	public abstract void render(GraphicsContext gc);
	
	protected abstract void calculateMove();
	
	protected abstract void move(double xa, double ya);
	
	public abstract void kill();
	
	protected abstract void afterKill();
	
	protected abstract boolean canMove(double x, double y);
	
	public boolean isAlive() {
		return _alive;
	}
	
	public boolean isMoving() {
		return _moving;
	}
	
	public int getDirection() {
		return _direction;
	}

	protected void animate() {
		if(_animate < MAX_ANIMATE) _animate++; else _animate = 0; //reset animation
	}
	
//	protected double getXMessage() {
//		return (_x * Game.SCALE) + (_sprite.SIZE / 2 * Game.SCALE);
//	}
//
//	protected double getYMessage() {
//		return (_y* Game.SCALE) - (_sprite.SIZE / 2 * Game.SCALE);
//	}
//
}
