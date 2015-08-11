package com.tuankhac.sokoban.game.utils;

public class Constants {
	public static final String TEXTURE_ATLAS_OBJECTS = "images/sokoban.atlas";
	public static int LEVEL = 1;
	public static int LIVES_START;
	
	public static final int NUM_BLOCK = 16;
	public static final float SIZE = 28;

	public static final byte LEFT_ORIENT = 0;
	public static final byte LEFT_ORIENT_CHANGE_COLOR = 4;
	public static final byte RIGHT_ORIENT = 1;
	public static final byte RIGHT_ORIENT_CHANGE_COLOR = 5;
	public static final byte UP_ORIENT = 2;
	public static final byte UP_ORIENT_CHANGE_COLOR = 6;
	public static final byte DOWN_ORIENT = 3;
	public static final byte DOWN_ORIENT_CHANGE_COLOR = 7;

	public static final byte PLAYER_ID = 1;
	public static final byte BOX_ID = -1;
	
	public static final byte SIZE_BUTTON = 100;
	
	public enum STATE{BEGIN,MODE,RUNNING}
	public static  STATE state = STATE.BEGIN;
}
