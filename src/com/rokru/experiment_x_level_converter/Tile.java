package com.rokru.experiment_x_level_converter;

import java.util.HashMap;

public class Tile {
	
	private int colorID;
	private String ID;
	private static HashMap<Integer, Tile> tileColorIDMap = new HashMap<Integer, Tile>();
	
	public static Tile voidTile = new Tile("e-x:"+0, 0xff242424);
	public static Tile grass = new Tile("e-x:"+1, 0xff67ae49);
	public static Tile flower_1 = new Tile("e-x:"+2, 0xffe7c60d);
	public static Tile rock_1 = new Tile("e-x:"+3, 0xff8f8f8f);
	public static Tile flower_2 = new Tile("e-x:"+4, 0xffc300d4);
	public static Tile rock_2 = new Tile("e-x:"+5, 0xff949494);
	public static Tile water_0 = new Tile("e-x:"+6, 0xff1f9be1);
	public static Tile water_1 = new Tile("e-x:"+7, 0xff1d98de);
	public static Tile water_2 = new Tile("e-x:"+8, 0xff5cacff);
	public static Tile tall_grass_1 = new Tile("e-x:"+9, 0xff62a646);
	public static Tile tall_grass_2 = new Tile("e-x:"+10, 0xff5fa143);
	public static Tile stone_bricks = new Tile ("e-x:"+11, 0xff404040);
	public static Tile clay_bricks = new Tile ("e-x:"+12, 0xff7F1900);
	public static Tile wooden_planks = new Tile ("e-x:"+13, 0xff351500);
	public static Tile wooden_slope_1 = new Tile ("e-x:"+14, 0xff3F1800);
	public static Tile wooden_slope_2 = new Tile ("e-x:"+15, 0xff4C1C00);
	public static Tile wooden_slope_3 = new Tile ("e-x:"+16, 0xff591F00);
	public static Tile wooden_slope_4 = new Tile ("e-x:"+17, 0xff662100);
	public static Tile metal_gray = new Tile ("e-x:"+18, 0xffBFBFBF);
	public static Tile teleporter_1 = new Tile ("e-x:"+19, 0xff0184ff);
	public static Tile teleporter_2 = new Tile ("e-x:"+20, 0xffc400ff);
	
	public Tile(String id, int colorID) {
		this.colorID = colorID;
		this.ID = id;
		tileColorIDMap.put(colorID, this);
	}
	
	public int getTileColorID(){
		return colorID;
	}
	
	public String getTileID(){
		return ID;
	}
	
	public static HashMap<Integer, Tile> getTileColorMap(){
		return tileColorIDMap;
	}
	
	public static Tile getTileFromColorID(int tileColorID){
		if(tileColorIDMap.containsKey(tileColorID)){
			return tileColorIDMap.get(tileColorID);
		}else if(tileColorID == 0xff9f00a2){
			return Tile.grass;
		}else{
			return Tile.voidTile;
		}
	}
}
