package se.wiktoreriksson.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import java.text.MessageFormat;

public class ModHUDRender {
    private Minecraft mc = Minecraft.getMinecraft();
    private String server;
    private String account;
    private String motd;
    private long lastmsping;

    ModHUDRender(String server, String account, String motd, long lastmsping) {
        this.account = account;
        this.server = server;
        this.motd = motd;
        this.lastmsping=lastmsping;
    }

    public void renderHUD() {
        int xPos = 2;
        int yPos = 2;
        GlStateManager.pushAttrib();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        String s = MessageFormat.format("Server: {0}", server);
        String s2 = MessageFormat.format("Account: {0}", account);
        String s3 = MessageFormat.format("Last Server Ping: {0}ms",lastmsping);
        drawString(s,xPos,yPos); //Draw Server
        yPos+=10;
        drawString(s2,xPos,yPos); //Draw Account
        if (lastmsping>-1) {
            yPos+=10;
            drawString(s3,xPos,yPos); //Draw Ping
        }
        if (motd!=null) {
            yPos += 10;
            drawString(motd,xPos,yPos); //Draw MOTD
        }
        GlStateManager.popAttrib();
    }
    private void drawString(String str, int xPos, int yPos) {
        this.mc.fontRendererObj.drawString(str, xPos + 1, yPos, 0);
        this.mc.fontRendererObj.drawString(str, xPos - 1, yPos, 0);
        this.mc.fontRendererObj.drawString(str, xPos, yPos + 1, 0);
        this.mc.fontRendererObj.drawString(str, xPos, yPos - 1, 0);
        this.mc.fontRendererObj.drawString(str, xPos, yPos, 8453920);
    }
    private void drawString(long l, int xPos, int yPos) {
        drawString(String.valueOf(l),xPos,yPos);
    }
    private void drawString(double d, int xPos, int yPos) {
        drawString(String.valueOf(d),xPos,yPos);
    }
}