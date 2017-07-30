package Screens;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MyGdxGame;
import scenes.Hud;

public class FinishLevel implements Screen{
	private Viewport viewport;
	private Stage stage;

	private Game game;
	
	public FinishLevel(Game game){
		this.game = game;
		viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, new OrthographicCamera()); 
		stage = new Stage(viewport,((MyGdxGame)game).batch);
		
		Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(),Color.WHITE);
		
		Table table = new  Table();
		table.center();
		table.setFillParent(true);
		
		Label scoreLabel = new Label("Score = ",font);
		table.add(scoreLabel).expandX();
		table.add(Hud.scoreLabel).expandX();
		table.row();
		
		Label finishLabel = new Label("Level 1 complite",font);
		table.add(finishLabel).expandX();
		table.row();
		
		;
		Label playAgainLabel = new Label("Click to Play Again",font);
		table.add(playAgainLabel).expandX().padTop(10f);
		stage.addActor(table);
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.justTouched()){
			game.setScreen(new PlayScreen((MyGdxGame) game));
			dispose();
		}
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		
	}

}

