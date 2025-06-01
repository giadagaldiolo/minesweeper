package ch.supsi.minesweeper.backend.data_access;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

public class JsonLoadDataAccess implements JsonLoadDataAccessInterface{

    @Override
    public <T> T loadFromFile(Path path, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(path.toFile(), clazz);
    }
}
