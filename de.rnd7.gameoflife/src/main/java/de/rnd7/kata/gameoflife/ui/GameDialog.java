package de.rnd7.kata.gameoflife.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.rnd7.kata.gameoflife.engine.GameEngine;
import de.rnd7.kata.gameoflife.model.Cell;
import de.rnd7.kata.gameoflife.model.GameField;

public class GameDialog {

	private final GameEngine engine;

	public GameDialog(final GameEngine engine) {
		this.engine = engine;
		final Display display = new Display();
		final Shell shell = new Shell(display);

		shell.setLayout(new GridLayout());
		shell.setLayoutData(new GridData(GridData.FILL_BOTH));

		final Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Button nextGenButton = new Button(composite, SWT.NONE);
		nextGenButton.setText("Next Generation");

		final Button clear = new Button(composite, SWT.NONE);
		clear.setText("Clear");

		final Button print = new Button(composite, SWT.NONE);
		print.setText("Print");

		final Label label = new Label(shell, SWT.NONE);
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Canvas canvas = new Canvas(shell, SWT.BORDER);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
		canvas.addPaintListener(new GameFieldRenderer(engine.getGameField()));
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(final MouseEvent e) {
				final int x = e.x / GameFieldRenderer.CELL_SIZE;
				final int y = e.y / GameFieldRenderer.CELL_SIZE;

				final GameField gameField = engine.getGameField();

				if ((x >= 0) && (y >= 0) && (gameField.getWidth() >= x) && (gameField.getHeight() >= y)) {

					final Cell cell = gameField.getCellAt(x, y);
					if (cell == null) {
						gameField.createCell(new Cell(x, y));
					} else {
						gameField.destoryCell(cell);
					}
				}

				canvas.redraw();

			}
		});
		nextGenButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				engine.nextGeneration();

				label.setText(String.format("Generation: %d", engine.getGeneration()));

				canvas.redraw();
			}
		});
		clear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				engine.clear();
				canvas.redraw();
			}
		});
		print.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				GameFieldPrinter.printField(engine.getGameField());
			}
		});

		final Rectangle clientArea = shell.getClientArea();
		shell.setBounds(clientArea.x + 10, clientArea.y + 10, 1000, 1000);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}