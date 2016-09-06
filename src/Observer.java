import java.lang.reflect.Field;
import java.util.Map;

public interface Observer<T extends Observable>
{
    boolean isApplicable(T observable,  Map<Field, Observable.FieldUpdate> updates);
    void update(T observable, Map<Field, Observable.FieldUpdate> updates, Object... args);
}