package splash.client.modules.misc;

import java.util.Queue;
import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PrimitiveIterator.OfDouble;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.lang3.RandomUtils;

import me.hippo.systems.lwjeb.annotation.Collect;
import me.hippo.systems.lwjeb.event.Stage;
import net.minecraft.block.BlockRedstoneComparator.Mode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.Packet;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import splash.Splash;
import splash.api.module.Module;
import splash.api.module.category.ModuleCategory;
import splash.api.value.impl.ModeValue;
import splash.client.events.network.EventPacketReceive;
import splash.client.events.network.EventPacketSend;
import splash.client.events.network.EventPacketSendNHPC;
import splash.client.events.player.EventMove;
import splash.client.events.player.EventPlayerUpdate;
import splash.client.modules.movement.Flight;
import splash.client.modules.movement.LongJump;
import splash.client.modules.movement.Phase;
import splash.client.modules.movement.Speed;
import splash.client.modules.player.Scaffold;
import splash.utilities.math.MathUtils;
import splash.utilities.player.BlockUtils;
import splash.utilities.system.ClientLogger;
import splash.utilities.time.Stopwatch;

/**
 * Author: Ice Created: 14:57, 13-Jun-20 Project: Client
 */
public class Disabler extends Module {
	private final Queue<Packet> packetQueue = new ConcurrentLinkedQueue();
	private final Stopwatch timer = new Stopwatch();
	private int transactionCounter;
	private int watchdogCounter;
	private double watchdogMovement;
	private boolean groundCheck, watchdogPacket;
	public ModeValue<Mode> disablerModeValue = new ModeValue<>("Mode", Mode.WATCHDOG, this);
	private final int[] counter = new int[10];
    private LinkedList<Packet> packets = new LinkedList<>();
    private EntityOtherPlayerMP position;
	public Disabler() {
		super("Disabler", "Disables anticheats.", ModuleCategory.MISC);
	}

	@Override
	public void onEnable() {
		super.onEnable();
		groundCheck = mc.thePlayer.onGround;
		if (disablerModeValue.getValue().equals(Mode.FAITHFUL)) {
			ClientLogger.printToMinecraft("To ensure disabler works, please relog.");
		}
		if (disablerModeValue.getValue().equals(Mode.WATCHDOG)) {
			ClientLogger.printToMinecraft("Please relog for the disabler to work!");
		}
		watchdogPacket = false;
		watchdogCounter = 0;
		watchdogMovement = 0;
		transactionCounter = 0;
		timer.reset();
	}

	@Override
	public void onDisable() {
		super.onDisable();
		packetQueue.clear();
		packets.forEach(packet -> mc.thePlayer.sendQueue.addToSendQueueNoEvent(packet));
        packets.clear();
	}
	

	public enum Mode {
		WATCHDOG, FAITHFUL, MINEPLEX, PVPTEMPLE, GHOSTLY, OMEGACRAFT, RINAORC, KOHI, OLDAGC, VALOR, VIPER//, WATCHDOGNCP
	}

	@Collect
	public void onPacketIn(EventPacketReceive eventPacketReceive) {
		if (eventPacketReceive.getReceivedPacket() instanceof S2APacketParticles) {
			eventPacketReceive.setCancelled(true);
		}
		
		if (disablerModeValue.getValue().equals(Mode.VIPER)) {
			if (eventPacketReceive.getReceivedPacket() instanceof S08PacketPlayerPosLook) {
				S08PacketPlayerPosLook  playerPosLook = (S08PacketPlayerPosLook) eventPacketReceive.getReceivedPacket();
				playerPosLook.y += 1.0E-4F;
			}
		}

//		if (disablerModeValue.getValue().equals(Mode.WATCHDOG) && eventPacketReceive.getReceivedPacket() instanceof net.minecraft.network.play.server.S32PacketConfirmTransaction) eventPacketReceive.setCancelled(true);
	}
	

	@Collect
	public void onUpdate(EventPlayerUpdate event) {
		
		if (event.getStage().equals(Stage.PRE)) {
		/*	if (disablerModeValue.getValue().equals(Mode.WATCHDOGNCP)) {
				if (groundCheck) {
					mc.thePlayer.onGround = true;
					double[] positions = new double[] {0.41999998688697815, 0.7531999805212024, 1.0013359791121417 , 1.1661092609382138, 1.2491870787446828 ,1.2491870787446828 ,1.2491870787446828};
					mc.thePlayer.motionY = mc.thePlayer.motionX = mc.thePlayer.motionZ = 0;
					switch (watchdogCounter) {
						case 0:
							if (!watchdogPacket) {
								for (double value : positions) {
									watchdogMovement += .15 / positions.length - 2;
									Number playerYaw = mc.thePlayer.getDirection();
									double x = mc.thePlayer.posX - Math.sin(playerYaw.doubleValue()) * watchdogMovement;
									double y = mc.thePlayer.posY;
									double z = mc.thePlayer.posZ + Math.cos(playerYaw.doubleValue()) * watchdogMovement;
								//	mc.thePlayer.sendQueue.addToSendQueueNoEvent(new C03PacketPlayer.C04PacketPlayerPosition(x, y + value, z, false));
									
									mc.thePlayer.setPositionAndUpdate(x,y + value,z);
								}
								watchdogPacket = true;
							}
						break;
						
					}
				} else if (mc.thePlayer.ticksExisted % 3 == 0) {
					groundCheck = mc.thePlayer.onGround;
				}
			}*/
		}
		
		if (disablerModeValue.getValue() == Mode.WATCHDOG) {
            if (Splash.getInstance().getModuleManager().getModuleByClass(Flight.class).isModuleActive() 
            		|| Splash.getInstance().getModuleManager().getModuleByClass(Speed.class).isModuleActive()
            		|| Splash.getInstance().getModuleManager().getModuleByClass(LongJump.class).isModuleActive()) {
            	
                if (event.getStage().equals(Stage.PRE)) {
                    if (mc.thePlayer.ticksExisted % (Splash.getInstance().getModuleManager().getModuleByClass(LongJump.class).isModuleActive() ? 10 : 20) == 0) {
                        PlayerCapabilities playerCapabilities = new PlayerCapabilities();
                        playerCapabilities.allowFlying = false;
                        playerCapabilities.isFlying = false;
                        mc.thePlayer.sendQueue.addToSendQueueNoEvent(new C13PacketPlayerAbilities(playerCapabilities));
                        
                    }
                    if (mc.thePlayer.ticksExisted % (Splash.getInstance().getModuleManager().getModuleByClass(LongJump.class).isModuleActive() ? 20 : 30) == 0) {
                        PlayerCapabilities playerCapabilities = new PlayerCapabilities();
                        playerCapabilities.allowFlying = true;
                        playerCapabilities.isFlying = true;
                        playerCapabilities.setFlySpeed(0.05F);
                        mc.thePlayer.sendQueue.addToSendQueueNoEvent(new C13PacketPlayerAbilities(playerCapabilities));
                    }
                }
            }
		}
		
		if (disablerModeValue.getValue().equals(Mode.PVPTEMPLE)) {
			event.setGround(false);
			event.setY(event.getY() + 0.42);
			event.setYaw(0);
			event.setPitch(0);
		}

		if (disablerModeValue.getValue().equals(Mode.OMEGACRAFT))  mc.thePlayer.sendQueue.addToSendQueueNoEvent(new C0FPacketConfirmTransaction(0, (short) 235, true));
	}
	

	@Collect
	public void onPacketOut(EventPacketSend e) {
		
		if (disablerModeValue.getValue().equals(Mode.VIPER)) {
			if (e.getSentPacket() instanceof C03PacketPlayer) {
				C03PacketPlayer packetPlayer = (C03PacketPlayer) e.getSentPacket();
				if (mc.thePlayer.ticksExisted < 50) {
					 mc.thePlayer.sendQueue.addToSendQueueNoEvent(new C03PacketPlayer.C04PacketPlayerPosition());
				}else {
					packetPlayer.y += 0.42F;
				}
			}
		}
		
		if (disablerModeValue.getValue().equals(Mode.WATCHDOG)) {
			if (e.getSentPacket() instanceof C03PacketPlayer ) {
				C03PacketPlayer packet = (C03PacketPlayer)e.getSentPacket();
				if (!packet.isMoving()) return;
				if (e.getSentPacket() instanceof C03PacketPlayer && EventMove.stillTime > 0) {
					e.setCancelled(true);
					if (mc.thePlayer.onGround && mc.thePlayer.isCollidedVertically) {
						packet.y = packet.y + 4.25E-12;
					}
					mc.thePlayer.sendQueue.addToSendQueueNoEvent(packet);
					if (Minecraft.getMinecraft().thePlayer.hurtTime > 0) { EventMove.stillTime = 0; }
				} else if (counter[5] == 1337) {
					this.counter[5] = 0;
					sendPosition(0,0,0, mc.thePlayer.onGround, mc.thePlayer.isMoving());
				}
			}
			if (e.getSentPacket() instanceof C0FPacketConfirmTransaction) {
				C0FPacketConfirmTransaction packetConfirmTransaction = (C0FPacketConfirmTransaction) e.getSentPacket();
				if (packetConfirmTransaction.getWindowId() == 0) {
					e.setCancelled(true);
					mc.thePlayer.sendQueue.addToSendQueueNoEvent(new C0FPacketConfirmTransaction(0, (short) (packetConfirmTransaction.getUid() + MathUtils.getRandomInteger(1000, 10000)), true));
				}
			}
			if (e.getSentPacket() instanceof C00PacketKeepAlive) {
				e.setCancelled(true);
			}
		}
		
		if (disablerModeValue.getValue().equals(Mode.VALOR)) {
			if (e.getSentPacket() instanceof C03PacketPlayer) {
				mc.thePlayer.sendQueue.addToSendQueueNoEvent(new C0CPacketInput(1.05F, 1.05F, true, false));
			}
		}
		if (disablerModeValue.getValue().equals(Mode.OLDAGC)) {
			if (mc.thePlayer.ticksExisted % 6 == 0) {
				if (!packetQueue.isEmpty()) {
					mc.thePlayer.sendQueue.addToSendQueueNoEvent(packetQueue.poll());
				} 
			} else {
				e.setCancelled(!(e.getSentPacket() instanceof C02PacketUseEntity || e.getSentPacket() instanceof C0APacketAnimation || e.getSentPacket() instanceof C08PacketPlayerBlockPlacement || e.getSentPacket() instanceof C07PacketPlayerDigging));
				packetQueue.add(e.getSentPacket());
			}
		}
		if (disablerModeValue.getValue() == Mode.FAITHFUL) {
            if (e.getSentPacket() instanceof C0BPacketEntityAction) {
                e.setCancelled(true);
            }
            if (Splash.getInstance().getModuleManager().getModuleByClass(Phase.class).isModuleActive()) return;
            
			if (mc.thePlayer != null && e.getSentPacket() instanceof C00PacketKeepAlive) {
				C00PacketKeepAlive packetKeepAlive = (C00PacketKeepAlive) e.getSentPacket();
				packetKeepAlive.key -= MathUtils.getRandomInRange(29, 45);
			}
			if (mc.thePlayer != null && mc.thePlayer.getDistance(mc.thePlayer.prevPosX, mc.thePlayer.prevPosY, mc.thePlayer.prevPosZ) > 0.6D && e.getSentPacket() instanceof C03PacketPlayer) {
	        	if (counter[1]++ > 2) {
	        		mc.thePlayer.sendQueue.addToSendQueueNoEvent(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.prevPosX + (mc.thePlayer.posX - mc.thePlayer.prevPosX) / 2.0D, mc.thePlayer.prevPosY + (mc.thePlayer.posY - mc.thePlayer.prevPosY) / 2.0D, mc.thePlayer.prevPosZ + (mc.thePlayer.posZ - mc.thePlayer.prevPosZ) / 2.0D, true));
	        		counter[1] = 0;
	        	} else {
	        		e.setCancelled(true);
	        		mc.thePlayer.sendQueue.addToSendQueueNoEvent(new C00PacketKeepAlive(-2147483648));
	        	}
			}
		}
		if (disablerModeValue.getValue().equals(Mode.MINEPLEX) || disablerModeValue.getValue().equals(Mode.KOHI)) {
			if (e.getSentPacket() instanceof C00PacketKeepAlive) {
				C00PacketKeepAlive packetKeepAlive = (C00PacketKeepAlive) e.getSentPacket();
				packetKeepAlive.key -= RandomUtils.nextInt(3, 128);
			}
			if (e.getSentPacket() instanceof C03PacketPlayer && disablerModeValue.getValue().equals(Mode.KOHI)) {
				C03PacketPlayer packet = (C03PacketPlayer) e.getSentPacket();
				e.setCancelled(true); 
				packet.onGround = true;
				mc.thePlayer.sendQueue.addToSendQueueNoEvent(packet);
			}
		}
		if (disablerModeValue.getValue().equals(Mode.RINAORC) || disablerModeValue.getValue().equals(Mode.GHOSTLY)) {
			if (e.getSentPacket() instanceof C0BPacketEntityAction || e.getSentPacket() instanceof C0FPacketConfirmTransaction || e.getSentPacket() instanceof C00PacketKeepAlive) e.setCancelled(true);
			if (e.getSentPacket() instanceof C03PacketPlayer && disablerModeValue.getValue().equals(Mode.GHOSTLY)) {
				mc.thePlayer.sendQueue.addToSendQueueNoEvent(new C18PacketSpectate(mc.thePlayer.getGameProfile().getId()));
				mc.thePlayer.sendQueue.addToSendQueueNoEvent(new C0CPacketInput());
			}
		}
		if (disablerModeValue.getValue() == Mode.OMEGACRAFT) {
            mc.timer.timerSpeed = 0.2F;
            if (e.getSentPacket() instanceof C0FPacketConfirmTransaction) {
                BlockUtils.placeHeldItemUnderPlayer();
                e.setCancelled(true);
            }
		}
	}
	public void sendDisable() {
		  PlayerCapabilities playerCapabilities = new PlayerCapabilities();
          playerCapabilities.allowFlying = true;
          playerCapabilities.isFlying = true;
          playerCapabilities.setFlySpeed(0.05F);
          mc.thePlayer.sendQueue.addToSendQueueNoEvent(new C13PacketPlayerAbilities(playerCapabilities));
	}
}