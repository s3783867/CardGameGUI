package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.interfaces.PlayingCard;

@SuppressWarnings("serial")
public class CardComponent extends JPanel{
	private PlayingCard card;
	public CardComponent(PlayingCard card) {
		setSize(getWidth(), (int) (getWidth()*1.5));
		this.card = card;
		setBorder(new EmptyBorder(50, 50, 50, 50));
		}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int boundHeight = this.getBounds().height;
		int boundWidth = this.getBounds().width;
		int width = getWidth()-20;
		int height = (int) ((getWidth()-20)*1.5);
		String valueChar = getCharValue(this.card);
		
		//setting padding
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//drawing round rectangle for card
		g.setColor(Color.BLACK);
		g.drawRoundRect(10, boundHeight/4, width, height, 10, 10);
		
		
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.BOLD, 25));
		g.drawString(valueChar, 15, boundHeight/3);
		g.drawString(valueChar, boundWidth -30, boundHeight/2 + boundHeight/4);
		
//		ImageIcon i = new ImageIcon(String.format("img%sc%s.png", File.separator, this.card.getSuit().toString().toLowerCase()));
//		i.paintIcon(this, g, boundHeight/2, boundWidth/2);
	}
	
	private String getCharValue(PlayingCard card) {
		switch(card.getValue()) {
		case EIGHT: return "8";
		case NINE: return "9";
		case TEN: return "10";
		case JACK: return "J";
		case QUEEN: return "Q";
		case KING: return "K";
		case ACE: return "A";
		default: return "0";
		}
	}
}
