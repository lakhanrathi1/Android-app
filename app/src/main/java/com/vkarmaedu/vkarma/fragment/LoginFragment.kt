package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.utility.TokenBroadcastReceiver
import com.vkarmaedu.vkarma.utility.hideKeyboard
import com.vkarmaedu.vkarma.utility.popBackStack
import com.vkarmaedu.vkarma.utility.showSnack
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private lateinit var tokenReceiver: TokenBroadcastReceiver
    private val functions: FirebaseFunctions by lazy { FirebaseFunctions.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        root.button_signin.setOnClickListener {
            val user = root.username.editableText.toString()
            val pass = root.password.editableText.toString()
            if (user.isEmpty() || pass.isEmpty() ){
                showSnack(root, "fields empty")
            }
            else getAuthToken(user, pass)
        }
        return root
    }

    private fun getAuthToken(username : String, password : String) {
        val data = hashMapOf(
            "username" to username,
            "password" to password
        )

        functions
            .getHttpsCallable("generateSignInToken")
            .call(data)
            .continueWith {
                it.result?.data as String
            }
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d(TAG, it.result.toString())
                    startSignIn(it.result as String, username)
                }
                else{
                    val e = it.exception
                    if (e is FirebaseFunctionsException) {
                        val code = e.code
                        val details = e.details
                        Log.d(TAG, "code : $code \n details : $details")
                        showSnack(this.requireView(), e.toString())
                        return@addOnCompleteListener
                    }
                    showSnack(this.requireView(), e.toString())
                    Log.d(TAG, e.toString())
                }
            }
    }

    private fun startSignIn(customToken : String, username: String) {
        activity?.let {
            auth.signInWithCustomToken(customToken)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        if (task.result?.additionalUserInfo?.isNewUser == true){
                            //update user info
                            val profileChangeRequest = UserProfileChangeRequest.Builder().setDisplayName(username).build()
                            auth.currentUser?.updateProfile(profileChangeRequest)?.addOnCompleteListener {
                                if (it.isSuccessful){
                                    Log.d(TAG, "profile update success")
                                }
                                else Log.d(TAG, "profile update fail")
                            }
                        }
                        activity?.let { activity ->
                            hideKeyboard(activity)
                            popBackStack(activity)
                        }
                    } else {
                        view?.let { it1 -> showSnack(it1, "Sign in failed") }
                    }
                }
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}
