package de.janjak.minecraft.tim.swordscarcity.data;

record RelevantAdvancement(
	String locationKey,
	SwordElement rewardingElement,
	String englishTitle,
	String germanTitle
) {
	String getTitleTranslationKey() {
		String locationKeyTransformed = locationKey.replace('/', '.');
		return "advancements." + locationKeyTransformed + ".title";
	}
}
