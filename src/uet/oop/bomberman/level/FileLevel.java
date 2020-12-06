package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.tile.GrassTile;
import uet.oop.bomberman.entities.tile.PortalTile;
import uet.oop.bomberman.entities.tile.WallTile;
import uet.oop.bomberman.entities.tile.destroyable.BrickTile;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;



public class FileLevel extends Level {
	public FileLevel(String path, Board board) throws LoadLevelException {
	    super(path, board);
	}

	@Override
	public void loadLevel(String path) throws LoadLevelException {
		try {
			URL absPath = FileLevel.class.getResource("/" + path);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(absPath.openStream()));

			String data = in.readLine();
			StringTokenizer tokens = new StringTokenizer(data);

			_level = Integer.parseInt(tokens.nextToken());
			_height = Integer.parseInt(tokens.nextToken());
			_width = Integer.parseInt(tokens.nextToken());

			_lineTiles = new String[_height];

			for(int i = 0; i < _height; ++i) {
				_lineTiles[i] = in.readLine().substring(0, _width);
 			}

			in.close();
		} catch (IOException e) {
			throw new LoadLevelException("Error loading level " + path, e);
		}
	}

	@Override
	public void createEntities() {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				addLevelEntity( _lineTiles[y].charAt(x), x, y );
			}
		}
	}

	public void addLevelEntity(char c, int x, int y) {
		int pos = x + y * getWidth();

		switch(c) {
			case '#':
				_board.addEntitie(pos, new WallTile(x, y, Sprite.wall));
				break;
//			case 'b':
//				LayeredEntity layer = new LayeredEntity(x, y,
//						new GrassTile(x ,y, Sprite.grass),
//						new BrickTile(x ,y, Sprite.brick));
//
//				if(_board.isPowerupUsed(x, y, _level) == false) {
//					layer.addBeforeTop(new PowerupBombs(x, y, _level, Sprite.powerup_bombs));
//				}
//
//				_board.addEntitie(pos, layer);
//				break;
//			case 's':
//				layer = new LayeredEntity(x, y,
//						new GrassTile(x ,y, Sprite.grass),
//						new BrickTile(x ,y, Sprite.brick));
//
//				if(_board.isPowerupUsed(x, y, _level) == false) {
//					layer.addBeforeTop(new PowerupSpeed(x, y, _level, Sprite.powerup_speed));
//				}
//
//				_board.addEntitie(pos, layer);
//				break;
//			case 'f':
//				layer = new LayeredEntity(x, y,
//						new GrassTile(x ,y, Sprite.grass),
//						new BrickTile(x ,y, Sprite.brick));
//
//				if(_board.isPowerupUsed(x, y, _level) == false) {
//					layer.addBeforeTop(new PowerupFlames(x, y, _level, Sprite.powerup_flames));
//				}
//
//				_board.addEntitie(pos, layer);
//				break;
//			case '*':
////				_board.addEntitie(pos, new LayeredEntity(x, y,
////						new GrassTile(x, y, Sprite.grass),
////						new BrickTile(x, y, Sprite.brick)));
//				break;
//			case 'x':
////				_board.addEntitie(pos, new LayeredEntity(x, y,
////						new GrassTile(x, y, Sprite.grass),
////						new PortalTile(x, y, _board, Sprite.portal),
////						new BrickTile(x, y, Sprite.brick)));
//				break;
			case ' ':
				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
				break;
			case 'p':
				_board.addBomber( new Bomber(x, y, Sprite.player_right.getFxImage(), _board));

				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
				break;
//			//Enemies
//			case '1':
//				_board.addMob( new Balloom(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
//				break;
//			case '2':
//				_board.addMob( new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
//				break;
//			case '3':
//				_board.addMob( new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
//				break;
//			case '4':
//				_board.addMob( new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
//				break;
//			case '5':
//				_board.addMob( new Kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
//				break;
			default:
				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
				break;
		}
	}

}
