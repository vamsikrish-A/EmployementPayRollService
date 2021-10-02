
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class NIOFileAPITest {
    private static  String HOME = System.getProperty("user.home");
    private static  String PLAY_WITH_NIO = "TempPlayGroud";

    @Test
    public void givenPathWhenCheckedThenConfirm() throws IOException {
        //check file Exists
        Path homePath = Paths.get(HOME);
        Assertions.assertTrue(Files.exists(homePath));

        //Delete file and Check File Not Exist
        Path playPath = Paths.get(HOME +"/"+PLAY_WITH_NIO);
        if (Files.exists(playPath)) FileUtils.deleteFiles(playPath.toFile());
        Assertions.assertTrue(Files.notExists(playPath));

        //create Directory
        Files.createDirectory(playPath);
        Assertions.assertTrue(Files.exists(playPath));

        //create File
        IntStream.range(1, 10).forEach(cntr -> {
            Path tempFile = Paths.get(playPath +"/temp"+cntr);
            Assertions.assertTrue(Files.notExists(tempFile));
            try {
                Files.createFile(tempFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Assertions.assertTrue(Files.exists(tempFile));
        });

        //List Files, Directors as well as Files with Extension
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && path.toString().startsWith("temp")).forEach(System.out::println);


    }
//    @Test
//    public void givenADirectoryWhenWatchedListsAllTheActivities() throws IOException {
//        Path dir = Paths.get(HOME+"/"+PLAY_WITH_NIO);
//        Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
//        new Java8WatchServiceExample(dir).processEvents();
//    }
}
