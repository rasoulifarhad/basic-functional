package com.farhad.example.functional02.tempdir;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TempDirTest {

    private static String HOME = System.getProperty("user.home");

    @Test
    public void temp_dir_test() throws IOException {

        Path tmppath = Files.createTempDirectory("tmpDirPrefix");
        String tmpdir = tmppath.toFile().getAbsolutePath();
        String tmpDirsLocation = System.getProperty("java.io.tmpdir");

        tmppath.toFile().deleteOnExit();;

        log.info("tmpdir: {}",tmpdir);
        log.info("tmpDirsLocation: {}",tmpDirsLocation);

        assertThat(tmpdir).startsWith(tmpDirsLocation);
    }
    
    @Test
    public void temp_dir_with_location_test() throws IOException {

        Path tmpdir = Files.createTempDirectory(Paths.get("target"),"tmpDirPrefix");
        tmpdir.toFile().deleteOnExit();

        log.info("tmpdir: {}",tmpdir.toFile().getPath());

        assertThat(tmpdir.toFile().getPath()).startsWith("target");
    }

    @Test
    public void temp_dir_with_file_attributes_test() throws IOException {
        
        FileAttribute<Set<PosixFilePermission>> attrs = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("r--------"));

        Path tmpdir = Files.createTempDirectory(Paths.get("target"),"tmpDirPrefix",attrs);
        tmpdir.toFile().deleteOnExit();

        log.info("tmpdir: {}",tmpdir.toFile().getPath());

        assertThat(tmpdir.toFile().getPath()).startsWith("target");
        assertThat(tmpdir.toFile().canWrite()).isFalse();
    }

    @Test
    public void GIVEN_existentPath_WHEN_confirms_fileExists_THEN_correct(){
            Path p = Paths.get(HOME);

            assertThat(Files.exists(p)).isTrue() ;
    }

    @Test
    public void GIVEN_none_existentPath_WHEN_confirms_fileNotExists_THEN_correct(){
            Path p = Paths.get(HOME + "/notExistFile.txt");

            assertThat(Files.exists(p)).isFalse() ;
    }

    @Test
    public void GIVEN_dir_path_WHEN_confirms_Not_regularFile_THEN_correct(){
            Path p = Paths.get(HOME );

            assertThat(Files.isRegularFile(p)).isFalse() ;
    }

    @Test
    public void GIVEN_dir_path_WHEN_confirms_writable_THEN_correct(){
            Path p = Paths.get(HOME );

            assertThat(Files.isWritable(p)).isTrue() ;
    }

    @Test
    public void GIVEN_dir_path_WHEN_confirms_executable_THEN_correct(){
            Path p = Paths.get(HOME );

            assertThat(Files.isExecutable(p)).isTrue() ;
    }

    @Test
    public void GIVEN_same_file_path_WHEN_confirms_is_same_THEN_correct() throws IOException{
            Path p1 = Paths.get(HOME );
            Path p2 = Paths.get(HOME );

            assertThat(Files.isSameFile(p1, p2)).isTrue() ;
    }

    @Test
    public void GIVEN_file_path_WHEN_create_new_file_THEN_correct() throws IOException{

        String fileName = "myfile_" + UUID.randomUUID().toString() + ".txt";

        Path p = Paths.get(HOME + "/" + fileName);
        assertThat(Files.exists(p)).isFalse() ;

        Files.createFile(p);
        assertThat(Files.exists(p)).isTrue();

        Files.delete(p);
        assertThat(Files.exists(p)).isFalse() ;

    }

    @Test
    public void GIVEN_dir_path_WHEN_create_new_dir_THEN_correct() throws IOException{

        String dirName = "myDir_" + UUID.randomUUID().toString() ;

        Path p = Paths.get(HOME + "/" + dirName);
        assertThat(Files.exists(p)).isFalse() ;

        Files.createDirectory(p);
        assertThat(Files.exists(p)).isTrue();
        assertThat(Files.isDirectory(p)).isTrue();

        Files.delete(p);
        assertThat(Files.exists(p)).isFalse() ;

    }

    @Test
    public void GIVEN_dir_path_WHEN_create_recursively_THEN_correct() throws IOException{

        String dirName = "myDir_" + UUID.randomUUID().toString() ;

        Path dir  = Paths.get(HOME + "/" + dirName);
        Path subDir = dir.resolve("subdir");

        assertThat(Files.exists(dir)).isFalse() ;
        assertThat(Files.exists(subDir)).isFalse() ;

        Files.createDirectories(subDir);

        assertThat(Files.exists(dir)).isTrue();
        assertThat(Files.isDirectory(subDir)).isTrue();

        Files.delete(subDir);
        Files.delete(dir);
        assertThat(Files.exists(dir)).isFalse() ;
        assertThat(Files.exists(subDir)).isFalse() ;

    }

    @Test
    public void GIVEN_file_path_WHEN_copies_to_new_location_THEN_correct() throws IOException{


        Path srcDir  = Paths.get(HOME + "/" + "mySrcDir_" + UUID.randomUUID().toString());
        Path destDir  = Paths.get(HOME + "/" + "myDestDir_" + UUID.randomUUID().toString());

        Files.createDirectory(srcDir);
        Files.createDirectory(destDir);
        Path srcFile = srcDir.resolve("srcFile.txt");
        Path destFile = destDir.resolve("destFile.txt");

        Files.createFile(srcFile);

        assertThat(Files.exists(srcFile)).isTrue();
        assertThat(Files.exists(destFile)).isFalse();

        Files.copy(srcFile, destFile);

        assertThat(Files.exists(destFile)).isTrue();

        Files.delete(srcFile);
        Files.delete(destFile);
        Files.delete(srcDir);
        Files.delete(destDir);

    }

    @Test
    public void GIVEN_file_path_WHEN_copies_to_new_location_existing_file_THEN_correct() throws IOException{


        Path srcDir  = Paths.get(HOME + "/" + "mySrcDir_" + UUID.randomUUID().toString());
        Path destDir  = Paths.get(HOME + "/" + "myDestDir_" + UUID.randomUUID().toString());

        Files.createDirectory(srcDir);
        Files.createDirectory(destDir);
        Path srcFile = srcDir.resolve("srcFile.txt");
        Path destFile = destDir.resolve("destFile.txt");

        Files.createFile(srcFile);
        Files.createFile(destFile);

        assertThat(Files.exists(srcFile)).isTrue();
        assertThat(Files.exists(destFile)).isTrue();

        Files.copy(srcFile, destFile,StandardCopyOption.REPLACE_EXISTING);

        Files.delete(srcFile);
        Files.delete(destFile);
        Files.delete(srcDir);
        Files.delete(destDir);

    }

    // @Test(expected = FileAlreadyExistsException.class)
    @Test
    public void GIVEN_file_path_WHEN_copies_to_new_location_existing_file_throw_exception_THEN_correct() throws IOException{


        Path srcDir  = Paths.get(HOME + "/" + "mySrcDir_" + UUID.randomUUID().toString());
        Path destDir  = Paths.get(HOME + "/" + "myDestDir_" + UUID.randomUUID().toString());

        Files.createDirectory(srcDir);
        Files.createDirectory(destDir);
        Path srcFile = srcDir.resolve("srcFile.txt");
        Path destFile = destDir.resolve("destFile.txt");

        Files.createFile(srcFile);
        Files.createFile(destFile);

        assertThat(Files.exists(srcFile)).isTrue();
        assertThat(Files.exists(destFile)).isTrue();

        Files.copy(srcFile, destFile);

        Files.delete(srcFile);
        Files.delete(destFile);
        Files.delete(srcDir);
        Files.delete(destDir);

    }

    @Test
    public void GIVEN__path_WHEN_deletes_THEN_correct() throws IOException {

        Path p = Paths.get(HOME + "/fileToDelete.txt");

        assertThat(Files.exists(p)).isFalse();

        Files.createFile(p);

        assertThat(Files.exists(p)).isTrue();

        Files.delete(p);

        assertThat(Files.exists(p)).isFalse();

        Files.deleteIfExists(p);

        
    }

}
