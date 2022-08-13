package com.example.ArrayListFragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.fragmentlist.R
import com.example.fragmentlist.databinding.FragmentFragmentarraylistBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [ArrayListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArrayListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var arrayList : ArrayList<String> =ArrayList()
    lateinit var binding: FragmentFragmentarraylistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentFragmentarraylistBinding.inflate(layoutInflater)
        var adapter = ArrayAdapter(requireContext(),R.layout.fragment_fragmentarraylist, arrayList)
        arrayList.add("yuvika")
        arrayList.add("trikha")
        binding.listView.adapter=adapter
        binding.fabButton.setOnClickListener{
            var dialogBinding = binding.inflate(layoutInflater)
            var dialog= Dialog(requireContext())
            dialog.setContentView(dialogBinding.root)
            dialogBinding.btnAdd.setOnClickListener {
                if (dialogBinding.etNewItem.text.toString().isNullOrEmpty()) {
                    dialogBinding.etNewItem.setError("Enter Item")
                }
                else{
                    arrayList.add(dialogBinding.etNewItem.text.toString())
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }

            }
            dialog.show()
        }
        binding.listView.setOnItemClickListener {adapterView,view,i,l ->
            var dialogBinding =UpdateItemBinding.inflate(layoutInflater)
            var dialog= Dialog(requireContext())
            dialog.setContentView(dialogBinding.root)
            dialogBinding.etUpdateItem.setText(arrayList[i])
            dialogBinding.btnUpdate.setOnClickListener{
                if(dialogBinding.etUpdateItem.text.toString().isNullOrEmpty()){
                    dialogBinding.etUpdateItem.setError("Enter Item")
                }
                else{
                    arrayList.set(i,dialogBinding.etUpdateItem.text.toString())
                    adapter.notifyDataSetChanged()

                    dialog.dismiss()
                }
            }
            dialog.show()
        }
        return binding.root
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ArrayListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ArrayListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}