package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.exceptions.LoadLevelException;
public abstract class Level  {

	protected int _width, _height, _level;
	protected String[] _lineTiles;
	protected Board _board;



	public Level(String path, Board board) throws LoadLevelException {
		loadLevel(path);
		_board = board;
	}

	public abstract void loadLevel(String path) throws LoadLevelException;

	public abstract void createEntities();

	public int getWidth() {
		return _width;
	}

	public int getHeight() {
		return _height;
	}

	public int getLevel() {
		return _level;
	}

}
