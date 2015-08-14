package com.rokru.experiment_x_level_converter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class LevelConverter {
	
	public LevelConverter(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("==========================================================");
		System.out.println("Welcome to the Experiment X map/ level converter!");
		System.out.println("Please enter the file path of the image to be converted:");
		setFilePath(getInput(scanner));
		System.out.println("==========================================================");
		scanner.close();
		System.exit(0);
	}
	
	private void setFilePath(String input) {
		try{
			File img = new File(input);
			if(img.exists()){
				System.out.println("File Located!");
				String extension = img.getName().split("\\.")[1];
				if(extension.equals("png") || extension.equals("jpg") || extension.equals("tif")){
					loadFile(img);
				}else throw new Exception("File is not an image!");
			}else throw new Exception("Image file does not exist!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void loadFile(File img) {
		try {
			BufferedImage image = ImageIO.read(img);
			int w = image.getWidth();
			int h = image.getHeight();
			int[] pixelColors = new int[w*h];
			image.getRGB(0, 0, w, h, pixelColors, 0, w);
			System.out.println("Image file loaded!");
			interpretPixelColors(img, pixelColors, w, h);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Exception! Could not load level file!");
		}
	}
	
	private void interpretPixelColors(File img, int[] pixelColors, int w, int h){
		String[] tileGrid = new String[w * h];
		for(int i = 0; i < pixelColors.length; i++){
			if(pixelColors[i] == 0xff9f00a2){
				tileGrid[i] = "e-x:random_grassy";
			}else{
				if(Tile.getTileColorMap().containsKey(pixelColors[i])){
					tileGrid[i] = Tile.getTileColorMap().get(pixelColors[i]).getTileID();
				}else{
					tileGrid[i] = Tile.voidTile.getTileID();
				}
			}
		}
		writeConvertedFile(img, tileGrid, w, h);
	}

	//TODO Fix
	private void writeConvertedFile(File img, String[] tileGrid, int w, int h) {
		File f = new File(img.getParentFile().getAbsolutePath() + "/" + img.getName().split("\\.")[0] + ".map");
		try{
			f.createNewFile();
			FileWriter fwrite = new FileWriter(f);
			fwrite.write("{");
			for(int q = 0; q < h; q++){
				for(int i = 0; i < w; i++){
					int index = w * q + i;
					/*if(q <= 5 && i <= 5){
						System.out.println("q: " + q + " i: " + i);
						System.out.println("index: " + index);
					}*/
					if(index != (w*h-1))
						fwrite.write("\"" + tileGrid[index] + "\",");
					else
						fwrite.write("\"" + tileGrid[index] + "\"");
				}
				if(q != h-1)
					fwrite.write("\n");
			}
			fwrite.write("}");
			fwrite.flush();
			fwrite.close();
			System.out.println("Conversion completed!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getInput(Scanner scanner) {
		System.out.print("> ");
		return scanner.nextLine();
	}

	public static void main(String[] args) {
		new LevelConverter();
	}

}
