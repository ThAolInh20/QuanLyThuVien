package haui.edu.libmanagerhaui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import haui.edu.libmanagerhaui.R;
import haui.edu.libmanagerhaui.model.LoaiSach;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> list;
    TextView tvMaLoaiSach, tvTenLoaiSach;

    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item_spinner,null);
        }
        final LoaiSach item = list.get(position);
        if(item != null){
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoaiSach.setText(item.getMaLoai()+". ");

            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoaiSach.setText(item.getTenLoai());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item_spinner,null);
        }
        final LoaiSach item = list.get(position);
        if(item != null){
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoaiSach.setText(item.getMaLoai()+". ");

            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoaiSach.setText(item.getTenLoai());
        }
        return v;
    }
}
