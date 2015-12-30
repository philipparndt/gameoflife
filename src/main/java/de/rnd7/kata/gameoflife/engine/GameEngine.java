/**
 * Copyright 2015 Philipp Arndt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.rnd7.kata.gameoflife.engine;

import java.util.ArrayList;
import java.util.Collection;

import de.rnd7.kata.gameoflife.model.Cell;
import de.rnd7.kata.gameoflife.model.GameField;

public class GameEngine {
	private final GameField gameField;
	private final RuleSet ruleSetForDead;
	private final RuleSet ruleSetForAlive;
	private int generation;

	public GameEngine(final GameField gameField, final RuleSet ruleSetForDead, final RuleSet ruleSetForAlive) {
		this.gameField = gameField;
		this.ruleSetForDead = ruleSetForDead;
		this.ruleSetForAlive = ruleSetForAlive;
	}

	public void nextGeneration() {
		this.generation++;
		final Collection<Cell> toBeCreated = new ArrayList<>();
		final Collection<Cell> toBeDeleted = new ArrayList<>();

		for (int x = 0; x < this.gameField.getWidth(); x++) {
			for (int y = 0; y < this.gameField.getHeight(); y++) {
				Cell cell = this.gameField.getCellAt(x, y);

				RuleSet ruleSet = this.ruleSetForAlive;
				if (cell == null) {
					ruleSet = this.ruleSetForDead;
					cell = new Cell(x, y);
				}

				final int neighbours = this.gameField.countNeighbours(cell);
				final EGameAction action = ruleSet.getRuleFor(neighbours);
				switch (action) {
				case CELL_DIE:
					toBeDeleted.add(cell);
					break;
				case CELL_BORN:
					toBeCreated.add(cell);
					break;
				default:
					break;
				}
			}
		}

		for (final Cell cell : toBeDeleted) {
			this.gameField.destoryCell(cell);
		}

		for (final Cell cell : toBeCreated) {
			this.gameField.createCell(cell);
		}
	}

	public int getGeneration() {
		return this.generation;
	}

	public GameField getGameField() {
		return this.gameField;
	}

	public void clear() {
		this.generation = 0;
		this.gameField.clear();
	}
}
