package net.ljcomputing.randy.store;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.ljcomputing.randy.factory.BuiltinStoresFactory;
import net.ljcomputing.randy.model.impl.GenericMap;
import net.ljcomputing.randy.model.impl.GenericString;
import net.ljcomputing.randy.reader.Reader;
import net.ljcomputing.randy.reader.impl.JsonInputStreamReader;
import net.ljcomputing.randy.store.exception.StoreException;
import net.ljcomputing.randy.store.impl.GenericMapStoreImpl;
import net.ljcomputing.randy.store.impl.GenericStringStoreImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Data store tests. */
public class StoreTest {
  /** The logger. */
  private static final Logger logger = LoggerFactory.getLogger(StoreTest.class);

  @Test
  void testBuiltinStoresFactory() throws StoreException {
    final BuiltinStoresFactory factory = new BuiltinStoresFactory();
    logger.debug("{}", factory.getStoreNames());
    Assert.assertTrue(!factory.getStoreNames().isEmpty());
    final GenericStringStore store = (GenericStringStore) factory.getStore("femaleGivenNames");
    logger.debug("{}", store.retrieve().getValue());
    Assert.assertNotNull(store.retrieve().getValue());
    Assert.assertNull(store.retrieve(9999));
    Assert.assertNull(factory.getStore("femaleGivenNam"));
  }

  @Test
  void testJsonFileReader() {
    final Path path =
        Path.of(
            System.getProperty("user.dir"),
            "src",
            "main",
            "resources",
            "stores",
            "maleGivenNames.json");
    final Reader reader = new JsonInputStreamReader("file:" + path.toString());

    try {
      final GenericStringStore store = new GenericStringStoreImpl(reader);
      Assert.assertNotNull(store.retrieve());
    } catch (Exception e1) {
      logger.error("Test failed: ", e1);
    }

    final Reader readerFail = new JsonInputStreamReader("file:" + path.toString() + "x");
    GenericStringStore storeFail = null;
    try {
      storeFail = new GenericStringStoreImpl(readerFail);
    } catch (Exception e) {
      logger.error("Test failed: ", e);
    }

    Assert.assertNull(storeFail);
  }

  @Test
  void testGenericMapStore()
      throws StoreException, StreamWriteException, DatabindException, IOException {
    final BuiltinStoresFactory factory = new BuiltinStoresFactory();
    final Path path = Path.of(System.getProperty("user.dir"), "out", "fullNames.json");

    final List<GenericMap> list = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      final GenericMap model = new GenericMap();
      final Map<String, Object> map = new HashMap<>();
      final String firstName =
          ((GenericString) factory.getStore("maleGivenNames").retrieve()).getValue();
      final String lastName = ((GenericString) factory.getStore("surnames").retrieve()).getValue();
      model.setId((long) i);
      map.put("firstName", firstName);
      map.put("lastName", lastName);
      model.setValue(map);
      list.add(model);
    }

    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(path.toFile(), list);

    final Reader reader = new JsonInputStreamReader("file:" + path.toString());
    final GenericMapStore store = new GenericMapStoreImpl(reader);
    final String result = store.retrieve().getValue().get("firstName").toString();
    logger.debug("result: {}", result);
    Assert.assertNotNull(result);
  }

  @Test
  void testHttpStore() throws StoreException {
    final Reader reader =
        new JsonInputStreamReader("http://localhost/~jim/json/femaleGivenNames.json");
    final GenericStringStore store = new GenericStringStoreImpl(reader);
    final String result = store.retrieve().getValue();
    logger.debug("result: {}", result);
    Assert.assertNotNull(result);
  }

  @Test
  void testHttpsStore() throws StoreException {
    final Reader reader = new JsonInputStreamReader("https://localhost/~jim/json/fullNames.json");
    final GenericMapStore store = new GenericMapStoreImpl(reader);
    final String result = store.retrieve().getValue().get("firstName").toString();
    logger.debug("result: {}", result);
    Assert.assertNotNull(result);
  }

  @Test
  void testStoreFailure() throws StoreException {
    String result = null;

    try {
      final Reader reader =
          new JsonInputStreamReader("http://localhost/~jim/json/femaleGivenNames.jso");
      final GenericStringStore store = new GenericStringStoreImpl(reader);
      result = store.retrieve().getValue();
    } catch (Exception e) {
      logger.error("Test failed: ", e);
    }

    logger.debug("result: {}", result);
    Assert.assertNull(result);

    try {
      final Reader reader =
          new JsonInputStreamReader("https://localhost/~jim/json/femaleGivenNames.jso");
      final GenericStringStore store = new GenericStringStoreImpl(reader);
      result = store.retrieve().getValue();
    } catch (Exception e) {
      logger.error("Test failed: ", e);
    }

    logger.debug("result: {}", result);
    Assert.assertNull(result);

    try {
      final Reader reader =
          new JsonInputStreamReader("htt://localhost/~jim/json/femaleGivenNames.jso");
      final GenericStringStore store = new GenericStringStoreImpl(reader);
      result = store.retrieve().getValue();
    } catch (Exception e) {
      logger.error("Test failed: ", e);
    }

    logger.debug("result: {}", result);
    Assert.assertNull(result);
  }
}
