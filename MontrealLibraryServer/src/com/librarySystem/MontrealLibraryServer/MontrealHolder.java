package com.librarySystem.MontrealLibraryServer;

/**
* MontrealLibraryServer/MontrealHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from MontrealLibraryServer.idl
* Saturday, March 2, 2019 2:20:01 o'clock AM EST
*/

public final class MontrealHolder implements org.omg.CORBA.portable.Streamable
{
  public Montreal value = null;

  public MontrealHolder ()
  {
  }

  public MontrealHolder (Montreal initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = MontrealHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    MontrealHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return MontrealHelper.type ();
  }

}
