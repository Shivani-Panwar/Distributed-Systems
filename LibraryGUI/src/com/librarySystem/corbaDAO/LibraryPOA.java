package com.librarySystem.corbaDAO;


/**
* corbaDAO/LibraryPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CorbaDAO.IDL
* Sunday, 3 March, 2019 1:41:35 AM IST
*/

@SuppressWarnings("unchecked")
public abstract class LibraryPOA extends org.omg.PortableServer.Servant
 implements com.librarySystem.corbaDAO.LibraryOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  @SuppressWarnings("rawtypes")
private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("addItem", new java.lang.Integer (0));
    _methods.put ("removeItem", new java.lang.Integer (1));
    _methods.put ("listItemAvailability", new java.lang.Integer (2));
    _methods.put ("borrowItem", new java.lang.Integer (3));
    _methods.put ("findItem", new java.lang.Integer (4));
    _methods.put ("returnItem", new java.lang.Integer (5));
    _methods.put ("addToQueue", new java.lang.Integer (6));
    _methods.put ("exchangeItem", new java.lang.Integer (7));
    _methods.put ("checkAvailability", new java.lang.Integer (8));
    _methods.put ("checkBorrowPossible", new java.lang.Integer (9));
    _methods.put ("checkReturnPossible", new java.lang.Integer (10));
    _methods.put ("shutdown", new java.lang.Integer (11));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // corbaDAO/Library/addItem
       {
         String managerID = in.read_string ();
         String itemID = in.read_string ();
         String itemName = in.read_string ();
         int quantity = in.read_long ();
         boolean $result = false;
         $result = this.addItem (managerID, itemID, itemName, quantity);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // corbaDAO/Library/removeItem
       {
         String managerID = in.read_string ();
         String itemID = in.read_string ();
         int quantity = in.read_long ();
         boolean $result = false;
         $result = this.removeItem (managerID, itemID, quantity);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 2:  // corbaDAO/Library/listItemAvailability
       {
         String managerID = in.read_string ();
         String $result = null;
         $result = this.listItemAvailability (managerID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // corbaDAO/Library/borrowItem
       {
         String userID = in.read_string ();
         String itemID = in.read_string ();
         int numberOfDays = in.read_long ();
         String $result = null;
         $result = this.borrowItem (userID, itemID, numberOfDays);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // corbaDAO/Library/findItem
       {
         String userID = in.read_string ();
         String itemName = in.read_string ();
         String $result = null;
         $result = this.findItem (userID, itemName);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 5:  // corbaDAO/Library/returnItem
       {
         String userID = in.read_string ();
         String itemID = in.read_string ();
         boolean $result = false;
         $result = this.returnItem (userID, itemID);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 6:  // corbaDAO/Library/addToQueue
       {
         String itemID = in.read_string ();
         String userID = in.read_string ();
         boolean $result = false;
         $result = this.addToQueue (itemID, userID);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 7:  // corbaDAO/Library/exchangeItem
       {
         String userID = in.read_string ();
         String oldItem = in.read_string ();
         String newItem = in.read_string ();
         boolean $result = false;
         $result = this.exchangeItem (userID, oldItem, newItem);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 8:  // corbaDAO/Library/checkAvailability
       {
         String itemID = in.read_string ();
         boolean $result = false;
         $result = this.checkAvailability (itemID);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 9:  // corbaDAO/Library/checkBorrowPossible
       {
         String userID = in.read_string ();
         String itemID = in.read_string ();
         boolean $result = false;
         $result = this.checkBorrowPossible (userID, itemID);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 10:  // corbaDAO/Library/checkReturnPossible
       {
         String userID = in.read_string ();
         String itemID = in.read_string ();
         boolean $result = false;
         $result = this.checkReturnPossible (userID, itemID);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 11:  // corbaDAO/Library/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corbaDAO/Library:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Library _this() 
  {
    return LibraryHelper.narrow(
    super._this_object());
  }

  public Library _this(org.omg.CORBA.ORB orb) 
  {
    return LibraryHelper.narrow(
    super._this_object(orb));
  }


} // class LibraryPOA
