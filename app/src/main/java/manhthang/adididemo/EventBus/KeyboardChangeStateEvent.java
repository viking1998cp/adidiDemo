package manhthang.adididemo.EventBus;

public class KeyboardChangeStateEvent {

    private double posy;

    public double getPosy() {
        return posy;
    }

    public KeyboardChangeStateEvent(double posy) {
        this.posy = posy;
    }
}
