package com.koles.gamedev.engin;

import com.koles.gamedev.io.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Settings {
    public static boolean soundEnabled = true;
    private static int[] highScores = {0, 0, 0, 0, 0};

    public static void loud(FileIO file){
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(file.readFile(
                    ".MrGreen")));
            soundEnabled = Boolean.parseBoolean(reader.readLine());
            for(int i = 0; i < highScores.length; i++){
                highScores[i] = Integer.parseInt(reader.readLine());
            }
        }catch (IOException e1){
            e1.printStackTrace();
        }catch (NumberFormatException e2){
            e2.printStackTrace();
        }finally {
            try{
                if(reader != null){
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void save (FileIO file){
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(file.writeFile(
                    ".MrGreen")));

            writer.write(Boolean.toString(soundEnabled));
            for (int i = 0; i < highScores.length; i++){
                writer.write(Integer.toString(highScores[i]));
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer != null){
                    writer.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static int[] getHighScores(){
        return highScores;
    }

    public static void addScore(int score){
        for(int i = 0; i < highScores.length; i++){
            if(highScores[i] < score){
                for(int j = 4; j > i; j--){
                    highScores[j] = highScores[j - 1];
                    highScores[i] = score;
                    break;
                }
            }
        }
    }



}
