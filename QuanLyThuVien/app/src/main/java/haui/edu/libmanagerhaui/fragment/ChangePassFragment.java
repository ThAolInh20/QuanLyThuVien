package haui.edu.libmanagerhaui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import haui.edu.libmanagerhaui.R;
import haui.edu.libmanagerhaui.dao.ThuThuDAO;
import haui.edu.libmanagerhaui.model.ThuThu;


public class ChangePassFragment extends Fragment {
    TextInputEditText edPassOld, edPass, edRePass;
    Button btnSave, btnCancel;
    ThuThuDAO dao;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_pass,container,false);
        dao = new ThuThuDAO(getActivity());
        edPassOld = v.findViewById(R.id.edPassOld);
        edPass = v.findViewById(R.id.edPassChange);
        edRePass = v.findViewById(R.id.edRePassChange);
        btnSave = v.findViewById(R.id.btnSaveUserChange);
        btnCancel = v.findViewById(R.id.btnCancelUserChange);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME", "");
                if(validate() > 0){
                    ThuThu thuThu = dao.getID(user);
                    thuThu.setMatKhau(edPass.getText().toString());
                    if(dao.updatePass(thuThu) > 0){
                        Toast.makeText(getActivity(), "Change pass successfully", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    }else{
                        Toast.makeText(getActivity(), "Change pass failure", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }

    public int validate(){
        int check = 1;
        if(edPassOld.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.length() == 0){
            Toast.makeText(getContext(), "Please fill all information!", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            //đọc user,pass trong SharedPreferences
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD","");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if(!passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getContext(), "Incorrectly old password", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if(!pass.equals(rePass)){
                Toast.makeText(getContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}