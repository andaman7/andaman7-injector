package biz.manex.andaman7.injector.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Pierre-Yves on 11/02/2015.
 */
public class PropertyUtils {

    private File file;
    private Properties propFile;


    public PropertyUtils(String filename) {
        this(new File(filename));
    }

    public PropertyUtils(File file) {

        this.file = file;
        propFile = new Properties();

        try {
            this.propFile.load(new FileInputStream(this.file));

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public File getFile() {
        return file;
    }

    public Properties getPropFile() {
        return propFile;
    }

    public void reload() {

        try {
            this.propFile.load(new FileInputStream(this.file));

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void save() {

        try {
            FileOutputStream outputFile = new FileOutputStream(file);
            propFile.store(outputFile, null);
            outputFile.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        save();

        super.finalize();
    }
}