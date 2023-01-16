package com.solvd.laba.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class File {

    public static void main(String[] args) {

        try (FileReader fr = new FileReader("src/main/resources/article.txt"); BufferedReader br = new BufferedReader(fr); FileWriter fw = new FileWriter("src/main/resources/newArticle.txt")) {
            Set<String> uniqueWords = new HashSet<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.replaceAll("[^\\p{L}0-9]", " ").toLowerCase().split("\\s");
                uniqueWords.addAll(Arrays.asList(words));
                uniqueWords.remove(""); //cannot figure out why the split is generating a non-space character after every comma and period
            }                               //I know there is possibly a better solution but this approach seems to work fine
            fw.write("Number of words in the article: " + uniqueWords.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



