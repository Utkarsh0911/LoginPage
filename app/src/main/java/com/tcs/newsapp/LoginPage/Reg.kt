package com.tcs.newsapp.LoginPage

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.loginpagelayout.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Reg.newInstance] factory method to
 * create an instance of this fragment.
 */

class Reg : Fragment() {
    // TODO: Rename and change types of parameters
    private var mAuth: FirebaseAuth?=null
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var progress = ProgressDialog(activity)
        login_button.text="REGISTER"

        try{
login_button.setOnClickListener {
    if(username.text.toString().trim().equals("") || (password.text.toString().trim().equals("")))
    {
        errorField.text="UserName or Password cannot be empty."
        errorField.visibility=View.VISIBLE
    }
    else if(emailPattern.containsMatchIn(username.text.toString())==false)
    {
        errorField.text="Email Id is badly Formatted"
        errorField.visibility=View.VISIBLE
    }

    else if(passwordPattern.containsMatchIn(password.text.toString())==false)
    {
        errorField.text="Password Error : Minimum eight characters, at least one letter, one number and one special character: "
        errorField.visibility=View.VISIBLE
    }
    else
    {


        progress.show()
        progress.setContentView(R.layout.progress)
        progress.window?.setBackgroundDrawableResource(android.R.color.transparent)


        errorField.text=""
        errorField.visibility=View.INVISIBLE
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.createUserWithEmailAndPassword(username.text.toString(),password.text.toString())
            .addOnCompleteListener(OnCompleteListener {
                if(it.isSuccessful)
                { progress.dismiss()
                    errorField.text="User Created Sucessfully."
                    errorField.visibility=View.VISIBLE
                }
                else
                {
                    progress.dismiss()
                    errorField.text="User Already exists with this username"

                    errorField.visibility=View.VISIBLE
                }

            })

    }
}


        }


        catch(Ex:Exception)
        {
            progress.dismiss()
            errorField.text="Error :- ${Ex.message.toString()}."
            errorField.visibility=View.VISIBLE
        }
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reg, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Reg.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Reg().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}