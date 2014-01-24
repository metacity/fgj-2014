package actors;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Rock {
	
	static float SIZE;			//Kiven koko arvotaan vaikka
	
	Vector2 mPosition = new Vector2();
	Rectangle mRect = new Rectangle();
	
	public Rock(Vector2 mPosition) {
		Random mRandom = new Random()	;
		this.mPosition = mPosition;
		this.mRect.height = SIZE;
		this.mRect.width = SIZE;
		SIZE = mRandom.nextFloat() + 1;
	}
	
}
