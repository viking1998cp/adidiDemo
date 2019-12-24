package manhthang.adididemo.Data;

import java.util.ArrayList;

import manhthang.adididemo.Object.ProductGroup;

public class Product {
    public static String[] productGroup = new String[]{"Phổ thông","Điện máy", "Tivi", "Tủ lạnh", "Máy giặt", "Máy sấy", "Điều hòa", "Tủ đông","Máy nước nóng"};
    public static ArrayList<ProductGroup> getProductGroup(){
        ArrayList<ProductGroup> products = new ArrayList<>();

        ProductGroup common = new ProductGroup();
        common.setNameGroup("Phổ thông");
        common.setChecked(true);
        products.add(common);

        ProductGroup electric = new ProductGroup();
        electric.setNameGroup("Điện máy");
        electric.setChecked(false);
        products.add(electric);

        ProductGroup television = new ProductGroup();
        television.setNameGroup("Ti vi");
        television.setChecked(false);
        products.add(television);

        ProductGroup fridge = new ProductGroup();
        fridge.setNameGroup("Tủ lạnh");
        fridge.setChecked(false);
        products.add(fridge);

        ProductGroup washingmachine = new ProductGroup();
        washingmachine.setNameGroup("Máy giặt");
        washingmachine.setChecked(false);
        products.add(washingmachine);

        ProductGroup dryer = new ProductGroup();
        dryer.setNameGroup("Máy sấy");
        dryer.setChecked(false);
        products.add(dryer);

        return products;
    }
}
