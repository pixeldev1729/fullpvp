package me.pixeldev.fullpvp.loader;

import me.fixeddev.ebcm.NamespaceAccesor;
import me.fixeddev.ebcm.bukkit.BukkitCommandManager;
import me.fixeddev.ebcm.exception.CommandException;
import me.fixeddev.ebcm.exception.CommandParseException;
import me.fixeddev.ebcm.parametric.CommandClass;
import me.fixeddev.ebcm.parametric.ParametricCommandBuilder;
import me.fixeddev.ebcm.parametric.ReflectionParametricCommandBuilder;

import me.pixeldev.fullpvp.commands.ChestCreatorCommand;
import me.pixeldev.fullpvp.commands.ClanCommands;
import me.pixeldev.fullpvp.commands.CoinsCommands;
import me.pixeldev.fullpvp.commands.SimpleI18n;

import team.unnamed.inject.InjectAll;
import team.unnamed.inject.InjectIgnore;

import java.util.List;

@InjectAll
public final class CommandsLoader implements Loader {

    private ChestCreatorCommand chestCreatorCommand;
    private CoinsCommands coinsCommands;
    private ClanCommands clanCommands;
    private SimpleI18n simpleI18n;

    @InjectIgnore
    private final ParametricCommandBuilder builder = new ReflectionParametricCommandBuilder();

    @InjectIgnore
    private final BukkitCommandManager commandManager = new BukkitCommandManager("FullPVP");

    private void registerCommands(CommandClass... commandClasses) {
        for (CommandClass commandClass : commandClasses) {
            commandManager.registerCommands(builder.fromClass(commandClass));
        }
    }

    @Override
    public void load() {
        commandManager.setI18n(simpleI18n);

        registerCommands(chestCreatorCommand, coinsCommands, clanCommands);
    }

}