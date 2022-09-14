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
            double sum = 0;
            double total = 0;
            HashMap<Character, Double> hashMapCode = new HashMap<>();
            while ((counter = bufferedReader.read()) != -1) {
                total++;
                char result = (char) counter;
                for (char c : alphabet) {
                    if (c == result) {
                        sum = sum + 1;
                        hashMapCode.putIfAbsent(result, sum);
                        if (hashMapCode.containsKey(result)) {
                            hashMapCode.merge(result,  1.0, (oldVal, newVal) -> oldVal + newVal);
                        }
                    }
                }
            }
            for (Map.Entry<Character, Double> entry : hashMapCode.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
            System.out.println(total);
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

    public HashMap<Character, Long> makeHashMap(char result, long sum, HashMap<Character, Long> hashMap) {
        for (char c : alphabet) {
            if (c == result) {
                sum++;
                hashMap.put(result, sum);
                if (hashMap.containsKey(result)) {
                    hashMap.replace(result, sum);
                }
            }
        }
        return hashMap;
    }

    public HashMap<Character, Double> makeFrequencyTreeMap() {
        HashMap<Character, Double> alphabetFrequencyTreeMap = new HashMap<>();
        alphabetFrequencyTreeMap.put('о', 10.983);
        alphabetFrequencyTreeMap.put('е', 8.4830);
        alphabetFrequencyTreeMap.put('а', 7.998);
        alphabetFrequencyTreeMap.put('и', 7.367);
        alphabetFrequencyTreeMap.put('н', 6.700);
        alphabetFrequencyTreeMap.put('т', 6.318);
        alphabetFrequencyTreeMap.put('с', 5.473);
        alphabetFrequencyTreeMap.put('р', 4.746);
        alphabetFrequencyTreeMap.put('в', 4.533);
        alphabetFrequencyTreeMap.put('л', 4.343);

        return alphabetFrequencyTreeMap;
    }

    public TreeMap<Character, Double> makeTreeMap(HashMap<Character, Double> hashMap) {
        TreeMap<Character, Double> treeMap = new TreeMap<>(hashMap);
        for (Map.Entry<Character, Double> entry : treeMap.entrySet())
            System.out.println(entry.getKey() + " = " + entry.getValue());
        return treeMap;
    }
    public int countFrequencyLetters() {
        int step = 0;
        File file2 = new File("countFrequencyLettersInCoding_text.txt");
        try (FileReader fileReader = new FileReader("src/main/resources/Chukovskiy.txt");
             FileWriter fileWriter = new FileWriter(file2);
             BufferedReader bufferedReader = new BufferedReader(fileReader, alphabet.length)) {
            int counter;
            double sum = 0;
            double total = 0;
            HashMap<Character, Double> hashMapOriginal = new HashMap<>();
            while ((counter = bufferedReader.read()) != -1) {
                total++;
                char result = (char) counter;
                for (char c : alphabet) {
                    if (c == result) {
                        sum = sum + 1;
                        hashMapOriginal.putIfAbsent(result, sum);
                        if (hashMapOriginal.containsKey(result)) {
                            hashMapOriginal.merge(result,  1.0, (oldVal, newVal) -> oldVal + newVal);
                        }
                    }
                }
            }
            for (Map.Entry<Character, Double> entry : hashMapOriginal.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
            System.out.println(total);
        } catch (FileNotFoundException e) {
            System.out.println("there is no file");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return step;
    }
}
