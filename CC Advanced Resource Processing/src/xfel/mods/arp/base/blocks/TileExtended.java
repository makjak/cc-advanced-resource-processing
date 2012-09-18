package xfel.mods.arp.base.blocks;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileExtended extends TileEntity {

	public boolean onActivation(EntityPlayer player,
			ForgeDirection orientation, float offsetX, float offsetY,
			float offsetZ) {
		return false;
	}

	public void onPlacedBy(EntityLiving entity) {
	}

	public void onDestroyed() {
	}

	@SideOnly(Side.CLIENT)
	public int getTextureFromSide(int side) {
		return 0;
	}

	public void onEntityCollided(Entity entity) {
	}

}