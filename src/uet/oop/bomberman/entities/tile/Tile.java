package uet.oop.bomberman.entities.tile;

import javafx.stage.Screen;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Tile extends Entity {
	
	
	public Tile(int x, int y, Sprite sprite) {
		this.x = x * Sprite.SCALED_SIZE;
		this.y = y * Sprite.SCALED_SIZE;
		this.img = sprite.getFxImage();
	}

	@Override
	public void update() {}
}
