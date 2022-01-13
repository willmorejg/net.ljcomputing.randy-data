package net.ljcomputing.randy.factory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import javax.net.ssl.HttpsURLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Resource factory. */
public enum ResourceFactory implements ResourceStream {
  BUILTIN("jar") {
    @Override
    public InputStream getInputStream(URI resource) {
      return this.getClass().getResourceAsStream(resource.getPath());
    }
  },
  FILE("file") {
    @Override
    public InputStream getInputStream(URI resource) {
      try {
        return new FileInputStream(Path.of(resource.getPath()).toFile());
      } catch (Exception e) {
        logger.error("Failed to obtain InputStream: ", e);
      }

      return null;
    }
  },
  HTTP("http") {
    @Override
    public InputStream getInputStream(URI resource) {
      try {
        final URL url = resource.toURL();
        return ((HttpURLConnection) url.openConnection()).getInputStream();
      } catch (Exception e) {
        logger.error("Failed to obtain InputStream: ", e);
      }

      return null;
    }
  },
  HTTPS("https") {
    @Override
    public InputStream getInputStream(URI resource) {
      try {
        final URL url = resource.toURL();
        return ((HttpsURLConnection) url.openConnection()).getInputStream();
      } catch (Exception e) {
        logger.error("Failed to obtain InputStream: ", e);
      }

      return null;
    }
  };

  /** The logger. */
  private static final Logger logger = LoggerFactory.getLogger(ResourceFactory.class);

  /** URI schemes associated with the resource. */
  private String[] schemes;

  /**
   * Constructor.
   *
   * @param schemes schemes associated with the resource
   */
  private ResourceFactory(final String... schemes) {
    this.schemes = schemes;
  }

  /**
   * Get the URI schemes associated with the resource.
   *
   * @return the URI scheme of the resource
   */
  public String[] getSchemes() {
    return schemes;
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
      if (Arrays.asList(current.getSchemes()).contains(scheme)) {
        result = current;
        break;
      }
    }

    return result;
  }
}
