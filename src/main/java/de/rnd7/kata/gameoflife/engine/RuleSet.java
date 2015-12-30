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

import java.util.HashMap;
import java.util.Map;

public class RuleSet {
	private final Map<Integer, EGameAction> rules = new HashMap<>();

	public void addRule(final int cellCount, final EGameAction action) {
		this.rules.put(cellCount, action);
	}

	public EGameAction getRuleFor(final int cellCount) {
		final EGameAction action = this.rules.get(cellCount);
		return action == null ? EGameAction.NOTHING : action;
	}
}
