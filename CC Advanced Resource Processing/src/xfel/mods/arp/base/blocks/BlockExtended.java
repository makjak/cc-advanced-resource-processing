package xfel.mods.arp.base.blocks;

import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class BlockExtended extends BlockContainer {

	protected BlockExtended(int id, Material material) {
		super(id, material);
		isBlockContainer=true;
	}
	
	@Deprecated
	@Override
	public TileEntity createNewTileEntity(World world) {
		// this method should not be called, as it is not metadata sensitive.
		return createTileEntity(world, 0);
	}
	
	@Override
	public abstract TileEntity createNewTileEntity(World world, int metadata);
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockTexture(IBlockAccess iba, int x, int y, int z, int side) {
		TileEntity te = iba.getBlockTileEntity(x, y, z);

		if (te instanceof TileExtended) {
			TileExtended tx = (TileExtended) te;

			return tx.getTextureFromSide(side);
		}

		return getBlockTextureFromSideAndMetadata(side,
				iba.getBlockMetadata(x, y, z));
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float offsetX, float offsetY,
			float offsetZ) {
		TileEntity te = world.getBlockTileEntity(x, y, z);

		if (te instanceof TileExtended) {
			TileExtended tx = (TileExtended) te;

			return tx.onActivation(player, ForgeDirection.getOrientation(side),
					offsetX, offsetY, offsetZ);
		}

		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLiving player) {
		TileEntity te = world.getBlockTileEntity(x, y, z);

		if (te instanceof TileExtended) {
			TileExtended tx = (TileExtended) te;

			tx.onPlacedBy(player);
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int blockId,
			int blockMetadata) {
		TileEntity te = world.getBlockTileEntity(x, y, z);

		if (te instanceof TileExtended) {
			TileExtended tx = (TileExtended) te;

			tx.onDestroyed();
		}
		
		world.removeBlockTileEntity(x, y, z);
	}

}
