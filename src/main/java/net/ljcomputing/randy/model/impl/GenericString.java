package net.ljcomputing.randy.model.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Generic string model. */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GenericString extends AbstractModel {
  /** The serialVersionUID. */
  private static final long serialVersionUID = -5014016733196180832L;

  /** The value. */
  private String value;
}
