package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.CandidateList;
import seedu.address.model.ReadOnlyCandidateList;

/**
 * Represents a storage for {@link CandidateList}.
 */
public interface FindrStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFindrFilePath();

    /**
     * Returns Findr data as a {@link ReadOnlyCandidateList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyCandidateList> readCandidateList() throws DataLoadingException;

    /**
     * @see #getFindrFilePath()
     */
    Optional<ReadOnlyCandidateList> readCandidateList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyCandidateList} to the storage.
     * @param candidateList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCandidateList(ReadOnlyCandidateList candidateList) throws IOException;

    /**
     * @see #saveCandidateList(ReadOnlyCandidateList)
     */
    void saveCandidateList(ReadOnlyCandidateList candidateList, Path filePath) throws IOException;

}
