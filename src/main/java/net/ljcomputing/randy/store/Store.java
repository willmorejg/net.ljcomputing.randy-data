package net.ljcomputing.randy.store;

import java.util.Map;
import net.ljcomputing.randy.store.exception.StoreException;

/** Interface shared by all data stores. */
public interface Store<M> {
  /**
   * Map the given Map to the inferred Model.
   *
   * @param map the map of values
   * @return the Model populated from the Map values
   * @throws StoreException StoreException
   */
  M mapToModel(Map<String, Object> map, Class<M> klass) throws StoreException;

  /**
   * Map the given Map to the inferred Model.
   *
   * @param map the map of values
   * @throws StoreException StoreException
   */
  M mapToModel(final Map<String, Object> map) throws StoreException;

  /** The total records in the data store. */
  int size();

  /**
   * Retrieve the data from the store by the given index.
   *
   * @return M the data in the store with the given index
   */
  M retrieve(int index);

  /**
   * Retrieve the data from the store by a random index.
   *
   * @return M the data in the store with a random index
   */
  M retrieve();
}
