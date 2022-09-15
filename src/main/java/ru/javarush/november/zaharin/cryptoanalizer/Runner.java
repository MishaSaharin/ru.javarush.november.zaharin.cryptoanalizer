package ru.javarush.november.zaharin.cryptoanalizer;

import java.io.File;
import java.util.Map;
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
            int choose = Integer.parseInt(c.nextLine());
            if (choose == 1) {
                System.out.println("Input the path to your file");
                Scanner cn = new Scanner(System.in);
                String path = cn.nextLine();
                System.out.println("Input the key");
                Scanner cns = new Scanner(System.in);
                int step = cns.nextInt();
                File enCode = creator.coding(path, step);
            } else if (choose == 2) {
                System.out.println("Input the path to your file");
                Scanner cn = new Scanner(System.in);
                String path = cn.nextLine();
                System.out.println("Input the key");
                Scanner cns = new Scanner(System.in);
                int step = cns.nextInt();
                File deCode = creator.deCoding(path,step);
            } else if (choose == 3) {
                System.out.println("Input the path to your file?");
                Scanner cn = new Scanner(System.in);
                String path = cn.nextLine();
                Map brakeBrut = creator.countChars(path);
            } else if (choose == 4) {
                isWorking = false;
            }
        }
    }
}
