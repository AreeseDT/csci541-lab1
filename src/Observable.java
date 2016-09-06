import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Observable
{
    private Map<Field, FieldUpdate> updates;
    private List<Observer> observers;

    public Observable()
    {
        updates = new HashMap<>();
        observers =  new ArrayList<>();
    }

    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    public void removeObserver(Observer observer)
    {
        observers.remove(observer);
    }

    public void addUpdate(Field field, FieldUpdate update, Object... args)
    {
        FieldUpdate previousUpdate = updates.remove(field);
        if(previousUpdate != null)
        {
            // This checks to see if there was already an update to a field set, and notify hasn't called.
            // If there was, consolidate the changes into a single update.
            updates.put(field, new FieldUpdate(previousUpdate.getPreviousValue(), update.getNewValue()));
        }
        else
        {
            updates.put(field, update);
        }
    }

    public void notifyObservers(Object... args)
    {
        observers.forEach(o -> {
            if(o.isApplicable(this, updates))
            {
                o.update(this, updates, args);
            }
        });

        updates.clear();
    }

    public class FieldUpdate<T>
    {
        private T previousValue;
        private T newValue;

        public FieldUpdate(T previousValue, T newValue)
        {
            this.previousValue = previousValue;
            this.newValue = newValue;
        }

        public T getPreviousValue()
        {
            return previousValue;
        }

        public T getNewValue()
        {
            return newValue;
        }
    }
}


