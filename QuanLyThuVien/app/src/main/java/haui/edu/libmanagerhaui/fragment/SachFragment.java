package haui.edu.libmanagerhaui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import haui.edu.libmanagerhaui.R;
import haui.edu.libmanagerhaui.adapter.LoaiSachSpinnerAdapter;
import haui.edu.libmanagerhaui.adapter.SachAdapter;
import haui.edu.libmanagerhaui.dao.LoaiSachDAO;
import haui.edu.libmanagerhaui.dao.SachDAO;
import haui.edu.libmanagerhaui.model.LoaiSach;
import haui.edu.libmanagerhaui.model.Sach;

public class SachFragment extends Fragment {
    ListView lvSach;
    SachDAO sachDAO;
    SachAdapter adapter;
    Sach item;
    List<Sach> list;
    
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach, edTenSach, edTacGia, edGiaThue;
    Spinner spinner;
    Button btnSave, btnCancel;
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach, position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sach, container, false);
        lvSach = v.findViewById(R.id.lvSach);
        sachDAO = new SachDAO(getActivity());
        updateLv();

        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }
    void updateLv(){
        list = (List<Sach>) sachDAO.getAll();
        adapter = new SachAdapter(getActivity(),this,list);
        lvSach.setAdapter(adapter);
    }
    public void delSach(final String id){
        //Sử dụng Arlert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn xóa?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Gọi function Delete
                sachDAO.delete(id);
                //Cập nhật listview
                updateLv();
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
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edTacGia = dialog.findViewById(R.id.edTacGia);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spLoaiSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);
        btnCancel = dialog.findViewById(R.id.btnCancelSach);
        
        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();

        spinnerAdapter = new LoaiSachSpinnerAdapter(context,listLoaiSach);

        spinner.setAdapter(spinnerAdapter);
        
        //lay ma loai sach
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).getMaLoai();
                Toast.makeText(context, "Choose "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        
        //kiem tra type insert 0 hay update 1
        edMaSach.setEnabled(false);
        if(type != 0){
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edTacGia.setText(item.getTacGia());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            for(int i = 0; i < listLoaiSach.size(); i++)
                if(item.getMaLoai() == (listLoaiSach.get(i).getMaLoai())) {
                    position = i;
                }
            Log.i("demo", "posSach " + position);
            spinner.setSelection(position);
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
                item = new Sach();
                item.setTenSach(edTenSach.getText().toString());
                item.setTacGia(edTacGia.getText().toString());
                item.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                item.setMaLoai(maLoaiSach);
                if(validate()>0){
                    if(type == 0){
                        if(sachDAO.insert(item)>0){
                            Toast.makeText(context, "Add Successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Add Failure", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                        if(sachDAO.update(item)>0){
                            Toast.makeText(context, "Edit Successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Edit Failure", Toast.LENGTH_SHORT).show();
                        }
                    }
                    updateLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    
    public int validate(){
        int check = 1;
        if(edTenSach.getText().length() == 0 || edTacGia.getText().length() == 0 || edGiaThue.getText().length() == 0 ){
            Toast.makeText(getContext(), "Please fill all information", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}