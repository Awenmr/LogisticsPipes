package logisticspipes.gui.popup;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.math.BlockPos;

import logisticspipes.interfaces.IGUIChannelInformationReceiver;
import logisticspipes.network.PacketHandler;
import logisticspipes.network.packets.gui.OpenAddChannelGUIPacket;
import logisticspipes.network.packets.gui.OpenEditChannelGUIPacket;
import logisticspipes.proxy.MainProxy;
import logisticspipes.routing.channels.ChannelInformation;
import logisticspipes.utils.gui.GuiGraphics;
import logisticspipes.utils.gui.SmallGuiButton;
import logisticspipes.utils.gui.SubGuiScreen;
import logisticspipes.utils.gui.TextListDisplay;
import logisticspipes.utils.string.StringUtils;

public class GuiSelectChannelPopup extends GuiManageChannelPopup {

	private static String GUI_LANG_KEY = "gui.popup.selectchannel.";

	private final Consumer<ChannelInformation> handleResult;

	public GuiSelectChannelPopup(List<ChannelInformation> channelList, BlockPos pos, Consumer<ChannelInformation> handleResult) {
		super(channelList, pos);
		this.handleResult = handleResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		buttonList.remove(0);
		buttonList.add(new SmallGuiButton(0, xCenter + 16, bottom - 27, 50, 10, "Select"));
	}

	protected void drawTitle() {
		mc.fontRenderer.drawStringWithShadow(
				StringUtils.translate(GUI_LANG_KEY + "title"), xCenter - (mc.fontRenderer.getStringWidth(StringUtils.translate(GUI_LANG_KEY + "title")) / 2f), guiTop + 6, 0xFFFFFF);
	}

	@Override
	protected void actionPerformed(GuiButton guibutton) throws IOException {
		if (guibutton.id == 0) { // Select
			int selected = textList.getSelected();
			if(selected >= 0) {
				handleResult.accept(channelList.get(selected));
				exitGui();
			}
		} else {
			super.actionPerformed(guibutton);
		}
	}
}
