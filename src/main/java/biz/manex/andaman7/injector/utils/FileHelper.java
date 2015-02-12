package biz.manex.andaman7.injector.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 *
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://a7-software.com/)
 * Date : 09/02/2015.
 */
public class FileHelper {

    public static File getFileInCurrentDir(String filename) {
        return new File(System.getProperty("user.dir") + "/" + filename);
    }

    public File getFileInResourcesDir(String filename) throws FileNotFoundException {
        URL url = FileHelper.class.getClassLoader().getResource(filename);

        if(url == null)
            throw new FileNotFoundException(String.format("File '%s' not found in resources directory.", filename));

        return new File(url.getFile());
    }
}
