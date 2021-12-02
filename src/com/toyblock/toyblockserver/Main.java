package com.toyblock.toyblockserver;


import java.io.File;
import java.util.*;


import com.destroystokyo.paper.event.entity.CreeperIgniteEvent;
import com.destroystokyo.paper.event.entity.EntityPathfindEvent;

import com.sk89q.worldguard.bukkit.event.entity.DamageEntityEvent;
import com.toyblock.toyblockserver.difficulty.advancements.adventurer.Adventurer;
import com.toyblock.toyblockserver.difficulty.advancements.adventurer.AdventurerLevelUp;
import com.toyblock.toyblockserver.difficulty.mana.Mana;
import com.toyblock.toyblockserver.structure.buildframe.HouseBuildFrame;
import com.toyblock.toyblockserver.tool.developer.bug;
import com.toyblock.toyblockserver.difficulty.item.weapon.*;
import com.toyblock.toyblockserver.difficulty.entity.ai.ZombieDunkShot;
import com.toyblock.toyblockserver.tool.RandomChest;
import com.toyblock.toyblockserver.structure.village.castle.CastleBuildPlayer;
import com.toyblock.toyblockserver.structure.village.path.PlayerCastlePath;
import com.toyblock.toyblockserver.structure.village.house.PlayerHouseBuild;
import com.toyblock.toyblockserver.structure.village.castle.investment.InventoryClick;
import com.toyblock.toyblockserver.structure.village.castle.item.ItemUse;
import com.toyblock.toyblockserver.structure.village.castle.vote.InvestmentNpc;
import com.toyblock.toyblockserver.structure.StructureMap;
import com.toyblock.toyblockserver.structure.village.info.Repute;
import com.toyblock.toyblockserver.tool.hashmap.MapData;
import com.toyblock.toyblockserver.structure.village.path.contract;
import com.toyblock.toyblockserver.difficulty.natural_spawn.natural_spawn;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BoundingBox;
import com.toyblock.toyblockserver.structure.village.npc.villager_test;

import org.bukkit.plugin.Plugin;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;


public class Main extends JavaPlugin implements Listener {
	ConsoleCommandSender consol = Bukkit.getConsoleSender();
	public static HashMap<String, File > structureFile = new HashMap<String, File > () ;
	 File f_protect = new File(getDataFolder(), "/ProtectData.txt");
	 File link = new File(getDataFolder(), "/Link.txt");
     File chunk = new File(getDataFolder(), "/chunk.txt");

	@Override
	public void onEnable() {
		super.onEnable();
		if(getDataFolder() == null) {
			getDataFolder().mkdir();
		}
		getServer().getPluginManager().registerEvents(this, this);
		this.getCommand("contract").setExecutor(new contract());
		getServer().getPluginManager().registerEvents(new CastleBuildPlayer(), this);
		getServer().getPluginManager().registerEvents(new PlayerCastlePath(), this);
		getServer().getPluginManager().registerEvents(new natural_spawn(), this);
		getServer().getPluginManager().registerEvents(new villager_test(), this);
		getServer().getPluginManager().registerEvents(new PlayerHouseBuild(), this);
		getServer().getPluginManager().registerEvents(new ItemUse(), this);
		getServer().getPluginManager().registerEvents(new InvestmentNpc(), this);
		getServer().getPluginManager().registerEvents(new InventoryClick(), this);
		getServer().getPluginManager().registerEvents(new test(), this);
		getServer().getPluginManager().registerEvents(new Adventurer(), this);
		getServer().getPluginManager().registerEvents(new AdventurerLevelUp(), this);
		getServer().getPluginManager().registerEvents(new AnvilIUpgrade(), this);
		getServer().getPluginManager().registerEvents(new EnchantUpgrade(), this);
		getServer().getPluginManager().registerEvents(new HouseBuildFrame(), this);
		getServer().getPluginManager().registerEvents(new NaturalSpawnChest(), this);
		getServer().getPluginManager().registerEvents(new RandomChest(), this);
		getServer().getPluginManager().registerEvents(new Repute(), this);
		getServer().getPluginManager().registerEvents(new bug(), this);
		getServer().getPluginManager().registerEvents(new ZombieDunkShot(), this);
		getServer().getPluginManager().registerEvents(new Mana(), this);
		consol.sendMessage("청크");
		allPlayerManaFull();
		MapData.makeFile(chunk);
		MapData.makeFile(link);
		MapData.Protect_fileToMap(link, StructureMap.Link);
		Bukkit.addRecipe(getRecipe());
		Bukkit.addRecipe(potionRecipe());
		Bukkit.addRecipe(getWoodenSwordUpgradeRecipe());
		timeFinder();
		mapList.MANA_SUN.put("Sun",0);
		structureFile.put("VILLAGE_CASTLE",new File(getDataFolder(),"/structure/village/castle"));
		structureFile.put("VILLAGE_PATH",new File(getDataFolder(),"/structure/village/path"));
		structureFile.put("VILLAGE_1X1HOUSE",new File(getDataFolder(),"/structure/village/2x2house"));
		structureFile.put("VILLAGE_2X2HOUSE",new File(getDataFolder(),"/structure/village/1x1house"));
		structureFile.put("DUNGEON_MOB",new File(getDataFolder(),"/structure/Dungeon/Mob"));
		bug.chat(""+structureFile.get("DUNGEON_MOB").toString());

		Location loc =  new Location(Bukkit.getWorld("world"),10,10,-10);
		consol.sendMessage(""+ StructureMap.Link.get(loc));
		StructureMap.Link.put( new Location(Bukkit.getWorld("world"),10,10,-10),"red");
	}

	@Override
	public void onDisable() {
		super.onDisable();
	//	MapData.Protect_mapToFile(f_protect, StructrueMap.protect);
		MapData.Protect_mapToFile(link, StructureMap.Link);


		//	data.mapToFile(data.file, villageindex);
	}
	public SmithingRecipe getWoodenSwordUpgradeRecipe() {
		ItemStack makeItem = WoodenSword.woodenUpgrade();
		NamespacedKey key = new NamespacedKey(this,"WoodenSwordUpgrade");
		RecipeChoice choice1 = new RecipeChoice.MaterialChoice(Material.WOODEN_SWORD);
		RecipeChoice choice2 = new RecipeChoice.MaterialChoice(Material.AMETHYST_SHARD);
		SmithingRecipe recipe = new SmithingRecipe(key,makeItem,choice1,choice2,false);
		return recipe;
	}
	String worldName = "world";
	public void timeFinder() {
		BukkitRunnable task = new BukkitRunnable() {
			World world = Bukkit.getWorld(worldName);
			@Override
			public void run() {
				long time = world.getTime();
				bug.chat("실행타이머"+time);
				if(time>0&&time<30) {
					healTime();
					this.cancel();
				}

			}
		};
		task.runTaskTimer(this,20,20);

	}

	public void healTime() {
		mapList.MANA_SUN.put("Sun",mapList.MANA_SUN.get("Sun")+1);
		int SunId = mapList.MANA_SUN.get("Sun")+1;
		BukkitRunnable task = new BukkitRunnable() {
			World world = Bukkit.getWorld(worldName);
			@Override
			public void run() {

				mapList.MANA.clear();
				mapList.MANA_REGEN.clear();
				allPlayerManaFull_day();
			}
		};
		task.runTaskTimer(this,0,24000);

	}
	public void allPlayerManaFull() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			Mana.createPlayerMana(player);
			Mana.createBoard_full(player);
			Mana.actionBarChat(player,ChatColor.AQUA+"Server Heal 100%");
			Mana.timeRemoveBoard(player);

		}
	}
	public void allPlayerManaFull_day() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			Mana.createPlayerMana(player);
			Mana.createBoard_full(player);
			Mana.actionBarChat(player,ChatColor.AQUA+"Sun Heal 100%");
			Mana.timeRemoveBoard(player);
		}
	}

	//@EventHandler
	public void creepercancel(EntityPathfindEvent event) {
		Location mobloc = event.getEntity().getLocation();
		List<Entity> mobs = (List<Entity>) mobloc.getWorld().getNearbyEntities(mobloc,10,10,10);
		if(mobs.isEmpty()) {
			return;
		}
		for(Entity mob : mobs) {
		}
	}
//	@EventHandler
	public void creeperloc(EntityPathfindEvent event) {
		Location mobloc = event.getEntity().getLocation();
		List<Entity> mobs = (List<Entity>) mobloc.getWorld().getNearbyEntities(mobloc,10,10,10);
		if(mobs.isEmpty()) {
			bug.chat("값없음");
			return;
		}
		for(Entity mob : mobs) {
			if(mob.getType().equals(EntityType.OCELOT)||mob.getType().equals(EntityType.CAT)) {
				Mob entity = (Mob)event.getEntity();
				entity.setTarget((LivingEntity) mob);
			}
			if(mob.getType().equals(EntityType.CREEPER)) {
				if(!(mob.getCustomName() == null)) {
					return;
				}
				Mob creeper = (Mob) mob;
				creeper.getPathfinder().moveTo(event.getLoc());
				bug.chat("이름도없음");
				mob.setCustomName("move");
			//	mob.setCustomNameVisible(false);
				bug.chat("실행 1");
				BukkitRunnable task = new BukkitRunnable() {
					Location loc =  creeper.getLocation().getBlock().getLocation();
					@Override
					public void run() {
						if(loc.equals(event.getLoc())) {
							this.cancel();
						}
						bug.chat("실행 2");
						creeper.getPathfinder().moveTo(event.getLoc());
						bug.chat(""+loc);
						if(loc.equals(creeper.getLocation().getBlock().getLocation())) {
							bug.chat("멈췄다");
							Creeper blow = (Creeper) creeper;
							blow.ignite();
							this.cancel();
							loc.getWorld().spawnFallingBlock(loc,Material.DIRT.createBlockData());
						}
						bug.chat("움직이는중");
						loc = creeper.getLocation().getBlock().getLocation();
						bug.chat(""+loc);
					}
				};
				task.runTaskTimer(this,20,20);
			}
		}
	}
//	@EventHandler
	public void onExplosion(ExplosionPrimeEvent e) {
		Entity entity = e.getEntity();
		if(entity instanceof Creeper) {
			int radius = Math.round(e.getRadius());
			ArrayList<Block> blocks = generateSphere(entity.getLocation(),radius,false);
	//		ArrayList<Block> blocks = getBlocksAroundCenter(entity.getLocation(), radius);
			for(Block b : blocks) {
				if(b.getType().equals(Material.AIR))
					b.setType(Material.IRON_BLOCK);
			}
		}
	}

	public ArrayList<Block> getNearbyBlocks(Location location, int radius) {
		ArrayList<Block> blocks = new ArrayList<Block>();
		for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
			for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
				for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
					blocks.add(location.getWorld().getBlockAt(x, y, z));
				}
			}
		}
		return blocks;
	}
	private static ArrayList<Block> getBlocksAroundCenter(Location loc, int radius) {
		ArrayList<Block> blocks = new ArrayList<>();

		for (int x = (loc.getBlockX()-radius); x <= (loc.getBlockX()+radius); x++) {
			for (int z = (loc.getBlockZ()-radius); z <= (loc.getBlockZ()+radius); z++) {
				Location l = new Location(loc.getWorld(), x, loc.getBlockY(), z);
				if (l.distance(loc) <= radius) {
					blocks.add(l.getBlock());
				}
			}
		}

		return blocks;
	}
//	@EventHandler
	public void explo(EntityExplodeEvent event) {
		if(event.getEntity().getCustomName() == null ){
			return;
		}
		event.setCancelled(true);
	}
	public static ArrayList<Block> generateSphere(Location centerBlock, int radius, boolean hollow) {
		if (centerBlock == null) {
			return new ArrayList<>();
		}

		ArrayList<Block> circleBlocks = new ArrayList<Block>();

		int bx = centerBlock.getBlockX();
		int by = centerBlock.getBlockY();
		int bz = centerBlock.getBlockZ();

		for(int x = bx - radius; x <= bx + radius; x++) {
			for(int y = by - radius; y <= by + radius; y++) {
				for(int z = bz - radius; z <= bz + radius; z++) {

					double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));

					if(distance < radius * radius && !(hollow && distance < ((radius - 1) * (radius - 1)))) {

						Location l = new Location(centerBlock.getWorld(), x, y, z);
						circleBlocks.add(l.getBlock());

					}

				}
			}
		}

		return circleBlocks;
	}
//	@EventHandler
	public void damagezombe(EntityDamageEvent event){
		if(event.getEntityType().equals(EntityType.ZOMBIE)) {
			if(event.getEntity().getLocation().getBlock().getType().equals(Material.JUNGLE_LEAVES)) {
				event.getEntity().getLocation().getBlock().breakNaturally();

			}
		}
	}

//	@EventHandler
	public void entityloc(EntityPathfindEvent event) {
		if(event.getEntity().getCustomName()==null) {
			return;
		}
		if (!(event.getEntity().getCustomName().equals("빌더"))) {
			return;
		}
		Location loc = event.getLoc();
		Block block = loc.getBlock();
//		event.getLoc().getWorld().spawnParticle(Particle.ASH,loc.getBlockX(),loc.getBlockY(),loc.getBlockY(),100);
		bug.chat("빌더맞음");
		bug.chat("타겟도있는데");
		event.getEntity().setCustomName("빌더.");
		Location target = event.getLoc();
		BukkitRunnable task = new BukkitRunnable() {
			Location loc =  event.getEntity().getLocation().getBlock().getLocation();
			@Override
			public void run() {
				if(loc.equals(target)) {
				}
				Mob mob = (Mob) event.getEntity();
				mob.getPathfinder().moveTo(target);
				bug.chat(""+loc);
				if(loc.equals(event.getEntity().getLocation().getBlock().getLocation())) {
					bug.chat("멈췄다");
					Boolean village = false;
					for( Entity entity : mob.getNearbyEntities(50,50,50)) {
						if(entity.getType().equals(EntityType.VILLAGER)) {
							double moby = entity.getLocation().getY()+5;
							double zombiey=event.getEntity().getLocation().getY();
							if(moby > zombiey) {
								village = true;
							}
						}
						if(entity.getType().equals(EntityType.PLAYER)) {
							double moby = entity.getLocation().getY()+5;
							double zombiey=event.getEntity().getLocation().getY();
							if(moby > zombiey) {
								village = true;
							}
						}
						if(!(event.getTargetEntity() == null)) {
						}
					}
					Location uploc = new Location(loc.getWorld(),loc.getX(),loc.getBlockY()+2,loc.getBlockZ());
					if(village) {
						if(uploc.getBlock().getType().equals(Material.AIR)) {
							if(!(loc.getBlock().getType().equals(Material.AIR))) {
								loc.getBlock().breakNaturally();
							}
							if(event.getEntity() instanceof LivingEntity ) {
								mob.getLocation().getWorld().spawnFallingBlock(loc,Material.DIRT.createBlockData());
								//mob.getLocation().getBlock().setType(Material.DIRT);
								mob.setJumping(true);
								loc = event.getEntity().getLocation().getBlock().getLocation();
								return;
							}
						}
							loc.getBlock().setType(Material.JUNGLE_LEAVES);
						loc = event.getEntity().getLocation().getBlock().getLocation();
					}
				}
				bug.chat("움직이는중");
				loc = event.getEntity().getLocation().getBlock().getLocation();
				bug.chat(""+loc);
			}
		};
		task.runTaskTimer(this,20,20);
	}
//	@EventHandler
	public void builder(EntityTargetLivingEntityEvent event){
		if (!(event.getEntity().getCustomName().equals("빌더"))) {
			return;
		}
		bug.chat("빌더맞음");
		bug.chat("타겟도있는데");
		event.getEntity().setCustomName("빌더.");
		BukkitRunnable task = new BukkitRunnable() {
			Location loc =  event.getEntity().getLocation().getBlock().getLocation();
			@Override
			public void run() {
				bug.chat(""+loc);
				if(loc.equals(event.getEntity().getLocation().getBlock().getLocation())) {
					bug.chat("멈췄다");
					Location loc = event.getEntity().getLocation().getBlock().getLocation();
					event.getEntity().remove();
					loc.getBlock().setType(Material.DIRT);

					//loc.getWorld().spawnFallingBlock(loc,Material.DIRT.createBlockData());
					this.cancel();
				}
				bug.chat("움직이는중");
				loc = event.getEntity().getLocation().getBlock().getLocation();
				bug.chat(""+loc);
			}
		};

		task.runTaskTimer(this,40,40);
	}
//	@EventHandler
	public void zombieshoot(EntityTargetLivingEntityEvent event) {
		if (!(event.getEntity().getCustomName().equals("빌더"))) {
			return;
		}
		bug.chat("슛실행");
		LivingEntity zombies = (LivingEntity) event.getEntity();
		Location loc = zombies.getLocation();
		BoundingBox box = new BoundingBox(loc.getX()-1,loc.getBlockY(),loc.getBlockZ()-1,loc.getX()+1,loc.getBlockY()+1,loc.getBlockZ()+1);
		List<Entity> mobs = (List<Entity>) loc.getWorld().getNearbyEntities(loc,2,2,2);
		bug.chat("슛실행1");
		 if(mobs.isEmpty()) {
			 bug.chat("슛실행2");
			 return;
		 }
		 if(mobs.size()>1) {
			 bug.chat("슛실행3");
			 LivingEntity mob1 =(LivingEntity) mobs.get(0);
			 LivingEntity mob2 =(LivingEntity) mobs.get(1);
			 mob1.addPassenger(mob2);
			 mob1.swingMainHand();
			 LivingEntity zombie = mob1;
			 Location locs = zombie.getEyeLocation().toVector().add(zombie.getLocation().getDirection().multiply(2)).
					 toLocation(zombie.getWorld(), zombie.getLocation().getYaw(), zombie.getLocation().getPitch());
			 Fireball fireball = zombie.getWorld().spawn(locs, Fireball.class);
			 fireball.setGravity(false);
			 fireball.setCustomName("이야");
			 zombie.getPassengers().get(0).setCustomName("boom");
			 fireball.addPassenger(zombie.getPassengers().get(0));
			 fireball.setVisualFire(false);
			 fireball.setGlowing(false);
			 fireball.setSilent(true);
		 }

	}
//	@EventHandler
	public void zombieBuild(EntityTargetLivingEntityEvent event) {
		bug.chat("좀타겟");
		if (!(event.getEntity().getCustomName().equals("빌더"))) {
			return;
		}
		LivingEntity zombie = (LivingEntity) event.getEntity();
		BukkitRunnable task = new BukkitRunnable() {
			@Override
			public void run() {
					zombie.getLocation().getBlock().setType(Material.DIRT);
					zombie.setJumping(true);
					zombie.swingMainHand();
				}
			};
		task.runTaskTimer(this,5,5);
	}
	//@EventHandler
	public void target(EntityPathfindEvent event) {
		bug.chat("이벤트 발생");
		LivingEntity zombie = (LivingEntity) event.getEntity();
		if(event.getEntity().getCustomName().equals("빌더")) {
			zombie.getLocation().getBlock().setType(Material.DIRT);
			zombie.setJumping(true);
			zombie.swingMainHand();
		}
	}
	public void buildOn(LivingEntity entity) {
		Block block = entity.getTargetBlock(2);
		if(!(block.getType().equals(Material.AIR))) {
			return;
		}
		World world = block.getWorld();
		Location loc = block.getLocation();
		world.spawnFallingBlock(block.getLocation(),Material.DIRT.createBlockData());

	}
//	@EventHandler
	public void zombietuch(DamageEntityEvent event) {
		bug.chat("좀비 실행 디버그");
		if(!(event.getEntity().getType().equals(EntityType.ZOMBIE))) {
			return;
		}
		LivingEntity entity = (LivingEntity) event.getEntity();
		LivingEntity creeper = (LivingEntity) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.CREEPER);
		if (entity.getType().equals(EntityType.ZOMBIE)) {
			entity.addPassenger(creeper);
			zombieCreeper(entity);
		}
	}
//	@EventHandler
	public void boom(EntityDamageEvent event) {
		bug.chat("엔티티"+event.getEntity()+"원인"+event.getCause());
		if(event.getCause().equals(EntityDamageEvent.DamageCause.SUFFOCATION)) {
			event.getEntity().remove();
		}
		if(event.getEntity().getType().equals(EntityType.ENDERMAN)) {
			event.getEntity().remove();
		}
		if(event.getEntity().getCustomName().equals("이야")) {
			event.setCancelled(true);
			return;
		}
		if(event.getEntity().getCustomName().equals("boom")) {
			if(event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
				Creeper creeper = (Creeper) event.getEntity();
				creeper.explode();
				return;
			}
		}

		if(event.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
			event.getEntity().teleport(event.getEntity().getWorld().getHighestBlockAt(event.getEntity().getLocation()).getLocation());
		}
	}
	//@EventHandler
	public void boomer(CreeperIgniteEvent event) {
		bug.chat("이그나이트");
			event.getEntity().explode();
	}
	public void zombieCreeper(LivingEntity zombie) {
		zombie.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 5, 5));
		zombie.setJumping(true);
		BukkitRunnable task = new BukkitRunnable() {
			@Override
			public void run() {
				zombie.swingMainHand();

				Location loc = zombie.getEyeLocation().toVector().add(zombie.getLocation().getDirection().multiply(2)).
						toLocation(zombie.getWorld(), zombie.getLocation().getYaw(), zombie.getLocation().getPitch());
				Fireball fireball = zombie.getWorld().spawn(loc, Fireball.class);
				fireball.setGravity(false);
				fireball.setCustomName("이야");
				zombie.getPassengers().get(0).setCustomName("boom");
				fireball.addPassenger(zombie.getPassengers().get(0));
				fireball.setVisualFire(false);
				fireball.setGlowing(false);
				fireball.setSilent(true);
				this.cancel();
			}
		};
		task.runTaskTimer(this,8,0);
	}
	public  ShapedRecipe getRecipe() {
		WoodenSword sword = new WoodenSword();
		ItemStack item = sword.woodenSword_Lv1();

		NamespacedKey Key = new NamespacedKey(this,"Wooden_Sword");

		ShapedRecipe recipe = new ShapedRecipe(Key,item);

		recipe.shape(" W "," W "," S ");

		recipe.setIngredient('W', Material.OAK_PLANKS);
		recipe.setIngredient('S',Material.STICK);

		return recipe;

	}
	public  ShapedRecipe potionRecipe() {

		ItemStack item = new ItemStack(Material.POTION);
		ItemMeta meta = item.getItemMeta();


		meta.setDisplayName(ChatColor.AQUA+"마나 포션");
		meta.addEnchant(Enchantment.DURABILITY,1,true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		NamespacedKey Key = new NamespacedKey(this,"Mana_Potion");

		ShapedRecipe recipe = new ShapedRecipe(Key,item);

		recipe.shape("RRR","RPR","RRR");

		recipe.setIngredient('R', Material.REDSTONE);
		recipe.setIngredient('P', Material.POTION);

		return recipe;

	}
	//
	static HashMap<String , Integer > buildname = new HashMap<String , Integer > () ;
	//
	static HashMap<String , Integer > guide = new HashMap<String , Integer > () ;
	//건물 인덱스
	public static HashMap<String , String > villageindex = new HashMap<String , String > () ;
	//건물 체크할 리스트
	static HashMap<String , Integer > buildcheck = new HashMap<String , Integer > () ;
	static HashMap<String , String > village_index_name = new HashMap<String , String > () ;
	static HashMap<String , Integer > player_stats = new HashMap<String , Integer > () ;
	//마을 청크 등록
	static HashMap<String , String > chunk_index = new HashMap<String , String > () ;
	public static HashMap<Location , String > villge_index_loc = new HashMap<Location, String > () ;
	
    private final String prefix = ChatColor.YELLOW + "[Structure] ";
    
	static World world = Bukkit.getServer().getWorld("World");
	
	 Plugin plugin = this;
	Block block;
	Block b;

	@EventHandler
	public void testAdvancements(PlayerJoinEvent event) {
		BukkitRunnable task = new BukkitRunnable() {
			@Override
			public void run() {
				Adventurer.createAdventurer();
				Adventurer.addPlayer(event.getPlayer());
				this.cancel();
			}
		};
		task.runTaskTimer(this, 5,5);

	}
	public static boolean structure_s_build_linkcheck(Location loc, Player player,String view) {
		String x="";
		String y1="";
		String y2="";
		String y3="";
		String z="";
		if (view.equals("S")) {
		y1=Integer.toString(loc.getBlockY());
		y2=Integer.toString(loc.getBlockY()-1);
		y3=Integer.toString(loc.getBlockY()+1);
		x=Integer.toString(loc.getBlockX());
		z=Integer.toString(loc.getBlockZ()-5);
		}
		if (view.equals("E")) {
		y1=Integer.toString(loc.getBlockY());
		y2=Integer.toString(loc.getBlockY()-1);
		y3=Integer.toString(loc.getBlockY()+1);
		x=Integer.toString(loc.getBlockX()-5);
		z=Integer.toString(loc.getBlockZ());
		}
		if (view.equals("W")) {
		y1=Integer.toString(loc.getBlockY());
		y2=Integer.toString(loc.getBlockY()-1);
		y3=Integer.toString(loc.getBlockY()+1);
		x=Integer.toString(loc.getBlockX()+5);
		z=Integer.toString(loc.getBlockZ());
		}
		if (view.equals("N")) {
		y1=Integer.toString(loc.getBlockY());
		y2=Integer.toString(loc.getBlockY()-1);
		y3=Integer.toString(loc.getBlockY()+1);
		x=Integer.toString(loc.getBlockX());
		z=Integer.toString(loc.getBlockZ()+5);
		}
		if (!(villageindex.containsKey(x+y1+z)==true||
				villageindex.containsKey(x+y2+z)==true||
				villageindex.containsKey(x+y3+z)==true)) {
			player.chat("길과 연결되어 있지 않음");
			return false;
		}
		player.chat("길과 연결 됌");
		return true;
	}
			   //50청크 이내에 랜덤 던전
			   public static Chunk village_around_random_chunk_get(Location loc) {
				  World world = loc.getWorld();
				  int x = loc.getBlockX();
				  int randomx = (int) (Math.random() * (x-500) + (x+500));
				  int z = loc.getBlockZ();
				  int randomz = (int) (Math.random() * (z-500) + (z+500));
				  int y = loc.getBlockY();
				  Location random_loc = new Location(world, randomx, y, randomz);
				  Chunk random_chunk = world.getChunkAt(random_loc);
				  return random_chunk;
			   }



}

	
//for(int x=-2; x<3; x++)
	//for(int y=-3; y=<0; y++)
	//	for(int z=-5; z<0; z++)