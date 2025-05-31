package ch.supsi.minesweeper.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

public class JsonLoadService {
    public <T> T loadFromFile(Path path, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(path.toFile(), clazz);
    }
}
