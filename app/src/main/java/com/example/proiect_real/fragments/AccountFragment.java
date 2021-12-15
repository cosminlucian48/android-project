package com.example.proiect_real.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proiect_real.R;
import com.example.proiect_real.models.UserModel;
import com.example.proiect_real.view_model.StudentViewModel;

public class AccountFragment extends Fragment {
    private static final String TAG = AccountFragment.class.getSimpleName();
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private StudentViewModel studentViewModel;

    public TextView loggedInUserUserName;
    private TextView loggedInUserAccountType;
    private TextView loggedInUserEmail;
    private TextView loggedInUserAddress;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account,container,false);
        loggedInUserUserName = view.findViewById(R.id.loggedUserFirstName);
        loggedInUserAccountType = view.findViewById(R.id.loggedUserLastName);
        loggedInUserEmail = view.findViewById(R.id.loggedUserEmail);
        Log.d(TAG,"in account fragment");
//        Bundle bundle = getArguments().getSerializable();
        UserModel userModel = (UserModel) getArguments().getSerializable("2");
        Log.d(TAG,userModel.getEmail());

        loggedInUserUserName.setText(userModel.getUserName());
        loggedInUserAccountType.setText(userModel.getAccountType());
        loggedInUserEmail.setText(userModel.getEmail());
        return view;
    }

}
