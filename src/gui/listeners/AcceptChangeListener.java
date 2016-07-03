package gui.listeners;

import java.util.function.BiConsumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class AcceptChangeListener<T, V> implements ChangeListener<V>{

	private T object;
	private BiConsumer<V, T> consumer;
	
	public AcceptChangeListener(T arg, BiConsumer<V, T> consumer){
		this.object = arg;
		this.consumer = consumer;
	}

	public AcceptChangeListener(T arg, BiConsumer<V, T> consumer, V value){
		this(arg, consumer);
		consumer.accept(value, arg);
	}
	
	@Override
	public void changed(ObservableValue<? extends V> observable, V oldValue, V newValue) {
		consumer.accept(observable.getValue(), object);
		
	}

}
