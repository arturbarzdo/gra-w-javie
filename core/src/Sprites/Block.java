package Sprites;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;

import Screens.PlayScreen;

public class Block extends InteractiveTiledObject{
	public Block(PlayScreen screen , Rectangle bounds){
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(MyGdxGame.BLOCK_BIT);
	}

	@Override
	public void onHeadHit() {
		
	}
}
