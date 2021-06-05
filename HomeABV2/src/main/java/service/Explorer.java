package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Explorer {
    private final boolean showDirectory;
    private final String mainPath;

    public Explorer(boolean showDirectory, String mainPath) throws IOException {
        this.showDirectory = showDirectory;
        this.mainPath = mainPath;
        startExplorer();
    }

    private void startExplorer() throws IOException {
        Path path = Paths.get(mainPath);
        Scanner scaner = new Scanner(System.in);
        MyFileVisitor myFileVisitor;
        myFileVisitor = new MyFileVisitor(showDirectory);
        String s ;
        do {
            System.out.print(path+"\\");
            s = scaner.nextLine();
            if (s.contains("\u003a")) {
                path = path.getParent();
                System.out.println(path);
                Files.walkFileTree(path, myFileVisitor);
            } else {
                if (s.contains("\u0063\u0064")) {
                    path = Paths.get(path + "/" + s.substring(3));
                    if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                        Files.walkFileTree(path, myFileVisitor);
                    } else {
                        reading(Files.readAllLines(path));
                    }
                } else {
                    if (s.contains("\u0063\u006f\u0070\u0079")){
                        copyFile(path);
                    }else {
                        if (s.contains("\u006d\u006f\u0076\u0065")) {
                            moveFile(path);
                        } else {
                            if (s.contains("\u0064\u0065\u006c\u0065\u0074\u0065")) {
                                deleteFile(path);
                            } else {
                                if (!Files.isDirectory(Paths.get(path + "/" + s), LinkOption.NOFOLLOW_LINKS)) {
                                    if (Files.isReadable(Paths.get(path + "/" + s))) {
                                        reading(Files.readAllLines(Paths.get(path + "/" + s)));
                                    } else {
                                        System.out.println("\u0049\u0020\u0063\u0061\u006e\u0074\u0020\u0072\u0065\u0061\u0064\u0020\u0066\u0069\u006c\u0065");
                                    }
                                } else {
                                    System.out.println("\u004e\u006f\u0074\u0020\u0063\u006f\u0072\u0072\u0065\u0063\u0074\u0020\u0063\u006f\u006d\u006d\u0061\u006e\u0064");
                                }
                            }
                        }
                    }
                }
            }
        } while (!s.equals("\u0063\u006c\u0065\u0061\u0072"));
        scaner.close();
    }

    private void copyFile(Path path) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Files.walkFileTree(path, new MyFileVisitor(true));
        System.out.println("\u0057\u0068\u0061\u0074\u0020\u0074\u006f\u0020\u0063\u006f\u0070\u0079\u003f\u0020");
        String from = scanner.nextLine();
        Path fromPath = Paths.get(path+"/"+from);
        if(Files.isDirectory(fromPath,LinkOption.NOFOLLOW_LINKS)) {
            System.out.println("\u004e\u006f\u0074\u0020\u0061\u0020\u0066\u0069\u006c\u0065\u002e");
            return;
        }
        System.out.println("\u0043\u006f\u0070\u0079\u0020\u0074\u006f\u003f\u0020");
        String to = scanner.nextLine();
        Path toPath = Paths.get(to+"/"+from);
        Files.copy(fromPath,toPath,REPLACE_EXISTING);
        System.out.println("\u0043\u006f\u0070\u0079\u0020\u0069\u0073\u0020\u0064\u006f\u006e\u002e");
    }

    private void deleteFile(Path path) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Files.walkFileTree(path, new MyFileVisitor(true));
        System.out.println("\u0057\u0068\u0061\u0074\u0020\u0064\u0065\u006c\u0065\u0074\u0065\u003f\u0020");
        String from = scanner.nextLine();
        Path fromPath = Paths.get(path+"/"+from);
        if(Files.isDirectory(fromPath,LinkOption.NOFOLLOW_LINKS)) {
            System.out.println("\u004e\u006f\u0074\u0020\u0061\u0020\u0066\u0069\u006c\u0065\u002e");
            return;
        }
        Files.delete(fromPath);
        System.out.println("\u0044\u0065\u006c\u0065\u0074\u0065\u0020\u0069\u0073\u0020\u0064\u006f\u006e\u002e");
    }

    private void moveFile(Path path) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Files.walkFileTree(path, new MyFileVisitor(true));
        System.out.println("\u0057\u0068\u0061\u0074\u0020\u0074\u006f\u0020\u006d\u006f\u0076\u0065\u003f\u0020");
        String from = scanner.nextLine();
        Path fromPath = Paths.get(path+"/"+from);
        if(Files.isDirectory(fromPath,LinkOption.NOFOLLOW_LINKS)) {
            System.out.println("\u004e\u006f\u0074\u0020\u0061\u0020\u0066\u0069\u006c\u0065\u002e");
            return;
        }
        System.out.println("\u004d\u006f\u0076\u0065\u0020\u0074\u006f\u003f\u0020");
        String to = scanner.nextLine();
        Path toPath = Paths.get(to+"/"+from);
        Files.move(fromPath,toPath,REPLACE_EXISTING);
        System.out.println("\u004d\u006f\u0076\u0065\u0020\u0069\u0073\u0020\u0064\u006f\u006e\u002e");
    }


    protected void reading(List<String> strings){
        for (String line : strings) {
            System.out.println(line+"\n");
        }
    }
}
