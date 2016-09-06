import java.util.ArrayList;
import java.lang.reflect.*;
import java.util.Map;
import java.util.Arrays;

public class ExampleObserver implements Observer<ExampleObservable>
{
    public ExampleObserver()
    {
    }

    public boolean isApplicable(ExampleObservable observable,  Map<Field, Observable.FieldUpdate> updates) throws Exception
    {
        ArrayList<Field> fields = new ArrayList<Field>(Arrays.asList(observable.getClass().getDeclaredFields()));
        Field x = fields.stream()
            .filter(f -> f.getName().equals("x"))
            .findFirst()
            .get();
        return updates.containsKey(x);
    }

    public void update(ExampleObservable observable,  Map<Field, Observable.FieldUpdate> updates) throws Exception
    {
        ArrayList<Field> fields = new ArrayList<Field>(Arrays.asList(observable.getClass().getDeclaredFields()));
        Field x = fields.stream()
            .filter(f -> f.getName().equals("x"))
            .findFirst()
            .get();
        Observable.FieldUpdate xUpdate = updates.get(x);
        System.out.println("X was " + xUpdate.getPreviousValue() + " and was set to " + xUpdate.getNewValue());
    }
}