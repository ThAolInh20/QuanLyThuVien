package haui.edu.libmanagerhaui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

import haui.edu.libmanagerhaui.R;
import haui.edu.libmanagerhaui.dao.LoaiSachDAO;
import haui.edu.libmanagerhaui.fragment.SachFragment;
import haui.edu.libmanagerhaui.model.LoaiSach;
import haui.edu.libmanagerhaui.model.Sach;

public class SachAdapter extends ArrayAdapter<Sach> {
    Context context;
    SachFragment fragment;
    List<Sach> list;
    TextView tvMaSach, tvTenSach, tvTacGia, tvGiaThue, tvLoai;
    ImageView imgDel;
    public SachAdapter(@NonNull Context context, SachFragment fragment, List<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item,null);
        }
        final Sach item = list.get(position);
        if(item  != null){
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Book ID: "+item.getMaSach());
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Book Name: "+item.getTenSach());
            tvTacGia = v.findViewById(R.id.tvTacGia);
            tvTacGia.setText("Author: "+item.getTacGia());
            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Price: "+item.getGiaThue());
            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText("Type: "+loaiSach.getTenLoai());

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xóa
                fragment.delSach(String.valueOf(item.getMaSach()));
            }
        });
        return v;
    }
}
