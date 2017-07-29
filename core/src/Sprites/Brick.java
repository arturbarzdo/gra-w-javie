package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;

import Screens.PlayScreen;

public class Brick extends InteractiveTiledObject{
	public Brick(PlayScreen screen , Rectangle bounds){
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(MyGdxGame.BRICK_BIT);
	}

	@Override
	public void onHeadHit() {
		Gdx.app.log("Brick", "Coin colision");
	}
}
