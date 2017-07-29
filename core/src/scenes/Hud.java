package scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class Hud implements Disposable{
	public Stage stage;
	private Viewport viewport;
	private Integer worldTimer;
	private float timeCount;
	private static Integer score;
	
	Label countdownLabel;
	static Label  scoreLabel;
	Label timeLabel;
	Label levelLabel;
	Label worldnLabel;
	Label titleLabel;
	
	public Hud(SpriteBatch sb){
		worldTimer = 500;
		timeCount = 0;
		score = 0;
		viewport = new FitViewport(MyGdxGame.V_WIDTH,MyGdxGame.V_HEIGHT,new OrthographicCamera());
		stage = new Stage(viewport, sb);
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		countdownLabel = new Label(String.format("%03d", worldTimer),new Label.LabelStyle(new BitmapFont(),Color.ORANGE));
		scoreLabel = new Label(String.format("%04d", score),new Label.LabelStyle(new BitmapFont(),Color.ORANGE));
		timeLabel = new Label("TIME",new Label.LabelStyle(new BitmapFont(),Color.ORANGE));
		levelLabel = new Label("1-1",new Label.LabelStyle(new BitmapFont(),Color.ORANGE));
		worldnLabel = new Label("WORLD",new Label.LabelStyle(new BitmapFont(),Color.ORANGE));
		titleLabel = new Label("Kapitan Bomba",new Label.LabelStyle(new BitmapFont(),Color.ORANGE));
		
		table.add(titleLabel).expandX().padTop(1);
		table.add(worldnLabel).expandX().padTop(1);
		table.add(timeLabel).expandX().padTop(1);
		table.row();
		table.add(scoreLabel).expandX();
		table.add(levelLabel).expandX();
		table.add(countdownLabel).expandX();
		
		stage.addActor(table);
	
	}
	public void update(float dt){
		timeCount += dt;
		
		if (timeCount >= 1){
			
			worldTimer--;
			countdownLabel.setText(String.format("%03d", worldTimer));
			timeCount = 0;
		}
	}
	
	public static void addScore(int scr){
		
		score+=scr;
		scoreLabel.setText(String.format("%04d", score));
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		
	}
	
}