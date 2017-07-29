package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import Screens.PlayScreen;

public class Empty  extends InteractiveTiledObject{
	public Empty(PlayScreen screen, Rectangle bounds){
		super(screen, bounds);
		fixture.setUserData(this);
		
	}

	@Override
	public void onHeadHit() {
		
	}
}
