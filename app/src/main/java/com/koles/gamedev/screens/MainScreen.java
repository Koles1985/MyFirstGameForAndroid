package com.koles.gamedev.screens;
import com.koles.gamedev.engin.Game;
import com.koles.gamedev.engin.Settings;
import com.koles.gamedev.graphic.Assets;
import com.koles.gamedev.graphic.Graphics;
import com.koles.gamedev.input.Input;
import com.koles.gamedev.media.GameMusicPlayer;
import com.koles.gamedev.media.Media;
import com.koles.gamedev.media.Music;
import com.koles.gamedev.input.Input.TouchEvent;

import java.util.List;

public class MainScreen extends Screen{

    public MainScreen(Game game){
        super(game);
        Assets.getFonMusic().play();




    }

    @Override
    public void update(float deltaTime) {
        Input in = game.getInput();
        Graphics g = game.getGraphics();
        List<TouchEvent> mainScreenEventList = in.getTouchEventsList();
        System.out.println(mainScreenEventList.size() +
                "  -  Size mainScreenEventList  - 1");
        for(int i = 0; i < mainScreenEventList.size(); i++) {
            TouchEvent mainScreenEvent = mainScreenEventList.get(i);
            if(mainScreenEvent.getType() == TouchEvent.TOUCH_UP) {
                if (inBounds(mainScreenEvent, 0, g.getHeight() - 72,
                        72, g.getHeight())) {

                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled){
                        Assets.getClick().play(1.0f);
                        System.out.println(Assets.getClick().getSoundId());
                    }//if(soundEnabled) close
                }//if(inBounds) close

                if(inBounds(mainScreenEvent, 10, 400,
                        g.getWidth() - 10, 604)){
                    if(Settings.soundEnabled){
                        Assets.getClick().play(1.0f);
                    }//close if(soundEnabled)
                    //game.setScreen(new GameScreen(game));
                }//close if (inBounds)

                if(inBounds(mainScreenEvent, 10, 605,
                        g.getWidth() - 10, 809)){
                    if(Settings.soundEnabled){
                        Assets.getClick().play(1.0f);
                    }//close if(soundEnsbled)
                    game.setScreen(new HighscoresScreen(game));
                    System.out.println("i = " + i);
                }//close if(inBounds)
                if(inBounds(mainScreenEvent, 10,810,
                        g.getWidth() - 10, 1015)){
                    System.out.println(mainScreenEventList.size() +
                            "Size mainScreenEventList  - 2");
                    if(Settings.soundEnabled){
                        Assets.getClick().play(1.0f);
                    }//close if(soundEnabled)
                    //game.setScreen(new HelpScreen1(game));
                }//close if(inBounds)
            }//if(type == type) close
        }//for close
    }//update close

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawBitmap(Assets.getBackground(), 0,0);
        g.drawBitmap(Assets.getLogo(), 10, 150);
        g.drawBitmap(Assets.getMainMenu(), 10, 400 );
        if(Settings.soundEnabled) {
            //soundOn
            g.drawBitmap(Assets.getButtons(), 0, g.getHeight() - 72, 0, 72,
                    72, 72);

        }else{
            g.drawBitmap(Assets.getButtons(), 0, g.getHeight() - 72,
                    72, 72, 72, 72);

        }

    }

    @Override
    public void pause() {
        Assets.getFonMusic().pause();
    }

    @Override
    public void resume() {
        Assets.getFonMusic().play();
    }

    @Override
    public void dispose() {
        Assets.getFonMusic().stop();
    }
}
