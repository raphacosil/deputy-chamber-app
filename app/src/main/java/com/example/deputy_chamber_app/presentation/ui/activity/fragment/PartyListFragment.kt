package com.example.deputy_chamber_app.presentation.ui.activity.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.deputy_chamber_app.domain.entity.PartyItem
import com.example.deputy_chamber_app.presentation.ui.activity.DeputyDetailActivity
import com.example.deputy_chamber_app.presentation.viewmodel.PartyListViewModel
import com.example.deputy_chamber_app.presentation.viewmodel.action.PartyListAction
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class PartyListFragment : Fragment() {

    private val viewModel: PartyListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                viewModel.handleAction(PartyListAction.LoadData)
                val state by viewModel.partyListState.observeAsState()

                state?.data?.let { flow ->
                    val lazyPagingItems = flow.collectAsLazyPagingItems()

                    PartyList(
                        partyItems = lazyPagingItems,
                        onItemClick = { onPartyItemClick(it) }
                    )
                }
            }
        }
    }


    @Composable
    fun PartyItem(partyItem: PartyItem) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .width(240.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = partyItem.acronym,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(
                    text = partyItem.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    @Composable
    fun PartyList(
        partyItems: LazyPagingItems<PartyItem>,
        onItemClick: (Int) -> Unit
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(partyItems.itemCount) { index ->
                partyItems[index]?.let { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClick(item.id) }
                            .padding(16.dp)
                    ) {
                        PartyItem(item)
                    }
                }
            }
        }
    }


    private fun onPartyItemClick(partyId: Int) {
        val intent = Intent(requireContext(), DeputyDetailActivity::class.java)
        intent.putExtra("partyId", partyId)
        startActivity(intent)
    }
}