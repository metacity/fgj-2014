package actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Palle {
	
	public enum State {
		RUN, JUMP, IDLE;					//Ukkelin eri tilat
	}
	
	public  final static float SIZE = 1.0f;			//Ukkelin koko 1.0f vaikka
	public  float SPEED = -0.1f; 		//Vakionoppeus 0.1f tuaksepp‰in
	public  float JUMP_SPEED = 0.5f;	//Ukon hyppynopeus 0.5f
	public Rectangle mRect = new Rectangle();		//Tehd‰‰n rektankeli
	public State mState = State.IDLE;				//Ukon vakiotila on IDLE
	
	public Vector2 mPosition = new Vector2();		//Lasketaan ukon paikka
	public Vector2 mAcceleration = new Vector2();	//Ukon liikkuvuus hypyn aikana
	public Vector2 mVelocity = new Vector2();		//Lasketaan ukon nopeus
	
	public Palle(Vector2 mPosition) {
		this.mPosition = mPosition;	
		mRect.height = SIZE;
		mRect.width = SIZE;
	}
	
	public Rectangle getBounds() {
		return mRect;
	}


	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return mPosition;
	}

}
