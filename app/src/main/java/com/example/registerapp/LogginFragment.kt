package com.example.registerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.registerapp.databinding.LoggedOutPageBinding
import com.example.registerapp.databinding.LoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LogginFragment : Fragment() {

    private lateinit var Mauth: FirebaseAuth
    private var _binding: LoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun userLogin(){
        val email = binding.edEmail.text.toString()
        val password = binding.edPass.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    Mauth.signInWithEmailAndPassword(email,password).await()

                    parentFragmentManager.beginTransaction().apply {
                        replace(R.id.frl, LoggedOutFragment())
                        addToBackStack(LoggedOutFragment::javaClass.name)
                        commit()
                    }
                    withContext(Dispatchers.Main){

                        Toast.makeText(requireContext(),"Logged in Successfully!", Toast.LENGTH_SHORT).show()


                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(),"Please Check Email or Password", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Mauth = FirebaseAuth.getInstance()


        binding.btnBack.setOnClickListener{


            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frl, LoggedOutFragment())
                addToBackStack(LoggedOutFragment::javaClass.name)
                commit()
            }

        }
        binding.btnLogin.setOnClickListener{
            userLogin()


        }






    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}