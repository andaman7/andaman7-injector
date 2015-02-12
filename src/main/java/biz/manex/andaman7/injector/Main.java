package biz.manex.andaman7.injector;

import biz.manex.andaman7.injector.controllers.MainController;
import biz.manex.andaman7.injector.utils.FileHelper;
import biz.manex.andaman7.injector.utils.PropertyUtils;
import biz.manex.andaman7.injector.views.LoginFrame;
import biz.manex.andaman7.injector.views.MainFrame;
import javax.swing.UIManager;
import java.io.File;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 19/01/2015.
 */
public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        File propertyFile = FileHelper.getFileInCurrentDir("config_local.properties");
        //File propertyFile = FileHelper.getFileInCurrentDir("config_prod.properties");
        PropertyUtils propertyUtils = new PropertyUtils(propertyFile);

        MainController mainController = new MainController();
        LoginFrame loginFrame = new LoginFrame(mainController, mainController, propertyUtils.getPropFile());
        MainFrame mainFrame = new MainFrame(mainController, mainController);
        mainController.start(loginFrame, mainFrame);
    }
}
