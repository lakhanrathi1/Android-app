package com.vkarmaedu.vkarma.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.Utility.TokenBroadcastReceiver
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {

    private val auth : FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private var customToken: String? = null
    private lateinit var tokenReceiver: TokenBroadcastReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        tokenReceiver = object : TokenBroadcastReceiver() {
            override fun onNewToken(token: String?) {
                Log.d(this.javaClass.name, "onNewToken:$token")
                setCustomToken(token.toString())
            }
        }
        root.button_signin.setOnClickListener {
            startSignIn()
        }
        return root
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    override fun onResume() {
        super.onResume()
        activity?.registerReceiver(tokenReceiver, TokenBroadcastReceiver.filter)
    }

    override fun onPause() {
        super.onPause()
        activity?.unregisterReceiver(tokenReceiver)
    }

    private fun startSignIn() {
        customToken?.let {
            activity?.let { it1 ->
                auth.signInWithCustomToken(it)
                    .addOnCompleteListener(it1) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(this.javaClass.name, "signInWithCustomToken:success")
                            val user = auth.currentUser
                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(this.javaClass.name, "signInWithCustomToken:failure", task.exception)
                            Toast.makeText(context, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            updateUI(null)
                        }
                    }
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            textSignInStatus.text = "User ID: $user.uid"
        } else {
            textSignInStatus.text = "Error: sign in failed"
        }
    }

    private fun setCustomToken(token: String) {
        customToken = token

        val status = "Token:$customToken"
        button_signin.isEnabled = true
        textTokenStatus.text = status
    }

}
