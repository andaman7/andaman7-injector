package biz.manex.andaman7.injector.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * A utility class to deal with files.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://a7-software.com/)
 * Date : 09/02/2015.
 */
public class FileHelper {

    /**
     * Returns a file in the current directory (where the program has been
     * launched).
     *
     * @param filename the name of the file
     * @return the expected file
     */
    public static File getFileInCurrentDir(String filename) {
        return new File(System.getProperty("user.dir") + "/" + filename);
    }

    /**
     * Returns a file in the resources directory of the project.
     *
     * @param filename the name of the file
     * @return the expected file
     * @throws FileNotFoundException if the file has not been found
     */
    public File getFileInResourcesDir(String filename) throws FileNotFoundException {
        URL url = FileHelper.class.getClassLoader().getResource(filename);

        if(url == null)
            throw new FileNotFoundException(String.format("File '%s' not found in resources directory.", filename));

        return new File(url.getFile());
    }
}
