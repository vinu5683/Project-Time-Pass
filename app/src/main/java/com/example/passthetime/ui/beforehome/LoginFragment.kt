package com.example.passthetime.ui.beforehome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.passthetime.R
import com.example.passthetime.localdatabases.LocalKeys
import com.example.passthetime.localdatabases.PreferenceHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import java.lang.Exception

import com.shobhitpuri.custombuttons.GoogleSignInButton

class LoginFragment : Fragment() {

    lateinit var gso: GoogleSignInOptions
    lateinit var googleSignInClient: GoogleSignInClient
    val SIGN_IN_CODE = 10
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        PreferenceHelper.getSharedPreferences(view.context)

        initializeGoogleSignin(view)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_CODE) {
            var task: Task<GoogleSignInAccount>? = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>?) {
        try {
            if (task!!.isSuccessful) {
                val account = task.getResult(ApiException::class.java)
                updatePreference(account!!)
                Toast.makeText(view?.context, "Welcome ${account.displayName}", Toast.LENGTH_SHORT)
                    .show()
                navController!!.navigate(R.id.action_getStarted_to_loginFragment)
            } else {
                Toast.makeText(
                    view?.context,
                    "Login Error " + task.exception?.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

        } catch (e: Exception) {

        }
    }

    private fun updatePreference(account: GoogleSignInAccount) {
        PreferenceHelper.writeBooleanToPreference(LocalKeys.KEY_USER_LOGGED_IN, true)
        PreferenceHelper.writeStringToPreference(LocalKeys.KEY_USER_GOOGLE_ID, account!!.id)
        PreferenceHelper.writeStringToPreference(
            LocalKeys.KEY_USER_NAME,
            account.displayName
        )
        PreferenceHelper.writeStringToPreference(
            LocalKeys.KEY_USER_GOOGLE_GMAIL,
            account.email
        )
    }

    private fun initializeGoogleSignin(view: View) {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(view.context, gso)

        val signInButton = view.findViewById<GoogleSignInButton>(R.id.signInButton);
        signInButton.setOnClickListener {
            val intent: Intent = googleSignInClient.signInIntent
            startActivityForResult(intent, SIGN_IN_CODE)
        }
    }

}