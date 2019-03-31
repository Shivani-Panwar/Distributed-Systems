
package com.librarySystem.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.librarysystem.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListItemAvailability_QNAME = new QName("http://soap.librarySystem.com/", "listItemAvailability");
    private final static QName _BorrowItemResponse_QNAME = new QName("http://soap.librarySystem.com/", "borrowItemResponse");
    private final static QName _AddItemResponse_QNAME = new QName("http://soap.librarySystem.com/", "addItemResponse");
    private final static QName _CheckBorrowPossible_QNAME = new QName("http://soap.librarySystem.com/", "checkBorrowPossible");
    private final static QName _CheckAvailabilityResponse_QNAME = new QName("http://soap.librarySystem.com/", "checkAvailabilityResponse");
    private final static QName _ListItemAvailabilityResponse_QNAME = new QName("http://soap.librarySystem.com/", "listItemAvailabilityResponse");
    private final static QName _CheckReturnPossible_QNAME = new QName("http://soap.librarySystem.com/", "checkReturnPossible");
    private final static QName _AddToQueue_QNAME = new QName("http://soap.librarySystem.com/", "addToQueue");
    private final static QName _FindItem_QNAME = new QName("http://soap.librarySystem.com/", "findItem");
    private final static QName _AddItem_QNAME = new QName("http://soap.librarySystem.com/", "addItem");
    private final static QName _AddToQueueResponse_QNAME = new QName("http://soap.librarySystem.com/", "addToQueueResponse");
    private final static QName _ReturnItem_QNAME = new QName("http://soap.librarySystem.com/", "returnItem");
    private final static QName _CheckAvailability_QNAME = new QName("http://soap.librarySystem.com/", "checkAvailability");
    private final static QName _CheckBorrowPossibleResponse_QNAME = new QName("http://soap.librarySystem.com/", "checkBorrowPossibleResponse");
    private final static QName _BorrowItem_QNAME = new QName("http://soap.librarySystem.com/", "borrowItem");
    private final static QName _ReturnItemResponse_QNAME = new QName("http://soap.librarySystem.com/", "returnItemResponse");
    private final static QName _RemoveItemResponse_QNAME = new QName("http://soap.librarySystem.com/", "removeItemResponse");
    private final static QName _ExchangeItem_QNAME = new QName("http://soap.librarySystem.com/", "exchangeItem");
    private final static QName _CheckReturnPossibleResponse_QNAME = new QName("http://soap.librarySystem.com/", "checkReturnPossibleResponse");
    private final static QName _ExchangeItemResponse_QNAME = new QName("http://soap.librarySystem.com/", "exchangeItemResponse");
    private final static QName _FindItemResponse_QNAME = new QName("http://soap.librarySystem.com/", "findItemResponse");
    private final static QName _RemoveItem_QNAME = new QName("http://soap.librarySystem.com/", "removeItem");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.librarysystem.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RemoveItem }
     * 
     */
    public RemoveItem createRemoveItem() {
        return new RemoveItem();
    }

    /**
     * Create an instance of {@link ExchangeItemResponse }
     * 
     */
    public ExchangeItemResponse createExchangeItemResponse() {
        return new ExchangeItemResponse();
    }

    /**
     * Create an instance of {@link FindItemResponse }
     * 
     */
    public FindItemResponse createFindItemResponse() {
        return new FindItemResponse();
    }

    /**
     * Create an instance of {@link CheckReturnPossibleResponse }
     * 
     */
    public CheckReturnPossibleResponse createCheckReturnPossibleResponse() {
        return new CheckReturnPossibleResponse();
    }

    /**
     * Create an instance of {@link ExchangeItem }
     * 
     */
    public ExchangeItem createExchangeItem() {
        return new ExchangeItem();
    }

    /**
     * Create an instance of {@link RemoveItemResponse }
     * 
     */
    public RemoveItemResponse createRemoveItemResponse() {
        return new RemoveItemResponse();
    }

    /**
     * Create an instance of {@link BorrowItem }
     * 
     */
    public BorrowItem createBorrowItem() {
        return new BorrowItem();
    }

    /**
     * Create an instance of {@link ReturnItemResponse }
     * 
     */
    public ReturnItemResponse createReturnItemResponse() {
        return new ReturnItemResponse();
    }

    /**
     * Create an instance of {@link CheckBorrowPossibleResponse }
     * 
     */
    public CheckBorrowPossibleResponse createCheckBorrowPossibleResponse() {
        return new CheckBorrowPossibleResponse();
    }

    /**
     * Create an instance of {@link CheckAvailability }
     * 
     */
    public CheckAvailability createCheckAvailability() {
        return new CheckAvailability();
    }

    /**
     * Create an instance of {@link ReturnItem }
     * 
     */
    public ReturnItem createReturnItem() {
        return new ReturnItem();
    }

    /**
     * Create an instance of {@link AddItem }
     * 
     */
    public AddItem createAddItem() {
        return new AddItem();
    }

    /**
     * Create an instance of {@link AddToQueueResponse }
     * 
     */
    public AddToQueueResponse createAddToQueueResponse() {
        return new AddToQueueResponse();
    }

    /**
     * Create an instance of {@link AddToQueue }
     * 
     */
    public AddToQueue createAddToQueue() {
        return new AddToQueue();
    }

    /**
     * Create an instance of {@link FindItem }
     * 
     */
    public FindItem createFindItem() {
        return new FindItem();
    }

    /**
     * Create an instance of {@link CheckReturnPossible }
     * 
     */
    public CheckReturnPossible createCheckReturnPossible() {
        return new CheckReturnPossible();
    }

    /**
     * Create an instance of {@link CheckAvailabilityResponse }
     * 
     */
    public CheckAvailabilityResponse createCheckAvailabilityResponse() {
        return new CheckAvailabilityResponse();
    }

    /**
     * Create an instance of {@link ListItemAvailabilityResponse }
     * 
     */
    public ListItemAvailabilityResponse createListItemAvailabilityResponse() {
        return new ListItemAvailabilityResponse();
    }

    /**
     * Create an instance of {@link AddItemResponse }
     * 
     */
    public AddItemResponse createAddItemResponse() {
        return new AddItemResponse();
    }

    /**
     * Create an instance of {@link CheckBorrowPossible }
     * 
     */
    public CheckBorrowPossible createCheckBorrowPossible() {
        return new CheckBorrowPossible();
    }

    /**
     * Create an instance of {@link BorrowItemResponse }
     * 
     */
    public BorrowItemResponse createBorrowItemResponse() {
        return new BorrowItemResponse();
    }

    /**
     * Create an instance of {@link ListItemAvailability }
     * 
     */
    public ListItemAvailability createListItemAvailability() {
        return new ListItemAvailability();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListItemAvailability }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "listItemAvailability")
    public JAXBElement<ListItemAvailability> createListItemAvailability(ListItemAvailability value) {
        return new JAXBElement<ListItemAvailability>(_ListItemAvailability_QNAME, ListItemAvailability.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BorrowItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "borrowItemResponse")
    public JAXBElement<BorrowItemResponse> createBorrowItemResponse(BorrowItemResponse value) {
        return new JAXBElement<BorrowItemResponse>(_BorrowItemResponse_QNAME, BorrowItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "addItemResponse")
    public JAXBElement<AddItemResponse> createAddItemResponse(AddItemResponse value) {
        return new JAXBElement<AddItemResponse>(_AddItemResponse_QNAME, AddItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckBorrowPossible }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "checkBorrowPossible")
    public JAXBElement<CheckBorrowPossible> createCheckBorrowPossible(CheckBorrowPossible value) {
        return new JAXBElement<CheckBorrowPossible>(_CheckBorrowPossible_QNAME, CheckBorrowPossible.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAvailabilityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "checkAvailabilityResponse")
    public JAXBElement<CheckAvailabilityResponse> createCheckAvailabilityResponse(CheckAvailabilityResponse value) {
        return new JAXBElement<CheckAvailabilityResponse>(_CheckAvailabilityResponse_QNAME, CheckAvailabilityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListItemAvailabilityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "listItemAvailabilityResponse")
    public JAXBElement<ListItemAvailabilityResponse> createListItemAvailabilityResponse(ListItemAvailabilityResponse value) {
        return new JAXBElement<ListItemAvailabilityResponse>(_ListItemAvailabilityResponse_QNAME, ListItemAvailabilityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckReturnPossible }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "checkReturnPossible")
    public JAXBElement<CheckReturnPossible> createCheckReturnPossible(CheckReturnPossible value) {
        return new JAXBElement<CheckReturnPossible>(_CheckReturnPossible_QNAME, CheckReturnPossible.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddToQueue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "addToQueue")
    public JAXBElement<AddToQueue> createAddToQueue(AddToQueue value) {
        return new JAXBElement<AddToQueue>(_AddToQueue_QNAME, AddToQueue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "findItem")
    public JAXBElement<FindItem> createFindItem(FindItem value) {
        return new JAXBElement<FindItem>(_FindItem_QNAME, FindItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "addItem")
    public JAXBElement<AddItem> createAddItem(AddItem value) {
        return new JAXBElement<AddItem>(_AddItem_QNAME, AddItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddToQueueResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "addToQueueResponse")
    public JAXBElement<AddToQueueResponse> createAddToQueueResponse(AddToQueueResponse value) {
        return new JAXBElement<AddToQueueResponse>(_AddToQueueResponse_QNAME, AddToQueueResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "returnItem")
    public JAXBElement<ReturnItem> createReturnItem(ReturnItem value) {
        return new JAXBElement<ReturnItem>(_ReturnItem_QNAME, ReturnItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAvailability }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "checkAvailability")
    public JAXBElement<CheckAvailability> createCheckAvailability(CheckAvailability value) {
        return new JAXBElement<CheckAvailability>(_CheckAvailability_QNAME, CheckAvailability.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckBorrowPossibleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "checkBorrowPossibleResponse")
    public JAXBElement<CheckBorrowPossibleResponse> createCheckBorrowPossibleResponse(CheckBorrowPossibleResponse value) {
        return new JAXBElement<CheckBorrowPossibleResponse>(_CheckBorrowPossibleResponse_QNAME, CheckBorrowPossibleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BorrowItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "borrowItem")
    public JAXBElement<BorrowItem> createBorrowItem(BorrowItem value) {
        return new JAXBElement<BorrowItem>(_BorrowItem_QNAME, BorrowItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "returnItemResponse")
    public JAXBElement<ReturnItemResponse> createReturnItemResponse(ReturnItemResponse value) {
        return new JAXBElement<ReturnItemResponse>(_ReturnItemResponse_QNAME, ReturnItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "removeItemResponse")
    public JAXBElement<RemoveItemResponse> createRemoveItemResponse(RemoveItemResponse value) {
        return new JAXBElement<RemoveItemResponse>(_RemoveItemResponse_QNAME, RemoveItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExchangeItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "exchangeItem")
    public JAXBElement<ExchangeItem> createExchangeItem(ExchangeItem value) {
        return new JAXBElement<ExchangeItem>(_ExchangeItem_QNAME, ExchangeItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckReturnPossibleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "checkReturnPossibleResponse")
    public JAXBElement<CheckReturnPossibleResponse> createCheckReturnPossibleResponse(CheckReturnPossibleResponse value) {
        return new JAXBElement<CheckReturnPossibleResponse>(_CheckReturnPossibleResponse_QNAME, CheckReturnPossibleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExchangeItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "exchangeItemResponse")
    public JAXBElement<ExchangeItemResponse> createExchangeItemResponse(ExchangeItemResponse value) {
        return new JAXBElement<ExchangeItemResponse>(_ExchangeItemResponse_QNAME, ExchangeItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "findItemResponse")
    public JAXBElement<FindItemResponse> createFindItemResponse(FindItemResponse value) {
        return new JAXBElement<FindItemResponse>(_FindItemResponse_QNAME, FindItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.librarySystem.com/", name = "removeItem")
    public JAXBElement<RemoveItem> createRemoveItem(RemoveItem value) {
        return new JAXBElement<RemoveItem>(_RemoveItem_QNAME, RemoveItem.class, null, value);
    }

}
