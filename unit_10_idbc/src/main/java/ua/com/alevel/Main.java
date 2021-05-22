package ua.com.alevel;

import ua.com.alevel.db.ConnectionToDB;
import ua.com.alevel.domain.Node;
import ua.com.alevel.reader.DataBaseReader;
import ua.com.alevel.writer.SolutionWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.ibatis.jdbc.ScriptRunner;

@Slf4j
public class Main {

    public static void main(String[] args) {
       Main main = new Main();
       main.start();
    }

    private void start() {
        runInitDatabaseScript();

        DataBaseReader dataBaseReader = new DataBaseReader(ConnectionToDB.getConnection());
        List<Node> graph = dataBaseReader.readGraph();
        Map<Node, List<Node>> problems = dataBaseReader.readProblems();

        log.info("Successfully read Graph from database.");

        SolutionWriter solutionWriter = new SolutionWriter(ConnectionToDB.getConnection());
        solutionWriter.writeSolution(graph, problems);

        log.info("Done. Check results in database.");
    }

    private void runInitDatabaseScript() {
        log.info("Trying to initialize database...");
        try {
            try (Connection connection = ConnectionToDB.getConnection()) {
                ScriptRunner scriptRunner = new ScriptRunner(connection);

                try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.sql")) {
                    try (Reader reader = new InputStreamReader(Objects.requireNonNull(input))) {
                        scriptRunner.runScript(reader);
                    }
                }
            }

            log.info("Successfully initialized database.");
        } catch (Exception e) {
            String message = "Failed to init database.";
            log.error(message);
            throw new RuntimeException(message + "Details: " + e);
        }
    }

}
