package com.anicaaz.leaguewarefx.ui.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChampionStats {
    private double abilityHaste;
    private double abilityPower;
    private double armor;
    private double armorPenetrationFlat;
    private double armorPenetrationPercent;
    private double attackDamage;
    private double attackRange;
    private double attackSpeed;
    private double bonusArmorPenetrationPercent;
    private double bonusMagicPenetrationPercent;
    private double critChance;
    private double critDamage;
    private double currentHealth;
    private double healShieldPower;
    private double healthRegenRate;
    private double lifeSteal;
    private double magicLethality;
    private double magicPenetrationFlat;
    private double magicPenetrationPercent;
    private double magicResist;
    private double maxHealth;
    private double moveSpeed;
    private double omnivamp;
    private double physicalLethality;
    private double physicalVamp;
    private double resourceMax;
    private double resourceRegenRate;
    private String resourceType;
    private double resourceValue;
    private double spellVamp;
    private double tenacity;
}
