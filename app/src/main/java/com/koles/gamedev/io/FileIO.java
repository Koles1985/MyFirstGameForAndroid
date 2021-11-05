package com.koles.gamedev.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO {
    InputStream readAssets(String assetsName)throws IOException;
    InputStream readFile(String fileName)throws IOException;
    OutputStream writeFile(String name)throws IOException;
}
