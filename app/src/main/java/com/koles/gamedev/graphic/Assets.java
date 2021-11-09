package com.koles.gamedev.graphic;

import android.graphics.Bitmap;

import com.koles.gamedev.engin.Game;
import com.koles.gamedev.media.Music;
import com.koles.gamedev.media.SoundEffect;

import java.io.IOException;

public final class Assets {
    private static Assets instance;

    private static Bitmap background;
    private static Bitmap logo;
    private static Bitmap mainMenu;
    private static Bitmap buttons;
    private static Bitmap headUp;
    private static Bitmap headRight;
    private static Bitmap headDown;
    private static Bitmap headLeft;
    private static Bitmap help1;
    private static Bitmap help2;
    private static Bitmap numbers;
    private static Bitmap part;
    private static Bitmap paused;
    private static Bitmap ready;
    private static Bitmap stain1;
    private static Bitmap stain2;
    private static Bitmap stain3;


    private static SoundEffect click;
    private static Music fonMusic;

    private Assets (Game game) throws IOException {
        Graphics g = game.getGraphics();

        background = g.newBitmap("background.png");
        logo = g.newBitmap("logo.png");
        mainMenu = g.newBitmap("main_menu.png");
        buttons = g.newBitmap("buttons.png");
        headUp = g.newBitmap("head_up.png");
        headRight = g.newBitmap("head_r.png");
        headDown = g.newBitmap("head_d.png");
        headLeft = g.newBitmap("head_l.png");
        help1 = g.newBitmap("help1.png");
        help2 = g.newBitmap("help2.png");
        numbers = g.newBitmap("numbers.png");
        part = g.newBitmap("part.png");
        paused  = g.newBitmap("paused.png");
        ready = g.newBitmap("ready.png");
        stain1 = g.newBitmap("stain1.png");
        stain2 = g.newBitmap("stain2.png");
        stain3 = g.newBitmap("stain3.png");

        click = game.getMedia().newSoundEffect("click.ogg");
        fonMusic = game.getMedia().newGameMusicPlayer("background_sound.ogg");

    }

    public static Bitmap getBackground(){
        return background;
    }
    public static Bitmap getLogo(){
        return logo;
    }
    public static Bitmap getMainMenu(){
        return mainMenu;
    }
    public static Bitmap getButtons(){
        return buttons;
    }
    public static Bitmap getHeadUp(){
        return headUp;
    }
    public static Bitmap getHeadRight(){
        return headRight;
    }
    public static Bitmap getHeadDown(){
        return headDown;
    }
    public static Bitmap getHeadLeft(){
        return headLeft;
    }
    public static Bitmap getHelp1(){
        return help1;
    }
    public static Bitmap getHelp2(){
        return help2;
    }
    public static Bitmap getNumbers(){
        return numbers;
    }
    public static Bitmap getPart(){
        return part;
    }
    public static Bitmap getPaused(){
        return paused;
    }
    public static Bitmap getReady(){
        return ready;
    }
    public static Bitmap getStain1(){
        return stain1;
    }
    public static Bitmap getStain2(){
        return stain2;
    }
    public static Bitmap getStain3(){
        return stain3;
    }

    public static SoundEffect getClick(){
        return click;
    }
    public static Music getFonMusic(){
        return fonMusic;
    }


    public static Assets setAssets(Game game){
       try {
           if (instance == null) {
               instance = new Assets(game);
           }
       }catch (IOException e){
           e.printStackTrace();
       }
        return instance;
    }



}
