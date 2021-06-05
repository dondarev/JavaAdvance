package service;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor {
    boolean showDirectory;

    public MyFileVisitor(boolean showDirectory) {
        this.showDirectory = showDirectory;
    }

    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        Path path = Paths.get(file.toString());
        if (showDirectory)
            System.out.println("file name:" + path.getFileName());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
        Path path = Paths.get(dir.toString());
        if (showDirectory)
            System.out.println("Directory name:" + path);
        return FileVisitResult.CONTINUE;
    }
}
