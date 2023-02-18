package com.farhad.example.functional02.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class Store {

    private static String HOME = System.getProperty("user.home");


    private final String TEMP_DIR = "target";

    public File save(String contents) throws IOException {
        // FileAttribute<Set<PosixFilePermission>> attrs = PosixFilePermissions.asFileAttribute(
        //                                                                             setOf(  PosixFilePermission.OWNER_WRITE,
        //                                                                                     PosixFilePermission.OWNER_READ));

        String fileName = "meta" + UUID.randomUUID().toString() + ".txt";
        Path filePath = Paths.get(TEMP_DIR + "/" + fileName);
        filePath.toFile().deleteOnExit();
        Files.createFile(filePath);
        byte[] strToByte = contents.getBytes();
        Files.write(filePath.toFile().toPath(), strToByte);

        return filePath.toFile().getAbsoluteFile() ;
    }

    public static DataTypes extractType(String contents) {
        return DataTypes.EMPTY_TYPE ;
    }

    private static Set<PosixFilePermission> setOf(PosixFilePermission... perms) {

        return new HashSet<>(Arrays.asList(perms));

    }
    
}
