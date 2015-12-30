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
package de.rnd7.kata.gameoflife;

import de.rnd7.kata.gameoflife.engine.EGameAction;
import de.rnd7.kata.gameoflife.engine.GameEngine;
import de.rnd7.kata.gameoflife.engine.RuleSet;
import de.rnd7.kata.gameoflife.model.Cell;
import de.rnd7.kata.gameoflife.model.GameField;
import de.rnd7.kata.gameoflife.ui.GameDialog;

public class Main {

	public static void main(final String[] args) {

		final GameField field = new GameField(100, 100);
		final GameEngine engine = createConwayWorld(field);
		// final GameEngine engine = createCopyWorld(field);

		creatBat(field);
		// createGospersGliderGun(field);

		new GameDialog(engine);

	}

	private static void creatBat(final GameField field) {
		field.createCell(new Cell(41, 46));
		field.createCell(new Cell(41, 45));
		field.createCell(new Cell(41, 44));
		field.createCell(new Cell(43, 44));
		field.createCell(new Cell(43, 45));
		field.createCell(new Cell(43, 46));
		field.createCell(new Cell(42, 47));
	}

	@SuppressWarnings("unused")
	private static void createGospersGliderGun(final GameField field) {
		field.createCell(new Cell(8, 8));
		field.createCell(new Cell(43, 6));
		field.createCell(new Cell(30, 8));
		field.createCell(new Cell(43, 5));
		field.createCell(new Cell(9, 8));
		field.createCell(new Cell(22, 8));
		field.createCell(new Cell(30, 4));
		field.createCell(new Cell(23, 10));
		field.createCell(new Cell(21, 5));
		field.createCell(new Cell(8, 7));
		field.createCell(new Cell(19, 6));
		field.createCell(new Cell(20, 11));
		field.createCell(new Cell(21, 11));
		field.createCell(new Cell(42, 6));
		field.createCell(new Cell(42, 5));
		field.createCell(new Cell(9, 7));
		field.createCell(new Cell(20, 5));
		field.createCell(new Cell(32, 9));
		field.createCell(new Cell(32, 8));
		field.createCell(new Cell(24, 7));
		field.createCell(new Cell(32, 4));
		field.createCell(new Cell(24, 8));
		field.createCell(new Cell(24, 9));
		field.createCell(new Cell(28, 6));
		field.createCell(new Cell(28, 7));
		field.createCell(new Cell(32, 3));
		field.createCell(new Cell(25, 8));
		field.createCell(new Cell(28, 5));
		field.createCell(new Cell(18, 9));
		field.createCell(new Cell(18, 8));
		field.createCell(new Cell(23, 6));
		field.createCell(new Cell(19, 10));
		field.createCell(new Cell(18, 7));
		field.createCell(new Cell(29, 5));
		field.createCell(new Cell(29, 7));
		field.createCell(new Cell(29, 6));
	}

	private static GameEngine createConwayWorld(final GameField field) {
		final RuleSet ruleSetForAlive = new RuleSet();
		ruleSetForAlive.addRule(0, EGameAction.CELL_DIE);
		ruleSetForAlive.addRule(1, EGameAction.CELL_DIE);

		ruleSetForAlive.addRule(2, EGameAction.NOTHING);
		ruleSetForAlive.addRule(3, EGameAction.NOTHING);

		ruleSetForAlive.addRule(4, EGameAction.CELL_DIE);
		ruleSetForAlive.addRule(5, EGameAction.CELL_DIE);
		ruleSetForAlive.addRule(6, EGameAction.CELL_DIE);
		ruleSetForAlive.addRule(7, EGameAction.CELL_DIE);
		ruleSetForAlive.addRule(8, EGameAction.CELL_DIE);

		final RuleSet ruleSetForDead = new RuleSet();
		ruleSetForDead.addRule(3, EGameAction.CELL_BORN);

		return new GameEngine(field, ruleSetForDead, ruleSetForAlive);
	}

	@SuppressWarnings("unused")
	private static GameEngine createCopyWorld(final GameField field) {
		final RuleSet ruleSetForAlive = new RuleSet();
		ruleSetForAlive.addRule(0, EGameAction.CELL_DIE);
		ruleSetForAlive.addRule(2, EGameAction.CELL_DIE);
		ruleSetForAlive.addRule(4, EGameAction.CELL_DIE);
		ruleSetForAlive.addRule(6, EGameAction.CELL_DIE);
		ruleSetForAlive.addRule(8, EGameAction.CELL_DIE);

		final RuleSet ruleSetForDead = new RuleSet();
		ruleSetForDead.addRule(1, EGameAction.CELL_BORN);
		ruleSetForDead.addRule(3, EGameAction.CELL_BORN);
		ruleSetForDead.addRule(5, EGameAction.CELL_BORN);
		ruleSetForDead.addRule(7, EGameAction.CELL_BORN);

		return new GameEngine(field, ruleSetForDead, ruleSetForAlive);
	}
}
