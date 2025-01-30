package edu.mephi.java.engine;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Snake {
    private final List<Point> body;

    private Direction direction;

    public Snake(int x, int y) {
        body = new ArrayList<>();
        body.add(new Point(x, y));
        direction = Direction.RIGHT;
    }

    public void setDirection(Direction newDirection) {
        if ((direction == Direction.UP && newDirection != Direction.DOWN) ||
                (direction == Direction.DOWN && newDirection != Direction.UP) ||
                (direction == Direction.LEFT && newDirection != Direction.RIGHT) ||
                (direction == Direction.RIGHT && newDirection != Direction.LEFT)) {
            direction = newDirection;
        }
    }

    public List<Point> getBody() {
        return body;
    }

    public Point getHead() {
        return body.getFirst();
    }

    public void move() {
        Point head = body.getFirst();
        Point newHead;

        switch (direction) {
            case UP -> newHead = new Point(head.x, head.y - 1);
            case DOWN -> newHead = new Point(head.x, head.y + 1);
            case LEFT -> newHead = new Point(head.x - 1, head.y);
            case RIGHT -> newHead = new Point(head.x + 1, head.y);
            default -> throw new IllegalArgumentException("Неоднозначное направление: " + direction);
        }

        body.addFirst(newHead);
        body.removeLast();
    }

    public void grow() {
        body.add(new Point(body.getLast()));
    }
}
