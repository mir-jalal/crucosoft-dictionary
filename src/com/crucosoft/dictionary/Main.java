package com.crucosoft.dictionary;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static DictionaryFile dictionaryFile = new DictionaryFile();

    public static void main(String[] args) {
	// write your code here

        while(true){
            System.out.print("command: ");

            String command = scanner.nextLine();

            switch(command) {
                case "quit":
                    return;
                case "help":
                    printHelp();
                    break;
                case "search":
                    searchText();
                    break;
                case "":
                    break;
                default:
                    System.out.println("unknown command: enter 'help' to show commands");
                    break;
            }
        }
    }

    private static void printHelp() {
        System.out.println("help \t- print this note");
        System.out.println("search \t- to search text");
    }

    public static void searchText(){
        System.out.println("enter word:");
        String searched = scanner.nextLine();
        ArrayList<String> fetchedWords = dictionaryFile.getWords(searched);
        System.out.println("Found:");
        System.out.println(fetchedWords);
        if(!dictionaryFile.getExactWord(searched)){
            addText(searched);
        }
    }

    public static void addText(String searched){
        System.out.println("Would you like to add this word '"+searched + "' (yes or no): ");
        String answer = scanner.nextLine();

        if(answer.equals("yes")){
            dictionaryFile.writeWord(searched);
            System.out.println("New word is added: " + searched);
        }
    }
}
