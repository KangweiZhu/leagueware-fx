package com.anicaaz.leaguewarefx.ui.ingameobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private boolean canUse;
    private boolean consumable;
    private int count;
    private String displayName;
    private int itemID;
    private int price;
    private String rawDescription;
    private String rawDisplayName;
    private int slot;
}
