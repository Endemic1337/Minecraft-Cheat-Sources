package splash.client.modules.movement;

import me.hippo.systems.lwjeb.annotation.Collect;
import me.hippo.systems.lwjeb.event.Stage;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import splash.Splash;
import splash.api.module.Module;
import splash.api.module.category.ModuleCategory;
import splash.api.value.impl.ModeValue;
import splash.client.events.player.EventPlayerUpdate;
import splash.client.events.player.EventSlow;
import splash.client.modules.combat.Aura;
import splash.utilities.math.MathUtils;
import splash.utilities.time.Stopwatch;

/**
 * Author: Ice
 * Created: 22:35, 11-Jun-20
 * Project: Client
 */
public class NoSlow extends Module {

    public ModeValue<Mode> mode = new ModeValue<>("Autoblock", Mode.PACKETLESS, this);
	private boolean blocking;
	private Stopwatch timer;

	public NoSlow() {
		super("NoSlow", "Doesnt slow you down when you block or eat.", ModuleCategory.MOVEMENT);
		timer = new Stopwatch();
	}

	public enum Mode {
        PACKETLESS, NCP, OFFSET, CUBECRAFT
    }
    
	public boolean isHoldingSword() {
        if (mc.thePlayer != null && mc.theWorld != null && mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemSword) {
            return true;
        }
        return false;
    }
	
	@Collect
	public void onSlow(EventSlow e) {
		if (mc.thePlayer.isUsingItem() || mc.thePlayer.isBlocking()) {
			e.setCancelled(true);
			if (onServer("minemen") || mode.getValue().equals(Mode.CUBECRAFT) && !isHoldingSword()) {
				mc.thePlayer.movementInput.moveStrafe *= .7F;
				mc.thePlayer.movementInput.moveForward *= .7F;
			}
		}
	}
    
	@Collect
	public void onUpdate(EventPlayerUpdate e) {
		Aura aura = ((Aura)Splash.getInstance().getModuleManager().getModuleByClass(Aura.class));
		if (!isHoldingSword() || mode.getValue().equals(Mode.PACKETLESS)) return;
        double value = 0;
        if (mode.getValue().equals(Mode.OFFSET)) {
            value = MathUtils.getRandomInRange(-0.091133777774, -0.13333777774);
        } else {
            value = -1;
        }
		if (mc.thePlayer.isUsingItem()) {
			if (e.getStage() == Stage.POST) {
				if (!blocking) {
					mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(0,0,0), 255, mc.thePlayer.getHeldItem(), 0,0,0));
					blocking = true;
				}
			} else if (e.getStage() == Stage.PRE) {
				if (blocking) {
					if (timer.delay(85) || !mode.getValue().equals(Mode.CUBECRAFT)) {
						mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
		            	blocking = false;
						timer.reset();
					}
				}
			}
		} else {
			if (blocking) {
	            mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
	            blocking = false;
			}
		}
	}
}

