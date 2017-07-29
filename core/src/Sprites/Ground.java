package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import Screens.PlayScreen;

public class Ground extends InteractiveTiledObject {
	public Ground(PlayScreen screen, Rectangle bounds){
		super(screen, bounds);
		fixture.setUserData(this);
		
}

	@Override
	public void onHeadHit() {
		// TODO Auto-generated method stub
		
	}
	
}