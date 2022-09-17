package ru.javarush.november.zaharin.cryptoanalizer;

import java.io.*;
import java.util.*;

public class Creator {
    private final char[] alphabet;

    public Creator(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public File brakeBrut(String pathBrut) {
        int step;
        HashMap<Character, Long> cOriginal = countChars("src/main/resources/Chukovskiy.txt");
        HashMap<Character, Long> cCoded = countChars(pathBrut);
        char maxOriginal = Collections.max(cOriginal.entrySet(), Map.Entry.comparingByValue()).getKey();
        char maxCoded = Collections.max(cCoded.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println(maxOriginal);
        System.out.println(maxCoded);
        int indexMaxC = 0;
        int indexMaxO = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == maxCoded) {
                indexMaxC = i;
            }
        }
        for (int j = 0; j < alphabet.length; j++) {
            if (alphabet[j] == maxOriginal) {
                indexMaxO = j;
            }
        }
        step = indexMaxC - indexMaxO;
        if (step < 0) {
            step = alphabet.length - Math.abs(step);
        }
        System.out.println(step);
        return deCoding(pathBrut, step);
    }

    public File coding(String path, int step) {
        File fResult = new File("coding_text.txt");
        try (FileReader fileReader = new FileReader(path);
             FileWriter fileWriter = new FileWriter(fResult);
             BufferedReader bufferedReader = new BufferedReader(fileReader, alphabet.length)) {
            if (bufferedReader.readLine() == null) {
                System.out.println("File is empty");
            }
            int counter;
            while ((counter = bufferedReader.read()) != -1) {
                char result = (char) counter;
                result = findNewSymbolCoding(step, result);
                fileWriter.write(result);
            }
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            System.out.println("there is no file");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return fResult;
    }

    public File deCoding(String path, int step) {
        File fResult = new File("deCoding_text.txt");
        try (FileReader fileReader = new FileReader(path);
             FileWriter fileWriter = new FileWriter(fResult);
             BufferedReader bufferedReader = new BufferedReader(fileReader, alphabet.length)) {
            if (bufferedReader.readLine() == null) {
                System.out.println("File is empty");
            }
            int counter;
            while ((counter = bufferedReader.read()) != -1) {
                char result = (char) counter;
                result = findNewSymbolDeCoding(step, result);
                fileWriter.write(result);
            }
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            System.out.println("there is no file");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return fResult;
    }

    public char findNewSymbolCoding(int step, char result) {
        int currentPosition = findCurrentPosition(result);
        if (currentPosition == -1) {
            return result;
        }
        int normalizedDelta = Math.abs(step % alphabet.length);
        int newIndex = (currentPosition + normalizedDelta) % alphabet.length;
        return alphabet[newIndex];
    }

    public char findNewSymbolDeCoding(int step, char result) {
        int currentPosition = findCurrentPosition(result);
        if (currentPosition == -1) {
            return result;
        }
        int normalizedDelta = Math.abs(step % alphabet.length);
        int newIndex = (currentPosition - normalizedDelta) % alphabet.length;
        if (newIndex < 0) {
            newIndex = alphabet.length - Math.abs(newIndex);
        }
        return alphabet[newIndex];
    }

    public int findCurrentPosition(char result) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == result) {
                return i;
            }
        }
        return -1;
    }

    public HashMap<Character, Long> countChars(String path) {
        HashMap<Character, Long> mapCalculatedChars = new HashMap<>();
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            if (bufferedReader.readLine() == null) {
                System.out.println("File is empty");
            } else {
                int counter;
                long sum = 0L;
                while ((counter = bufferedReader.read()) != -1) {
                    mapOfChar(mapCalculatedChars, counter, sum);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("there is no file");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return mapCalculatedChars;
    }

    public HashMap<Character, Long> mapOfChar(HashMap<Character, Long> hashMap, int counter, long sum) {
        char result = (char) counter;
        for (char c : alphabet) {
            if (c == result) {
                sum = sum + 1;
                hashMap.putIfAbsent(result, sum);
                if (hashMap.containsKey(result)) {
                    hashMap.merge(result, 1L, Long::sum);
                }
            }
        }
        return hashMap;
    }
}
