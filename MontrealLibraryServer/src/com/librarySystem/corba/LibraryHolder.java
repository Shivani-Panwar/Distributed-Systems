package com.librarySystem.corba;

/**
* corba/LibraryHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* Monday, March 4, 2019 2:26:32 o'clock PM EST
*/

public final class LibraryHolder implements org.omg.CORBA.portable.Streamable
{
  public Library value = null;

  public LibraryHolder ()
  {
  }

  public LibraryHolder (Library initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = LibraryHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    LibraryHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return LibraryHelper.type ();
  }

}
