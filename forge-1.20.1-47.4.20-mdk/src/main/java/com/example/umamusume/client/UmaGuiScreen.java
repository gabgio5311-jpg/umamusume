package com.example.umamusume.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class UmaGuiScreen extends Screen {

    private final ResourceLocation texture;
    private final String umaName;
    private final String[] dialogues;
    private int dialogueIndex = 0;

    public UmaGuiScreen(ResourceLocation texture, String umaName, String[] dialogues) {
        super(Component.empty());
        this.texture = texture;
        this.umaName = umaName;
        this.dialogues = dialogues;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // Fundo escuro semitransparente
        graphics.fill(0, 0, this.width, this.height, 0xAA000000);

        // Imagem da personagem (lado esquerdo)
        int imgWidth = 200;
        int imgHeight = 300;
        int imgX = this.width / 2 - 250;
        int imgY = this.height - imgHeight - 40;
        graphics.blit(texture, imgX, imgY, 0, 0, imgWidth, imgHeight, imgWidth, imgHeight);

        // Caixa de diálogo (parte inferior)
        int boxX = 40;
        int boxY = this.height - 120;
        int boxW = this.width - 80;
        int boxH = 100;
        graphics.fill(boxX, boxY, boxX + boxW, boxY + boxH, 0xDD000000);
        graphics.renderOutline(boxX, boxY, boxW, boxH, 0xFFFFFFFF);

        // Nome da personagem
        graphics.drawString(this.font, umaName, boxX + 10, boxY + 8, 0xFFD700, true);

        // Texto do diálogo atual
        if (dialogueIndex < dialogues.length) {
            graphics.drawString(this.font, dialogues[dialogueIndex], boxX + 10, boxY + 30, 0xFFFFFF, false);
        }

        // Indicador de continuar
        graphics.drawString(this.font, "[ Clique para continuar ]", boxX + boxW - 130, boxY + boxH - 15, 0xAAAAAA, false);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        dialogueIndex++;
        if (dialogueIndex >= dialogues.length) {
            this.onClose();
        }
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}