/*
 *
 * IBM Confidential
 * OCO Source Materials
 * 5724X98 5724Y15 5655V82 5724X99 5724Y16 5655V89 5725B69 5655W88 5725C52 5655W90 5655Y31
 * Copyright IBM Corp. 1987, 2019
 * The source code for this program is not published or other-
 * wise divested of its trade secrets, irrespective of what has
 * been deposited with the U.S Copyright Office.
 *
 */

package com.ibm.decisionMicroservice.codeGen.processor.swagger.trace;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The serialized representation of a Java object instance.
 * 
 * <p>Java class for DataObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;any processContents='skip' minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="serializer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataObject", propOrder = {
    "content"
})
public class DataObject {

    @XmlMixed
    @XmlAnyElement
    protected List<Object> content;
    @XmlAttribute(name = "serializer")
    protected String serializer;

    /**
     * The serialized representation of a Java object instance.Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * {@link String }
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

    /**
     * Gets the value of the serializer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerializer() {
        return serializer;
    }

    /**
     * Sets the value of the serializer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerializer(String value) {
        this.serializer = value;
    }

}
