package uk.co.harieo.seasons.core.v1_13_R1.bad;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import uk.co.harieo.seasons.plugin.Seasons;
import uk.co.harieo.seasons.plugin.models.Weather;
import uk.co.harieo.seasons.plugin.models.effect.SeasonsPotionEffect;

public class StrongCurrent extends SeasonsPotionEffect {

	public StrongCurrent() {
		super("Strong Current", "Receive Slowness 2 for 20 seconds when you enter water",
				Collections.singletonList(Weather.STORMY), false,
				new PotionEffect(PotionEffectType.SLOW, 20 * 20, 1));
		setIgnoreRoof(false);
	}

	@Override
	public String getId() {
		return "strong-current";
	}

	@Override
	public boolean shouldGive(Player player) {
		if (PlaceholderAPI.setPlaceholders(player,"%projectkorra_element%").contains("Water")){
			return false;
		}
		if (isPlayerCycleApplicable(player) && !player.isInsideVehicle()) {
			Block block = player.getLocation().getBlock();
			return block.getType() == Material.WATER;
		} else {
			return false;
		}
	}

	@Override
	public void sendGiveMessage(Player player) {
		sendGiveMessage(player, ChatColor.RED + "The current crashes against you and your muscles cry in pain...");
	}

	@Override
	public void sendRemoveMessage(Player player) {
		sendRemoveMessage(player, ChatColor.GREEN + "The waters die down and grow still...");
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		giveEffect(player, true);
	}

}
