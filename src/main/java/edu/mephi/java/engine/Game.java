package edu.mephi.java.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements ActionListener {
	private final int TILE_SIZE = 20;
	private final int WIDTH = 20;
	private final int HEIGHT = 20;
	private boolean gameOver = false;
	private final Timer timer;

	private int score = 0;
	private long startTime;
	private final int INTERFACE_HEIGHT = 60;
	private Snake snake;
	private int frameCounter = 0;
	private Fruits fruits;

	public Game() {
		setPreferredSize(new Dimension(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE + INTERFACE_HEIGHT));
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("You pressed " + e.getKeyChar() + " key!");
			}
		});

		startTime = System.currentTimeMillis();
		timer = new Timer(100, this);
		timer.start();

		snake = new Snake(WIDTH / 2, HEIGHT / 2);
		fruits = new Fruits(WIDTH, HEIGHT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frameCounter++;

		if (frameCounter % 3 == 0) {
			snake.move();
			checkCollision();
		}
		repaint();
	}

	public void checkCollision() {
		Point head = snake.getHead();

		if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
			gameOver = true;
			timer.stop();
		}

		for (int i = 1; i < snake.getBody().size(); i++)
			if (head.equals(snake.getBody().get(i))) {
				gameOver = true;
				timer.stop();
				break;
			}

		for (int j = 0; j < fruits.getFruits().size(); j++)
			if (head.equals(fruits.getFruits().get(j))) {
				fruits.removeFruit(fruits.getFruits().get(j));
				snake.grow();
				score++;
			}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		long time = (System.currentTimeMillis() - startTime) / 1000;

		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Roman", Font.BOLD, 16));
		g.drawString("Время жизни: " + time + " секунд", 10, 20);
		g.drawString("Съедено фруктов: " + score, 10, 40);

		if (!gameOver) {
			g.setColor(Color.GREEN);
			for (int i = 0; i <= WIDTH; i++)
				g.drawLine(i * TILE_SIZE, INTERFACE_HEIGHT, i * TILE_SIZE, HEIGHT * TILE_SIZE + INTERFACE_HEIGHT);

			for (int j = 0; j <= HEIGHT; j++)
				g.drawLine(0, j * TILE_SIZE + INTERFACE_HEIGHT, WIDTH * TILE_SIZE, j * TILE_SIZE + INTERFACE_HEIGHT);

			g.setColor(Color.RED);
			for (Point p : fruits.getFruits())
				g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE + INTERFACE_HEIGHT, TILE_SIZE, TILE_SIZE);

			drawSnake(g);
			fruits.generateFruits();
		}
		else {
			g.drawString("Вы проиграли!", 150, 150);
			g.drawString("Нажмите R, чтобы начать сначала.", 70, 170);
		}
	}

	public void drawSnake(Graphics g) {
		g.setColor(Color.GREEN);
		for (Point p : snake.getBody())
			g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE + INTERFACE_HEIGHT, TILE_SIZE, TILE_SIZE);
	}

	public void setDirection(Direction direction) {
		snake.setDirection(direction);
	}

	public void restart() {
		gameOver = false;

		startTime = System.currentTimeMillis();
		timer.start();

		snake = new Snake(WIDTH / 2, HEIGHT / 2);
		fruits = new Fruits(WIDTH, HEIGHT);
		score = 0;
		repaint();
	}

	public boolean isGameOver() {
		return gameOver;
	}
}
