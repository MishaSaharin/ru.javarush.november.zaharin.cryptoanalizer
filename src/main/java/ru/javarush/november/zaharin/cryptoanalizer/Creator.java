package ru.javarush.november.zaharin.cryptoanalizer;

import java.io.*;

public class Creator {
    private char[] alphabet;
//    private char[] charsAlphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и',
//            'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т',
//            'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь',
//            'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё',
//            'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П',
//            'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
//            'Ь', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '.', ',', '”', ':',
//            '-', '!', '?', ' '};

    public Creator(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public int brut(String text) {
        ///....
        return Integer.parseInt(null);
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

    public File deCoding(String path, int step) {
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

    private int findCurrentPosition(char result) {
        int findCurrentPosition = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == result) {
                findCurrentPosition = i;
            }
        }
        return findCurrentPosition;
    }
}
