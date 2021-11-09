package com.koles.gamedev.media;

import java.io.IOException;

public interface Media {

    Music newGameMusicPlayer(String longMusicFile) throws IOException;
    SoundEffect newSoundEffect(String soundEffectFile) throws IOException;
}
