//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.11 at 10:08:02 AM CST 
//


package com.hotent.bpmx.core.defxml.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tChoreography complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tChoreography">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tCollaboration">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.omg.org/spec/BPMN/20100524/MODEL}flowElement" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tChoreography", propOrder = {
    "flowElement"
})
@XmlSeeAlso({
    GlobalChoreographyTask.class
})
public class Choreography
    extends Collaboration
{

    @XmlElementRef(name = "flowElement", namespace = "http://www.omg.org/spec/BPMN/20100524/MODEL", type = JAXBElement.class)
    protected List<JAXBElement<? extends FlowElement>> flowElement;

    /**
     * Gets the value of the flowElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flowElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlowElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link DataObject }{@code >}
     * {@link JAXBElement }{@code <}{@link ImplicitThrowEvent }{@code >}
     * {@link JAXBElement }{@code <}{@link UserTask }{@code >}
     * {@link JAXBElement }{@code <}{@link ScriptTask }{@code >}
     * {@link JAXBElement }{@code <}{@link DataStoreReference }{@code >}
     * {@link JAXBElement }{@code <}{@link ManualTask }{@code >}
     * {@link JAXBElement }{@code <}{@link ComplexGateway }{@code >}
     * {@link JAXBElement }{@code <}{@link ReceiveTask }{@code >}
     * {@link JAXBElement }{@code <}{@link StartEvent }{@code >}
     * {@link JAXBElement }{@code <}{@link IntermediateCatchEvent }{@code >}
     * {@link JAXBElement }{@code <}{@link FlowElement }{@code >}
     * {@link JAXBElement }{@code <}{@link AdHocSubProcess }{@code >}
     * {@link JAXBElement }{@code <}{@link IntermediateThrowEvent }{@code >}
     * {@link JAXBElement }{@code <}{@link CallActivity }{@code >}
     * {@link JAXBElement }{@code <}{@link SubProcess }{@code >}
     * {@link JAXBElement }{@code <}{@link Task }{@code >}
     * {@link JAXBElement }{@code <}{@link SubChoreography }{@code >}
     * {@link JAXBElement }{@code <}{@link ChoreographyTask }{@code >}
     * {@link JAXBElement }{@code <}{@link ExclusiveGateway }{@code >}
     * {@link JAXBElement }{@code <}{@link EndEvent }{@code >}
     * {@link JAXBElement }{@code <}{@link CallChoreography }{@code >}
     * {@link JAXBElement }{@code <}{@link ParallelGateway }{@code >}
     * {@link JAXBElement }{@code <}{@link BoundaryEvent }{@code >}
     * {@link JAXBElement }{@code <}{@link EventBasedGateway }{@code >}
     * {@link JAXBElement }{@code <}{@link BusinessRuleTask }{@code >}
     * {@link JAXBElement }{@code <}{@link DataObjectReference }{@code >}
     * {@link JAXBElement }{@code <}{@link Event }{@code >}
     * {@link JAXBElement }{@code <}{@link InclusiveGateway }{@code >}
     * {@link JAXBElement }{@code <}{@link ServiceTask }{@code >}
     * {@link JAXBElement }{@code <}{@link SendTask }{@code >}
     * {@link JAXBElement }{@code <}{@link Transaction }{@code >}
     * {@link JAXBElement }{@code <}{@link SequenceFlow }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends FlowElement>> getFlowElement() {
        if (flowElement == null) {
            flowElement = new ArrayList<JAXBElement<? extends FlowElement>>();
        }
        return this.flowElement;
    }

}
