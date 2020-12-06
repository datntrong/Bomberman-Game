package uet.oop.bomberman.level;

import uet.oop.bomberman.graphics.Sprite;

public class Coordinates {
	
	public static int pixelToTile(double i) {
		return (int)(i / Sprite.TILES_SIZE);
	}
	
	public static int tileToPixel(int i) {
		return i * Sprite.TILES_SIZE;
	}
	
	public static int tileToPixel(double i) {
		return (int)(i * Sprite.TILES_SIZE);
	}
	
	
}
