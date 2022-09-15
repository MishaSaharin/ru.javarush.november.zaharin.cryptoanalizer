package ru.javarush.november.zaharin.cryptoanalizer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Creator {
    private char[] alphabet;

    public Creator(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public File brakeBrut(String path) {
        int step;
        String path1 = "src/main/resources/Chukovskiy.txt"; // for treemap with reference values
        countFrequencyLetters(path1); // map reference values
        File file2 = new File("brakeBrut_text.txt");
        TreeMap<Character, Long> treeMapCode = new TreeMap<>();
        try (FileReader fileReader = new FileReader(path);
             //FileWriter fileWriter = new FileWriter(file2);
             BufferedReader bufferedReader = new BufferedReader(fileReader, alphabet.length)) {
            int counter;
            long sum = 0L;
            long total = 0L;
            while ((counter = bufferedReader.read()) != -1) {
                total++;
                char result = (char) counter;
                for (char c : alphabet) {
                    if (c == result) {
                        sum = sum + 1;
                        treeMapCode.putIfAbsent(result, sum);
                        if (treeMapCode.containsKey(result)) {
                            treeMapCode.merge(result, 1L, (oldVal, newVal) -> oldVal + newVal);
                        }
                    }
                }
            }

            //step = foundStepForBrut(treeMapCode, countFrequencyLetters(path));
            //deCoding(path, step);
        } catch (FileNotFoundException e) {
            System.out.println("there is no file");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return file2;
    }

    public File coding(String path, int step) {
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

    public File deCoding(String path, int step) {
        File file2 = new File("deCoding_text.txt");
        try (FileReader fileReader = new FileReader(path);
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

    public char findNewSymbolCoding(int step, char result) {// Andrii Shylin (UA Kyiv)
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

    public TreeMap<Character, Long> countFrequencyLetters(String path) {
        TreeMap<Character, Long> treeMapOriginal = new TreeMap<>();
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader, alphabet.length)) {
            int counter;
            long sum = 0L;
            long total = 0L;
            while ((counter = bufferedReader.read()) != -1) {
                total++;
                char result = (char) counter;
                for (char c : alphabet) {
                    if (c == result) {
                        sum = sum + 1;
                        treeMapOriginal.putIfAbsent(result, sum);
                        if (treeMapOriginal.containsKey(result)) {
                            treeMapOriginal.merge(result, 1L, (oldVal, newVal) -> oldVal + newVal);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("there is no file");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return treeMapOriginal;
    }

    public int foundStepForBrut(TreeMap<Character, Long> treeMapOriginal, TreeMap<Character, Long> treeMapCoded) {
        char original = treeMapOriginal.firstKey();
        char coded = treeMapCoded.firstKey();
        int indexOriginal = 0;
        int indexCoded = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (original == alphabet[i]) {
                indexOriginal = i;
            }
        }
        for (int i = 0; i < alphabet.length; i++) {
            if (coded == alphabet[i]) {
                indexCoded = i;
            }
        }
        return indexCoded - indexOriginal;
    }

    public TreeMap<Character, Long> makeFrequencyTreeMap() {
        TreeMap<Character, Long> alphabetFrequencyTreeMap = new TreeMap<>();
        alphabetFrequencyTreeMap.put('о', 10983L);
        alphabetFrequencyTreeMap.put('е', 84830L);
        alphabetFrequencyTreeMap.put('а', 7998L);
        alphabetFrequencyTreeMap.put('и', 7367L);
        alphabetFrequencyTreeMap.put('н', 6700L);
        alphabetFrequencyTreeMap.put('т', 6318L);
        alphabetFrequencyTreeMap.put('с', 5473L);
        alphabetFrequencyTreeMap.put('р', 4746L);
        alphabetFrequencyTreeMap.put('в', 4533L);
        alphabetFrequencyTreeMap.put('л', 4343L);
        return alphabetFrequencyTreeMap;
    }

    public int foundIndexOfCharacters(TreeMap<Character, Long> hashMapO, TreeMap<Character, Long> hashMapC) {
        //the most used characters 'о','е','а','и','н','т','с','в','р','л'

        long original = hashMapO.get('о');
        long coded = hashMapC.get('o');
        //

        int indexOriginal = 0;
        int indexCoded = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (original == alphabet[i]) {
                indexOriginal = i;
            }
        }
        for (int i = 0; i < alphabet.length; i++) {
            if (coded == alphabet[i]) {
                indexCoded = i;
            }

        }
        return indexCoded - indexOriginal;
    }

    public HashMap<Character, Long> countChars(String path) {
        HashMap<Character, Long> hashMap = new HashMap<>();
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            int counter;
            long sum = 0L;
            long total = 0L;
            while ((counter = bufferedReader.read()) != -1) {
                total++;
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
            }
            Map<Character, Long> sortedMap = hashMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (a, b) -> { throw new AssertionError(); },
                            LinkedHashMap::new
                    ));
            sortedMap.entrySet().forEach(System.out::println);
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
            TreeMap<Character, Long> treeMap = new TreeMap<>(sortedMap);
            for (Map.Entry m : treeMap.entrySet()) {
                System.out.println(m.getKey() + " " + m.getValue());
            }
            System.out.println(total);
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
            System.out.println(treeMap.firstKey());
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
            System.out.println(treeMap.lastKey());
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
        } catch (FileNotFoundException e) {
            System.out.println("there is no file");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return hashMap;
    }
}
