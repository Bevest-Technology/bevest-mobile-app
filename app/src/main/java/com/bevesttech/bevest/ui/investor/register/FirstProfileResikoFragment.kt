package com.bevesttech.bevest.ui.investor.register

import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentFirstProfileResikoBinding
import com.bevesttech.bevest.utils.ViewModelFactory

class FirstProfileResikoFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentFirstProfileResikoBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ProfileResikoViewModel by activityViewModels { ViewModelFactory(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstProfileResikoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLanjut.setOnClickListener(this)

        with(binding) {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_lanjut) {
            val umur = binding.edtUmur.text
            val anak = binding.edtChildren.text
            val pendapatan = binding.edtPendapatan.text
            var valid = true

            if (anak.isNullOrEmpty()) {
                binding.edtChildren.error = "Field ini tidak boleh kosong!"
                valid = false
            }
            if (umur.isNullOrEmpty()) {
                binding.edtUmur.error = "Field ini tidak boleh kosong!"
                valid = false
            }
            if (pendapatan.isNullOrEmpty()) {
                binding.edtPendapatan.error = "Field ini tidak boleh kosong!"
                valid = false
            }

            if (valid) {
                val fragmentManager = parentFragmentManager
                val secondFragment = SecondProfileResikoFragment()
                fragmentManager.commit {
                    replace(
                        R.id.fragment_container,
                        secondFragment,
                        SecondProfileResikoFragment::class.java.simpleName
                    )
                    addToBackStack(null)
                }
            }
        }
    }

}