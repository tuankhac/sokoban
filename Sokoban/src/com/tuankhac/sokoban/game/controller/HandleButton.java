package com.tuankhac.sokoban.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.tuankhac.sokoban.game.level.Level;
import com.tuankhac.sokoban.game.object.Boxes;
import com.tuankhac.sokoban.game.object.Player;

public class HandleButton implements Disposable {
	private TextureAtlas buttonsAtlas; 
	private Skin buttonSkin;

	private Button next_button;
	private Button pre_button;
	private Button undo_button;
	private Button reset_button;
	private Level level;

	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	public HandleButton() {}
	public HandleButton(Level level){
		this.level = level;
		initButtons();
	}

	public void initButtons(){
		buttonsAtlas = new TextureAtlas("images/sokoban.atlas"); 
		buttonSkin = new Skin();
		buttonSkin.addRegions(buttonsAtlas); 

		next_button =initButton("next");
		pre_button = initButton("previous");
		undo_button = initButton("undo");
		reset_button = initButton("redo");

		activeButton(pre_button,"previous",width/24, height -100,width/14,width/14 );
		activeButton(next_button,"next",width/24,width/24,width/14,width/14);
		activeButton(undo_button,"undo", 9*width/10 - width/16  ,height/4-width/16  ,width/8,width/8);
		activeButton(reset_button,"redo", 9*width/10 - width/16 ,3*height/4-width/16 ,width/8,width/8 );

	}

	public void addAllButton(Stage stage){
		stage.addActor(undo_button);
		stage.addActor(next_button);
		stage.addActor(pre_button);
		stage.addActor(reset_button);
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
				if(buttons == reset_button){ level.init(1);	}
				if(buttons == undo_button){
					Player player;
					Array<Vector2> pointPlayer;
					Array<Vector2> pointBox;
					Array<Byte> orientPlayer;
					Array<Byte> orientBox;
					Array<Integer> indexBox;
					Array<Boxes> arrBox;
					player = level.player;
					pointPlayer = player.pointPlayer;
					orientPlayer = player.orientPlayer;
					arrBox = level.getArrBoxes();
					indexBox = player.indexBox;
					pointBox = player.pointBox;
					orientBox = player.orientBox;
					if(pointPlayer.size>0){

						//đặt lại vị trí trước đó cho player
						player.setX(pointPlayer.get(pointPlayer.size-1).x);
						player.setY(pointPlayer.get(pointPlayer.size-1).y);
						player.changeOrient(orientPlayer.get(orientPlayer.size-1));

						//đặt lại vị trí trước đó cho box
						arrBox.get(indexBox.get(indexBox.size-1)).setX(pointBox.get(pointBox.size-1).x);
						arrBox.get(indexBox.get(indexBox.size-1)).setY(pointBox.get(pointBox.size-1).y);
						arrBox.get(indexBox.get(indexBox.size-1)).changeOrient(orientBox.get(orientBox.size-1));

						//rời đi mảng phần tử cuối cùng của player
						pointPlayer.removeIndex(pointPlayer.size-1);
						orientPlayer.removeIndex(orientPlayer.size-1);

						//rời đi mảng phần tử cuối cùng của box
						pointBox.removeIndex(pointBox.size-1);
						indexBox.removeIndex(indexBox.size-1);
						orientBox.removeIndex(orientBox.size-1);
					}
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
