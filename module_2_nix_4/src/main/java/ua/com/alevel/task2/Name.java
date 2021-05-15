package ua.com.alevel.task2;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Name {

    public Optional<String> uniqueName(List<String> name){
        return name.stream().filter(e -> Collections.frequency(name,e) == 1)
                .findFirst();
    }
}
