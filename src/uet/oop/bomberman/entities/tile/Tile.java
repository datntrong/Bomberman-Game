package uet.oop.bomberman.entities.tile;

import javafx.stage.Screen;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Tile extends Entity {
	
	
	public Tile(int x, int y, Sprite sprite) {
		super(x,y,sprite.getFxImage());
	}

	@Override
	public void update() {}
}
