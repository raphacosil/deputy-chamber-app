package com.example.deputy_chamber_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.deputy_chamber_app.domain.entity.PartyItem
import com.example.deputy_chamber_app.presentation.viewmodel.action.PartyListAction
import com.example.deputy_chamber_app.presentation.viewmodel.state.PartyListState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class PartyListViewModel(): ViewModel() {
    private val _partyListState = MutableLiveData<PartyListState>()
    val partyListState: LiveData<PartyListState> = _partyListState
    init {
        _partyListState.value = PartyListState()
    }

    fun handleAction(action: PartyListAction) {
        when (action) {
            is PartyListAction.LoadData -> loadData()
            is PartyListAction.DataLoaded -> onDataLoaded(action.data)
            is PartyListAction.Error -> onError(action.error)
        }
    }

    private fun loadData() {
        Log.d("PartyListViewModel", "loadData: ")
        _partyListState.value = _partyListState.value?.copy(isLoading = true)
        getPartyList()
    }

    private fun onDataLoaded(data: Flow<PagingData<PartyItem>>) {
        Log.d("PartyListViewModel", "onDataLoaded: $data")
        _partyListState.value = _partyListState.value?.copy(isLoading = false, data = data)
    }

    private fun onError(error: String) {
        Log.d("PartyListViewModel", "onError: $error")
        _partyListState.value = _partyListState.value?.copy(isLoading = false, errorMessage = error)
    }

    private fun getPartyList() {
        viewModelScope.launch {
            try {
                val result: Flow<PagingData<PartyItem>> =
                    flow {
                        emit(PagingData.from(
                            listOf(
                                PartyItem(id = 1, acronym = "PT",   name = "Partido dos Trabalhadores"),
                                PartyItem(id = 2, acronym = "PSDB", name = "Partido da Social Democracia Brasileira"),
                                PartyItem(id = 3, acronym = "MDB",  name = "Movimento Democrático Brasileiro"),
                                PartyItem(id = 4, acronym = "PL",   name = "Partido Liberal"),
                                PartyItem(id = 5, acronym = "PSD",  name = "Partido Social Democrático"),
                                PartyItem(id = 6, acronym = "PP",   name = "Progressistas"),
                                PartyItem(id = 7, acronym = "UNIÃO", name = "União Brasil"),
                                PartyItem(id = 8, acronym = "REDE", name = "Rede Sustentabilidade"),
                                PartyItem(id = 9, acronym = "PSOL", name = "Partido Socialismo e Liberdade"),
                                PartyItem(id = 10, acronym = "NOVO", name = "Partido Novo"),
                                PartyItem(id = 11, acronym = "PDT",  name = "Partido Democrático Trabalhista"),
                                PartyItem(id = 12, acronym = "PTB",  name = "Partido Trabalhista Brasileiro"),
                                PartyItem(id = 13, acronym = "PCdoB", name = "Partido Comunista do Brasil"),
                                PartyItem(id = 14, acronym = "PSC",   name = "Partido Social Cristão"),
                                PartyItem(id = 15, acronym = "CIDADANIA", name = "Cidadania"),
                                PartyItem(id = 16, acronym = "PATRIOTA", name = "Patriota"),
                                PartyItem(id = 17, acronym = "PSB",  name = "Partido Socialista Brasileiro"),
                                PartyItem(id = 18, acronym = "PV",   name = "Partido Verde"),
                                PartyItem(id = 19, acronym = "AGIR", name = "Agir"),
                                PartyItem(id = 20, acronym = "DC",   name = "Democracia Cristã"),
                                PartyItem(id = 21, acronym = "PCB",  name = "Partido Comunista Brasileiro"),
                                PartyItem(id = 22, acronym = "PMB",  name = "Partido da Mulher Brasileira"),
                                PartyItem(id = 23, acronym = "UP",   name = "Unidade Popular")
                            )
                        ))
                    }


                onDataLoaded(result)
            } catch (e: Exception) {
                Log.e("PartyListViewModel",
                    "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
                onError(e.message.toString())
            }
        }
    }
}