package uet.oop.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;

public interface IRender {

	public void update();
	
	public void render(GraphicsContext gc);
}
