package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;

public class GuiConfirmOpenLink
extends GuiYesNo {
    private final String openLinkWarning;
    private final String copyLinkButtonText;
    private final String linkText;
    private boolean showSecurityWarning = true;

    public GuiConfirmOpenLink(GuiYesNoCallback p_i1084_1_, String linkTextIn, int p_i1084_3_, boolean p_i1084_4_) {
        super(p_i1084_1_, I18n.format((String)(p_i1084_4_ ? "chat.link.confirmTrusted" : "chat.link.confirm"), (Object[])new Object[0]), linkTextIn, p_i1084_3_);
        this.confirmButtonText = I18n.format((String)(p_i1084_4_ ? "chat.link.open" : "gui.yes"), (Object[])new Object[0]);
        this.cancelButtonText = I18n.format((String)(p_i1084_4_ ? "gui.cancel" : "gui.no"), (Object[])new Object[0]);
        this.copyLinkButtonText = I18n.format((String)"chat.copy", (Object[])new Object[0]);
        this.openLinkWarning = I18n.format((String)"chat.link.warning", (Object[])new Object[0]);
        this.linkText = linkTextIn;
    }

    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonList.add((Object)new GuiButton(0, width / 2 - 50 - 105, height / 6 + 96, 100, 20, this.confirmButtonText));
        this.buttonList.add((Object)new GuiButton(2, width / 2 - 50, height / 6 + 96, 100, 20, this.copyLinkButtonText));
        this.buttonList.add((Object)new GuiButton(1, width / 2 - 50 + 105, height / 6 + 96, 100, 20, this.cancelButtonText));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 2) {
            this.copyLinkToClipboard();
        }
        this.parentScreen.confirmClicked(button.id == 0, this.parentButtonClickedId);
    }

    public void copyLinkToClipboard() {
        GuiConfirmOpenLink.setClipboardString((String)this.linkText);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (this.showSecurityWarning) {
            this.drawCenteredString(this.fontRendererObj, this.openLinkWarning, width / 2, 110, 0xFFCCCC);
        }
    }

    public void disableSecurityWarning() {
        this.showSecurityWarning = false;
    }
}
