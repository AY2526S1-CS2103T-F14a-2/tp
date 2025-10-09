package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyCandidateList;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonFindrStorage implements FindrStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFindrStorage.class);

    private Path filePath;

    public JsonFindrStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFindrFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCandidateList> readCandidateList() throws DataLoadingException {
        return readCandidateList(filePath);
    }

    /**
     * Similar to {@link #readCandidateList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyCandidateList> readCandidateList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableFindr> jsonCandidateList = JsonUtil.readJsonFile(
                filePath, JsonSerializableFindr.class);
        if (!jsonCandidateList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCandidateList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveCandidateList(ReadOnlyCandidateList candidateList) throws IOException {
        saveCandidateList(candidateList, filePath);
    }

    /**
     * Similar to {@link #saveCandidateList(ReadOnlyCandidateList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCandidateList(ReadOnlyCandidateList candidateList, Path filePath) throws IOException {
        requireNonNull(candidateList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFindr(candidateList), filePath);
    }

}
