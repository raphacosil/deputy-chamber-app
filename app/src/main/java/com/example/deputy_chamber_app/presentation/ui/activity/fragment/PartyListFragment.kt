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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deputy_chamber_app.domain.entity.PartyItem
import com.example.deputy_chamber_app.presentation.ui.activity.DeputyDetailActivity

class PartyListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            val partyList = listOf(
                PartyItem(
                    id = 1,
                    acronym = "ABC",
                    name = "Partido ABC"
                ),
                PartyItem(
                    id = 2,
                    acronym = "DEF",
                    name = "Partido DEF"
                ),
                PartyItem(
                    id = 3,
                    acronym = "XYZ",
                    name = "Partido XYZ"
                )
            )

            setContent {
                PartyList(
                    partyList = partyList,
                    onItemClick = { id ->
                        onPartyItemClick(id)
                    }
                )
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
        partyList: List<PartyItem>,
        onItemClick: (Int) -> Unit
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(partyList) { item ->
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

    private fun onPartyItemClick(partyId: Int) {
        val intent = Intent(requireContext(), DeputyDetailActivity::class.java)
        intent.putExtra("partyId", partyId)
        startActivity(intent)
    }
}