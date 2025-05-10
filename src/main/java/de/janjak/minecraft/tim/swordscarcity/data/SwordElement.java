package de.janjak.minecraft.tim.swordscarcity.data;

import java.util.HashMap;
import java.util.Map;

enum SwordElement {
	FIRE,
	AIR,
	WATER,
	EARTH,
}

abstract class SwordElementColor {
	private static final Map<SwordElement, Integer> COLOR_MAP = new HashMap<>() {{
		put(SwordElement.FIRE, 0xFF4400);
		put(SwordElement.AIR, 0xDDDDFF);
		put(SwordElement.WATER, 0x0022FF);
		put(SwordElement.EARTH, 0x555500);
	}};
	private static final int DEFAULT_COLOR = 0xAAAAAA;
	static int of(SwordElement element) {
		return SwordElementColor.COLOR_MAP.getOrDefault(element, SwordElementColor.DEFAULT_COLOR);
	}
}

abstract class SwordElementName {
	private static final Map<SwordElement, String> NAME_MAP = new HashMap<>() {{
		put(SwordElement.FIRE, "Feuer");
		put(SwordElement.AIR, "Luft");
		put(SwordElement.WATER, "Wasser");
		put(SwordElement.EARTH, "Erd");
	}};
	static String of(SwordElement element, String defaultName) {
		if (SwordElementName.NAME_MAP.containsKey(element)) {
			return SwordElementName.NAME_MAP.get(element) + "schwert";
		}
		return defaultName;
	}
	static String of(SwordElement element) {
		return SwordElementName.of(element, null);
	}
}