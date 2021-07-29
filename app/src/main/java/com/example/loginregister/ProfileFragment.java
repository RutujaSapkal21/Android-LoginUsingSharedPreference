package com.example.loginregister;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import static android.content.Context.MODE_APPEND;

public class ProfileFragment extends Fragment {
    TextView nametxt,emailtxt,phonetext;
    List<User> userList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.profilefragment,container,false);

        nametxt=view.findViewById(R.id.profileusername);
        emailtxt=view.findViewById(R.id.profileuseremail);
        phonetext=view.findViewById(R.id.profileuserphone);
        Bundle bundle=this.getArguments();
        String phone = null;
        if(getArguments()!=null)
        {
            phone=getArguments().getString("phone");

        }

        @SuppressLint("WrongConstant") SharedPreferences sharedPreferences=getActivity().getSharedPreferences("Myfile", MODE_APPEND);
        String namee=sharedPreferences.getString("username","");
        String mobilee=sharedPreferences.getString("mobile","");
        String emaill=sharedPreferences.getString("Email","");
        String password=sharedPreferences.getString("password","");

        Database database=new Database(getContext());
        User user=new User();
        user.setMobile(mobilee);
        userList=database.getUserDetails(user);

        for (User user1:userList)
        {
            nametxt.setText(user1.getUsername());
            emailtxt.setText(user1.getEmail());
            phonetext.setText(user1.getMobile());
        }

        return view;
    }
}
