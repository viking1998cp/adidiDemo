package manhthang.adididemo.Adapter;



import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
/**
 * Created by manh thắng 98.
 */
public class MainViewPager extends FragmentPagerAdapter {
    //Tạo mảng có kiểu Fragment
    private ArrayList<Fragment> arrayfragment = new ArrayList<>();
    //Tạo mảng có kiểu string
    private ArrayList<String> arraytitle = new ArrayList<>();

    public MainViewPager(FragmentManager fm) {
        super(fm);
    }

    //Truyền vào đối tượng fragment muốn add
    @Override
    public Fragment getItem(int i) {
        return arrayfragment.get(i);
    }
    //Truyền số lượng viewpager
    @Override
    public int getCount() {
        return arrayfragment.size();
    }
    //add các tile và fragment vào viewpager
    public void addfragment(Fragment fragment, String title)
    {
        arrayfragment.add(fragment);
        arraytitle.add(title);
    }
    public void resetfragment(){
        for (int i=0;i<arrayfragment.size();i++)
        {
            arrayfragment.remove(i);
        }
        for (int j=0;j<arraytitle.size();j++)
        {
            arraytitle.remove(j);
        }

    }
    //Trả về tiêu đề
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arraytitle.get(position);
    }
}
