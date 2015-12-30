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
package de.rnd7.kata.gameoflife.ui;

import java.util.Collection;

import de.rnd7.kata.gameoflife.model.Cell;
import de.rnd7.kata.gameoflife.model.GameField;

public class GameFieldPrinter {
	public static void printField(final GameField field) {
		final Collection<Cell> cells = field.getCells();
		for (final Cell cell : cells) {
			final String message = String.format("field.createCell(new Cell(%d, %d));", cell.getX(), cell.getY());
			System.out.println(message);
		}
	}
}
