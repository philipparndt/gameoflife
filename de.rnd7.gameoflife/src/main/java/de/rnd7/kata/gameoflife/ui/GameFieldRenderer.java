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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;

import de.rnd7.kata.gameoflife.model.Cell;
import de.rnd7.kata.gameoflife.model.GameField;

public class GameFieldRenderer implements PaintListener {

	static final int CELL_SIZE = 8;
	private final GameField field;

	public GameFieldRenderer(final GameField field) {
		this.field = field;
	}

	@Override
	public void paintControl(final PaintEvent e) {
		final GC gc = e.gc;

		gc.setBackground(gc.getDevice().getSystemColor(SWT.COLOR_DARK_GRAY));

		gc.fillRectangle(0, 0, (int) this.field.getWidth() * CELL_SIZE, (int) this.field.getHeight() * CELL_SIZE);

		gc.setForeground(gc.getDevice().getSystemColor(SWT.COLOR_BLACK));
		gc.setBackground(gc.getDevice().getSystemColor(SWT.COLOR_CYAN));

		for (int x = 0; x < this.field.getWidth(); x++) {
			for (int y = 0; y < this.field.getHeight(); y++) {

				final int xx = x * CELL_SIZE;
				final int yy = y * CELL_SIZE;

				final Cell cell = this.field.getCellAt(x, y);

				if (cell != null) {
					gc.setBackground(gc.getDevice().getSystemColor(SWT.COLOR_CYAN));
					gc.fillRectangle(xx, yy, CELL_SIZE, CELL_SIZE);
				} else {
					gc.setBackground(gc.getDevice().getSystemColor(SWT.COLOR_DARK_GRAY));
				}

				// gc.fillRectangle(xx, yy, CELL_SIZE, CELL_SIZE);
			}
		}

		for (int x = 0; x < this.field.getWidth(); x++) {
			final int xx = x * CELL_SIZE;
			gc.drawLine(xx, 0, xx, (int) this.field.getHeight() * CELL_SIZE);
		}

		for (int y = 0; y < this.field.getHeight(); y++) {
			final int yy = y * CELL_SIZE;
			gc.drawLine(0, yy, (int) this.field.getWidth() * CELL_SIZE, yy);
		}
	}

}
