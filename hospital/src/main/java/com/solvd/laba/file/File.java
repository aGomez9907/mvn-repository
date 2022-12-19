package com.solvd.laba.file;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class File {

    public static void main(String[] args) throws IOException {

            FileReader fr = new FileReader("F:\\Documentos\\JAVA_GITHUB_REPOSITORY\\maven\\hospital\\src\\main\\resources\\article.txt");
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw= new FileWriter("F:\\Documentos\\JAVA_GITHUB_REPOSITORY\\maven\\hospital\\src\\main\\resources\\newArticle.txt");

            String line;
            long count = 0;
            String[] words = null;
            while((line = br.readLine()) != null){
                words = line.toLowerCase().replace(",", "").replace(".", "").replace(":", "").split(" ");

            }

        Arrays.sort(words);
        count =Arrays.stream(words).distinct().count();

            fw.write("Number of words in the article: " + count);
            br.close();
            fw.close();

        }
    }

