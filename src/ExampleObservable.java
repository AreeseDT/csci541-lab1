import java.lang.reflect.*;
import java.util.Map;

public class ExampleObservable extends Observable
{
    private int x;
    private int y;

    public ExampleObservable()
    {
        x = 0;
        y = 0;
    }

    public ExampleObservable(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int value) throws Exception
    {
        Field field = this.getClass().getDeclaredField("x");
        this.addUpdate(field, new FieldUpdate(x, value));
        x = value;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int value) throws Exception
    {
        Field field = this.getClass().getDeclaredField("y");
        this.addUpdate(field, new FieldUpdate(y, value));
        y = value;
    }
 
    public void setXAndY(int x, int y) throws Exception
    {
        this.setX(x);
        this.setY(y);
        notifyObservers();
    }
}