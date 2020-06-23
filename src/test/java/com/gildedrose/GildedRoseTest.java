package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    private void createNewGildedRoseAppAndUpdateQuality(Item[] items) {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void assertThatQualityReducesAtEndOfDay() {
        Item[] items = new Item[]{new Item("BigBoiBrain", 2, 5)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].quality).isEqualTo(4);
    }

    @Test
    void assertThatSellInReducesAtEndOfDay() {
        Item[] items = new Item[]{new Item("BigBoiBrain", 2, 5)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].sellIn).isEqualTo(1);
    }

    @Test
    void assertThatItemQualityIsNeverNegative() {
        Item[] items = new Item[]{new Item("BigBoiBrain", 2, 0)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].quality).isEqualTo(0);
    }

    @Test
    void assertThatItemQualityDegradesTwiceAsFastWhenSellInDateHasPassed() {
        Item[] items = new Item[]{new Item("BigBoiBrain", 0, 10)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].quality).isEqualTo(8);
    }

    @Test
    void assertThatAgedBrieIncreasesInQualityTheOlderItGets() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 0)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].quality).isEqualTo(2);
    }

    @Test
    void assertThatBrieQualityIsNeverHigherThan50() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 49)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].quality).isEqualTo(50);
    }

    @Test
    void assertThatBackstagePassQualityIsNeverHigherThan50() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 1, 49)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].quality).isEqualTo(50);
    }

    @Test
    void assertThatBackstagePassIncreasesInQualityBy1When11DaysOrMoreUntilConcert() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].quality).isEqualTo(11);
    }

    @Test
    void assertThatBackstagePassIncreasesInQualityBy2When10DaysOrLessUntilConcert() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].quality).isEqualTo(12);
    }

    @Test
    void assertThatBackstagePassIncreasesInQualityBy3When5DaysOrLessUntilConcert() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].quality).isEqualTo(13);
    }

    @Test
    void assertThatBackstagePassQualityDropToZeroAfterConcert() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].quality).isEqualTo(0);
    }

    @Test
    void assertThatSulfurasDoesNotGetChanged() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 0, 80)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].sellIn).isEqualTo(0);
        assertThat(items[0].quality).isEqualTo(80);
    }

    @Test
    void assertThatConjuredItemsDegradeTwiceAsFastSellInGreaterThan0() {
        Item[] items = new Item[]{new Item("Conjured Mana Bun", 4, 20)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].quality).isEqualTo(18);
    }

    @Test
    void assertThatConjuredItemsDegradeTwiceAsFastSellInSmallerThan0() {
        Item[] items = new Item[]{new Item("Conjured Mana Bun", -1, 20)};
        createNewGildedRoseAppAndUpdateQuality(items);
        assertThat(items[0].quality).isEqualTo(16);
    }

}
