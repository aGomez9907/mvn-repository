package com.solvd.laba.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtilsMethods {
    public static void main(String[] args) throws IOException {
        // Creation of the files
        File fileZero = new File("src/main/resources/fileZero");
        File fileOne = new File("src/main/resources/fileOne");
        String data = "This file will not be deleted.";
        byte[] bytes2 = data.getBytes();

        FileUtils.touch(fileZero);
        FileUtils.touch(fileOne);
        FileUtils.writeByteArrayToFile(fileOne, bytes2);
        FileUtils.forceDelete(fileZero); //deletion of fileZero

        // copy of fileOne
        File fileOneCopy = new File("src/main/resources/fileOneCopy");
        FileUtils.copyFile(fileOne, fileOneCopy, true);
    }
}
