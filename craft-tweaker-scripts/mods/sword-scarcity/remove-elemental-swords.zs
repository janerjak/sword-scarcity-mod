println("Removing Four Elemental Swords crafting table recipes");

val elementsToRemove = ["fire", "water", "air", "earth"];

for element in elementsToRemove {
    val itemName = "elementalswords:" + element + "_sword";
    craftingTable.removeByName(itemName);
}

println("Done removing crafting recipes");