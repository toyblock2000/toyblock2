package locate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.EditSessionFactory;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.function.RegionFunction;
import com.sk89q.worldedit.function.RegionMaskingFilter;
import com.sk89q.worldedit.function.block.BlockReplace;
import com.sk89q.worldedit.function.mask.BlockTypeMask;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.function.visitor.RegionVisitor;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;

public class WorldEditAPIController {
	// copy, paste, load가 공유하는 클립보드
	private Clipboard clipboard;

	// 기본 디렉토리
	private String baseDir;

	// 월드
	private World w;

	public int count;

	public WorldEditAPIController(String baseDir, String worldString) {
		this.baseDir = baseDir;
		this.w = new BukkitWorld(Bukkit.getWorld(worldString));  
	}

	public void copy(BlockVector3 pos1, BlockVector3 pos2) {
		CuboidRegion region = new CuboidRegion(w, pos1, pos2);
		this.clipboard = new BlockArrayClipboard(region);

		try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(w, -1)) {
			ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(editSession, region, this.clipboard,
					region.getMinimumPoint());
			// configure here
			try {
				Operations.complete(forwardExtentCopy);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void copy(Location loc1, Location loc2, Location center) {
		BlockVector3 centerpos = BlockVector3.at(center.getX(), center.getY(), center.getZ());
		BlockVector3 pos1 = BlockVector3.at(loc1.getX(), loc1.getY(), loc1.getZ());
		BlockVector3 pos2 = BlockVector3.at(loc2.getX(), loc2.getY(), loc2.getZ());

		CuboidRegion region = new CuboidRegion(w, pos1, pos2);
		this.clipboard = new BlockArrayClipboard(region);

		try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(w, -1)) {
			ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(editSession, region,this.clipboard,
					region.getMinimumPoint());
			this.clipboard.setOrigin(centerpos);
			// configure here
			forwardExtentCopy.setCopyingEntities(true);
			
			try {
				Operations.complete(forwardExtentCopy);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

	public void paste(BlockVector3 pos) {
		try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(w, -1)) {
			Operation operation = new ClipboardHolder(this.clipboard).createPaste(editSession).to(pos)
					// configure here
					.build();
			try {
				Operations.complete(operation);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void paste(Location loc,int rotate) {
		BlockVector3 pos = BlockVector3.at(loc.getX(), loc.getY(), loc.getZ());
		try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(w, -1)) {
			ClipboardHolder hold = new ClipboardHolder(this.clipboard);
			AffineTransform transform = new AffineTransform();
			hold.setTransform(hold.getTransform().combine(transform.rotateY(rotate)));
			Operation operation = hold.createPaste(editSession).to(pos)
					.copyEntities(true)
					
					// configure here
					.build();
			try {
				Operations.complete(operation);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void save(String fileTitle) {
		File baseDirFile = new File(this.baseDir);
		if (!baseDirFile.exists()) {
			baseDirFile.mkdir();
		}
		File file = new File(baseDirFile, fileTitle);

		try (ClipboardWriter writer = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(file))) {
			writer.write(this.clipboard);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void load(String fileTitle) {
		// file
		File baseDirFile = new File(this.baseDir);
		if (!baseDirFile.exists()) {
			baseDirFile.mkdir();
		}
		File file = new File(baseDirFile, fileTitle);
		if (!file.exists()) {
			return;
		}

		ClipboardFormat format = ClipboardFormats.findByFile(file);
		try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
			this.clipboard = reader.read();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Clipboard clip() {
		return clipboard;
	}
	public void rotate() {
		AffineTransform transform = new AffineTransform();
		ClipboardHolder clip = new ClipboardHolder(clipboard);
		clip.setTransform(clip.getTransform().combine(transform.rotateY(180)));
		this.clipboard = clip.getClipboard();
		
		
	}
	public void airCheck() {
		
		EditSession edit = WorldEdit.getInstance().newEditSession(w);
		Set<BaseBlock> air = new HashSet<>();
		BlockType water = BlockTypes.AIR;
		BlockState waterState = water.getDefaultState();
		air.add(waterState.toBaseBlock());
		int count = edit.countBlocks(clipboard.getRegion(), air);
		this.count = count;
		
	}
	public void tester() {
	}

}
