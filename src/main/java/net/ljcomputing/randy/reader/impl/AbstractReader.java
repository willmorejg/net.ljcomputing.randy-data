package net.ljcomputing.randy.reader.impl;

import net.ljcomputing.randy.reader.Reader;

/** Abstract implementation of a resource reader. */
public abstract class AbstractReader implements Reader {
  /**
   * Resource definition. <br>
   *
   * <p>Examples:
   *
   * <ul>
   *   <li>jdbc:postgresql://localhost/data - PostgreSQL resource definition
   *   <li>/home/jim/data.txt - file resource definition
   * </ul>
   */
  protected final transient String resourceDefinition;

  /**
   * Constructor.
   *
   * @param resourceDefinition the definition of the resource
   */
  public AbstractReader(final String resourceDefinition) {
    this.resourceDefinition = resourceDefinition;
  }
}
