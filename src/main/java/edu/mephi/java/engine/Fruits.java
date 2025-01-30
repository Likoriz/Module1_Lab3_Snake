package edu.mephi.java.engine;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Fruits {
    private final List<Point> fruits;
    private final int maxAmount = 5;
    private final int width, height;
    private final Random random;

    public Fruits(int width, int height) {
        this.width = width;
        this.height= height;

        this.fruits = new ArrayList<>(maxAmount);
        this.random = new Random();
        generateFruits();
    }

    public void generateFruits() {
        while (fruits.size() < maxAmount) {
            Point newFruit = new Point(random.nextInt(width), random.nextInt(height));

            if (!fruits.contains(newFruit))
                fruits.add(newFruit);
        }
    }

    public void removeFruit(Point fruit) {
        fruits.remove(fruit);
    }

    public List<Point> getFruits() {
        return fruits;
    }
}
