package locate;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;


import com.destroystokyo.paper.event.entity.CreeperIgniteEvent;
import com.destroystokyo.paper.event.entity.EntityPathfindEvent;
import com.github.shynixn.structureblocklib.api.bukkit.StructureBlockLibApi;

import com.github.shynixn.structureblocklib.api.bukkit.block.StructureBlockLoad;
import com.github.shynixn.structureblocklib.api.enumeration.StructureMode;

import com.sk89q.worldguard.bukkit.event.entity.DamageEntityEvent;
import com.toyblock.toyblockserver.advancements.adventurer.Adventurer;
import com.toyblock.toyblockserver.advancements.adventurer.AdventurerLevelUp;
import com.toyblock.toyblockserver.buildframe.HouseBuildFrame;
import com.toyblock.toyblockserver.developer.bug;
import com.toyblock.toyblockserver.difficulty.item.weapon.*;
import com.toyblock.toyblockserver.entity.ai.ZombieDunkShot;
import com.toyblock.toyblockserver.randomchest.RandomChest;
import com.toyblock.toyblockserver.structure.CastleBuildPlayer;
import com.toyblock.toyblockserver.structure.castle.PlayerCastlePath;
import com.toyblock.toyblockserver.structure.castle.PlayerHouseBuild;
import com.toyblock.toyblockserver.structure.castle.investment.InventoryClick;
import com.toyblock.toyblockserver.structure.castle.item.ItemUse;
import com.toyblock.toyblockserver.structure.castle.vote.InvestmentNpc;
import com.toyblock.toyblockserver.structure.protect.StructrueMap;
import com.toyblock.toyblockserver.village.Repute;
import hashmap.MapData;
import hashmap.data;
import natural_spawn.natural_spawn;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.util.BoundingBox;
import village.villager_test;

import org.bukkit.plugin.Plugin;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


public class Main extends JavaPlugin implements Listener {
	ConsoleCommandSender consol = Bukkit.getConsoleSender();

	 File f_protect = new File(getDataFolder(), "/ProtectData.txt");
	 File link = new File(getDataFolder(), "/Link.txt");
     File chunk = new File(getDataFolder(), "/chunk.txt");
	@Override
	public void onEnable() {
		super.onEnable();
		getServer().getPluginManager().registerEvents(this, this);
		this.getCommand("contract").setExecutor(new contract());
		this.getCommand("log").setExecutor(new log());
		this.getCommand("manager_spawn").setExecutor(new manager_spawn());
		getServer().getPluginManager().registerEvents(new castle_manager_class(), this);
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
		consol.sendMessage("청크");
		MapData.makeFile(chunk);
		MapData.makeFile(link);
		MapData.Protect_fileToMap(link, StructrueMap.Link);
		Bukkit.addRecipe(getRecipe());
		if(getDataFolder() == null) {
			getDataFolder().mkdir();
		}
		Location loc =  new Location(Bukkit.getWorld("world"),10,10,-10);
		consol.sendMessage(""+StructrueMap.Link.get(loc));
		StructrueMap.Link.put( new Location(Bukkit.getWorld("world"),10,10,-10),"red");
	}

	@Override
	public void onDisable() {
		super.onDisable();
	//	MapData.Protect_mapToFile(f_protect, StructrueMap.protect);
		MapData.Protect_mapToFile(link, StructrueMap.Link);


		//	data.mapToFile(data.file, villageindex);
	}
//	@EventHandler
	public void player_tuch(PlayerInteractEvent event) {
		List castleBuildLore = new ArrayList();
		castleBuildLore.add(0,"엔피시");
		List checkLore = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
		if (castleBuildLore.get(0).equals(checkLore.get(0))) {
			Location loc = event.getPlayer().getLocation();
		}
	}
	public void enderspawn(Location loc,LivingEntity zombie) {
		Enderman ender = (Enderman) loc.getWorld().spawnEntity(loc,EntityType.ENDERMAN);

		ender.setAI(false);
		ender.setCarriedBlock(Material.DIRT.createBlockData());
		ender.swingMainHand();
		loc.getBlock().setType(Material.DIRT);
		ender.setJumping(true);
		BukkitRunnable task = new BukkitRunnable() {
			@Override
			public void run() {
				zombie.setJumping(true);
				ender.remove();
				this.cancel();
			}
		};
		task.runTaskTimer(this,20,20);
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
							//loc.getWorld().spawnFallingBlock(loc,Material.DIRT.createBlockData());
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
  //@EventHandler
	public void build(PlayerInteractEvent event) {
	  if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		  return;
	  }
		ItemStack build = new ItemStack(Material.WRITABLE_BOOK);
		ItemMeta Meta = build.getItemMeta();
		Meta.setDisplayName("계약서");
		build.setItemMeta(Meta);

		BukkitRunnable task = new BukkitRunnable() {
			public void run() {
				Player p = event.getPlayer();
				b = p.getTargetBlockExact(5);

				if(!(b.getType().equals(Material.AIR))) {
					
		Location loc = b.getLocation();
		Player player = event.getPlayer();
		double view = player.getLocation().getYaw();
		Vector viewcheck = 		player.getLocation().getDirection();
		
		String x1 = Integer.toString(loc.getBlockX());
		StringBuilder newx = new StringBuilder(x1);

		int xlength = newx.length();
		char getx= newx.charAt(xlength-1);
		char putx = tool.XYZcanter(getx);
		newx.setCharAt(xlength-1, putx);
		String newx1 = newx.toString();
		int changex = Integer.parseInt(newx1);
		


		String z1 = Integer.toString(loc.getBlockZ());
		StringBuilder newz = new StringBuilder(z1);

		int zlength = newz.length();
		char getz= z1.charAt(zlength-1);
		char putz = tool.XYZcanter(getz);
		newz.setCharAt(zlength-1, putz);
		String newz1 = newz.toString();
		int changez = Integer.parseInt(newz1);
		block = world.getBlockAt(changex, (int) loc.getY(), changez);
		world.spawnParticle(Particle.ASH,block.getX()+0.5,block.getY()+2,block.getZ()+0.5,10);

		block.setType(Material.STRUCTURE_BLOCK);
		StructureBlockLoad b = StructureBlockLibApi.INSTANCE.getStructureBlockAt(block.getLocation(), plugin);
		Location l = new Location(world, -2, -1, -2);
		b.setStructureMode(StructureMode.LOAD);
		b.setSaveName("load");
		b.setBoundingBoxVisible(false);
		b.setStructureLocation(l);
		b.update();
		
		Block bloc = world.getBlockAt(block.getX(),block.getY()-1,block.getZ());
		bloc.setType(Material.REDSTONE_BLOCK);
	//	if(viewcheck == "S") {
		//	Block blockadd = world.getBlockAt(block.getX(), block.getY(), block.getZ()-2);
			//blockadd.setType(Material.STRUCTURE_BLOCK);
			//world.spawnParticle(Particle.ASH,blockadd.getX()+0.5,blockadd.getY()+2,blockadd.getZ()+0.5,10);
		
	//	if(viewcheck == "W") {
			//Block blockadd = world.getBlockAt(block.getX()+2, block.getY(), block.getZ());
			//blockadd.setType(Material.STRUCTURE_BLOCK);
		//	world.spawnParticle(Particle.ASH,blockadd.getX()+0.5,blockadd.getY()+2,blockadd.getZ()+0.5,10);
		
		//if(viewcheck == "N") {
			//Block blockadd = world.getBlockAt(block.getX(), block.getY(), block.getZ()+2);
			//blockadd.setType(Material.STRUCTURE_BLOCK);
		//	world.spawnParticle(Particle.ASH,blockadd.getX()+0.5,blockadd.getY()+2,blockadd.getZ()+0.5,10);
		
	//	if(viewcheck == "E") {
		//	Block blockadd = world.getBlockAt(block.getX()-2, block.getY(), block.getZ());
			//blockadd.setType(Material.STRUCTURE_BLOCK);
	//		world.spawnParticle(Particle.ASH,blockadd.getX()+0.5,blockadd.getY()+2,blockadd.getZ()+0.5,10);
		
		}
			}
		
	};
	// 100초 딜레이, 트리 부슬수 있는 횟수가 적으면 100초당 1 증가
	task.runTaskTimer(this, 10L,10L);
	
	}
	@EventHandler
	public void Structrue_plus(int x ,int y ,int z,String guidekey) {
		BukkitRunnable task = new BukkitRunnable() {
			public void run() {
				if(!(guide.get(guidekey)==1)) {
					this.cancel();
				}
		world.spawnParticle(Particle.ASH,x+0.5,y+1.5,z+0.5,10);
			}
			
			};
			task.runTaskTimer(this, 1L,1L);
		}
	
   @EventHandler
	public void StructrueGuide(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		Action action = event.getAction();
		if (!(action == Action.LEFT_CLICK_AIR||action == Action.LEFT_CLICK_BLOCK)) {
			return;
		}	
		if (!(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("길 계약서"))) {
			return;
		}


		

		String ID = player.getUniqueId().toString() + "guide";
		if (guide.containsKey(ID)==false) {

			guide.put(ID, 1);
		}
		if (guide.get(ID)== 0) {
			guide.put(ID, 1);
			player.chat("ON");
		}
		else if (guide.get(ID)== 1) {
			guide.put(ID, 0);
			player.chat("OFF");
		}
		BukkitRunnable task = new BukkitRunnable() {
			public void run() {
				if(!(guide.get(ID)==1)) {
					this.cancel();
				}
				if(!(event.getPlayer().getInventory().getItemInMainHand()
				.getItemMeta().getDisplayName().equals("길 계약서"))) {
					this.cancel();
				}
				
				Player p = event.getPlayer();
				b = p.getTargetBlockExact(5);
 
				if(b.getType().equals(Material.AIR)) {
				this.cancel();	
				}
		Location loc = b.getLocation();

		String viewcheck = tool.getDirection(player);
		
				String x1 = Integer.toString(loc.getBlockX());
				StringBuilder newx = new StringBuilder(x1);

				int xlength = newx.length();
				char getx= newx.charAt(xlength-1);
				char putx = tool.XYZcanter(getx);
				newx.setCharAt(xlength-1, putx);
				String newx1 = newx.toString();
				int changex = Integer.parseInt(newx1);
				int xx = tool.change_xyz(loc.getBlockX());
				int zz = tool.change_xyz(loc.getBlockZ());
				int y = loc.getBlockY();
				


				String z1 = Integer.toString(loc.getBlockZ());
				StringBuilder newz = new StringBuilder(z1);

				int zlength = newz.length();
				char getz= z1.charAt(zlength-1);
				char putz = tool.XYZcanter(getz);
				newz.setCharAt(zlength-1, putz);
				String newz1 = newz.toString();
				int changez = Integer.parseInt(newz1);
				Block particleloc ;
				particleloc = world.getBlockAt(xx,y,zz);
				
		world.spawnParticle(Particle.ASH,particleloc.getX()+0.5,particleloc.getY()+1.5,particleloc.getZ()+0.5,10);

		player.chat(x1);
		if(viewcheck == "S") {

		}
		if(viewcheck == "W") {

		}
		if(viewcheck == "N") {

		}
		if(viewcheck == "E") {

		}
			}
			
		};
		
		task.runTaskTimer(this, 1L,1L);
				}
   
	@EventHandler
	public void buildtool(Player player) {
		ItemStack build = new ItemStack(Material.WRITABLE_BOOK);
		ItemMeta Meta = build.getItemMeta();
		Meta.setDisplayName("계약서");
		Meta.addEnchant(null, 0, isEnabled());
		build.setItemMeta(Meta);
		player.getInventory().addItem(build);
	}
	
	

    public  StructureBlockLibApi structureBlockLibApi;
    static Player player = Bukkit.getPlayer("toy_block");
	//@EventHandler
	public void Structure_15x15(PlayerInteractEvent event) {
		
		String viewcheck;
		
		Block targetblock;	
		Block centerblock;
			 
		Player player = event.getPlayer();
		player = this.player;
		Action action = event.getAction(); 
		targetblock = player.getTargetBlockExact(5);
		Location handle = targetblock.getLocation();
		double view = player.getLocation().getYaw();
		viewcheck = tool.getDirection(player);

		if(!(viewcheck =="S"||
				viewcheck =="W"||
				viewcheck =="N"||
				viewcheck =="E"
				)) {
			return;
		}
		
		if (!(player.getInventory().getItemInMainHand()
				.getItemMeta().getDisplayName().equals("센터"))) {

			return;
		}
		player.chat("개발진행중");
		if(targetblock.getType() ==Material.AIR) {

			return;

		}

		int xx = tool.change_xyz(handle.getBlockX());
		int zz = tool.change_xyz(handle.getBlockZ());
		int y = handle.getBlockY();
		int x = xx+40;
		int z = zz+40;

		int index = 1;
		// s일때 오른쪽 뒤
		// w 일때 오른쪽 앞
		// n 일때 왼쪽 앞
		for(int x1=-75; x1<-1; x1=x1+5)
		{
				for(int z1=-75; z1<-1; z1=z1+5) {
					
				Structure_1x1 (x+x1,y,z+z1,"castle"+index,"castle","S");
				index++;
			}
		}

		
	}
	  
	//@EventHandler
	public void Structure_15x15s(PlayerInteractEvent event) {
		
		String viewcheck;
		
		Block targetblock;	
		Block centerblock;
			 
		Player player = event.getPlayer();
		player = this.player;
		Action action = event.getAction(); 
		targetblock = player.getTargetBlockExact(5);
		Location handle = targetblock.getLocation();
		double view = player.getLocation().getYaw();
		viewcheck = tool.getDirection(player);

		if(!(viewcheck =="S"||
				viewcheck =="W"||
				viewcheck =="N"||
				viewcheck =="E"
				)) {
			return;
		}
		
		if (!(player.getInventory().getItemInMainHand()
				.getItemMeta().getDisplayName().equals("센터"))) {

			return;
		}
		player.chat("개발진행중");
		if(targetblock.getType() ==Material.AIR) {

			return;

		}

		int xx = tool.change_xyz(handle.getBlockX());
		int zz = tool.change_xyz(handle.getBlockZ());
		int y = handle.getBlockY();
		Location main = new Location(handle.getWorld(), xx, y, zz);
		village village;
		village = new village(main);
		village.castle_build("castle_test.schem", "0", "test");
		
	}

	public void Structure_maker (Location loc) {
		//75,36
		int x = loc.getBlockX();
		int z = loc.getBlockZ();
		int y = loc.getBlockY();
		int index =1;
		for(int x1=-37; x1<38; x1=x1+5) {
		
				for(int z1=-37; z1<38; z1=z1+5) {
					Location loc_make = new Location (Bukkit.getWorld("world"),x+x1,y,z+z1);
					Location loc2 = new Location (Bukkit.getWorld("world"),loc_make.getX()-2,loc_make.getY()-4,loc_make.getZ()-2);

					Structure_makers(index,loc2);					
				index++;
			}
		}
	}
	String toyblock = "toyblock"; 
	Path path = Paths.get("build/structure.nbt");
	@EventHandler
	public void Structure_makers (int nomber, Location loc) {

		  StructureBlockLibApi.INSTANCE
		  .saveStructure(plugin)
		  .at(loc)
	        .sizeX(5)
	        .sizeY(10)
	        .sizeZ(5)
	        .saveToWorld("world", "build", "Castle"+nomber)
	        .onException(e -> plugin.getLogger().log(Level.SEVERE, "Failed to save structure.", e))
	        .onResult(e -> plugin.getLogger().log(Level.INFO, ChatColor.GREEN + "Saved structure 'mystructure'."));
		
	}
	public  void Structure_1x1 (double d, double f, double g, String structure_name, String indexname, String view) {
		player.chat("시작");
		Location loc = new Location (world, d,f,g);
		Block block = world.getBlockAt(loc);
		StructureBlockLibApi.INSTANCE
	    .loadStructure(plugin)
	    .at(new Location(Bukkit.getWorld("world"), loc.getX()-2, loc.getY()+1, loc.getZ()-2))
	    .loadFromWorld("world", "build", structure_name)
	    .onException(e -> plugin.getLogger().log(Level.SEVERE, "Failed to load structure.", e))
	    .onResult(e -> plugin.getLogger().log(Level.INFO, ChatColor.GREEN + "Loaded structure 'mystructure'."));
		
		player.chat(structure_name+"생성");
		Location locs = new Location (world, d,f,g);
		player.chat("청크 생성");
		chunk_make(locs);
		
		
		Block newblock = world.getBlockAt(loc);
		if(newblock.getType().equals(Material.DIRT_PATH)) {
			Structureindex("path",loc);
		}
		else {
			Structureindex(indexname,loc);
		}

		
	}
	public static boolean structure_s_buildcheck(Location centerloc,Player player, int up1down0) {

		String ID = UUID.randomUUID().toString();
		Main.buildcheck.put(ID+"air",0);
		Main.buildcheck.put(ID+"block",0);
		
		for(int x=-2; x<3; x++)
		{

			for(int y=-3; y<1; y++)
			{

				for(int z=-2; z<3; z++)
				{
					Block block = world.getBlockAt(centerloc.getBlockX()+x, centerloc.getBlockY()+y, centerloc.getBlockZ()+z);
					if(block.getType()==Material.AIR) {
						Main.buildcheck.put(ID+"air",Main.buildcheck.get(ID+"air")+1);
					}
					else if(!(block.getType()==Material.AIR)) {
						Main.buildcheck.put(ID+"block",Main.buildcheck.get(ID+"block")+1);
					}
					int locx= centerloc.getBlockX()+x;
					int locy= centerloc.getBlockY()+y;
					int locz= centerloc.getBlockZ()+z;
				}
			}
		}
		if(up1down0==0) {
			if(Main.buildcheck.get(ID+"air")>25) {
				player.chat(""+Main.buildcheck.get(ID+"air"));
				player.chat("바닥 문제로 불 가능");
				Main.buildcheck.remove(ID+"air",0);
				Main.buildcheck.remove(ID+"block",0);
				return false;
				}
			Main.buildcheck.remove(ID+"air",0);
			Main.buildcheck.remove(ID+"block",0);
			return true;
			
		}
		if(up1down0==1) {
			if(Main.buildcheck.get(ID+"block")>25) {
				player.chat(""+Main.buildcheck.get(ID+"block"));
				player.chat("언덕 문제로 불 가능");
				Main.buildcheck.remove(ID+"air",0);
				Main.buildcheck.remove(ID+"block",0);
				return false;
			}
			Main.buildcheck.remove(ID+"air",0);
			Main.buildcheck.remove(ID+"block",0);
			return true;
			
		}
		return false;
		
		
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
	//@EventHandler
	public void Structure_build (PlayerInteractEvent event) {

	
		buildname.put("path", 1);
		//선언부
		Block targetblock;	
		Block centerblock;
	
		//플레이어 정보
		Player player = event.getPlayer();
		//플레이어가 취한 액션
		Action action = event.getAction();
		//5칸 내의 보고있는 블럭
		targetblock = player.getTargetBlockExact(5);
		//보고있는 블럭의 이름
		Location blockloc = targetblock.getLocation();
		//플레이어의 보고있는 방향
		String view = tool.getDirection(player);
		//주문서에 붙은 설명란 0번의 이름
		String lore = player.getInventory().getItemInMainHand().getItemMeta().getLore().get(0);
	
		//주문서를 들고 있지 않을경우 종료
		if (!(player.getInventory().getItemInMainHand()
				.getType().equals(Material.GLOBE_BANNER_PATTERN))) {
			return;
		}
		//주문서의 이름이 실존하지않는 건물이름일경우 종료
		if (!(buildname.containsKey(lore))) {
			return;
		}
		//타겟블럭이 공기일 경우 종료
		if(targetblock.getType() ==Material.AIR) {
			return;
		}
		//보고있는 방향이 대각선일경우 종료
		if(tool.getDirection(player).equals("NE")||
				tool.getDirection(player).equals("SE")||
				tool.getDirection(player).equals("SW")||
				tool.getDirection(player).equals("NW")||
				tool.getDirection(player).equals(null)) {
			return;
		}
		//오른쪽 클릭이 아니라면 종료
		if(!(action == Action.RIGHT_CLICK_AIR||action == Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		//XY끝자리 변환
		int xx = tool.change_xyz(blockloc.getBlockX());
		int zz = tool.change_xyz(blockloc.getBlockZ());
		int yy = blockloc.getBlockY();
		//최종 변환된 좌표의 블럭
		centerblock = world.getBlockAt(xx,yy,zz);
		//변환된 좌표의 언덕에 해당하는 위치
		Location checkloc = world.getBlockAt(xx,yy+5,zz).getLocation();
		Location centerloc = centerblock.getLocation();
		String ID = UUID.randomUUID().toString();
		if(lore.equals("path")) {
			player.chat("길");
			path_0(xx, yy, zz, player);
		}
			}
	 public void path_0 (int x, int y, int z, Player player) {
		World world = player.getWorld();
		String view = tool.getDirection(player);
		Location upside_loc = world.getBlockAt(x, y+5, z).getLocation();
		Location main_loc = world.getBlockAt(x, y, z).getLocation();
		
		if (structure_s_buildcheck(main_loc,player,0)==false) {
		return;
		}
		if (structure_s_buildcheck(upside_loc,player,1)==false) {
		return;
		}
		if (structure_s_build_linkcheck(main_loc,player,view)==false) {
		return;			
		}
		Structure_1x1(main_loc.getX(),main_loc.getY(),main_loc.getZ(), "test_path", "path", view);
		Structureindex("path", main_loc);
		return;
	}
			
Villager v;
	public void village_career (VillagerCareerChangeEvent event) {

		village_add(event);
		
	}
	public void village_add(VillagerCareerChangeEvent event) {

		Player player = Bukkit.getPlayer("toy_block");
		player.chat("주민 찾음");
		
		v = event.getEntity();
		v.setAI(false);
		BukkitRunnable timetask = new BukkitRunnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (v.getProfession().equals(Profession.ARMORER)) {
					player.chat("실행이됨");
					Merchant villager = v;
					List <org.bukkit.inventory.MerchantRecipe> recipe = new ArrayList <> () ;
					ItemStack armor = new ItemStack(Material.DIAMOND_CHESTPLATE);
					org.bukkit.inventory.MerchantRecipe recipes = new org.bukkit.inventory.MerchantRecipe(armor, 0, 30,true, 30, 30);
					recipes.setMaxUses(5);
					recipes.addIngredient(armor);
					recipes.addIngredient(armor);
					recipe.add(recipes);
					recipe.add(recipes);
					recipe.add(recipes);
					v.setRecipes(recipe);
					v.setAI(true);
					this.cancel();
				}
				else {
					player.chat("실행이안됫음");
					player.chat(v.getProfession().toString());
					this.cancel();
				}
			}
		};
		timetask.runTaskTimer(this, 1L,1L);
	}
	//@EventHandler
	public void village_data (PlayerInteractEvent event ) {

		event.getPlayer().chat(v.getProfession().toString());
	}
	public void blockcheck(Location centerloc , String ID ,Player player) {
		for(int x=-2; x<3; x++)
		{

			for(int y=-3; y<1; y++)
			{

				for(int z=-2; z<3; z++)
				{
					Block block = world.getBlockAt(centerloc.getBlockX()+x, centerloc.getBlockY()+y, centerloc.getBlockZ()+z);
					if(block.getType()==Material.AIR) {
						Main.buildcheck.put(ID+"downair",Main.buildcheck.get(ID+"downair")+1);
					}
					else if(!(block.getType()==Material.AIR)) {
						Main.buildcheck.put(ID+"downblock",Main.buildcheck.get(ID+"downblock")+1);
					}
					int locx= centerloc.getBlockX()+x;
					int locy= centerloc.getBlockY()+y;
					int locz= centerloc.getBlockZ()+z;
					player.chat(locx+ " "+locy+ " "+locz);
				}
			}
		}
		if(Main.buildcheck.get(ID+"downair")>25) {
			player.chat("바닥 문제로 불 가능");
			Main.buildcheck.remove(ID+"downair",0);
			Main.buildcheck.remove(ID+"downblock",0);
			Main.buildcheck.remove(ID+"upair",0);
			Main.buildcheck.remove(ID+"upblock",0);
			return;
		}
		
		for(int x=-2; x<3; x++)
		{

			for(int y=1; y<7; y++)
			{

				for(int z=-2; z<3; z++)
				{
					Block block = world.getBlockAt(centerloc.getBlockX()+x, centerloc.getBlockY()+y, centerloc.getBlockZ()+z);
					if(block.getType()==Material.AIR) {
						Main.buildcheck.put(ID+"upair",Main.buildcheck.get(ID+"upair")+1);
					}
					else if(!(block.getType()==Material.AIR)) {
						Main.buildcheck.put(ID+"upblock",Main.buildcheck.get(ID+"upblock")+1);
					}
				}
			}
		}
		if(Main.buildcheck.get(ID+"upblock")>25) {
			player.chat(""+Main.buildcheck.get(ID+"upblock"));
			player.chat("언덕 문제로 불 가능");
			Main.buildcheck.remove(ID+"downair",0);
			Main.buildcheck.remove(ID+"downblock",0);
			Main.buildcheck.remove(ID+"upair",0);
			Main.buildcheck.remove(ID+"upblock",0);
			return;
		}
	}
	public static void Structureindex(String index,Location loc) {
		String x=Integer.toString(loc.getBlockX());
		String y=Integer.toString(loc.getBlockY());
		String z=Integer.toString(loc.getBlockZ());
		villageindex.put(x+y+z,index);
	}
	
	public void spawn_zombie(Player player) {
		Location loc = player.getLocation();
		Entity nomal_zombie = player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
		LivingEntity entity = (LivingEntity) nomal_zombie;
		entity.getAttribute(null).setBaseValue(1);
	}
	   static HashMap<String , String > chunk_level = new HashMap<String , String > () ;
	   
	   public static void chunk_make(Location tag ) {
	      World world = tag.getWorld();
	      Chunk chunk = tag.getChunk();
	      Location chunk_loc = (Location) chunk.getBlock(1, 64, 1).getLocation();
	      Location chunk_loc_1 = new Location(world,chunk_loc.getX()+16,chunk_loc.getY(),chunk_loc.getZ());
	      Location chunk_loc_2 = new Location(world,chunk_loc.getX()+16,chunk_loc.getY(),chunk_loc.getZ()+16);
	      Location chunk_loc_3 = new Location(world,chunk_loc.getX(),chunk_loc.getY(),chunk_loc.getZ()+16);
	      Location chunk_loc_4 = new Location(world,chunk_loc.getX()-16,chunk_loc.getY(),chunk_loc.getZ()+16);
	      Location chunk_loc_5 = new Location(world,chunk_loc.getX()-16,chunk_loc.getY(),chunk_loc.getZ());
	      Location chunk_loc_6 = new Location(world,chunk_loc.getX()-16,chunk_loc.getY(),chunk_loc.getZ()-16);
	      Location chunk_loc_7 = new Location(world,chunk_loc.getX(),chunk_loc.getY(),chunk_loc.getZ()-16);
	      Location chunk_loc_8 = new Location(world,chunk_loc.getX()+16,chunk_loc.getY(),chunk_loc.getZ()-16);
		   player.chat("청크 만듬");
	      chunk_level.put(chunk_loc.getX()+"/"+chunk_loc.getY()+"/"+chunk_loc.getZ(),"castle" );
	      chunk_level.put(chunk_loc_1.getX()+"/"+chunk_loc_1.getY()+"/"+chunk_loc_1.getZ(),"castle" );
	      chunk_level.put(chunk_loc_2.getX()+"/"+chunk_loc_2.getY()+"/"+chunk_loc_2.getZ(),"castle" );
	      chunk_level.put(chunk_loc_3.getX()+"/"+chunk_loc_3.getY()+"/"+chunk_loc_3.getZ(),"castle" );
	      chunk_level.put(chunk_loc_4.getX()+"/"+chunk_loc_4.getY()+"/"+chunk_loc_4.getZ(),"castle" );
	      chunk_level.put(chunk_loc_5.getX()+"/"+chunk_loc_5.getY()+"/"+chunk_loc_5.getZ(),"castle" );
	      chunk_level.put(chunk_loc_6.getX()+"/"+chunk_loc_6.getY()+"/"+chunk_loc_6.getZ(),"castle" );
	      chunk_level.put(chunk_loc_7.getX()+"/"+chunk_loc_7.getY()+"/"+chunk_loc_7.getZ(),"castle" );
	      chunk_level.put(chunk_loc_8.getX()+"/"+chunk_loc_8.getY()+"/"+chunk_loc_8.getZ(),"castle" );
	      
	   }
	   //@EventHandler
	   public void chunk_spawn_event(EntitySpawnEvent event) {
		   SpawnReason what = event.getEntity().getEntitySpawnReason();
		   if(!(what.equals(SpawnReason.NATURAL))) {
			   return;
		   }
		   player.chat("좀비생성");
	      Chunk chunk_loc = event.getLocation().getChunk();
	      Location spawn_loc = (Location) chunk_loc.getBlock(1, 64, 1).getLocation();
	      String level = chunk_level.get(spawn_loc.getX()+"/"+spawn_loc.getY()+"/"+spawn_loc.getZ());
	      if(level.equals("castle")) {
	         world.spawnEntity(event.getLocation(), EntityType.ZOMBIE);
	         world.spawnEntity(event.getLocation(), EntityType.ZOMBIE);
	         world.spawnEntity(event.getLocation(), EntityType.ZOMBIE);
	         
	      }
	      else { 
	    	  return;
	      }
	      
	   }
		   //건물 종류
		   //마크 새기기
		   //옆 마크 확인
		   //옆 옆 마크 확인
		   
		   public void structure_level(int level ) {
		      if(level == 1) {
		         
		      }
		      
		   }
		   
		   public void Structure_index(String index,Location loc) {
		      String x=Integer.toString(loc.getBlockX());
		      String y=Integer.toString(loc.getBlockY());
		      String z=Integer.toString(loc.getBlockZ());
		      villageindex.put("X"+x+"Y"+y+"Z"+z+"",index);
		   }
		   public boolean handitem(Player player,String code ) {
		      String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
		      if (!(name.equals(code))) {
		         return false;
		      }
		      return true;
		      
		   }
		   public void index_show(Player player) {
		      BukkitRunnable task = new BukkitRunnable() {

		         @Override
		         public void run() {
		            String index = "X100Y100Z100";
		               String target_x = "X";
		               String target_y = "Y";
		               String target_z = "Z";
		               int target_num_x = index.indexOf(target_x); 
		               String result_x = index.substring(target_num_x+1,(index.substring(target_num_x+1).indexOf("Y")+target_num_x+1));
		               int x = Integer.parseInt(result_x);
		               int target_num_y = index.indexOf(target_y); 
		               String result_y = index.substring(target_num_y+1,(index.substring(target_num_y+1).indexOf("Z")+target_num_y+1));
		               int y = Integer.parseInt(result_y);
		               int target_num_z = index.indexOf(target_z); 
		               String result_z = index.substring(target_num_z+1);
		               int z = Integer.parseInt(result_z);
		               world.spawnParticle(Particle.CLOUD,x+0.5,y+2,z+0.5,10);
		               player.chat("실행");
		               if(handitem(player,"index_show")==false) {
		                  this.cancel();
		               }
		               
		               
		            }
		            
		      };
		      task.runTaskTimer(this, 1L,1L);
		   }
		   @EventHandler
		   public void hand_use(PlayerInteractEvent event) {
		      villageindex.put("X100Y100Z100","path");
		      if( event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("index_show")) {
		         index_show(event.getPlayer());
		      }   

		      
		   }
		   public void structure_index_particle(Location loc ) {
		      
		      if(loc.getBlock().getType().equals(Material.AIR)) {
		         BukkitRunnable index_task = new BukkitRunnable() {
		            
		            @Override
		            public void run() {
		               // TODO Auto-generated method stub
		               loc.getWorld().spawnParticle(Particle.FLAME, loc.getX(), loc.getY(), loc.getZ(), 10 );
		            }
		         };
		         index_task.runTaskTimer(this, 10L, 10L);
		   }
		      else {
		         UUID id = loc.getWorld().spawnEntity(loc, EntityType.SHULKER).getUniqueId();
		         LivingEntity index_mob =  (LivingEntity) world.getEntity(id);
		         index_mob.setGlowing(true);
		         index_mob.setAI(false);
		         index_mob.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
		         
		      }
		   }
		   public boolean structure_index_upgrade_widelevel_if_connection (Location loc,String name) {
		      
		      int x =  loc.getBlockX(); 
		      int y =  loc.getBlockY();
		      int z =  loc.getBlockZ();
		      String a =village_index_name.get("X"+x+5+"Y"+y+"Z"+z);
		      String b =village_index_name.get("X"+(x-5)+"Y"+y+"Z"+z);
		      String c =village_index_name.get("X"+x+"Y"+y+"Z"+z+5);
		      String d =village_index_name.get("X"+x+"Y"+y+"Z"+(z-5));
		      if(a.equals(name)) {
		         return true;
		      }
		      else if (b.equals(name)) {
		         return true;
		      }
		      else if (c.equals(name)) {
		         return true;
		      }
		      else if (d.equals(name)) {
		         return true;
		      }
		      else {
		         return false;
		      }
		   }
		   
		   public void structure_index_upgrade_widelevel(Location loc) {
		      World world = loc.getWorld();
		      int x =  loc.getBlockX(); 
		      int y =  loc.getBlockY();
		      int z =  loc.getBlockZ();
		      villageindex.get("X"+x+"Y"+y+"Z"+z);
		   }
		   public void overworld_zombie_level_1(EntitySpawnEvent event) {

		      if (!(event.getEntity().getEntitySpawnReason().equals(SpawnReason.NATURAL))) {
		         return;
		      }
		      
		   }
		   public Entity zombie_original_level_1 (Location loc) {

		      World world = loc.getWorld();
		      UUID UUID =world.spawnEntity(loc, EntityType.ZOMBIE).getUniqueId();
		      LivingEntity zombie = (LivingEntity) world.getEntity(UUID);
		      return zombie;
		   }
		   //foundation = 15x15
		   static public boolean structure_center_seting_if_groundair(Location loc) {

		      int notair = 0 ;
		      int check_end_x = loc.getBlockX()+37;
		      int check_end_z = loc.getBlockZ()+37;
		      int check_end_y = loc.getBlockY()+3;
		      for(int check_x=loc.getBlockX()-37; check_x>=check_end_x; check_x++) {
		            for(int check_y=loc.getBlockY()+1; check_y>=check_end_y; check_y++) {
		               for(int check_z=loc.getBlockZ()-37; check_z>=check_end_z; check_z++) {
		                  Block block = world.getBlockAt(check_x,check_y,check_z);
		                  if (!(block.getType().equals(Material.AIR))) {
		                     notair++;
		                     if(notair > 75) {
		                        loc.getWorld().spawnParticle(Particle.FLAME, loc.getX(), loc.getY(), loc.getZ(), 10 );
		                        return false;
		                     }
		                  }
		               }
		               
		            }
		         }
		      return true;
		      //build
		      }
		   public Location structure_index_loc_finder(Location loc) {
				int xx = tool.change_xyz(loc.getBlockX());
				int zz = tool.change_xyz(loc.getBlockZ());
				int yy = loc.getBlockY();
				Location index_loc = new Location(loc.getWorld(), xx, yy, zz);
				return index_loc;
		   }
		   public void structure_center_build_work(Location loc , String direction) {

				int xx = tool.change_xyz(loc.getBlockX());
				int zz = tool.change_xyz(loc.getBlockZ());
				int y = loc.getBlockY();
				int x = xx+40;
				int z = zz+40;

				int index = 1;
				// s일때 오른쪽 뒤
				// w 일때 오른쪽 앞
				// n 일때 왼쪽 앞
				for(int x_add=-75; x_add<-1; x_add=x_add+5)
				{
						for(int z_add=-75; z_add<-1; z_add=z_add+5) {
							
						Structure_1x1 (x+x_add,y,z+z_add,"castle"+index,"castle","S");
						index++;
					}
				}
			   
		   }
		   public void strictrue_center_build_save_frame_build(Location loc) {
			   
		   }
		   @EventHandler
		   public void structrue_cneter_build (PlayerInteractEvent event) {
			   if(!(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
				   return;
			   }
			   if(!(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("에너지코어"))) {
				   return;
			   }
			  String direction = tool.getDirection(event.getPlayer());
			  Location loc = event.getClickedBlock().getLocation();
			   //위치 정하기
			  Location index_loc = structure_index_loc_finder(loc);
			   //조건식 확인
			  boolean check = structure_center_seting_if_groundair(loc);
			  if(check == false) {
				  return;
			  }
			   //15 x 15 생성
			  structure_center_build_work(index_loc,direction);
			   //인덱스 새기기
			   //건물 스폰
			   
			   
		   }
		   @EventHandler
		   public void player_stats_zombieattack(EntityDamageByEntityEvent event) {
		      if(!(event.getEntityType().equals(EntityType.ZOMBIE))) {
		         return;
		      }
		      LivingEntity entity = (LivingEntity) event.getDamager();
		      if (!(entity instanceof Player)) { return;}
		      
		      Player player = (Player) entity;
		      String player_name = player.getName();
		      int level = player_stats.get(player_name+"zombie_attack_damage");
		      if(level == 0 ) {
		         return;
		      }
		      if(level == 1) {
		         event.setDamage(event.getDamage()+1);
		         return;
		      }
		      if(level == 2) {
		         event.setDamage(event.getDamage()+2);
		      }
		         
		   }
		   //z줄어듬
		   Player dev = (Bukkit.getPlayer("toy_block"));
		   public void structure_center_build_save_frame_manager_inventory_build_save(Location loc) {
			   int x = loc.getBlockX();
			   int y = loc.getBlockY()-1;
			   int z = loc.getBlockZ()-40;
			   Location location = new Location(loc.getWorld(), x, y, z);
			   dev.chat("세이브 시작");
			   structure_center_build_save_frame_save(location);
			   
		   }
		   public void structure_center_build_save_frame_manager_build_save_startpoint(Location loc) {
			   
		   }
			public void structure_center_build_save_frame_save(Location loc) {
				//75,36
				int x = loc.getBlockX();
				int z = loc.getBlockZ();
				int y = loc.getBlockY();
				int index =1;
				for(int x1=-37; x1<37; x1=x1+5) {
					
					for(int y1=10; y1<50; y1=y1+10) {
						
						for(int z1=-37; z1<37; z1=z1+5) {
							Location loc_make = new Location (Bukkit.getWorld("world"),x+x1,y+y1,z+z1);
							Location loc2 = new Location (Bukkit.getWorld("world"),loc_make.getX()+2,loc_make.getY()+10,loc_make.getZ()+2);

							spawnFireworks(loc2,1);
							Structure_center_save(index,loc2);
							loc2.getBlock().setType(Material.GOLD_BLOCK);;
						index++;
						}
					}
				}
			}
		    public static void spawnFireworks(Location location, int amount){
		    	
		        Location loc = location;
		        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		        FireworkMeta fwm = fw.getFireworkMeta();
		       
		        fwm.setPower(2);
		        fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());
		       
		        fw.setFireworkMeta(fwm);
		        fw.detonate();
		       
		        for(int i = 0;i<amount; i++){
		            Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		            fw2.setFireworkMeta(fwm);
		        }
		    }
			public void Structure_center_save (int nomber, Location location) {
		        Location loc = new Location(location.getWorld(), location.getX()-2, location.getY()-9, location.getZ()-2);
				  StructureBlockLibApi.INSTANCE
				  .saveStructure(plugin)
				  .at(loc)
			        .sizeX(5)
			        .sizeY(10)   
			        .sizeZ(5) 
			        .saveToWorld("world", "build", "Castle"+nomber)
			        .onException(e -> plugin.getLogger().log(Level.SEVERE, "Failed to save structure.", e))
			        .onResult(e -> plugin.getLogger().log(Level.INFO, ChatColor.GREEN + "Saved structure 'mystructure'."));
				
			}
			public void structure_center_load (PlayerInteractEvent event) {
				
				String viewcheck;
				
				Block targetblock;	
				Block centerblock;
					 
				Player player = event.getPlayer();
				player = this.player;
				Action action = event.getAction(); 
				targetblock = player.getTargetBlockExact(5);
				Location handle = targetblock.getLocation();
				double view = player.getLocation().getYaw();
				viewcheck = tool.getDirection(player);

				if(!(viewcheck =="S"||
						viewcheck =="W"||
						viewcheck =="N"||
						viewcheck =="E"
						)) {
					return;
				}
				
				if (!(player.getInventory().getItemInMainHand()
						.getItemMeta().getDisplayName().equals("센터"))) {

					return;
				}
				player.chat("개발진행중");
				if(targetblock.getType() ==Material.AIR) {

					return;

					
				}
				structure_center_build(handle);
				
			}
			@EventHandler
			public void structure_center_build(Location handle) {

				int xx = tool.change_xyz(handle.getBlockX());
				int zz = tool.change_xyz(handle.getBlockZ());
				int y = handle.getBlockY();
				int x = xx+40;
				int z = zz+40;

				int index = 1;
				// s일때 오른쪽 뒤
				// w 일때 오른쪽 앞
				// n 일때 왼쪽 앞
				for(int x1=-75; x1<-1; x1=x1+5)
				{
					for(int y1=10; y1<50; y1=y1+10) {
						for(int z1=-75; z1<-1; z1=z1+5) {
							
						Structure_1x1 (x+x1,y,z+z1,"castle"+index,"castle","S");
						index++;
					}
					}
				}

				
			}
			
			public void structure_set_chunk (Location loc,String main_tag, String side_tag) {
				Chunk chunk = loc.getChunk();
				Location chunk_loc = chunk.getBlock(1, 64, 1).getLocation();
			     World world = loc.getWorld();
			     chunk_tag.put(chunk_loc, main_tag);
			     chunk_sidechunk_set(chunk,side_tag);
			     
			      
				}
			   public static void chunk_sidechunk_set(Chunk main_chunk,String tag) {
				   World world = main_chunk.getWorld();
				   Location chunk_loc =  main_chunk.getBlock(1, 64, 1).getLocation();
				      
				   Location chunk_loc_1 = new Location(world,chunk_loc.getX()+16,chunk_loc.getY(),chunk_loc.getZ());
				   Location chunk_loc_2 = new Location(world,chunk_loc.getX()+16,chunk_loc.getY(),chunk_loc.getZ()+16);
				   Location chunk_loc_3 = new Location(world,chunk_loc.getX(),chunk_loc.getY(),chunk_loc.getZ()+16);
				   Location chunk_loc_4 = new Location(world,chunk_loc.getX()-16,chunk_loc.getY(),chunk_loc.getZ()+16);
				   Location chunk_loc_5 = new Location(world,chunk_loc.getX()-16,chunk_loc.getY(),chunk_loc.getZ());
				   Location chunk_loc_6 = new Location(world,chunk_loc.getX()-16,chunk_loc.getY(),chunk_loc.getZ()-16);
				   Location chunk_loc_7 = new Location(world,chunk_loc.getX(),chunk_loc.getY(),chunk_loc.getZ()-16);
				   Location chunk_loc_8 = new Location(world,chunk_loc.getX()+16,chunk_loc.getY(),chunk_loc.getZ()-16);
				   
				   chunk_sidechunk_set_checking(chunk_loc_1,tag);
				   chunk_sidechunk_set_checking(chunk_loc_2,tag);
				   chunk_sidechunk_set_checking(chunk_loc_3,tag);
				   chunk_sidechunk_set_checking(chunk_loc_4,tag);
				   chunk_sidechunk_set_checking(chunk_loc_5,tag);
				   chunk_sidechunk_set_checking(chunk_loc_6,tag);
				   chunk_sidechunk_set_checking(chunk_loc_7,tag);
				   chunk_sidechunk_set_checking(chunk_loc_8,tag);
				      
				      
				   }
				static HashMap<Location , String> chunk_tag = new HashMap<Location , String > () ;
			   public static void chunk_sidechunk_set_checking(Location loc,String tag) {
				   if (chunk_tag.containsKey(loc)) {

					   return;
				   }
				   chunk_tag.put(loc, tag);
				   
			   }
			   @EventHandler
			   public void player_move_join_village_chunk (PlayerMoveEvent event) {
				   if (!event.hasChangedBlock() ) {
					   return;
				   }
				   Location player_loc = event.getPlayer().getLocation();
				   Location chunk_loc = player_loc.getChunk().getBlock(1, 64, 1).getLocation();
				   if ( chunk_tag.containsKey(chunk_loc) ) {
					   if (chunk_tag.get(chunk_loc).equals("village")) {
						   tool.player_center_chat(player, "플레이어가 마을에 진입 했습니다!");
					   }
				   }
			   }
			   public void village_map_setting (Location loc) {
				   
			   }
			   public void village_map_center (Location loc) {
				   World world = loc.getWorld();
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
			   public void test_doungeon_build(Location village_loc) {
				   World world = village_loc.getWorld();
				   Chunk chunk = village_around_random_chunk_get(village_loc);
				   Location chunk_center_main = chunk.getBlock(8,8,8).getLocation();
				   int loc = world.getHighestBlockYAt(chunk_center_main);
				   Location chunk_center_loc = chunk.getBlock(8,loc,8).getLocation();
			   }
				static HashMap<Location , Block> chunk_backup = new HashMap<Location , Block > () ;
			   public void structure_build_backup_doungeon_save(Location loc) {
				   World world = loc.getWorld();
				   int x = loc.getBlockX();
				   int z = loc.getBlockZ();
				   int index = 1;
					for(int x1=-100; x1<+100; x1=x1+1)	{
						for(int y1=-100; y1<+300; y1=y1+1) {
							for(int z1=-100; z1<+100; z1=z1+1) {
								Location new_loc = new Location(world, x+x1, y1, z+z1);
								Block block = world.getBlockAt(new_loc);
								chunk_backup.put(new_loc, block);
							}
						}
					}  
			   }
			   public void structure_build_backup_doungeon_load(Location loc) {
				   World world = loc.getWorld();
				   int x = loc.getBlockX();
				   int z = loc.getBlockZ();
				   int index = 1;
					for(int x1=-100; x1<+100; x1=x1+1)	{
						for(int y1=-100; y1<+300; y1=y1+1) {
							for(int z1=-100; z1<+100; z1=z1+1) {
								Location new_loc = new Location(world, x+x1, y1, z+z1);
								Material block = chunk_backup.get(new_loc).getType();
								new_loc.getBlock().setType(block);
							}
						}
					}  
			   }
 
			   public void structure_save_test(PlayerInteractEvent event) {
				   player.chat("저장");
				   if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					   return;
					   
				   }
				   player.chat("저장");
				   Player player = event.getPlayer();
				   World world = player.getWorld();
				   Location center = player.getChunk().getBlock(8, 64, 8).getLocation();
				   schematic_tool(center,event.getPlayer());
				   player.chat("저장");
			   }

			   public void structure_save_test_load(PlayerInteractEvent event) {
				   if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
					   return;
					   
				   }
				   
				   Player player = event.getPlayer();
				   World world = player.getWorld();
				   Location center = player.getChunk().getBlock(8, 64, 8).getLocation();
				   schematic_tool_load(center, player, "0");
				   player.chat("로드");
				   
			   }
			   public void schematic_tool_load(Location loc,Player player,String a) {
				   int x_down = loc.getBlockX()-4;
				   int x_up = loc.getBlockX()+4;
				   int y_down = 0;
				   int y_up = loc.getBlockY();
				   int z_down =loc.getBlockZ()-4;
				   int z_up = loc.getBlockZ()+4;
				   Location set1 = new Location(loc.getWorld(), x_up, y_up, z_up);
				   Location set2 = new Location(loc.getWorld(), x_down, y_up , z_down);
				   WorldEditAPIController control = new WorldEditAPIController("C:/Users/근원/Desktop/1.17.1/plugins/WorldEdit/schematics", "world");
				   control.load("0.schem");
				   
				   control.paste(set1,0);
				   control.paste(set2, 180);
				   player.chat("최종완성");
						   }

				public void structure_dungeoun_backup_save(Location loc) {
					//75,36
					int x = loc.getBlockX();
					int z = loc.getBlockZ();
					int y = loc.getBlockY();
					int index =1;
					for(int x1=-50; x1<50; x1=x1+48) {
						
						for(int y1=-200; y1<200; y1=y1+48) {
							
							for(int z1=-50; z1<50; z1=z1+48) {
								Location loc_make = new Location (Bukkit.getWorld("world"),x+x1,y+y1,z+z1);
								Location loc2 = new Location (Bukkit.getWorld("world"),loc_make.getX()+2,loc_make.getY()+10,loc_make.getZ()+2);

								structure_dungeoun_backup_save(index,loc2,1);
								loc2.getBlock().setType(Material.GOLD_BLOCK);;
							index++;
							}
						}
					}
				}
				public void structure_dungeoun_backup_save (int nomber, Location location,int add) {
			        Location loc = new Location(location.getWorld(), location.getX()-2, location.getY()-9, location.getZ()-2);
					  StructureBlockLibApi.INSTANCE
					  .saveStructure(plugin)
					  .at(loc)
				        .sizeX(48)
				        .sizeY(48)   
				        .sizeZ(48) 
				        .saveToWorld("world", "build", add+"dungeoun_save"+nomber)
				        .onException(e -> plugin.getLogger().log(Level.SEVERE, "Failed to save structure.", e))
				        .onResult(e -> plugin.getLogger().log(Level.INFO, ChatColor.GREEN + "Saved structure 'mystructure'."));
					
				}
				public  void Structure_1x1_test (double d, double f, double g, String structure_name, String indexname, String view) {
					player.chat("시작");
					Location loc = new Location (world, d,f,g);
					Block block = world.getBlockAt(loc);
					StructureBlockLibApi.INSTANCE
				    .loadStructure(plugin)
				    .at(new Location(Bukkit.getWorld("world"), loc.getX()-2, loc.getY()+1, loc.getZ()-2))
				    .loadFromWorld("world", "build", structure_name)
				    .onException(e -> plugin.getLogger().log(Level.SEVERE, "Failed to load structure.", e))
				    .onResult(e -> plugin.getLogger().log(Level.INFO, ChatColor.GREEN + "Loaded structure 'mystructure'."));
					
					player.chat(structure_name+"생성");
					Location locs = new Location (world, d,f,g);
					player.chat("청크 생성");
					chunk_make(locs);
					
					
					Block newblock = world.getBlockAt(loc);
					if(newblock.getType().equals(Material.DIRT_PATH)) {
						Structureindex("path",loc);
					}
					else {
						Structureindex(indexname,loc);
					}

					
				}
				public void test_save (Location loc) {
					  StructureBlockLibApi.INSTANCE
					  .saveStructure(plugin)
					  .at(loc)
				        .sizeX(50)
				        .sizeY(200)   
				        .sizeZ(50) 
				        .saveToWorld("world", "build", "test")
				        .onException(e -> plugin.getLogger().log(Level.SEVERE, "Failed to save structure.", e))
				        .onResult(e -> plugin.getLogger().log(Level.INFO, ChatColor.GREEN + "Saved structure 'mystructure'."));
				}
				public void test_load (Location loc) {
					Structure_1x1 (loc.getBlockX(),loc.getBlockY(),loc.getBlockZ(),"","test","S");
				}
				   public void test_load(PlayerInteractEvent event) {
					   player.chat("로드");
					   if(!event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
						   return;
						   
					   }
					   Player player = event.getPlayer();
					   World world = player.getWorld();
					   player.chat("로드");
				   }

				   public void test_saver(PlayerInteractEvent event) {
					   player.chat("저장");
					   if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
						   return;
						   
					   }
					   
					   Player player = event.getPlayer();
					   World world = player.getWorld();
					   Location center = player.getChunk().getBlock(8, 64, 8).getLocation();
					  test_save(center);
					   player.chat("저장");
				   }
				   public void schematic_tool (Location loc,Player player) {
					   int x_down = loc.getBlockX()-4;
					   int x_up = loc.getBlockX()+4;
					   int y_down = 0;
					   int y_up = 256;
					   int z_down =loc.getBlockZ()-4;
					   int z_up = loc.getBlockZ()+4;
					   Location set1 = new Location(loc.getWorld(), x_up, y_up, z_up);
					   Location set2 = new Location(loc.getWorld(), x_down, y_down , z_down);
					   WorldEditAPIController control = new WorldEditAPIController("C:/Users/근원/Desktop/1.17.1/plugins/WorldEdit/schematics", "world");
					   control.copy(set1,set2,set2);
					   control.save("0.schem");
					   WorldEditAPIController control2 = new WorldEditAPIController("C:/Users/근원/Desktop/1.17.1/plugins/WorldEdit/schematics", "world");
					   control2.copy(set1,set2,set2);
					   control2.rotate();
					   control2.save("180.schem");
					   player.chat("최종완성");
							   }
				   public void village_dongeun(Location vill_loc) {
					   Chunk vill_chunk = village_around_random_chunk_get(vill_loc);
					   vill_chunk.getBlock(8, 64, 8).getLocation();
					   //밑 공간 에어 조사
					   
					   //안될시 새로운 랜덤 청크 겟
					   //밑공간 에어 조사
					   //10번의 재시도 후 강제 그 청크 생성
					   //기록 저장 후 그중 가장 에어가 없는 곳으로 선택
					   //청크 저장 
					   //그자리에 바로 생성
					   //백업 위치 저장
				   }
				   //@EventHandler
				   public void village_king_save_set (PlayerInteractEvent event) {
						   String King = "test_King";
						   LivingEntity entity = (LivingEntity) event.getPlayer().getTargetEntity(5);
						   if(!(entity.getType().equals(EntityType.ARMOR_STAND))) {
							   return;
						   }
						   if(!(entity.getEquipment().getHelmet().getItemMeta().getDisplayName().equals(King))) {
							   return;
						   }
						   Location king_loc = entity.getLocation();
						   
						   
				   }
					//	   @EventHandler
				   public void entity_name (EntitySpawnEvent event ) {
					   player.chat(event.getEntity().getCustomName());
				   }

}

	
//for(int x=-2; x<3; x++)
	//for(int y=-3; y=<0; y++)
	//	for(int z=-5; z<0; z++)