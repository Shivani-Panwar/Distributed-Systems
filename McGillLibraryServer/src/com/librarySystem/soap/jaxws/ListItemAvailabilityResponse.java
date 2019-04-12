
package com.librarySystem.soap.jaxws;


import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.librarySystem.model.*;

@XmlRootElement(name = "listItemAvailabilityResponse", namespace = "http://soap.librarySystem.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listItemAvailabilityResponse", namespace = "http://soap.librarySystem.com/")
public class ListItemAvailabilityResponse {

    @XmlElement(name = "return", namespace = "")
    private ArrayList<Item> _return;

    /**
     * 
     * @return
     *     returns String
     */
    public ArrayList<Item> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(ArrayList<Item> _return) {
        this._return = _return;
    }

}
