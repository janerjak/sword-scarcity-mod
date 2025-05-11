package de.janjak.minecraft.tim.swordscarcity.data;

public record RelevantAdvancement(
    String locationKey,
    SwordElement rewardingElement,
    String englishTitle,
    String germanTitle
) {
    public String getTitleTranslationKey() {
        String locationKeyTransformed = this.locationKey.replace('/', '.');
        return "advancements." + locationKeyTransformed + ".title";
    }

    public String getRewardItemId() {
        return this.rewardingElement.name().toLowerCase() + "_sword";
    }

    public String getFullRewardItemId() {
        return "elementalswords:" + this.getRewardItemId();
    }
}
