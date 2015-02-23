package biz.manex.andaman7.injector.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A utility class to deal with properties files.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 11/02/2015.
 */
public class PropertyUtils {

    /**
     * The file containing the properties.
     * @see java.io.File
     */
    private final File file;

    /**
     * The properties.
     * @see java.util.Properties
     */
    private final Properties properties;

    /**
     * Builds a PropertyUtils instance.
     *
     * @param filename the name of the properties file
     */
    public PropertyUtils(String filename) {
        this(new File(filename));
    }

    /**
     * Builds a PropertyUtils instance.
     *
     * @param file the properties file
     */
    public PropertyUtils(File file) {

        this.file = file;
        properties = new Properties();

        try {
            properties.load(new FileInputStream(this.file));

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns the file containing the properties.
     *
     * @return the file containing the properties
     * @see java.io.File
     */
    public File getFile() {
        return file;
    }

    /**
     * Returns the properties.
     *
     * @return the properties
     * @see java.util.Properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Reloads the properties from the file.
     */
    public void reload() {

        try {
            properties.load(new FileInputStream(file));

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    /**
     * Saves the properties to the file.
     */
    public void save() {

        try {
            FileOutputStream outputFile = new FileOutputStream(file);
            properties.store(outputFile, null);
            outputFile.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    /**
     * Saves the changes to the file when the object is no more referenced.
     *
     * @throws Throwable if an error occurred
     */
    @Override
    protected void finalize() throws Throwable {
        save();

        super.finalize();
    }
}