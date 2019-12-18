package manhthang.adididemo.Data;


import java.util.ArrayList;

import manhthang.adididemo.Object.ServiceObject;
import manhthang.adididemo.R;

public class ServiceData {

    //Danh sách các dịch vụ

    public static ArrayList<ServiceObject> getDataService(){
        ArrayList<ServiceObject> serviceObjects = new ArrayList<>();
        //Giao hàng
        ServiceObject delivery = new ServiceObject();
        delivery.setTitle("Giao hàng");
        delivery.setIdImv(R.drawable.ic_delivery);
        serviceObjects.add(delivery);

        ServiceObject installationSanitary = new ServiceObject();
        installationSanitary.setTitle("Lắp đặt vệ sinh");
        installationSanitary.setIdImv(R.drawable.ic_install_sanitary);
        serviceObjects.add(installationSanitary);

        ServiceObject deliveryAndinstallation = new ServiceObject();
        deliveryAndinstallation.setTitle("Giao hàng lắp đặt");
        deliveryAndinstallation.setIdImv(R.drawable.ic_install_delivery);
        serviceObjects.add(deliveryAndinstallation);

        ServiceObject guarantee = new ServiceObject();
        guarantee.setTitle("Bảo hành sữa chữa");
        guarantee.setIdImv(R.drawable.ic_guarantee);
        serviceObjects.add(guarantee);

        ServiceObject rentHouse = new ServiceObject();
        rentHouse.setTitle("Thuê kho");
        rentHouse.setIdImv(R.drawable.ic_renthouse);
        serviceObjects.add(rentHouse);

        return serviceObjects;
    }
}
