package Sprites;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;

import Screens.PlayScreen;

public class LevelComplete extends InteractiveTiledObject{
	public LevelComplete (PlayScreen screen , Rectangle bounds){
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(MyGdxGame.COMPLETE_BIT);
	}

	@Override
	public void onHeadHit() {
		
	}
}
