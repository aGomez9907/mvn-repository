package com.solvd.laba.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class File {

    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("F:\\Documentos\\JAVA_GITHUB_REPOSITORY\\maven\\hospital\\src\\main\\resources\\article.txt");
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("F:\\Documentos\\JAVA_GITHUB_REPOSITORY\\maven\\hospital\\src\\main\\resources\\newArticle.txt");

        Set<String> uniqueWords = new HashSet<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] words = line.toLowerCase().replace(",", "").replace(".", "").replace(":", "").split(" ");
            uniqueWords.addAll(Arrays.asList(words));
        }
        fw.write("Number of words in the article: " + uniqueWords.size());
        br.close();
        fw.close();
    }
}

