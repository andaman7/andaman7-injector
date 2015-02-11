package biz.manex.andaman7.injector.utils;

import java.io.File;

/**
 * Created by Pierre-Yves on 09/02/2015.
 */
public class FileHelper {

    public static File getFileInCurrentDir(String filename) {
        return new File(System.getProperty("user.dir") + "/" + filename);
    }

    public File getFileInResourcesDir(String filename) {
        return new File(FileHelper.class.getClassLoader().getResource(filename).getFile());
    }
}
