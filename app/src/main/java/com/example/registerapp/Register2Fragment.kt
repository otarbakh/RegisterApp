package com.example.registerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.registerapp.databinding.LoginBinding
import com.example.registerapp.databinding.Register2Binding

class Register2Fragment : Fragment() {


    private var _binding: Register2Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Register2Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener{

            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frl, Register1Fragment())
                addToBackStack(Register1Fragment::javaClass.name)
                commit()
            }

        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}