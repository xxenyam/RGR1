package consoleTasks.interpolation;

import consoleTasks.Point2D;

import java.util.ArrayList;
import java.util.List;

public class ListInterpolation extends Interpolator {
    private ArrayList<Point2D> data = null;
    public ListInterpolation(ArrayList<Point2D> data) {
        this.data = data;
    }

    public ListInterpolation() {data = new ArrayList<Point2D>();}
    public ListInterpolation(Point2D[] data) {
        this();
        for (Point2D pt : data)
            this.data.add(pt);
    }
    @Override
    public void clear() {data.clear();}
    @Override
    public int numPoints() {return data.size();}
    @Override
    public void addPoint(Point2D pt) {data.add(pt);}
    @Override
    public Point2D getPoint(int i) {return data.get(i);}
    public ArrayList<Point2D> getData() {
        return data;
    }
    @Override
    public void setPoint(int i, Point2D pt) {data.set(i, pt);}
    @Override
    public void removeLastPoint() {data.remove(data.size()-1);}
    @Override
    public void sort() {java.util.Collections.sort(data);}

    public static void main(String[] args) {
        System.out.println("List Interpolation");
        ListInterpolation fun = new ListInterpolation();
        int num;
        double x;
        java.util.Scanner in = new java.util.Scanner(System.in);
        do {
            System.out.print("Количество точек: ");
            num = in.nextInt();
        } while (num <= 0);
        for (int i = 0; i < num; i++){
            x = 1.0 + (5.0 - 1.0)*Math.random();
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }
        System.out.println("Интерполяция по: " + fun.numPoints() + " точкам");
        System.out.println("Несортированный набор: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        fun.sort();
        System.out.println("Отсортированный набор: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));

        System.out.println("Минимальное значение x: " + fun.getPoint(0).getX());
        System.out.println("Максимальное значение x: " + fun.getPoint(fun.numPoints()-1).getX());

        x = 0.5*(fun.getPoint(0).getX() + fun.getPoint(fun.numPoints()- 1).getX());
        System.out.println("Значение интеролляции fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Точное значение sin(" + x + ") = " + Math.sin(x));
        System.out.println("Абсолютная ошибка = " + Math.abs(fun.evalf(x)-Math.sin(x)));

        System.out.println("\nОтсортированный набор (удалили последнюю точку)");
        fun.removeLastPoint();
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));

        System.out.println("\nОтсортированный набор (добавили последнюю точку)");
        fun.addPoint(new Point2D(6,Math.sin(x)));
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        fun.clear();
        System.out.println("Длинна после очистки: "+fun.numPoints());
    }
}

