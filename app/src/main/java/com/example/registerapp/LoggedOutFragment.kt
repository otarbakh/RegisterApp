package com.example.registerapp

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.registerapp.databinding.LoggedOutPageBinding
import com.google.firebase.auth.FirebaseAuth

class LoggedOutFragment : Fragment() {

    private lateinit var Mauth: FirebaseAuth
    private var _binding: LoggedOutPageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoggedOutPageBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Mauth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener{
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frl, LogginFragment())
                addToBackStack(LogginFragment::javaClass.name)
                commit()
            }
        }
        binding.btnRegister.setOnClickListener{
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frl, Register1Fragment())
                addToBackStack(Register1Fragment::javaClass.name)
                commit()
            }
        }
        binding.btnLogout.setOnClickListener{
            Mauth.signOut()

        }



    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}