import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class helloJava {

    private static String fileListInputFile = "/Users/daniel/dev/java/helloJava/input.txt";
    private static String generationTemplate = "/Users/daniel/dev/java/helloJava/template.txt";
    private static String outPath = "/Users/daniel/dev/java/helloJava/";
    private static String pgmName;

    public static void main(String[] args){

        generateFiles();
    }

    private static void generateFiles(){

        // read file names
        List<String> fileNames = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileListInputFile))) {
            fileNames = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // for each fileName: call generateFile()
        fileNames.forEach(helloJava::generateFile);
    }

    private static void generateFile(String fileName){

        // set pgmName to filename without extension
        pgmName = fileName.split("\\.")[0];

        System.out.println("Generation of: " + pgmName);

        // read template
        List<String> templateLines = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(generationTemplate))) {
            templateLines = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // for each line in template: call writeNewFile
        templateLines.forEach(helloJava::writeNewFile);
    }

    private static void writeNewFile(String line) {
        Path path = Paths.get(outPath + pgmName + ".txt");

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            if (line.contains("XXX_REPLACEME_XXX"))
            {
                String newLine = line.replace("XXX_REPLACEME_XXX", pgmName);
                writer.write(newLine + "\n");
            }
            else
            {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
