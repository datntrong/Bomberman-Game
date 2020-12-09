package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
//import uet.oop.bomberman.entities.mob.Mob;
//import uet.oop.bomberman.graphics.Screen;

public class DirectionalExplosion extends Entity {

	protected Board _board;
	protected int _direction;
	private int _radius;
	protected int xOrigin, yOrigin;
	protected Explosion[] _explosions;
	protected GraphicsContext gc;
	
	public DirectionalExplosion(int x, int y, int direction, int radius, Board board) {
		this.x=x;
		this.y=y;
		this.img=null;
		//super(x, y, null);
		xOrigin = x;
		yOrigin = y;
		_direction = direction;
		_radius = radius;
		_board = board;
		
		_explosions = new Explosion[ calculatePermittedDistance() ];
		createExplosions();
	}
	
	private void createExplosions() {
		boolean last = false;
		
		int _x = x;
		int _y = y ;
		for (int i = 0; i < _explosions.length; i++) {
			last = i == _explosions.length - 1 ? true : false;
			
			switch (_direction) {
				case 0: _y--; break;
				case 1: _x++; break;
				case 2: _y++; break;
				case 3: _x--; break;
			}
//			System.out.println(_x+"DD"+_y);
			_explosions[i] = new Explosion(_x, _y, _direction, last, _board);
		}
	}
	
	private int calculatePermittedDistance() {
		int radius = 0;
		int _x = x;
		int _y = y;
		while(radius < _radius) {
			if(_direction == 0) _y--;
			if(_direction == 1) _x++;
			if(_direction == 2) _y++;
			if(_direction == 3) _x--;
//			System.out.println(_x+" D "+_y);
			Entity a = _board.getEntity(_x, _y);
			
//			if(a instanceof Mob) ++radius; //explosion has to be below the mob
			
			if(a.collide(this) == false) //cannot pass thru
				break;
			
			++radius;
		}
//		System.out.println(radius + " R ");
		return radius;
	}
	
	public Explosion explosionAt(int x, int y) {
		for (int i = 0; i < _explosions.length; i++) {
			if(_explosions[i].getX() == x && _explosions[i].getY() == y) 
				return _explosions[i];
		}
		return null;
	}

	@Override
	public void update() {}

	@Override
	public void render(GraphicsContext gc) {
		for (int i = 0; i < _explosions.length; i++) {
			if(_explosions[i]!=null){
				_explosions[i].render(gc);
			}
		}
	}

	@Override
	public boolean collide(Entity e) {
		return true;
	}
}
