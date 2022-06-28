package com.example.registerapp

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.example.registerapp.databinding.Register1Binding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class Register1Fragment : Fragment() {

    private lateinit var Mauth: FirebaseAuth
    private var _binding: Register1Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Register1Binding.inflate(inflater, container, false)
        return binding.root
    }

    private fun userRegistration(){
        val email = binding.edEmail.text.toString()
        val password = binding.edPass.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(binding.edEmail.text.toString()).matches()){
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    Mauth.createUserWithEmailAndPassword(email,password).await()
                }catch (e:Exception){

                }
            }
        }
        else{
            Toast.makeText(requireContext(),"Check Email or Password !", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Mauth = FirebaseAuth.getInstance()

        binding.btnNext.setOnClickListener{
            userRegistration()

            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frl, Register2Fragment())
                addToBackStack(Register2Fragment::javaClass.name)
                commit()
            }
        }
        binding.btnBack.setOnClickListener{

            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frl, LoggedOutFragment())
                addToBackStack(LoggedOutFragment::javaClass.name)
                commit()
            }

        }



    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}

