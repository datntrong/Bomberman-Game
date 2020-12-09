package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
//import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;
//import uet.oop.bomberman.entities.mob.Mob;
import uet.oop.bomberman.entities.Bomber;
//import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;


public class Bomb extends Entity {

	//options
	protected double _timeToExplode = 120; //2 seconds
	public int _timeAfter = 20; //time to explosions disapear
	protected int _animate = 0;
	protected final int MAX_ANIMATE = 7500;
	protected Sprite _sprite;

	protected Board _board;
	protected boolean _allowedToPassThru = true;
	protected DirectionalExplosion[] _explosions = null;
	protected boolean _exploded = false;

	private int a,b;

	public Bomb(int x, int y, Board board) {
		//super(x, y, Sprite.bomb.getFxImage());
		this.a=x;
		this.b=y;
		_board = board;
	}

	@Override
	public void update() {
		if(_timeToExplode > 0)
			_timeToExplode--;
		else {
			if(!_exploded)
				explosion();
			else
				updateExplosions();

			if(_timeAfter > 0)
				_timeAfter--;
			else
				remove();
		}

		if(_animate < MAX_ANIMATE) _animate++; else _animate = 0; //reset animation
	}

	public void render(GraphicsContext gc) {
		if(_exploded) {
			_sprite =  Sprite.bomb_exploded2;
			renderExplosions();
		} else
			_sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
		img = _sprite.getFxImage();
		gc.drawImage(img,a,b);
		//super.render(gc);
	}

	public void renderExplosions() {
		for (int i = 0; i < _explosions.length; i++) {
//			System.out.println(_explosions[i] + " " + _explosions[i].getX() + " " + _explosions[i].getY());
			_explosions[i].render(BombermanGame.getGc());
		}
	}

	public void updateExplosions() {
		for (int i = 0; i < _explosions.length; i++) {
			if (_explosions[i] != null) {
				_explosions[i].update();
			}
		}
	}

	public void explode() {
		_timeToExplode = 0;
	}

	protected void explosion() {
		_allowedToPassThru = true;
		_exploded = true;

//		Mob a = _board.getMobAt(x, y);
//		if(a != null)  {
//			a.kill();
//		}
		_explosions = new DirectionalExplosion[4];
//		System.out.println("Hello");
		for (int i = 0; i < _explosions.length; i++) {
//			System.out.println(x+" "+y);
			_explosions[i] = new DirectionalExplosion(x , y , i, BombermanGame.getBombRadius(), _board);
//			System.out.println(_explosions[i]);
		}
	}

	public Explosion explosionAt(int x, int y) {
		if(!_exploded) return null;

		for (int i = 0; i < _explosions.length; i++) {
			if(_explosions[i] == null) return null;
			Explosion e = _explosions[i].explosionAt(x, y);
			if(e != null) return e;
		}

		return null;
	}

	public boolean isExploded() {
		return _exploded;
	}


	@Override
	public boolean collide(Entity e) {
		if(e instanceof Bomber) {
			double diffX = e.getX() - Coordinates.tileToPixel(getX());
			double diffY = e.getY() - Coordinates.tileToPixel(getY());

			if(!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) { // differences to see if the player has moved out of the bomb, tested values
				_allowedToPassThru = false;
			}

			return _allowedToPassThru;
		}

		if(e instanceof DirectionalExplosion) {
			explode();
			return true;
		}

		return false;
	}
}