package net.ljcomputing.randy.factory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Resource factory. */
public enum ResourceFactory implements ResourceStream {
  BUILTIN("jar") {
    @Override
    public InputStream getInputStream(String resourceDefinition) {
      return this.getClass().getResourceAsStream(resourceDefinition);
    }
  },
  FILE("file") {
    @Override
    public InputStream getInputStream(String resourceDefinition) {
      try {
        return new FileInputStream(Path.of(resourceDefinition).toFile());
      } catch (Exception e) {
        logger.error("Failed to obtain InputStream: ", e);
      }

      return null;
    }
  };

  /** The logger. */
  private static final Logger logger = LoggerFactory.getLogger(ResourceFactory.class);

  /** URI scheme associated with the resource. */
  private String scheme;

  /**
   * Constructor.
   *
   * @param scheme scheme associated with the resource
   */
  private ResourceFactory(final String scheme) {
    this.scheme = scheme;
  }

  /**
   * Get the URI scheme associated with the resource.
   *
   * @return the URI scheme of the resource
   */
  public String getScheme() {
    return scheme;
  }

  /**
   * Find the resource by the given URI scheme.
   *
   * @param scheme the given scheme
   * @return the resource
   */
  public static final ResourceFactory getByScheme(final String scheme) {
    ResourceFactory result = null;

    for (final ResourceFactory current : values()) {
      if (current.getScheme().equals(scheme)) {
        result = current;
        break;
      }
    }

    return result;
  }
}
