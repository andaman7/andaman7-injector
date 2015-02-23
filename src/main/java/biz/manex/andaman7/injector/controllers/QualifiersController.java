/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manex.andaman7.injector.controllers;

import biz.manex.andaman7.injector.models.AMI;
import biz.manex.andaman7.injector.models.QualifiableItem;
import biz.manex.andaman7.injector.models.Qualifier;
import biz.manex.andaman7.injector.models.types.QualifierType;
import biz.manex.andaman7.injector.views.MainFrame;
import biz.manex.andaman7.injector.views.QualifiersDialog;
import biz.manex.andaman7.injector.webservice.REST.AndamanContextService;
import biz.manex.andaman7.injector.webservice.REST.AndamanEhrService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pierre-Yves
 */
public class QualifiersController {
    
    /**
     * The context web service.
     */
    private AndamanContextService contextService;

    /**
     * The EHR web service.
     */
    private AndamanEhrService ehrService;
    
    /**
     * The main controller.
     */
    private MainController mainController;
    
    /**
     * The main GUI frame.
     */
    private MainFrame mainFrame;
    
    /**
     * The qualifiers GUI dialog.
     */
    private QualifiersDialog qualifiersDialog;
    

    /**
     * Builds a qualifiers controller.
     * 
     * @param mainController the main controller
     * @param contextService the connection to the context web service
     * @param ehrService the connection to the EHR web service
     */
    public QualifiersController(MainController mainController, AndamanContextService contextService, AndamanEhrService ehrService) {
        this.mainController = mainController;
        this.contextService = contextService;
        this.ehrService = ehrService;
    }

    /**
     * Returns the qualifiers GUI dialog.
     * 
     * @return the qualifiers GUI dialog
     */
    public QualifiersDialog getQualifiersDialog() {
        return qualifiersDialog;
    }
    
    public QualifiableItem showQualifiersDialog(QualifiableItem item, List<QualifierType> qualifierTypes) {
        
        qualifiersDialog = new QualifiersDialog(mainFrame, true, this);
        qualifiersDialog.setQualifierTypes(qualifierTypes);
        qualifiersDialog.setQualifiers(item.getQualifiers());
        qualifiersDialog.setVisible(true);
        
        if(qualifiersDialog.getCloseStatus() == QualifiersDialog.Status.VALIDATED)
            item.setQualifiers(qualifiersDialog.getQualifiers());
        
        return item;
    }
}
