package net.ljcomputing.randy.reader.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import net.ljcomputing.randy.factory.ResourceFactory;
import net.ljcomputing.randy.reader.Reader;
import net.ljcomputing.randy.reader.exception.ReaderException;

/** XML input stream reader implementation. */
public class XmlInputStreamReader extends AbstractReader implements Reader {
  /** The Jackson type reference definition. */
  public static final TypeReference<List<Map<String, Object>>> TYPE_REF =
      new TypeReference<List<Map<String, Object>>>() {};

  /**
   * Constructor.
   *
   * @param resourceDefinition the definition of the resource
   */
  public XmlInputStreamReader(final String resourceDefinition) {
    super(resourceDefinition);
  }

  /**
   * Read the resource into a List of Map values.
   *
   * @return List of Map values
   * @throws ReaderException ReaderException
   */
  @Override
  public List<Map<String, Object>> resourceToListOfMaps() throws ReaderException {
    try (final InputStream storeFile =
        ResourceFactory.getByScheme(getResourceUri().getScheme())
            .getInputStream(getResourceUri())) {
      return xmlToList(storeFile);
    } catch (Exception e) {
      throw new ReaderException("Failed to retrieve resource into list of maps", e);
    }
  }

  /**
   * Read the given InputStream and return a List of Maps.
   *
   * @param storeFile the store file InputStream
   * @return a List of Map values
   * @throws ReaderException ReaderException
   */
  protected List<Map<String, Object>> xmlToList(final InputStream storeFile)
      throws ReaderException {
    try {
      return new XmlMapper().readValue(storeFile, TYPE_REF);
    } catch (Exception e) {
      throw new ReaderException("Failed to convert JSON to List: ", e);
    }
  }
}
