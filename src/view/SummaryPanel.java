package view;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controller.PlayerUpdatedListener;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel{
	
	private GameEngine engine = new GameEngineImpl();
	private AddPlayerDialog addPlayerDialog;
	private StatusBar statusBar;
	private Vector<String> columnNames = new Vector<>();
	private Vector<Vector<String>> data = new Vector<>();
	private DefaultTableModel tableDFMO = new DefaultTableModel(data, columnNames);
	
	private String houseResult = "0";
	private int houseResultInt = 0;
	public SummaryPanel(GameEngine engine, AddPlayerDialog addPlayerDialog, StatusBar statusBar) {
		updateData();
		JTable summaryTable = new JTable(tableDFMO);
		this.engine = engine;
		this.addPlayerDialog = addPlayerDialog;
		this.statusBar = statusBar;
		PlayerUpdatedListener playerListener = new PlayerUpdatedListener(this);
		addPlayerPropListener(playerListener);
		summaryTable.setBounds(30,40,200,300);
		JScrollPane summaryScroll = new JScrollPane(summaryTable);
		Border border = BorderFactory.createTitledBorder("Summary Panel");
		summaryScroll.setBorder(border);
		summaryTable.setPreferredScrollableViewportSize(summaryTable.getPreferredSize());
		setLayout(new BorderLayout());
		add(summaryScroll, BorderLayout.NORTH);
		add(statusBar, BorderLayout.SOUTH);
		updateData();
	}
	
	public void addPlayerPropListener(PlayerUpdatedListener addPlayerListener) {
		addPlayerDialog.addPropertyChangeListener("NEW_PLAYER", addPlayerListener);
		//setBetDialog.addPropertyChangeListener("NEW_PLAYER", addPlayerListener);
	}
	public void updateData() { 
		new Thread()
		 {

			@Override
			 public void run()
			 {
				 columnNames.add("ID");
				 columnNames.add("Name");
				 columnNames.add("Bet");
				 columnNames.add("Points");
				 columnNames.add("Result");
				 columnNames.add("Win/Loss");
				 data.clear();
				 Vector<String> houseRow = new Vector<>();
				 houseRow.add("1");
				 houseRow.add("House");
				 houseRow.add("");
				 houseRow.add("");
				 houseRow.add(houseResult);
				 houseRow.add("");
				 data.add(houseRow);
				 if(engine.getAllPlayers().size() > 0) {
					for(Player player : engine.getAllPlayers()) {
						Vector<String> row = new Vector<>();
						row.add(player.getPlayerId());
						row.add(player.getPlayerName());
						row.add(Integer.toString(player.getBet()));
						row.add(Integer.toString(player.getPoints()));
						row.add(Integer.toString(player.getResult()));
						if(houseResultInt != 0) {
							if(player.getResult() < houseResultInt) {
								row.add("LOSS");
							} else if(player.getResult() < houseResultInt) {
								row.add("WIN");
							} else {
								row.add("DRAW");
							}
						} else {
							row.add("");
						}
						data.add(row);
					}
				 }
				if(engine.getAllPlayers().size() <=5) {
					tableDFMO.setNumRows(5);
				} else {
					tableDFMO.setNumRows(engine.getAllPlayers().size());
				}
				tableDFMO.fireTableDataChanged();
			 }
			 }.start();
	}
	
	public void setHouseResult(int i) {
		this.houseResultInt = i;
		this.houseResult = Integer.toString(i);
	}
}
