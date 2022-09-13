package ru.javarush.november.zaharin.cryptoanalizer;

public class Application {
    private static final char[] alphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и',
            'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т',
            'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь',
            'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё',
            'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П',
            'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
            'Ь', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '.', ',', '”', ':',
            '-', '!', '?', ' '};
    //'\n', '\r', '\f', '\t'

    public static void main(String[] args) {
        Creator creator = new Creator(alphabet);
        new Runner(creator).run();
    }
}
