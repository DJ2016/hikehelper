package util;

import java.util.function.*;
import javafx.util.Pair;
public class ExpressionConsumer<T, V> {
	
	private BiConsumer<T,V> consumer;
	
 	public ExpressionConsumer(BiConsumer<T,V> consumer){
		this.consumer = consumer;
	}

 	public boolean accept(Pair<T,V> pair){
 		consumer.accept(pair.getKey(), pair.getValue());
		return true;
 	}

 	public <U> boolean requireNull (Pair<T,V> pair, U arg){
 		if (arg != null){
 			return false;
 		}
 		return accept(pair);
 	}
 	public <U> boolean requireNullAnd (Pair<T,V> pair, U arg, boolean value){
 		if (!value)
 			return false;
 		return requireNull(pair, arg);
 	}
 	
 	public <U> boolean requireNullOr (Pair<T,V> pair, U arg, boolean value){
 		if (value == true)
 			return accept(pair);
 		return requireNull(pair, arg);
 	}
 	
 	public <U> boolean requireNotNull(Pair<T,V> pair, U arg){
		if (arg != null)
			return requireNull(pair, null);
		return false;		
 	}
 	
 	public <U> boolean requireNotNullAnd(Pair<T,V> pair, U arg, boolean value){
 		if (!value)
 			return false;
 		return requireNotNull(pair, arg);
 	}
 	
 	public <U> boolean requireNotNullOr(Pair<T,V> pair, U arg, boolean value){
 		if (value == true)
 			return accept(pair);
 		return requireNotNull(pair, arg);
 	}
 	

}
