package uet.oop.bomberman.entities.tile.powerup;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class PowerupSpeed extends Powerup {

	public PowerupSpeed(int x, int y, int level, Sprite sprite) {
		super(x, y, level, sprite);
	}
	
//	@Override
//	public boolean collide(Entity e) {
//
//		if(e instanceof Player) {
//			((Player) e).addPowerup(this);
//			remove();
//			return true;
//		}
//
//		return false;
//	}
	
	@Override
	public void setValues() {
		_active = true;
		//Game.addPlayerSpeed(0.1);
	}


	@Override
	public boolean collide(Entity e) {
		return false;
	}
}
