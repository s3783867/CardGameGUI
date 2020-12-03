package view;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class PlayerSelectionRenderer extends BasicComboBoxRenderer{

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Player player = (Player) value;
		setText(player.getPlayerName());
		return this;
	}
}
