package com.gildedrose;

class GildedRose 
{
    Item[] items;

    public GildedRose(Item[] items) 
    {
        this.items = items;
    }

    public void updateQuality() 
    {
        for (Item item : items)
         {
            if (isSulfuras(item)) 
            {
                continue;
            }
            else
            {
            updateSellIn(item);
            updateQualityByType(item);
            limitQuality(item);
            }
        }
    }

    private void updateSellIn(Item item) 
    {
        item.sellIn--;
    }

    private void updateQualityByType(Item item) 
    {
        if (isAgedBrie(item)) 
        {
            updateAgedBrie(item);
        } 
        else if (isBackstage(item)) 
        {
            updateBackstage(item);
        } 
        else if (isConjured(item)) 
        {
            updateConjured(item);
        } 
        else 
        {
            updateNormal(item);
        }
    }

    private void updateAgedBrie(Item item) 
    {
        increaseQuality(item, 1);
        if (item.sellIn < 0) 
        {
            increaseQuality(item, 1);
        }
    }

   private void updateBackstage(Item item) 
   {
        if (item.sellIn < 0) 
        {
            item.quality = 0;
            return;
        }

        if (item.sellIn <= 5) 
        {
            increaseQuality(item, 3);
        } 
        else if (item.sellIn <= 10) 
        {
            increaseQuality(item, 2);
        } else 
        {
            increaseQuality(item, 1);
        }
    }

    private void updateConjured(Item item) 
    {
        decreaseQuality(item, 2);
        
        if (item.sellIn < 0) 
        {
            decreaseQuality(item, 2);
        }
    }

    private void updateNormal(Item item) 
    {
        decreaseQuality(item, 1);

        if (item.sellIn < 0) 
        {
            decreaseQuality(item, 1);
        }
    }

    private void increaseQuality(Item item, int amount) 
    {
        item.quality += amount;
    }

    private void decreaseQuality(Item item, int amount) 
    {
        item.quality -= amount;
    }

    private void limitQuality(Item item) 
    {
        if (item.quality < 0) item.quality = 0;
        if (item.quality > 50) item.quality = 50;
    }

    private boolean isSulfuras(Item item) 
    {
        return "Sulfuras, Hand of Ragnaros".equals(item.name) && item.quality == 80;
    }

    private boolean isAgedBrie(Item item) 
    {
        return "Aged Brie".equals(item.name);
    }

    private boolean isBackstage(Item item) 
    {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item.name);
    }

    private boolean isConjured(Item item) 
    {
        return item.name != null && item.name.toLowerCase().contains("conjured");
    }
}
