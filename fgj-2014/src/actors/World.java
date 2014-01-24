package actors;
import com.badlogic.gdx.math.Vector2;

import actors.Palle;

public class World {
	
	public final int WORLD_WIDTH = 30;
	public final int WORLD_HEIGHT = 20;
	public int DIFFICULTY_LEVEL;
	
	public Palle palle;
	Wood wood;
	Rock rock;
	//Vaikeustaso muuttuu ajanfunktiona
	long time;
	
	public World() {
		createWorld();
	}
	
	public void createWorld() {
		palle = new Palle(new Vector2(1, 1));
		time = System.nanoTime();
	}
	
	public Palle getPalle() {
		return palle;
	}
	
}
