//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.11 at 10:08:02 AM CST 
//


package com.hotent.bpmx.core.defxml.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for tExtension complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tExtension">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.omg.org/spec/BPMN/20100524/MODEL}documentation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="definition" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *       &lt;attribute name="mustUnderstand" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tExtension", propOrder = {
    "documentation"
})
public class Extension {

    protected List<Documentation> documentation;
    @XmlAttribute(name = "definition")
    protected QName definition;
    @XmlAttribute(name = "mustUnderstand")
    protected Boolean mustUnderstand;

    /**
     * Gets the value of the documentation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Documentation }
     * 
     * 
     */
    public List<Documentation> getDocumentation() {
        if (documentation == null) {
            documentation = new ArrayList<Documentation>();
        }
        return this.documentation;
    }

    /**
     * Gets the value of the definition property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getDefinition() {
        return definition;
    }

    /**
     * Sets the value of the definition property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setDefinition(QName value) {
        this.definition = value;
    }

    /**
     * Gets the value of the mustUnderstand property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isMustUnderstand() {
        if (mustUnderstand == null) {
            return false;
        } else {
            return mustUnderstand;
        }
    }

    /**
     * Sets the value of the mustUnderstand property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMustUnderstand(Boolean value) {
        this.mustUnderstand = value;
    }

}
