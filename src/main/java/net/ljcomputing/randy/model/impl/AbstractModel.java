package net.ljcomputing.randy.model.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.ljcomputing.randy.model.Model;

/** Abstract implementation of a Model. */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AbstractModel implements Model {
  /** The serialVersionUID. */
  private static final long serialVersionUID = 8315178759159551070L;

  /** The id of the model. */
  private Long id;
}
