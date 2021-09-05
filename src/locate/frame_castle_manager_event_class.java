package locate;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class frame_castle_manager_event_class {

	@EventHandler
	public void castle_manager_damage_cancel(EntityDamageEvent event) {
		if(!(event.getEntityType().equals(EntityType.ENDER_CRYSTAL))) {
			return;
		}
		if(!(event.getEntity().getCustomName().equals("castle_manager"))) {
			return;
		}
		event.setCancelled(true);
	}
}
