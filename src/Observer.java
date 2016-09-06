import java.lang.reflect.Field;
import java.util.Map;

public interface Observer
{
    boolean isApplicable(Observable observable,  Map<Field, Observable.FieldUpdate<?>> updates);
    void update(Observable observable, Map<Field, Observable.FieldUpdate<?>> updates, Object... args);
}
