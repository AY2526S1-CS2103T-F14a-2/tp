package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.CandidateList;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableFindrTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFindrTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsCandidateList.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonCandidateList.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonCandidateList.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableFindr dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableFindr.class).get();
        CandidateList candidateListFromFile = dataFromFile.toModelType();
        CandidateList typicalPersonsCandidateList = TypicalPersons.getTypicalCandidateList();
        assertEquals(candidateListFromFile, typicalPersonsCandidateList);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFindr dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableFindr.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableFindr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableFindr.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFindr.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
