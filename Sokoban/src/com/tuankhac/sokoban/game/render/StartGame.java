package com.tuankhac.sokoban.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.tuankhac.sokoban.asset.Asset;
import com.tuankhac.sokoban.game.utils.Constants;
import com.tuankhac.sokoban.game.utils.Constants.STATE;

public class StartGame extends Actor implements Disposable {
	private TextureAtlas buttonsAtlas; 
	private Skin buttonSkin;

	public Button play;
	public Button option;
	
	public Button easyMode;
	public Button hardMode;

	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	
	public StartGame(){
		initButtons();
	}

	private void initButtons(){
		buttonsAtlas = new TextureAtlas("images/sokoban.atlas"); 
		buttonSkin = new Skin();
		buttonSkin.addRegions(buttonsAtlas); 

		play =initButton("play");
		option = initButton("option");
		
		easyMode =initButton("easymode");
		hardMode = initButton("hardmode");
		
		activeButton(play,"play",width/2 - width/8 ,height/2- height/20,width/4,height/10);
		activeButton(option,"option",width/2 - width/8, height/2 - 2 * height/10 ,width/4,height/10 );
		
		activeButton(easyMode,"easymode",width/2 - width/6 ,height/2- height/20,width/3,height/6);
		activeButton(hardMode,"hardmode",width/2 - width/6, height/2 - 4 * height/10 ,width/3,height/6 );
	}
	
	@Override public void draw(SpriteBatch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		if(Constants.state == STATE.BEGIN)
		batch.draw(Asset.instance.start.background, 0, 0, width, height);
		else if(Constants.state == STATE.MODE){
			batch.draw(Asset.instance.start.background, 0, 0, width, height);
			batch.draw(Asset.instance.start.selectMode, width/2 - width/5.3f, height - height/10, width/2.65f, height/10);
		}
	}

	private Button initButton(String name){
		ButtonStyle style = new ButtonStyle();
		style.up = buttonSkin.getDrawable(name);
		style.down = buttonSkin.getDrawable(name);
		return new Button(style);
	}

	private void activeButton(final Button buttons,String name,float x,float y, float width, float height){
		buttons.setSize(width, height);
		buttons.setPosition(x, y);
		buttons.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(buttons == play){
					buttons.remove();
					Constants.state = STATE.MODE;
				}
				if(buttons == easyMode){
					buttons.remove();
					Constants.state = STATE.RUNNING;
				}
				return true;
			}
		});
	}

	@Override public void dispose() {
		buttonSkin.dispose();
		buttonsAtlas.dispose();
	}

}
