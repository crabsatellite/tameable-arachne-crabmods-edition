package com.crabmods.tameable_arachne;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = TameableArachneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TameableArachneConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    // Arachne settings
    private static final ForgeConfigSpec.BooleanValue ARACHNE_SPAWN_ENABLED = BUILDER
            .comment("Enable Arachne spawning")
            .define("arachneSpawnEnabled", true);

    private static final ForgeConfigSpec.IntValue ARACHNE_BASE_HP = BUILDER
            .comment("Base HP for Arachne")
            .defineInRange("arachneBaseHp", 20, 1, 100);

    private static final ForgeConfigSpec.IntValue ARACHNE_BASE_ATTACK = BUILDER
            .comment("Base attack damage for Arachne")
            .defineInRange("arachneBaseAttack", 4, 1, 20);

    private static final ForgeConfigSpec.IntValue ARACHNE_BASE_DEFENSE = BUILDER
            .comment("Base defense for Arachne")
            .defineInRange("arachneBaseDefense", 2, 0, 20);

    // Arachne Medium settings
    private static final ForgeConfigSpec.BooleanValue ARACHNE_MEDIUM_SPAWN_ENABLED = BUILDER
            .comment("Enable Arachne Medium spawning")
            .define("arachneMediumSpawnEnabled", true);

    private static final ForgeConfigSpec.IntValue ARACHNE_MEDIUM_BASE_HP = BUILDER
            .comment("Base HP for Arachne Medium")
            .defineInRange("arachneMediumBaseHp", 30, 1, 100);

    private static final ForgeConfigSpec.IntValue ARACHNE_MEDIUM_BASE_ATTACK = BUILDER
            .comment("Base attack damage for Arachne Medium")
            .defineInRange("arachneMediumBaseAttack", 6, 1, 20);

    private static final ForgeConfigSpec.IntValue ARACHNE_MEDIUM_BASE_DEFENSE = BUILDER
            .comment("Base defense for Arachne Medium")
            .defineInRange("arachneMediumBaseDefense", 3, 0, 20);

    // Harpy settings
    private static final ForgeConfigSpec.BooleanValue HARPY_SPAWN_ENABLED = BUILDER
            .comment("Enable Harpy spawning")
            .define("harpySpawnEnabled", true);

    private static final ForgeConfigSpec.IntValue HARPY_BASE_HP = BUILDER
            .comment("Base HP for Harpy")
            .defineInRange("harpyBaseHp", 20, 1, 100);

    private static final ForgeConfigSpec.IntValue HARPY_BASE_ATTACK = BUILDER
            .comment("Base attack damage for Harpy")
            .defineInRange("harpyBaseAttack", 2, 1, 20);

    private static final ForgeConfigSpec.IntValue HARPY_BASE_DEFENSE = BUILDER
            .comment("Base defense for Harpy")
            .defineInRange("harpyBaseDefense", 6, 0, 20);

    private static final ForgeConfigSpec.IntValue HARPY_DROP_RATE = BUILDER
            .comment("Harpy additional drop rate percentage")
            .defineInRange("harpyDropRate", 10, 0, 100);

    // General settings
    private static final ForgeConfigSpec.IntValue HP_UP = BUILDER
            .comment("HP increase per level")
            .defineInRange("hpUp", 2, 1, 10);

    private static final ForgeConfigSpec.IntValue ATTACK_UP = BUILDER
            .comment("Attack increase per level")
            .defineInRange("attackUp", 1, 1, 10);

    private static final ForgeConfigSpec.IntValue DEFENSE_UP = BUILDER
            .comment("Defense increase per level")
            .defineInRange("defenseUp", 1, 1, 10);

    private static final ForgeConfigSpec.IntValue POWER_UP_LIMIT = BUILDER
            .comment("Maximum power up level")
            .defineInRange("powerUpLimit", 16, 1, 50);

    private static final ForgeConfigSpec.BooleanValue NAME_BONUS = BUILDER
            .comment("Enable name bonus")
            .define("nameBonus", true);

    private static final ForgeConfigSpec.IntValue NAME_BONUS_VALUE = BUILDER
            .comment("Name bonus value")
            .defineInRange("nameBonusValue", 1, 1, 10);

    private static final ForgeConfigSpec.BooleanValue AUTO_HEAL = BUILDER
            .comment("Enable auto heal")
            .define("autoHeal", true);

    private static final ForgeConfigSpec.IntValue AUTO_HEAL_VALUE = BUILDER
            .comment("Auto heal value")
            .defineInRange("autoHealValue", 1, 1, 10);

    private static final ForgeConfigSpec.IntValue AUTO_HEAL_INTERVAL = BUILDER
            .comment("Auto heal interval in ticks")
            .defineInRange("autoHealInterval", 50, 10, 200);

    // Protection settings
    private static final ForgeConfigSpec.IntValue FIRE_PROTECTION_LIMIT = BUILDER
            .comment("Fire protection limit")
            .defineInRange("fireProtectionLimit", 4, 1, 10);

    private static final ForgeConfigSpec.IntValue FALL_PROTECTION_LIMIT = BUILDER
            .comment("Fall protection limit")
            .defineInRange("fallProtectionLimit", 4, 1, 10);

    private static final ForgeConfigSpec.IntValue BLAST_PROTECTION_LIMIT = BUILDER
            .comment("Blast protection limit")
            .defineInRange("blastProtectionLimit", 4, 1, 10);

    private static final ForgeConfigSpec.IntValue PROJECTILE_PROTECTION_LIMIT = BUILDER
            .comment("Projectile protection limit")
            .defineInRange("projectileProtectionLimit", 4, 1, 10);

    private static final ForgeConfigSpec.IntValue PROTECTION_LIMIT = BUILDER
            .comment("General protection limit")
            .defineInRange("protectionLimit", 4, 1, 10);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    // Public getters for config values
    public static boolean arachneSpawnEnabled;
    public static int arachneBaseHp;
    public static int arachneBaseAttack;
    public static int arachneBaseDefense;

    public static boolean arachneMediumSpawnEnabled;
    public static int arachneMediumBaseHp;
    public static int arachneMediumBaseAttack;
    public static int arachneMediumBaseDefense;

    public static boolean harpySpawnEnabled;
    public static int harpyBaseHp;
    public static int harpyBaseAttack;
    public static int harpyBaseDefense;
    public static int harpyDropRate;

    public static int hpUp;
    public static int attackUp;
    public static int defenseUp;
    public static int powerUpLimit;
    public static boolean nameBonus;
    public static int nameBonusValue;
    public static boolean autoHeal;
    public static int autoHealValue;
    public static int autoHealInterval;

    // Protection limits
    public static int fireProtectionLimit;
    public static int fallProtectionLimit;
    public static int blastProtectionLimit;
    public static int projectileProtectionLimit;
    public static int protectionLimit;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        arachneSpawnEnabled = ARACHNE_SPAWN_ENABLED.get();
        arachneBaseHp = ARACHNE_BASE_HP.get();
        arachneBaseAttack = ARACHNE_BASE_ATTACK.get();
        arachneBaseDefense = ARACHNE_BASE_DEFENSE.get();

        arachneMediumSpawnEnabled = ARACHNE_MEDIUM_SPAWN_ENABLED.get();
        arachneMediumBaseHp = ARACHNE_MEDIUM_BASE_HP.get();
        arachneMediumBaseAttack = ARACHNE_MEDIUM_BASE_ATTACK.get();
        arachneMediumBaseDefense = ARACHNE_MEDIUM_BASE_DEFENSE.get();

        harpySpawnEnabled = HARPY_SPAWN_ENABLED.get();
        harpyBaseHp = HARPY_BASE_HP.get();
        harpyBaseAttack = HARPY_BASE_ATTACK.get();
        harpyBaseDefense = HARPY_BASE_DEFENSE.get();
        harpyDropRate = HARPY_DROP_RATE.get();

        hpUp = HP_UP.get();
        attackUp = ATTACK_UP.get();
        defenseUp = DEFENSE_UP.get();
        powerUpLimit = POWER_UP_LIMIT.get();
        nameBonus = NAME_BONUS.get();
        nameBonusValue = NAME_BONUS_VALUE.get();
        autoHeal = AUTO_HEAL.get();
        autoHealValue = AUTO_HEAL_VALUE.get();
        autoHealInterval = AUTO_HEAL_INTERVAL.get();

        // Protection limits
        fireProtectionLimit = FIRE_PROTECTION_LIMIT.get();
        fallProtectionLimit = FALL_PROTECTION_LIMIT.get();
        blastProtectionLimit = BLAST_PROTECTION_LIMIT.get();
        projectileProtectionLimit = PROJECTILE_PROTECTION_LIMIT.get();
        protectionLimit = PROTECTION_LIMIT.get();
    }
}
