package net.ljcomputing.randy.model.impl;

import java.util.Collections;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** Generic string model. */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GenericMap extends AbstractModel {
  /** The serialVersionUID. */
  private static final long serialVersionUID = -5014016733196180832L;

  /** The value. */
  private Map<String, Object> value;

  /**
   * Get the value.
   *
   * @return the Map value
   */
  public Map<String, Object> getValue() {
    return Collections.unmodifiableMap(value);
  }

  /**
   * Set the value.
   *
   * @param value the Map value
   */
  public void setValue(final Map<String, Object> value) {
    this.value = Collections.unmodifiableMap(value);
  }
}
