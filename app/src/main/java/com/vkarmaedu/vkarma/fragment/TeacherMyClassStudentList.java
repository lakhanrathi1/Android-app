package com.vkarmaedu.vkarma.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vkarmaedu.vkarma.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherMyClassStudentList extends Fragment {


    public TeacherMyClassStudentList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_my_class_student_list, container, false);
    }

}
