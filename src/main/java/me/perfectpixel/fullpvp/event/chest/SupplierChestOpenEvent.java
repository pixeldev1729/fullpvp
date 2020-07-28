package me.perfectpixel.fullpvp.event.chest;

import me.perfectpixel.fullpvp.chest.SupplierChest;
import me.perfectpixel.fullpvp.chest.viewer.UserViewer;
import me.perfectpixel.fullpvp.message.Message;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.Optional;

public class SupplierChestOpenEvent extends SupplierChestEvent {

    private final static HandlerList HANDLER_LIST = new HandlerList();

    private final Optional<UserViewer> userViewerOptional;

    public SupplierChestOpenEvent(Player who, SupplierChest supplierChest, Message message, Optional<UserViewer> userViewerOptional) {
        super(who, supplierChest, message);

        this.userViewerOptional = userViewerOptional;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public Optional<UserViewer> getUserViewerOptional() {
        return userViewerOptional;
    }

}