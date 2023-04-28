package net.ljcomputing.randy.factory;

import java.io.InputStream;
import java.net.URI;

/** Interface shared by all resources that can create an InputStream. */
public interface ResourceStream {
  /**
   * Get the resource's InputStream.
   *
   * @param resource the resource
   * @return the InputStream for the resource
   */
  InputStream getInputStream(URI resource);

  
}
