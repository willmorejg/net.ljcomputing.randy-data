package net.ljcomputing.randy.factory;

import java.io.InputStream;

/** Interface shared by all resources that can create an InputStream. */
public interface ResourceStream {
  /**
   * Get the resource's InputStream.
   *
   * @param resourceDefinition the resource's definition
   * @return the InputStream for the resource
   */
  InputStream getInputStream(String resourceDefinition);
}
