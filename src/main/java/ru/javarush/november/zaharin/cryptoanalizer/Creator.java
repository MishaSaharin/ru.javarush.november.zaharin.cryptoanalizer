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
        File file2 = new File("coding_text.txt");
        try (FileReader fileReader = new FileReader(path);
             FileWriter fileWriter = new FileWriter(file2);
             BufferedReader bufferedReader = new BufferedReader(fileReader, alphabet.length)) {
            int counter;
            while ((counter = bufferedReader.read()) != -1) {
                // work with (char)counter
                for (int i = 0; i < alphabet.length; i++) {
                    // compare (char)counter
                    char result = (char) counter;
                    if (alphabet[i] == result) {
                        result = alphabet[(i + step) % alphabet.length];
                        fileWriter.write(result);
                    } else {
                        result = (char) counter;
                        fileWriter.write(result);
                    }
                }
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
                // work with (char)counter
                for (int i = 0; i < alphabet.length; i++) {
                    // compare (char)counter
                    char result = (char) counter;
                    if (alphabet[i] == result) {
                        result = alphabet[(i - step) % alphabet.length];
                        fileWriter.write(result);
                    } else {
                        result = (char) counter;
                        fileWriter.write(result);
                    }
                }
            }
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            System.out.println("there is no file");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return file2;
    }

    public File easyCoding(String path, int step) {
        File file2 = new File("easyCoding_text.txt");
        try (FileReader fileReader = new FileReader(path);
             FileWriter fileWriter = new FileWriter(file2);
             BufferedReader bufferedReader = new BufferedReader(fileReader, alphabet.length)) {
            int counter;
            char result = ' ';
            while ((counter = bufferedReader.read()) != -1) {
                // work with (char)counter
                result = (char) (counter + step);
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

    public File easyDeCoding(String path, int step) {
        File file2 = new File("easyDeCoding_text.txt");
        try (FileReader fileReader = new FileReader(path);
             FileWriter fileWriter = new FileWriter(file2);
             BufferedReader bufferedReader = new BufferedReader(fileReader, alphabet.length)) {
            int counter;
            char result = ' ';
            while ((counter = bufferedReader.read()) != -1) {
                // work with (char)counter
                result = (char) (counter - step);
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
}
