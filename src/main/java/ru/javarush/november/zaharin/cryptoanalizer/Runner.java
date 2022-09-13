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
            int choose = Integer.parseInt(c.nextLine());
            if (choose == 1) {
                System.out.println("Input the path to your file");
                Scanner cn = new Scanner(System.in);
                String path = cn.nextLine();
                System.out.println("Input the key");
                Scanner cns = new Scanner(System.in);
                int step = cns.nextInt();
                File enCode = creator.coding(path, step);
                //System.out.println(enCode);
            } else if (choose == 2) {
//                System.out.println("Input the path to your file");
//                Scanner cn = new Scanner(System.in);
//                String path = cn.nextLine();
                System.out.println("Input the key");
                Scanner cns = new Scanner(System.in);
                int step = cns.nextInt();
                File deCode = creator.deCoding(step);
                //System.out.println(deCode);
            } else if (choose == 3) {
                System.out.println("Input the path to your file?");
                Scanner cn = new Scanner(System.in);
                String path = cn.nextLine();
                int reStep = creator.brakeBrut(path);
            } else if (choose == 4) {
                isWorking = false;
            }
        }
    }
}
