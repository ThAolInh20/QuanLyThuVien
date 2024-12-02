package haui.edu.libmanagerhaui.fragment;

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

public class AddUserFragment extends Fragment {
    TextInputEditText edUserName, edName, edPassword, edConfirmPassword;
    Button btnSave, btnCancel;
    ThuThuDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_them_nguoi_dung, container, false);

        // Initialize DAO
        dao = new ThuThuDAO(getActivity());

        // Bind views
        edUserName = v.findViewById(R.id.edUserName);
        edName = v.findViewById(R.id.edName);
        edPassword = v.findViewById(R.id.edPassword);
        edConfirmPassword = v.findViewById(R.id.edConfirmPassword);
        btnSave = v.findViewById(R.id.btnSaveUser);
        btnCancel = v.findViewById(R.id.btnCancel);

        // Cancel button functionality
        btnCancel.setOnClickListener(v1 -> {
            edUserName.setText("");
            edName.setText("");
            edPassword.setText("");
            edConfirmPassword.setText("");
        });

        // Save button functionality
        btnSave.setOnClickListener(v12 -> {
            if (validate() > 0) {
                ThuThu newUser = new ThuThu();
                newUser.setMaTT(edUserName.getText().toString());
                newUser.setHoTen(edName.getText().toString());
                newUser.setMatKhau(edPassword.getText().toString());

                long result = dao.insert(newUser);
                if (result > 0) {
                    Toast.makeText(getActivity(), "User added successfully", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(getActivity(), "Failed to add user", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    // Validate input fields
    public int validate() {
        int check = 1;

        if (edUserName.getText().length() == 0 ||
                edName.getText().length() == 0 ||
                edPassword.getText().length() == 0 ||
                edConfirmPassword.getText().length() == 0) {
            Toast.makeText(getContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String password = edPassword.getText().toString();
            String confirmPassword = edConfirmPassword.getText().toString();

            if (!password.equals(confirmPassword)) {
                Toast.makeText(getContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }

        return check;
    }

    // Clear input fields
    private void clearFields() {
        edUserName.setText("");
        edName.setText("");
        edPassword.setText("");
        edConfirmPassword.setText("");
    }
}
