package com.librarySystem.ConcordiaLibraryServer;


/**
* ConcordiaLibraryServer/ConcordiaHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ConcordiaLibraryServer.idl
* Wednesday, February 27, 2019 1:34:05 o'clock AM EST
*/

abstract public class ConcordiaHelper
{
  private static String  _id = "IDL:ConcordiaLibraryServer/Concordia:1.0";

  public static void insert (org.omg.CORBA.Any a, Concordia that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Concordia extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (ConcordiaHelper.id (), "Concordia");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Concordia read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ConcordiaStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream,Concordia value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static Concordia narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Concordia)
      return (Concordia)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      _ConcordiaStub stub = new _ConcordiaStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static Concordia unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Concordia)
      return (Concordia)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      _ConcordiaStub stub = new _ConcordiaStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
