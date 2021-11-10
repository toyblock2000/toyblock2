package natural_spawn;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.data.type.Bell.Attachment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class natural_spawn implements Listener {

	@EventHandler
	public void village_spawn(EntitySpawnEvent event) {
	if(!event.getEntityType().equals(EntityType.ZOMBIE)) {
		return;
	}
	LivingEntity entity = (LivingEntity) event.getEntity();
	entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()+2);
	}
}
