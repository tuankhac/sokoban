package com.tuankhac.sokoban;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.assets.AssetManager;
import com.tuankhac.sokoban.asset.Asset;

public class MainMenu implements ApplicationListener {

	MainStage stage;
	public static boolean paused;
	@Override
	public void create() {
		Asset.instance.init(new AssetManager());
		stage = new MainStage();
	}

	@Override
	public void dispose() {
		stage.dispose();
		Asset.instance.dispose();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {	}

	@Override
	public void render() {  
		stage.act();
		stage.draw();
	}
}
