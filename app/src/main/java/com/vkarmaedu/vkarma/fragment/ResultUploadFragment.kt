package com.vkarmaedu.vkarma.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vkarmaedu.vkarma.R
import kotlinx.android.synthetic.main.fragment_result_upload.view.*

class ResultUploadFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_result_upload, container, false)
        root.upload.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(intent, PICK_EXCEL_SHEET)
        }
        return root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_EXCEL_SHEET && resultCode == RESULT_OK && data != null){
            Log.d(TAG, data.data?.toString())
        }
        else{
            Log.d(TAG, "error")
        }
        Log.d(TAG, "yeah")
    }

    companion object {
        private const val TAG = "ResultUpload"
        private const val PICK_EXCEL_SHEET = 3
    }
}
