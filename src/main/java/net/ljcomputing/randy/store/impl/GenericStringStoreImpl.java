package net.ljcomputing.randy.store.impl;

import java.util.Map;
import net.ljcomputing.randy.model.impl.GenericString;
import net.ljcomputing.randy.reader.Reader;
import net.ljcomputing.randy.store.GenericStringStore;
import net.ljcomputing.randy.store.exception.StoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Generic string data store implementation. */
public class GenericStringStoreImpl extends AbstractStore<GenericString>
    implements GenericStringStore {
  /** The logger. */
  private static final Logger logger = LoggerFactory.getLogger(GenericStringStoreImpl.class);

  /**
   * Constructor.
   *
   * @param reader the data store loader
   * @throws StoreException StoreException
   */
  public GenericStringStoreImpl(final Reader reader) throws StoreException {
    super(reader);
  }

  /**
   * Map the given Map to the inferred Model.
   *
   * @param map the map of values
   * @return the Model populated from the Map values
   * @throws StoreException StoreException
   */
  public GenericString mapToModel(final Map<String, Object> map) throws StoreException {
    try {
      final GenericString model = super.mapToModel(map, GenericString.class);
      model.setValue(map.get("value").toString());
      return model;
    } catch (StoreException e) {
      logger.error("Failed to map to model: ", e);
      throw e;
    }
  }
}
