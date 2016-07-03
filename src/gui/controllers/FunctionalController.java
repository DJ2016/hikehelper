package gui.controllers;

import java.io.IOException;
import java.lang.FunctionalInterface;
import java.util.function.Function;

import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import fxmls.FXMLFrameLoader;
import gui.App;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.util.Pair;

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
	
	

	/**
	 * there are an implementation of {@link javafx.scene.control.Alert}
	 * with auto showing
	 */
	public class AutoShowableAlert extends Alert{
		/**
		 * @param title the Title of the dialog
		 * @param information the string to show in the dialog header area.
		 */
		public AutoShowableAlert(String title, String information){
			this(title, information, Alert.AlertType.ERROR);
		}
		/**
		 * Creates an alert with the given AlertType 
		 * @param title the Title of the dialog
		 * @param information the string to show in the dialog header area.
		 * @param type 
		 */
		public AutoShowableAlert(String title, String information, AlertType type){
			super(type);
			super.setTitle(title);
			super.setHeaderText(information);
			super.show();
		}
		
		/**
		 * returns an alert with the given args
		 * @param lookup this arg provides a way in which developers 
		 * 		  may retrieve the actual Node for a given and set text on it
		 * @param context of alert
		 * @param header of alert
		 * @return instance
		 */
		public static Alert lookupInstance(Pair<ButtonType, String> lookup, String context, String header){
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, context);
			if (lookup != null)
				((Button) alert.getDialogPane().lookupButton(lookup.getKey())).setText(lookup.getValue());
			alert.setHeaderText(header);
			alert.initModality(Modality.APPLICATION_MODAL);
			return alert;
		}

	}
}
