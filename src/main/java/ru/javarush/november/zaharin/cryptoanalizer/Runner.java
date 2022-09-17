package ru.javarush.november.zaharin.cryptoanalizer;

import java.io.File;
import java.util.Scanner;

public class Runner {
    public final Creator creator;

    public Runner(Creator creator) {
        this.creator = creator;
    }

    public void run() {
        boolean isWorking = true;
        while (isWorking) {
            System.out.println("Input your choose [1 coding] [2 recoding] [3 force] [4 exit]");
            Scanner c = new Scanner(System.in);
            String pathOriginal = null;
            String pathCoded = null;
            String pathBrut = null;
            int choose = Integer.parseInt(c.nextLine());
            if (choose == 1) {
                System.out.println("Input the path to your file (for example src/main/resources/Chukovskiy.txt)");
                Scanner cn = new Scanner(System.in);
                pathOriginal = cn.nextLine();
                System.out.println("Input the key");
                Scanner cns = new Scanner(System.in);
                String sStep = cns.nextLine();
                long step = Integer.parseInt(sStep);
                File enCode = creator.coding(pathOriginal, step);
            } else if (choose == 2) {
                System.out.println("Input the path to the coded file (for example coding_text.txt)");
                Scanner cn = new Scanner(System.in);
                pathCoded = cn.nextLine();
                System.out.println("Input the key");
                Scanner cns = new Scanner(System.in);
                String sStep = cns.nextLine();
                long step = Integer.parseInt(sStep);
                File deCode = creator.deCoding(pathCoded, step);
            } else if (choose == 3) {
                System.out.println("Input the path to the coded file (for example coding_text.txt)");
                Scanner cn = new Scanner(System.in);
                pathBrut = cn.nextLine();
                File brakeBrut = creator.brakeBrut(pathBrut);
            } else if (choose == 4) {
                isWorking = false;
            }
        }
    }
}
