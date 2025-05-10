package de.janjak.minecraft.tim.swordscarcity.data;

import java.util.HashMap;
import java.util.Map;

public abstract class RelevantAdvancementStorage {
	public static final Map<String, RelevantAdvancement> titleKeyToAdvancementMap = new HashMap<>();
	private static final RelevantAdvancement[] relevantAdvancements = {
		new RelevantAdvancement(
			"nether/obtain_blaze_rod",
			SwordElement.FIRE,
			"Into Fire",
			"TODO"
		),
		new RelevantAdvancement(
			"story/root",
			SwordElement.EARTH,
			"Minecraft",
			"Minecraft"
		),
	};

	public static void initialize() {
		for (RelevantAdvancement advancement : RelevantAdvancementStorage.relevantAdvancements) {
			RelevantAdvancementStorage.titleKeyToAdvancementMap.put(advancement.getTitleTranslationKey(), advancement);
		}
	}
}
