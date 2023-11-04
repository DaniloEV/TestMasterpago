package com.example.appmasterpago.ui.fragment.tenant_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmasterpago.databinding.FragmentListTenantBinding
import com.example.appmasterpago.models.api_data.TenantItemData
import com.example.appmasterpago.ui.FullscreenActivity
import com.example.appmasterpago.ui.SelectTenantActivity
import com.example.appmasterpago.ui.recyclerview.tenants.OnTenantListClickListener
import com.example.appmasterpago.ui.recyclerview.tenants.TenantListAdapter
import com.example.appmasterpago.ui.viewmodel.TenantViewModel
import com.example.appmasterpago.utils.base_model.Resource
import com.example.appmasterpago.utils.base_model.hide
import com.example.appmasterpago.utils.base_model.show
import com.example.appmasterpago.utils.ui.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONObject


/** El fragment va a ser la unión entre la vista y el view model para obtener la información y cargarka según el adapter utilizado*/
@AndroidEntryPoint
class ListTenantFragment: Fragment(),
    OnTenantListClickListener {
    ///Se le infica que vista o layout va a utilizar
    private var _binding: FragmentListTenantBinding? = null

    //el by viewModels Esto hace que hace la conexión y el ciclo de vida
    private  val tenantViewModel: TenantViewModel by viewModels()
    //Dialog
    val loadingDialog = LoadingDialog()

    //Sino funca ponerlo lazy
    //Adapter a utilizar
    private lateinit var adapter: TenantListAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Expone el layout para poder realizar el binding
        _binding = FragmentListTenantBinding.inflate(inflater, container, false)
        val view = binding.root
        initSubscription()
        initRecyclerView()


        return view
    }

    /**Indica que al crearse debe cargar en el adapter del fragment que trabaje como el del tenantListAdapter*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TenantListAdapter(requireContext(), this)

    }

    override fun onDestroyView() {
//        clicked= false
        super.onDestroyView()
        _binding = null
    }
    //region Iniciaciones
    private fun initRecyclerView() {
        binding.rvTenants.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTenants.adapter = adapter
    }

    private fun initSubscription() {
        tenantViewModel.listaSearchTenant.observe(viewLifecycleOwner, Observer { observerData ->
            try {
                when (observerData) {
                    is Resource.Loading -> {
                        loadingDialog.StartLoadingDialog(requireActivity());
                    }
                    is Resource.Success -> {

                        val isDataSucess=observerData!=null && observerData.data!=null
                                && observerData.data.result!=null && !observerData.data.sucess;
                        if (isDataSucess) {
                            var listFiterTenants=filtrarInformacion(observerData.data.result.items);
                            updateTenantList(listFiterTenants)
                            loadingDialog.DismissDialog();
                        }else{
                            binding.rvTenants.hide()
                            binding.emptyContainer.root.show()
                            loadingDialog.DismissDialog();
                            return@Observer
                        }
                    }
                    is Resource.Failure -> {
                        Toast.makeText(
                            requireActivity(),
                            "An error occurred ${observerData.exception}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }catch (e: Exception ){
                loadingDialog.DismissDialog();
            }

        })
    }

    private fun updateTenantList(it: MutableList<TenantItemData>) {
        adapter.setTenantList(it)
    }

    //endregion



    override fun onTenantClick(producto: TenantItemData, position: Int) {
//            val action =
//                ListTenantFragmentDirections.actionListTenantFragmentToSelectScannerFragment()
//            view?.findNavController()?.navigate(action)

        val intent= Intent(activity, FullscreenActivity::class.java)
        activity?.startActivity(intent)


    }

    override fun onTenantLongClick(producto: TenantItemData, position: Int) {
        TODO("Not yet implemented")
    }


    //region CUSTOM METHODS
    /**
     * Metodo que se encarga de filtrar el payload que se recibe del service para sacar obtener la información
     * @param listaFiltrar:MutableList<ResponsePayload>
     */
    private fun filtrarInformacion(result: MutableList<TenantItemData>): MutableList<TenantItemData> {
            try {
                var listaFiltrada: MutableList<TenantItemData> = mutableListOf()
                result.forEach {
                    if (!it.customCssJson.isNullOrEmpty()){

                        var jsonCss =  JSONObject(it.customCssJson);
                        var jsonStyles = jsonCss.getString("styles");


                        if (!jsonStyles.isNullOrEmpty()){
                            var jsonStyleData =JSONArray(jsonStyles)

                            for (i in 0 until jsonStyleData.length()) {
                                val element =
                                    jsonStyleData.getJSONObject(i) // which for example will be Types,TotalPoints,ExpiringToday in the case of the first array(All_Details)


                                var a=element.names();
                                if (a[0] =="logo"){
                                    var logo= element["logo"] as String;
                                    var listLogo= logo.split(".logoApp { content:url(")[1]
                                        .split(")}")[0];
                                    it.image=listLogo;
                                }

                            }
                        }

                    }
                    listaFiltrada.add(it)
                }
                return listaFiltrada;
            } catch (e: Exception ){
                  throw  e;
            }

    }
    //endregion
}