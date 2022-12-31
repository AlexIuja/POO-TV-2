import classes.fileio.Input;
import classes.packet.Site;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        Input inputData = objectMapper.readValue(new File("D:\\CA\\POO\\POO TV 2\\checker\\resources\\in\\basic_1.json"), Input.class);
        Site site = new Site(inputData.getUsers(), inputData.getMovies(), inputData.getActions());
        ArrayNode output = objectMapper.createArrayNode();
        //site.exec(site.getActionsIn(), objectWriter, objectMapper, output, args);
       // System.out.println(args[0] + " " + args[1]);
    }
}