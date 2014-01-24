package actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Palle {
	
	enum State {
		RUN, JUMP, IDLE;					//Ukkelin eri tilat
	}
	
	static final float SIZE = 1.0f;			//Ukkelin koko 1.0f vaikka
	static final float SPEED = -0.1f; 		//Vakionoppeus 0.1f tuaksepp‰in
	static final float JUMP_SPEED = 0.5f;	//Ukon hyppynopeus 0.5f
	Rectangle mRect = new Rectangle();		//Tehd‰‰n rektankeli
	State mState = State.IDLE;				//Ukon vakiotila on IDLE
	
	Vector2 mPosition = new Vector2();		//Lasketaan ukon paikka
	Vector2 mAcceleration = new Vector2();	//Ukon liikkuvuus hypyn aikana
	Vector2 mVelocity = new Vector2();		//Lasketaan ukon nopeus
	
	public Palle(Vector2 mPosition) {
		this.mPosition = mPosition;	
		mRect.height = SIZE;
		mRect.width = SIZE;
	}

}
