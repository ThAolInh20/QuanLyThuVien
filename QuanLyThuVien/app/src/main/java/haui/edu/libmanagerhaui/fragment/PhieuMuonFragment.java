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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import haui.edu.libmanagerhaui.R;
import haui.edu.libmanagerhaui.adapter.PhieuMuonAdapter;
import haui.edu.libmanagerhaui.adapter.SachSpinnerAdapter;
import haui.edu.libmanagerhaui.adapter.ThanhVienSpinnerAdapter;
import haui.edu.libmanagerhaui.dao.PhieuMuonDAO;
import haui.edu.libmanagerhaui.dao.SachDAO;
import haui.edu.libmanagerhaui.dao.ThanhVienDAO;
import haui.edu.libmanagerhaui.model.PhieuMuon;
import haui.edu.libmanagerhaui.model.Sach;
import haui.edu.libmanagerhaui.model.ThanhVien;

public class PhieuMuonFragment extends Fragment {
    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay, tvTienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCancel;

    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listTV;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;

    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon2, container, false);
        lvPhieuMuon = v.findViewById(R.id.lvPhieuMuon);
        fab = v.findViewById(R.id.fab);
        dao = new PhieuMuonDAO(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);

            }
        });
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1); //update
                return false;
            }
        });
        capNhatLv();
        return v;
    }
    void capNhatLv(){
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(), this, list);
        lvPhieuMuon.setAdapter(adapter);
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        edMaPM.setEnabled(false);
        spTV = dialog.findViewById(R.id.spMaTV);
        spSach = dialog.findViewById(R.id.spMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        btnSave = dialog.findViewById(R.id.btnSavePM);

        //set ngay thue
        tvNgay.setText("Date: "+sdf.format(new Date()));

        //sp Thanh vien
        thanhVienDAO = new ThanhVienDAO(context);
        listTV = new ArrayList<ThanhVien>();
        listTV = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, listTV);
        spTV.setAdapter(thanhVienSpinnerAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listTV.get(position).getMaTV();
                //Toast.makeText(context, "Choose "+listTV.get(position).getHoTen(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //sp Sach
        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context, listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                tvTienThue.setText("Rent: "+tienThue);
                //Toast.makeText(context, "Choose "+listSach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //edit: set data lên form
        if(type != 0){
            edMaPM.setText(String.valueOf(item.getMaPM()));
            for(int i = 0; i < listTV.size(); i++){
                if(item.getMaTV() == (listTV.get(i).getMaTV())){
                    positionTV = i;
                }
            }
            spTV.setSelection(positionTV);

            for(int j = 0; j < listSach.size(); j++){
                if(item.getMaSach() == (listSach.get(j).getMaSach())){
                    positionSach = j;
                }
            }
            spSach.setSelection(positionSach);

            tvNgay.setText("Date: "+sdf.format(item.getNgay()));
            tvTienThue.setText("Rent: "+item.getTienThue());
            if(item.getTraSach() == 1){
                chkTraSach.setChecked(true);
            }else{
                chkTraSach.setChecked(false);
            }

        }

        //button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                if(chkTraSach.isChecked()){
                    item.setTraSach(1);
                }else{
                    item.setTraSach(0);
                }

                if(type == 0){
                    //insert
                    if(dao.insert(item) > 0){
                        Toast.makeText(context, "Add Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Add Failure", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //update
                    item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                    if(dao.update(item) > 0){
                        Toast.makeText(context, "Edit Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Edit Failure", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void xoa(final String Id){
        //sử dụng Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("You want to delete?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //gọi function xóa
                dao.delete(Id);
                //cập nhật listview
                capNhatLv();
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
}