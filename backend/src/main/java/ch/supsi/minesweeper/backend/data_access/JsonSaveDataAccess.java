package ch.supsi.minesweeper.backend.data_access;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonSaveDataAccess implements JsonSaveDataAccessInterface {
    @Override
    public void saveToFile(Path path, Object objectToSave) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), objectToSave);
    }

    @Override
    public void ensureDirectoryExists(Path dir) throws IOException {
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
    }
}
