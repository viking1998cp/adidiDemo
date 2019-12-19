package manhthang.adididemo.Data;


import java.util.ArrayList;

import manhthang.adididemo.Object.Service;
import manhthang.adididemo.R;

public class ServiceData {

    //Danh sách các dịch vụ

    public static ArrayList<Service> getDataService(){
        ArrayList<Service> services = new ArrayList<>();
        //Giao hàng
        Service delivery = new Service();
        delivery.setTitle("Giao hàng");
        delivery.setIdImv(R.drawable.ic_delivery);
        services.add(delivery);

        Service installationSanitary = new Service();
        installationSanitary.setTitle("Lắp đặt vệ sinh");
        installationSanitary.setIdImv(R.drawable.ic_install_sanitary);
        services.add(installationSanitary);

        Service deliveryAndinstallation = new Service();
        deliveryAndinstallation.setTitle("Giao hàng\n lắp đặt");
        deliveryAndinstallation.setIdImv(R.drawable.ic_install_delivery);
        services.add(deliveryAndinstallation);

        Service guarantee = new Service();
        guarantee.setTitle("Bảo hành\n sữa chữa");
        guarantee.setIdImv(R.drawable.ic_guarantee);
        services.add(guarantee);

        Service rentHouse = new Service();
        rentHouse.setTitle("Thuê kho");
        rentHouse.setIdImv(R.drawable.ic_renthouse);
        services.add(rentHouse);

        return services;
    }
}
