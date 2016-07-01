package gui.controllers;

import java.io.IOException;
import java.lang.FunctionalInterface;
import java.util.function.Function;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import fxmls.FXMLFrameLoader;
import gui.App;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;

@FunctionalInterface
public interface FunctionalController<T extends Node> extends Function<FunctionalController<T>, Void>{
	
	/**
	 * invoked when sportmaster search button had been pressed
	 * sets the scene to sportmaster frame
	 * @see fxmls.FXMLFrameLoader
	 */
	@FXML
	public default void onSearchAtSportmasterClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getSportmasterFrame());
	}
	
	/**
	 * invoked when return to menu button had been pressed
	 * sets the scene to menu frame
	 * @see fxmls.FXMLFrameLoader
	 */
	@FXML
	public default void onReturnToMenuClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getMainFrame());
	}
	
	
	/**
	 * tooltip for little tips to user
	 * so if u wanna init or change fields
	 * on tooltip u should do it on ur
	 * class-inheritor
	 */
	final Tooltip tip = new Tooltip();
	
	
	/**
	 * shows the tooltip
	 * @param src for coordinates
	 * @param msg message at tooltip
	 */
	public default void showTooltip(T src, String msg){
		Bounds bnds = src.getBoundsInParent();
		Stage st = App.getStage();
		tip.setText(msg);
		tip.show(st, st.getX() +(bnds.getMinX() + bnds.getMaxX()) / 2, st.getY() + bnds.getMinY());
	}
	
	/**
	 * hides the tooltip
	 */
	public default void hideTooltip(){
		tip.hide();
	}
}
