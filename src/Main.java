import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // x-координаты точек первого множества
    static double[] x1 = new double[100];
    // y-координаты точек первого множества
    static double[] y1 = new double[100];
    // кол-во точек в первом множестве
    static int cnt1;
    // x-координаты точек второго множества
    static double[] x2 = new double[100];
    // y-координаты точек второго множества
    static double[] y2 = new double[100];
    // кол-во точек во втором множестве
    static int cnt2;

    // метод выводит меню
    static void menu() {
        System.out.println("Выберите пункт меню:");
        System.out.println("0 - выход");
        System.out.println("1 - добавить точку");
        System.out.println("2 - список точек");
        System.out.println("3 - добавить случайные точки");
        System.out.println("4 - сохранить точки");
        System.out.println("5 - загрузить точки");
        System.out.println("6 - решить задачу");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // текущий пункт меню
        int v;
        // пока считанное с клавиатуры число не равно 0
        do {
            // выводим меню
            menu();
            // читаем число с клавиатуры
            v = sc.nextInt();
            // разбираем, каким может быть введённый пункт меню
            switch (v) {
                case 1:
                    // запускаем добавление точки
                    add(sc);
                    break;
                case 2:
                    // запускаем вывод точек
                    show();
                    break;
                case 3:
                    // запускаем порождение случайных точек
                    rnd(sc);
                    break;
                case 4:
                    // сохраняем точки
                    save();
                    break;
                case 5:
                    // сохраняем точки
                    load();
                    break;
                case 6:
                    // решение задачи
                    solve();
                    break;
            }
        } while (v != 0);
    }

    // решение задачи
    static void solve() {
        // массив индексов пересекающихся точек из первого множества
        int[] crossedIndexes = new int[100];
        // количество пересекающихся точек из первого множества
        int crossedCnt = 0;

        // перебираем все пары точек по одной из каждого множества
        for (int i = 0; i < cnt1; i++) {
            for (int j = 0; j < cnt2; j++) {
                // если их координаты совпадают
                if (Math.abs(x1[i] - x2[j]) < 0.001 && Math.abs(y1[i] - y2[j]) < 0.001) {
                    // сохраняем индекс совпадающей точки
                    crossedIndexes[crossedCnt] = i;
                    // увеличиваем количество совпавших точек на 1
                    crossedCnt++;
                }
            }
        }

        // выводим данные
        System.out.println("Пересекающиеся точки:");
        for (int i = 0; i < crossedCnt; i++) {
            System.out.printf("%.1f %.1f\n", x1[crossedIndexes[i]], y1[crossedIndexes[i]]);
        }
    }

    // чтение данных из файла
    static void load() {
        // блок try...catch обязателен при работе с файлами
        try {
            // открываем файл
            Scanner sc = new Scanner(new File("data.txt"));
            // вводим количество точек в первом множестве
            cnt1 = sc.nextInt();
            // вводим координаты точек
            for (int i = 0; i < cnt1; i++) {
                x1[i] = sc.nextDouble();
                y1[i] = sc.nextDouble();
            }
            // вводим количество точек во втором множестве
            cnt2 = sc.nextInt();
            // вводим координаты точек
            for (int i = 0; i < cnt2; i++) {
                x2[i] = sc.nextDouble();
                y2[i] = sc.nextDouble();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    // сохранение данных в файл
    static void save() {
        // блок try...catch обязателен при работе с файлами
        try {
            // открываем файл out
            PrintStream out = new PrintStream("data.txt");
            // выводим количество точек в первом множестве
            out.println(cnt1);
            // выводим координаты точек
            for (int i = 0; i < cnt1; i++) {
                out.printf("%.3f %.3f\n", x1[i], y1[i]);
            }
            // выводим количество точек во втором множестве
            out.println(cnt2);
            // выводим координаты точек
            for (int i = 0; i < cnt2; i++) {
                out.printf("%.3f %.3f\n", x2[i], y2[i]);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // добавить случайные точки
    static void rnd(Scanner sc) {
        // выводим подсказку
        System.out.println("Введите количество случайных точек: int");
        // читаем кол-во точек
        int cnt = sc.nextInt();
        // объект для порождения случайных чисел
        Random r = new Random();
        // повторяем cnt раз
        for (int i = 0; i < cnt; i++) {
            // если случайное логическое значение равно истине
            if (r.nextBoolean()) {
                // добавляем новую случайную x-координату первого множества от -5 до 5
                x1[cnt1] = r.nextDouble() * 10 - 5;
                // добавляем новую случайную y-координату первого множества от -5 до 5
                y1[cnt1] = r.nextDouble() * 10 - 5;
                // увеличиваем количество точек в первом множестве на 1
                cnt1++;
            } else {
                // добавляем новую случайную x-координату второго множества от -5 до 5
                x2[cnt2] = r.nextDouble() * 10 - 5;
                // добавляем новую случайную y-координату второго множества от -5 до 5
                y2[cnt2] = r.nextDouble() * 10 - 5;
                // увеличиваем количество точек во втором множестве на 1
                cnt2++;
            }
        }
        System.out.println("Точки добавлены");
    }


    // метод вывода всех добавленных точек на экран
    static void show() {
        System.out.println("Точки первого множества:");
        for (int i = 0; i < cnt1; i++) {
            System.out.printf("%.3f, %.3f\n", x1[i], y1[i]);
        }
        System.out.println("Точки второго множества:");
        for (int i = 0; i < cnt2; i++) {
            System.out.printf("%.3f, %.3f\n", x2[i], y2[i]);
        }
    }

    // метод добавления точек в качестве аргумента принимает ссылку на сканер
    static void add(Scanner sc) {
        // выводим подсказку
        System.out.println("Введите множество точки и её координаты: int, double, double");
        // читаем номер множества
        int set = sc.nextInt();
        // читаем x-координату
        double x = sc.nextDouble();
        // читаем y-координату
        double y = sc.nextDouble();
        // если указано первое множество
        if (set == 1) {
            // количество точек в первом множестве меньше 100
            if (cnt1 < 100) {
                // добавляем x координату в массив
                x1[cnt1] = x;
                // добавляем y координату в массив
                y1[cnt1] = y;
                // увеличиваем количество точек в первом множестве на 1
                cnt1++;
                System.out.printf("точка (%.1f, %.1f) добавлена в первое множество\n", x, y);
            } else
                System.out.println("первое множество заполнено полностью");
        } else if (set == 2) { // если указано второе множество
            // количество точек во втором множестве меньше 100
            if (cnt2 < 100) {
                // добавляем x координату в массив
                x2[cnt2] = x;
                // добавляем y координату в массив
                y2[cnt2] = y;
                // увеличиваем количество точек во втором множестве на 1
                cnt2++;
                System.out.printf("точка (%.3f, %.3f) добавлена во второе множество\n", x, y);
            } else
                System.out.println("второе множество заполнено полностью");
        } else {
            System.out.println(set + " - недопустимое значение множества");
        }
    }


}