package se.wiktoreriksson.hud;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.minecraftforge.fml.common.Mod.*;

@SideOnly(Side.CLIENT)
@Mod(modid = ModHUD.MODID, version = ModHUD.VERSION, name = ModHUD.NAME, clientSideOnly = true, acceptedMinecraftVersions = "1.8.8")
public class ModHUD {
    public static final String VERSION = "1.0";
    public static final String MODID = "wgamehud";
    public static final String NAME = "HUD Mod";

    @Instance(MODID)
    public static ModHUD inst;

    @EventHandler
    public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent) {
        System.out.println(fmlPreInitializationEvent.toString());
    }

    @EventHandler
    public void init(FMLInitializationEvent fmlInitializationEvent) {
        System.out.println(fmlInitializationEvent.toString());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent fmlPostInitializationEvent) {
        System.out.println(fmlPostInitializationEvent.toString());
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void connect(RenderGameOverlayEvent.Post rgoe) {
        try {
            if (rgoe.type==RenderGameOverlayEvent.ElementType.EXPERIENCE) new ModHUDRender().renderHUD();
        } catch (NullPointerException e) { //This means server==null
            new ModHUDRender(
                    "MINECRAFT INTEGRATED SERVER",
                    Minecraft.getMinecraft().thePlayer.getDisplayNameString(),
                    null,
                    -1
            ).renderHUD();
        }
    }
}
