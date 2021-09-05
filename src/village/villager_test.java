package village;

import java.util.ArrayList;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
public class villager_test implements Listener{
	
	@EventHandler
	public void villager_career (VillagerCareerChangeEvent event) {
		Villager npc = event.getEntity();
		Merchant shop = npc;
		
		if (!(npc.getProfession().equals(Profession.TOOLSMITH))) {
			return;
		}
		int level = npc.getVillagerLevel();
		if(level == 1) {
			ItemStack wood_sward = new ItemStack(Material.WOODEN_SWORD);
			MerchantRecipe recipes = new MerchantRecipe(wood_sward, 30, 30,true, 1,2);
			recipes.addIngredient(wood_sward);
			List <MerchantRecipe> recipe = new ArrayList <> () ;
			recipe.add(recipes);
			npc.setRecipes(recipe);
		}
		
	}
}
