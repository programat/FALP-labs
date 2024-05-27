package combinatorics;

import java.io.FileWriter;
import java.io.IOException;

public interface CombinatorialObject {
    void generate(FileWriter writer) throws IOException;
}
