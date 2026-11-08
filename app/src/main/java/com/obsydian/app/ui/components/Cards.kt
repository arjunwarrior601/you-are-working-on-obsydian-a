package com.obsydian.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obsydian.app.ui.theme.ElectricBlue
import com.obsydian.app.ui.theme.GraphiteBorder
import com.obsydian.app.ui.theme.GraphiteCard
import com.obsydian.app.ui.theme.GraphiteSurface
import com.obsydian.app.ui.theme.TitaniumLight
import com.obsydian.app.ui.theme.TitaniumMedium
import com.obsydian.app.ui.theme.TitaniumWhite

@Composable
fun ObsydianCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = GraphiteCard,
    borderColor: Color = GraphiteBorder,
    borderWidth: Dp = 1.dp,
    cornerRadius: Dp = 14.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .border(
                BorderStroke(borderWidth, borderColor),
                RoundedCornerShape(cornerRadius)
            ),
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = content
        )
    }
}

@Composable
fun ObsydianStatCard(
    title: String,
    value: String,
    subtitle: String? = null,
    badgeText: String? = null,
    modifier: Modifier = Modifier,
    accentColor: Color = ElectricBlue
) {
    ObsydianCard(
        modifier = modifier,
        backgroundColor = GraphiteSurface
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title.uppercase(),
                color = TitaniumMedium,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            if (badgeText != null) {
                ObsydianBadge(
                    text = badgeText,
                    color = accentColor
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            color = TitaniumWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        if (subtitle != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                color = TitaniumMedium,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun ObsydianMediaItemRow(
    title: String,
    artist: String,
    duration: String,
    format: String = "FLAC",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(GraphiteCard)
            .border(1.dp, GraphiteBorder, RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Audio Glyph
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(GraphiteSurface)
                    .border(1.dp, GraphiteBorder, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "♪",
                    color = ElectricBlue,
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = TitaniumWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = artist,
                    color = TitaniumMedium,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            ObsydianBadge(text = format, color = ElectricBlue)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = duration,
                color = TitaniumMedium,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
