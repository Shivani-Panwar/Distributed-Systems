
package com.librarySystem.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SOAPService", targetNamespace = "http://soap.librarySystem.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SOAPService {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listItemAvailability", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.ListItemAvailability")
    @ResponseWrapper(localName = "listItemAvailabilityResponse", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.ListItemAvailabilityResponse")
    @Action(input = "http://soap.librarySystem.com/SOAPService/listItemAvailabilityRequest", output = "http://soap.librarySystem.com/SOAPService/listItemAvailabilityResponse")
    public String listItemAvailability(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkAvailability", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.CheckAvailability")
    @ResponseWrapper(localName = "checkAvailabilityResponse", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.CheckAvailabilityResponse")
    @Action(input = "http://soap.librarySystem.com/SOAPService/checkAvailabilityRequest", output = "http://soap.librarySystem.com/SOAPService/checkAvailabilityResponse")
    public boolean checkAvailability(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkReturnPossible", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.CheckReturnPossible")
    @ResponseWrapper(localName = "checkReturnPossibleResponse", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.CheckReturnPossibleResponse")
    @Action(input = "http://soap.librarySystem.com/SOAPService/checkReturnPossibleRequest", output = "http://soap.librarySystem.com/SOAPService/checkReturnPossibleResponse")
    public boolean checkReturnPossible(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkBorrowPossible", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.CheckBorrowPossible")
    @ResponseWrapper(localName = "checkBorrowPossibleResponse", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.CheckBorrowPossibleResponse")
    @Action(input = "http://soap.librarySystem.com/SOAPService/checkBorrowPossibleRequest", output = "http://soap.librarySystem.com/SOAPService/checkBorrowPossibleResponse")
    public boolean checkBorrowPossible(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "borrowItem", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.BorrowItem")
    @ResponseWrapper(localName = "borrowItemResponse", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.BorrowItemResponse")
    @Action(input = "http://soap.librarySystem.com/SOAPService/borrowItemRequest", output = "http://soap.librarySystem.com/SOAPService/borrowItemResponse")
    public String borrowItem(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findItem", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.FindItem")
    @ResponseWrapper(localName = "findItemResponse", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.FindItemResponse")
    @Action(input = "http://soap.librarySystem.com/SOAPService/findItemRequest", output = "http://soap.librarySystem.com/SOAPService/findItemResponse")
    public String findItem(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addToQueue", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.AddToQueue")
    @ResponseWrapper(localName = "addToQueueResponse", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.AddToQueueResponse")
    @Action(input = "http://soap.librarySystem.com/SOAPService/addToQueueRequest", output = "http://soap.librarySystem.com/SOAPService/addToQueueResponse")
    public boolean addToQueue(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "removeItem", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.RemoveItem")
    @ResponseWrapper(localName = "removeItemResponse", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.RemoveItemResponse")
    @Action(input = "http://soap.librarySystem.com/SOAPService/removeItemRequest", output = "http://soap.librarySystem.com/SOAPService/removeItemResponse")
    public boolean removeItem(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "exchangeItem", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.ExchangeItem")
    @ResponseWrapper(localName = "exchangeItemResponse", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.ExchangeItemResponse")
    @Action(input = "http://soap.librarySystem.com/SOAPService/exchangeItemRequest", output = "http://soap.librarySystem.com/SOAPService/exchangeItemResponse")
    public boolean exchangeItem(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addItem", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.AddItem")
    @ResponseWrapper(localName = "addItemResponse", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.AddItemResponse")
    @Action(input = "http://soap.librarySystem.com/SOAPService/addItemRequest", output = "http://soap.librarySystem.com/SOAPService/addItemResponse")
    public boolean addItem(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        int arg3);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "returnItem", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.ReturnItem")
    @ResponseWrapper(localName = "returnItemResponse", targetNamespace = "http://soap.librarySystem.com/", className = "com.librarysystem.soap.ReturnItemResponse")
    @Action(input = "http://soap.librarySystem.com/SOAPService/returnItemRequest", output = "http://soap.librarySystem.com/SOAPService/returnItemResponse")
    public boolean returnItem(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

}
