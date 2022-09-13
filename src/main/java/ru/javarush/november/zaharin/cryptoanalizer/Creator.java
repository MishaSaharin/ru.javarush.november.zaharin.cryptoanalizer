package ru.javarush.november.zaharin.cryptoanalizer;

import java.io.*;
import java.util.*;

public class Creator {
    private char[] alphabet;

    public Creator(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public int brakeBrut() {
        int step = 0;
        File file2 = new File("brakeBrut_text.txt");
        try (FileReader fileReader = new FileReader("coding_text.txt");
             FileWriter fileWriter = new FileWriter(file2);
             BufferedReader bufferedReader = new BufferedReader(fileReader, alphabet.length)) {
            int counter;
            Double sum = 0.00;
            HashMap<Character, Double> characterDoubleHashMap = new HashMap<>();
            while ((counter = bufferedReader.read()) != -1) {
                Character result = (char) counter;
                makeHashMap(result, sum);
            }
            TreeMap<Character, Double> characterDoubleTreeMap = new TreeMap<>(characterDoubleHashMap);
            for (Map.Entry<Character, Double> entry : characterDoubleTreeMap.entrySet())
                System.out.println(entry.getKey() + " = " + entry.getValue());
        } catch (FileNotFoundException e) {
            System.out.println("there is no file");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return step;
    }

    public File coding(String path, int step) {
        // think about "n\"
        File file2 = new File("coding_text.txt");
        try (FileReader fileReader = new FileReader(path);
             FileWriter fileWriter = new FileWriter(file2);
             BufferedReader bufferedReader = new BufferedReader(fileReader, alphabet.length)) {
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
        return file2;
    }

    public File deCoding(int step) {
        // think about "n\"
        File file2 = new File("deCoding_text.txt");
        try (FileReader fileReader = new FileReader("coding_text.txt");
             FileWriter fileWriter = new FileWriter(file2);
             BufferedReader bufferedReader = new BufferedReader(fileReader, alphabet.length)) {
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
        return file2;
    }

    public char findNewSymbolCoding(int step, char result) { // Andrii Shylin (UA Kyiv)
//        findCurrentPosition returns -1 if current symbol is not present in alphabet
        int currentPosition = findCurrentPosition(result);
        if (currentPosition == -1) {
            return result;
        }
        int normalizedDelta = Math.abs(step % alphabet.length);
        int newIndex = (currentPosition + normalizedDelta) % alphabet.length;
        return alphabet[newIndex];
    }

    public char findNewSymbolDeCoding(int step, char result) { // Andrii Shylin (UA Kyiv)
//        findCurrentPosition returns -1 if current symbol is not present in alphabet
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

    public HashMap<?, ?> makeHashMap(char result, double sum) {
        HashMap<Character, Double> characterDoubleHashMap = new HashMap<>();
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == result) {
                sum++;
                characterDoubleHashMap.put(result, sum);
                if (characterDoubleHashMap.containsKey(result)) {
                    characterDoubleHashMap.replace(result, sum);
                }
            }
        }
        return characterDoubleHashMap;
    }

    public TreeMap<Character, Double> makeTreeMap(HashMap<Character, Double> characterDoubleHashMap) {
        TreeMap<Character, Double> characterDoubleTreeMap = new TreeMap<>(characterDoubleHashMap);
        for (Map.Entry<Character, Double> entry : characterDoubleTreeMap.entrySet())
            System.out.println(entry.getKey() + " = " + entry.getValue());
        return characterDoubleTreeMap;
    }
}
