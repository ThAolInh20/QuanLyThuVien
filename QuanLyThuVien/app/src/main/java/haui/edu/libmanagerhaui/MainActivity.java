package haui.edu.libmanagerhaui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import haui.edu.libmanagerhaui.dao.PhieuMuonDAO;
import haui.edu.libmanagerhaui.dao.ThuThuDAO;
import haui.edu.libmanagerhaui.fragment.ChangePassFragment;
import haui.edu.libmanagerhaui.fragment.DoanhThuFragment;
import haui.edu.libmanagerhaui.fragment.PhieuMuonFragment;
import haui.edu.libmanagerhaui.fragment.SachFragment;
import haui.edu.libmanagerhaui.fragment.ThanhVienFragment;
import haui.edu.libmanagerhaui.fragment.TopFragment;
import haui.edu.libmanagerhaui.model.ThuThu;
import haui.edu.libmanagerhaui.model.Top;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView edUser;
    PhieuMuonDAO dao;
    ThuThuDAO thuThuDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DemoDB demoDB = new DemoDB(getApplicationContext());
//        //demoDB.thanhVien();
//        //demoDB.thuThu();

        drawer=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar1);

        //set toolbar thay thế chỗ actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, phieuMuonFragment)
                .commit();

        NavigationView nv = findViewById(R.id.nvView);
        //show user in header
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.txtUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        String username = thuThu.getHoTen();
        edUser.setText("Welcome "+username+"!");
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                int i = item.getItemId();
                if(i == R.id.nav_PhieuMuon){
                    setTitle("Receipt management");
                    PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, phieuMuonFragment)
                            .commit();
                }
                if(i == R.id.nav_LoaiSach){
                    setTitle("Type management");
                    Toast.makeText(getApplicationContext(), "Quản lý Loại sách", Toast.LENGTH_SHORT).show();
                }
                if(i == R.id.nav_Sach){
                    setTitle("Book management");
                    SachFragment sachFragment = new SachFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, sachFragment)
                            .commit();
                }
                if(i == R.id.nav_ThanhVien){
                    setTitle("Member management");
                    ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,thanhVienFragment)
                            .commit();
                }
                if(i == R.id.sub_Top){
                    setTitle("Top 10 most lent book");
                    TopFragment topFragment = new TopFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,topFragment)
                            .commit();
                }
                if(i == R.id.sub_DoanhThu){
                    setTitle("Revenue statistics");
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,doanhThuFragment)
                            .commit();
                }
                if(i == R.id.sub_AddUser){
                    setTitle("Add user");
                    AddUserFragment user = new AddUserFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,user)
                            .commit();
                }
                if(i == R.id.sub_Pass){
                    setTitle("Change password");
                    ChangePassFragment change = new ChangePassFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,change)
                            .commit();
                }
                if(i == R.id.sub_Logout){
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                drawer.closeDrawers();

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
            drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}
