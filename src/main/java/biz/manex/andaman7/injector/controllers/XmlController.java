package biz.manex.andaman7.injector.controllers;

import biz.manex.andaman7.injector.models.SelectionList;
import biz.manex.andaman7.injector.models.SelectionListItem;
import biz.manex.andaman7.injector.models.TamiGroup;
import biz.manex.andaman7.injector.models.types.MultivaluedQualifierType;
import biz.manex.andaman7.injector.models.types.MultivaluedTAMI;
import biz.manex.andaman7.injector.models.types.QualifierType;
import biz.manex.andaman7.injector.models.types.TAMI;
import biz.manex.andaman7.injector.utils.XmlHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.util.*;

/**
 * The controller that is used to parse the TAMI dictionary.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://www.a7-software.com)<br/>
 *         Date : 07/03/2015.
 */
public class XmlController {

    /**
     * Returns a map of selection lists.
     *
     * @param xmlDoc the XML document from which to get the selection lists
     * @param translations the translations of the keys
     * @return a map of selection lists
     */
    public Map<String, SelectionList> getSelectionLists(Document xmlDoc, HashMap<String, String> translations) {

        HashMap<String, SelectionList> selectionLists = new HashMap<String, SelectionList>();

        try {
            XPathExpression selectionListsExpr = XmlHelper.getXPathExpression("//SelectionList");
            NodeList selectionListsNodes = (NodeList) selectionListsExpr.evaluate(xmlDoc, XPathConstants.NODESET);

            for (int i = 0; i < selectionListsNodes.getLength(); i++) {
                Node node = selectionListsNodes.item(i);

                String slKey = node.getAttributes().getNamedItem("id").getNodeValue();
                String slName = translations.get(slKey);
                HashMap<String, SelectionListItem> items = new HashMap<String, SelectionListItem>();

                // Get the items of the selection list
                for (int j = 0; j < node.getChildNodes().getLength(); j++) {
                    Node childNode = node.getChildNodes().item(j);
                    if (childNode.getNodeName().equals("Item")) {

                        String itemKey = childNode.getAttributes().getNamedItem("id").getNodeValue();
                        String itemName;

                        if(translations.containsKey(itemKey))
                            itemName = translations.get(itemKey);
                        else
                            itemName = itemKey;

                        SelectionListItem item = new SelectionListItem(itemKey, itemName);
                        items.put(itemKey, item);
                    }
                }

                selectionLists.put(slKey, new SelectionList(slKey, items));
            }

        } catch(XPathExpressionException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }

        return selectionLists;
    }

    /**
     * Returns a list of the default qualifier types.
     *
     * @param doc the XML document from which to get the default qualifier types
     * @param translations the translation of the keys
     * @return a list of the default qualifier types
     */
    public List<QualifierType> getDefaultQualifierTypes(Document doc, HashMap<String, String> translations) {

        ArrayList<QualifierType> defaultQualifiersTypes = new ArrayList<QualifierType>();

        try {
            XPathExpression defaultQualifierTypesExpr = XmlHelper.getXPathExpression("//DefaultQualifier");
            NodeList defaultQualifierTypesNodes = (NodeList) defaultQualifierTypesExpr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < defaultQualifierTypesNodes.getLength(); i++) {
                Node node = defaultQualifierTypesNodes.item(i);
                String key = node.getAttributes().getNamedItem("id").getNodeValue();
                String value;

                if (key.equals("default.note"))
                    value = "Note";
                else if (key.equals("default.stars"))
                    continue;
                else
                    value = translations.get(key);

                defaultQualifiersTypes.add(new QualifierType(key, value));
            }
        } catch(XPathExpressionException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }

        return defaultQualifiersTypes;
    }

    /**
     * Returns a list of the qualifier types of a TAMI.
     *
     * @param defaultQualifierTypes the list of all default qualifier types
     * @param translations the translation of the keys
     * @param tamiNode the XML node of a TAMI
     * @param selectionLists all the available selection lists
     * @return a list of the qualifier types of a TAMI
     */
    public List<QualifierType> getQualifierTypes(List<QualifierType> defaultQualifierTypes,
            HashMap<String, String> translations, Node tamiNode, Map<String, SelectionList> selectionLists) {

        List<QualifierType> qualifierTypes = new ArrayList<QualifierType>();
        qualifierTypes.addAll(defaultQualifierTypes);

        NodeList childNodes = tamiNode.getChildNodes();

        // Process all qualifiers
        for (int j = 0; j < childNodes.getLength(); j++) {

            Node qualifierNode = childNodes.item(j);

            if(!qualifierNode.getNodeName().equals("Qualifier"))
                continue;

            String key = qualifierNode.getAttributes().getNamedItem("id").getNodeValue();
            String name;

            // Use the key as name if the key is not found in the translations
            if(translations.containsKey(key))
                name = translations.get(key);
            else
                name = key;

            String type = qualifierNode.getAttributes().getNamedItem("type").getNodeValue();

            // If the qualifier is multivalued
            if (type.equals("oneSelection") || type.equals("multiSelection")) {
                String selectionListId = qualifierNode.getAttributes().getNamedItem("selectionListId").getNodeValue();
                SelectionList selectionList = selectionLists.get(selectionListId);

                MultivaluedQualifierType qualifierType = new MultivaluedQualifierType(key, name, selectionList);
                qualifierTypes.add(qualifierType);

                // Otherwise
            } else {
                QualifierType qualifierType = new QualifierType(key, name);
                qualifierTypes.add(qualifierType);
            }
        }

        return qualifierTypes;
    }

    // TODO
    public List<TAMI> getAmis(Document doc, HashMap<String, String> translations, List<QualifierType> defaultQualifierTypes,
                              Map<String, SelectionList> selectionLists, Map<String, TamiGroup> tamiGroups) {

        List<TAMI> tamis = new ArrayList<>();

        try {
            XPathExpression amisExpr = XmlHelper.getXPathExpression("//Ami");
            NodeList amisNodes = (NodeList) amisExpr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < amisNodes.getLength(); i++) {

                Node node = amisNodes.item(i);

                // Ignore nodes that are not AMIs and nodes that don't have a tags attribute
                if (!node.getNodeName().equals("Ami") || node.getAttributes().getNamedItem("tags") == null)
                    continue;

                // Prepare the fields of the TAMI
                String key = node.getAttributes().getNamedItem("id").getNodeValue();
                String type = node.getAttributes().getNamedItem("type").getNodeValue();
                List<String> tags = Arrays.asList(node.getAttributes().getNamedItem("tags").getNodeValue().split(","));
                String tamiName;

                if (translations.containsKey(key))
                    tamiName = translations.get(key);
                else
                    tamiName = key;

                List<QualifierType> qualifierTypes = getQualifierTypes(defaultQualifierTypes, translations, node, selectionLists);

                // Create the TAMI and add it to the list
                TAMI tami;

                if (type.equals("oneSelection") || type.equals("multiSelection")) {
                    String selectionListId = node.getAttributes().getNamedItem("selectionListId").getNodeValue();
                    SelectionList selectionList = selectionLists.get(selectionListId);

                    MultivaluedTAMI multiTami = new MultivaluedTAMI(key, tamiName, selectionList, qualifierTypes);
                    tamis.add(multiTami);
                    tami = multiTami;

                } else {
                    tami = new TAMI(key, tamiName, qualifierTypes);
                    tamis.add(tami);
                }

                // Add the TAMI to its groups and add the group to the map if needed
                for (String tag : tags) {

                    TamiGroup tamiGroup;

                    if (tamiGroups.containsKey(tag)) {
                        tamiGroup = tamiGroups.get(tag);
                        tamiGroup.getTamis().add(tami);

                    } else {
                        String groupName;

                        if (translations.containsKey(tag))
                            groupName = translations.get(tag);
                        else
                            groupName = tag;

                        tamiGroup = new TamiGroup(tag, groupName, new ArrayList<TAMI>());
                        tamiGroup.getTamis().add(tami);
                        tamiGroups.put(tag, tamiGroup);
                    }
                }
            }
        } catch(XPathExpressionException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }

        return tamis;
    }
}
