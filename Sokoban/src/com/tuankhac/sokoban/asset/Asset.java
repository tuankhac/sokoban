package com.tuankhac.sokoban.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.tuankhac.sokoban.game.utils.Constants;

public class Asset implements Disposable {
	public Unit unit;
	public Box box;
	public Player player;
	public Gui gui;
	public StartGame start;
	public static final Asset instance = new Asset();

	private Asset() {}

	public void init(AssetManager assetManager) {
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		assetManager.finishLoading();
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		// enable texture filtering for pixel smoothing
		for (Texture t : atlas.getTextures())
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		// create game resource objects
		start = new StartGame(atlas);
		unit = new Unit(atlas);
		player = new Player(atlas);
		box = new Box(atlas);
		gui = new Gui(atlas);
		fonts = new AssetFonts();
	}

	@Override
	public void dispose() {
		fonts.defaultSmall.dispose();
	}

	public AssetFonts fonts;

	public class AssetFonts {
		public final BitmapFont defaultSmall;

		public AssetFonts() {
			// create three fonts using Libgdx's 15px bitmap font
			defaultSmall = new BitmapFont(	Gdx.files.internal("images/arial-15.fnt"), true);
			// set font sizes
			defaultSmall.setScale(0.75f);
			// enable linear texture filtering for smooth fonts
			defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}
}
