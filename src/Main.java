public class Main
{
    public static void main(String[] args) 
    {
        try 
        {
            ExampleObservable observable = new ExampleObservable();
            ExampleObserver observer = new ExampleObserver();

            observable.addObserver(observer);

            observable.setXAndY(5,6);
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}