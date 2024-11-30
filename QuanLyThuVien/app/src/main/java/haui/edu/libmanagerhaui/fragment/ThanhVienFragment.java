package haui.edu.libmanagerhaui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import haui.edu.libmanagerhaui.R;
import haui.edu.libmanagerhaui.adapter.ThanhVienAdapter;
import haui.edu.libmanagerhaui.dao.ThanhVienDAO;
import haui.edu.libmanagerhaui.model.ThanhVien;


public class ThanhVienFragment extends Fragment {
    ListView lvThanhVien;
    ArrayList<ThanhVien> list;
    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSave, btnCancel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lvThanhVien = v.findViewById(R.id.lvThanhVien);
        fab = v.findViewById(R.id.fab);
        dao = new ThanhVienDAO(getActivity());
        updateListView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0); //insert
            }
        });
        lvThanhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1); //update
                return false;
            }
        });
        return v;
    }
    void updateListView(){
        list =(ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(),this,list);
        lvThanhVien.setAdapter(adapter);
    }
    public void delTV(final String id){
        //Sử dụng Arlert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Are you sure to delete?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Gọi function Delete
                dao.delete(id);
                //Cập nhật listview
                updateListView();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context, final int type){
        //Custom Dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.thanh_vien_dialog);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnSave = dialog.findViewById(R.id.btnSaveTV);

        //Kiểm tra type là insert 0 hay update 1
        edMaTV.setEnabled(false);
        if(type != 0){
            edMaTV.setText(String.valueOf(item.getMaTV()));
            edTenTV.setText(item.getHoTen());
            edNamSinh.setText(item.getNamSinh());
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new ThanhVien();
                item.setHoTen(edTenTV.getText().toString());
                item.setNamSinh(edNamSinh.getText().toString());
                if(validate() > 0){
                    if(type == 0){
                        // type = 0 => insert
                        if(dao.insert(item) > 0){
                            Toast.makeText(context, "Add Successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Add Failure", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        //type = 1 => update
                        item.setMaTV(Integer.parseInt(edMaTV.getText().toString()));
                        if(dao.update(item) > 0){
                            Toast.makeText(context, "Edit Successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Edit Failure", Toast.LENGTH_SHORT).show();
                        }
                    }
                    updateListView();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public int validate(){
        int check = 1;
        if(edTenTV.getText().length() == 0 || edNamSinh.getText().length() == 0){
            Toast.makeText(getContext(), "Please fill all information!", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}