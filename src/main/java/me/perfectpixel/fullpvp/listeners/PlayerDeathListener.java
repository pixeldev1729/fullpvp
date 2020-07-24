package me.perfectpixel.fullpvp.listeners;

import me.perfectpixel.fullpvp.Storage;
import me.perfectpixel.fullpvp.files.FileManager;
import me.perfectpixel.fullpvp.message.SimpleMessageDecorator;
import me.perfectpixel.fullpvp.user.User;

import me.yushust.inject.Inject;
import me.yushust.inject.name.Named;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class PlayerDeathListener implements Listener {

    @Inject @Named("users") private Storage<User, UUID> userStorage;

    @Inject @Named("config") private FileManager config;
    @Inject private SimpleMessageDecorator messageDecorator;

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();

        userStorage.find(player.getUniqueId()).ifPresent(user -> {
            user.getDeaths().add(1);

            player.sendMessage(messageDecorator.getMessage(player, "events.player-death"));
        });

        userStorage.find(killer.getUniqueId()).ifPresent(user -> {
            int coins = config.getInt("game.coins-per-kill");

            user.getKills().add(1);
            user.getCoins().add(coins);

            killer.sendMessage(messageDecorator.getMessage(killer, "events.player-kill"));
            killer.sendMessage(messageDecorator.getMessage(killer, "events.player-gain-coins").replace("%coins%", coins + ""));
        });
    }

}