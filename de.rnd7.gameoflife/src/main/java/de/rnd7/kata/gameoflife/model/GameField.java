/**
 * Copyright 2016 Philipp Arndt
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
package de.rnd7.kata.gameoflife.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GameField {

	private final int width;
	private final int height;

	private final Map<Integer, Cell> cells = new HashMap<>();

	public GameField(final int width, final int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public void createCell(final Cell cell) {
		final Integer key = this.getCellKey(cell);
		this.cells.put(key, cell);
	}

	public void destoryCell(final Cell cell) {
		final Integer key = this.getCellKey(cell);
		this.cells.remove(key);
	}

	private Integer getCellKey(final Cell cell) {
		return this.getCellKey(cell.getX(), cell.getY());
	}

	public Cell getCellAt(final int x, final int y) {
		final Integer key = this.getCellKey(x, y);
		return this.cells.get(key);
	}

	private int getCellKey(final int x, final int y) {
		return (x * 1000) + y;
	}

	public int countNeighbours(final Cell cell) {
		int result = 0;

		if (this.isCellAt(cell.getX(), cell.getY())) {
			// Start with -1 as we are counting the cell itself
			result--;
		}

		final int cellX = cell.getX();
		final int cellY = cell.getY();

		for (int x = cellX - 1; x <= (cellX + 1); x++) {
			for (int y = cellY - 1; y <= (cellY + 1); y++) {
				if (this.isCellAt(x, y)) {
					result++;
				}
			}
		}

		return result;
	}

	public Collection<Cell> getCells() {
		return this.cells.values();
	}

	private boolean isCellAt(final int x, final int y) {
		return this.getCellAt(x, y) != null;
	}

	public long getWidth() {
		return this.width;
	}

	public long getHeight() {
		return this.height;
	}

	public void clear() {
		this.cells.clear();
	}
}
