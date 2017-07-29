package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;

import Screens.PlayScreen;
import scenes.Hud;

public class Coin extends InteractiveTiledObject{
	public Coin(PlayScreen screen, Rectangle bounds){
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(MyGdxGame.COIN_BIT);
	}

	@Override
	public void onHeadHit() {
		Gdx.app.log("Coin", "Coin colision");
		setCategoryFilter(MyGdxGame.DESTROYED_BIT);
		getCell().setTile(null);
		Hud.addScore(1);
	}

}
