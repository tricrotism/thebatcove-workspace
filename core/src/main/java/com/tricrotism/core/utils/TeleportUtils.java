package com.tricrotism.core.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public enum TeleportUtils {

    TPA,
    TPAHERE;

    public static HashMap<Player, Map<Player, TeleportUtils>> pendingTeleports = new HashMap<>();

}
