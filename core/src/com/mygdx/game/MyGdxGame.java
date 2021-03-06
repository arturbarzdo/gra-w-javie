package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.PlayScreen;

public class MyGdxGame extends Game {
	
	public SpriteBatch batch;

	public static final int V_WIDTH= 400;
	public static final int V_HEIGHT= 208;
	public static final float PPM= 100;
	
	public static final short EMPTY_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short BOMBA_BIT = 2;
	public static final short BLOCK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short COMPLETE_BIT = 256;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
		
		
	}

	@Override
	public void render () {
		
	
		super.render();
	}
	
		
	public void dispose(){
		
	}
	
}
