package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class PortalTile extends Tile {

	protected Board _board;
	
	public PortalTile(int x, int y, Board board, Sprite sprite) {
		super(x, y, sprite);
		_board = board;
	}

	@Override
	public boolean collide(Entity e) {
		return false;
	}

//	@Override
//	public boolean collide(Entity e) {
//
//		if(e instanceof Player ) {
//
//			if(_board.detectNoEnemies() == false)
//				return false;
//
//			if(e.getXTile() == getX() && e.getYTile() == getY()) {
//				if(_board.detectNoEnemies())
//					_board.nextLevel();
//			}
//
//			return true;
//		}
//
//		return false;
//	}

}
