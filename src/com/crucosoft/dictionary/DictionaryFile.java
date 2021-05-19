package com.crucosoft.dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryFile {

    File dictionaryFile;

    DictionaryFile(){
        File newDictionaryFile = new File("dictionary.txt");
        File defaultDictionaryFile = new File("default.txt");
        if(newDictionaryFile.exists()){
            dictionaryFile = newDictionaryFile;
        }
        else if(defaultDictionaryFile.exists()){

            try {
                if(newDictionaryFile.createNewFile()) System.out.println("New dictionary file created: used default");
                FileOutputStream fileOutputStream = new FileOutputStream(newDictionaryFile);
                FileInputStream fileInputStream = new FileInputStream(defaultDictionaryFile);

                fileOutputStream.write(fileInputStream.readAllBytes());
            } catch (IOException e) {
                System.err.println("Unknown error occurred");
                System.exit(-1);
            }
        }
        else{
            try {
                if(newDictionaryFile.createNewFile()) System.out.println("New dictionary file created: can't used default");
            } catch (IOException e) {
                System.err.println("Cannot create file");
                System.exit(-1);
            }
        }
        dictionaryFile = newDictionaryFile;
    }

    public ArrayList<String> getWords(String searched){
        ArrayList<String> fetchedStrings = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new FileInputStream(dictionaryFile));
            while(scanner.hasNext()){
                String text = scanner.nextLine();

                if(text.indexOf(searched)==0){
                    fetchedStrings.add(text);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fetchedStrings;
    }

    public boolean getExactWord(String searched){

        try {
            Scanner scanner = new Scanner(new FileInputStream(dictionaryFile));
            while(scanner.hasNext()){
                String text = scanner.nextLine();

                if(text.equals(searched)){
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean writeWord(String text){

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dictionaryFile.getAbsolutePath(),true))){
            bufferedWriter.newLine();
            bufferedWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
