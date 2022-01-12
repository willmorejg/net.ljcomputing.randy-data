package net.ljcomputing.randy.model;

import java.io.Serializable;

/** Interface shared by all model implementations. */
public interface Model extends Serializable {
  /**
   * Retrieve the id of the model.
   *
   * @return the id
   */
  Long getId();

  /**
   * Set the id of the model.
   *
   * @param id the id
   */
  void setId(Long id);
}
