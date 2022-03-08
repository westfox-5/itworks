package it.itworks;

import it.itworks.reader.CustomReader;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String filepath = args[0];

        CustomReader newReader = new CustomReader(filepath, ' ');

        try {
            System.out.println("token: " + newReader.getNextToken());
            System.out.println("token: " + newReader.getNextToken());
            System.out.println("newLine: " + newReader.isLastNewLine());
            System.out.println("token: " + newReader.getNextToken());
            System.out.println("newLine: " + newReader.isLastNewLine());
            System.out.println("token: " + newReader.getNextToken());
            System.out.println("newLine: " + newReader.isLastNewLine());
            System.out.println("token: " + newReader.getNextToken());
            System.out.println("newLine: " + newReader.isLastNewLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
