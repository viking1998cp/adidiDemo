package manhthang.adididemo.EventBus;

public class NFCTagDiscoveredEvent {
    public static final int FROM_IN = 0;
    public static final int FROM_OUT = 1;
    private String id;
    private int type;

    @Override
    public String toString() {
        return "NFCTagDiscoveredEvent{" +
                "id='" + id + '\'' +
                ", type=" + type +
                '}';
    }

    public String getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public NFCTagDiscoveredEvent(String id, int type) {
       /* id=id.substring(2,id.length());
        id= "0"+ Utils.hex2Decimal(id);*/
        this.id = id;
        this.type = type;
    }
}
