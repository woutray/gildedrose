package com.gildedrose;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        List<Item> itemsAsList = Arrays.asList(items);
        List<Item> itemsExcludingRagnaros = itemsAsList.stream()
                .filter(item -> !item.name.equals("Sulfuras, Hand of Ragnaros"))
                .collect(Collectors.toList());
        itemsExcludingRagnaros.forEach(this::handleQualityAndSellInOfItems);
    }

    private void handleQualityAndSellInOfItems(Item item) {
        item.sellIn = item.sellIn - 1;
        if (item.name.equals("Aged Brie")) {
            this.handleBrieQuality(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            this.handleBackstagePassQuality(item);
        } else if (item.name.contains("Conjured")) {
            this.handleConjuredItemsQuality(item);
        } else {
            this.handleRandomItemQuality(item);
        }
    }

    private void handleBrieQuality(Item item) {
        if (item.quality <= 50) {
            if (item.sellIn < 0) {
                item.quality = item.quality + 2;
            }
        }
        capItemQualityAt50(item);
    }

    private void handleBackstagePassQuality(Item item) {
        if (item.quality <= 50) {
            if (item.sellIn >= 10) {
                item.quality = item.quality + 1;
            } else if (item.sellIn >= 5) {
                item.quality = item.quality + 2;
            } else if (item.sellIn >= 0) {
                item.quality = item.quality + 3;
            } else {
                item.quality = 0;
            }
        }
        capItemQualityAt50(item);
    }

    private void capItemQualityAt50(Item item) {
        if (item.quality > 50) {
            item.quality = 50;
        }
    }

    private void handleConjuredItemsQuality(Item item) {
        if (item.sellIn >= 0) {
            item.quality = item.quality - 2;
        } else {
            item.quality = item.quality - 4;
        }
        floorItemQualityAtZero(item);
    }

    private void handleRandomItemQuality(Item item) {
        if (item.sellIn >= 0) {
            item.quality = item.quality - 1;
        } else {
            item.quality = item.quality - 2;
        }
        floorItemQualityAtZero(item);
    }

    private void floorItemQualityAtZero(Item item) {
        if (item.quality < 0) {
            item.quality = 0;
        }
    }
}
