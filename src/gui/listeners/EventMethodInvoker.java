package gui.listeners;

import java.lang.reflect.InvocationTargetException;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;

public class EventMethodInvoker<V, T> implements EventHandler<TableColumn.CellEditEvent<V,T>> {
	private final String methodName;
	private final Class<V> type;
	
	public EventMethodInvoker(Class<V> type, String methodName){ 
		this.methodName = methodName;
		this.type = type;
	}

	@Override
	public void handle(CellEditEvent<V, T> event){
		V item = event.getTableView().getItems().get(event.getTablePosition().getRow());
		
		try {
			
			type.getDeclaredMethod(methodName, String.class).invoke(item, event.getNewValue());
			
			// слишком мало эксепшенов на квадратный метр
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

			
			
		
	}
	
}