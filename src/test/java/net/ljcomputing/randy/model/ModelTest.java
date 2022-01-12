package net.ljcomputing.randy.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.ljcomputing.randy.model.impl.GenericString;
import net.ljcomputing.randy.random.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Test class for model. */
public class ModelTest {
  /** The logger. */
  private static final Logger logger = LoggerFactory.getLogger(ModelTest.class);

  /** A base path to use for JSON files. */
  private static final Path basePath =
      Path.of(System.getProperty("user.dir"), "src", "main", "resources", "stores");

  /** The Jackson Object Mapper. */
  private static final ObjectMapper mapper = new ObjectMapper();

  /** SLF4J getting template. */
  private static final String GETTING = "getting: {}";

  /** Test model creation. */
  @Test
  void testCreate() throws Exception {
    // mapper.enable(SerializationFeature.INDENT_OUTPUT);
    final Path jsonFile = Path.of(System.getProperty("user.dir"), "out", "names.json");
    final List<GenericString> models = new ArrayList<>();
    final String[] names = {"Manny", "Moe", "Jack", "Alice", "Mary", "Jane"};

    for (int id = 0; id < names.length; id++) {
      final String name = names[id];
      final GenericString model = new GenericString();
      model.setId((long) id);
      model.setValue(name);
      models.add(model);
    }

    Assert.assertTrue(names.length == models.size());

    logger.debug("json: {}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(models));

    mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile.toFile(), models);

    final GenericString[] fromFile = mapper.readValue(jsonFile.toFile(), GenericString[].class);

    final int get = RandomGenerator.getRandomInt(fromFile.length);
    logger.debug(GETTING, get);

    final GenericString retrieved = fromFile[get];
    final GenericString fromModel = models.get(get);
    final int id = retrieved.getId().intValue();
    logger.debug("{}", retrieved);

    Assert.assertEquals(id, get);
    Assert.assertEquals(retrieved, fromModel);
  }

  @Test
  void testSurname() throws Exception {
    final Path jsonFile = Path.of(basePath.toAbsolutePath().toString(), "surnames.json");
    final GenericString[] fromFile = mapper.readValue(jsonFile.toFile(), GenericString[].class);
    final int rand = RandomGenerator.getRandomInt(fromFile.length) - 1;
    final int get = rand != -1 ? rand : 0;
    logger.debug(GETTING, get);

    final GenericString retrieved = fromFile[get];
    final int id = retrieved.getId().intValue();
    logger.debug("{}", retrieved);
    Assert.assertEquals(id, get);
  }

  @Test
  void testMaleGivenName() throws Exception {
    final Path jsonFile = Path.of(basePath.toAbsolutePath().toString(), "maleGivenNames.json");
    final GenericString[] fromFile = mapper.readValue(jsonFile.toFile(), GenericString[].class);
    final int rand = RandomGenerator.getRandomInt(fromFile.length) - 1;
    final int get = rand != -1 ? rand : 0;
    logger.debug(GETTING, get);

    final GenericString retrieved = fromFile[get];
    final int id = retrieved.getId().intValue();
    logger.debug("{}", retrieved);
    Assert.assertEquals(id, get);
  }

  @Test
  void testFemaleGivenName() throws Exception {
    final Path jsonFile =
        Path.of(
            System.getProperty("user.dir"),
            "src",
            "main",
            "resources",
            "stores",
            "femaleGivenNames.json");
    final GenericString[] fromFile = mapper.readValue(jsonFile.toFile(), GenericString[].class);
    final int rand = RandomGenerator.getRandomInt(fromFile.length) - 1;
    final int get = rand != -1 ? rand : 0;
    logger.debug(GETTING, get);

    final GenericString retrieved = fromFile[get];
    final int id = retrieved.getId().intValue();
    logger.debug("{}", retrieved);
    Assert.assertEquals(id, get);
  }
}
