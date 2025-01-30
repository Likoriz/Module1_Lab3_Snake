package edu.mephi.java;

import edu.mephi.java.engine.Direction;
import edu.mephi.java.engine.Game;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Game");
		Game game = new Game();
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (game.isGameOver() && e.getKeyCode() == KeyEvent.VK_R) {
					game.restart();
				}
				else
					switch (e.getKeyCode()) {
						case KeyEvent.VK_UP -> game.setDirection(Direction.UP);
						case KeyEvent.VK_DOWN -> game.setDirection(Direction.DOWN);
						case KeyEvent.VK_LEFT -> game.setDirection(Direction.LEFT);
						case KeyEvent.VK_RIGHT -> game.setDirection(Direction.RIGHT);
					}
			}
		});
	}
}