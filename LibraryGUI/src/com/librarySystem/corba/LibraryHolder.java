package com.librarySystem.corba;

/**
* corba/LibraryHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* Sunday, 3 March, 2019 1:18:42 AM IST
*/

public final class LibraryHolder implements org.omg.CORBA.portable.Streamable
{
  public com.librarySystem.corba.Library value = null;

  public LibraryHolder ()
  {
  }

  public LibraryHolder (com.librarySystem.corba.Library initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.librarySystem.corba.LibraryHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
	  com.librarySystem.corba.LibraryHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.librarySystem.corba.LibraryHelper.type ();
  }

}
