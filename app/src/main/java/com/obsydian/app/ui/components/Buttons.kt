package com.obsydian.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obsydian.app.ui.theme.ElectricBlue
import com.obsydian.app.ui.theme.ElectricBlueDim
import com.obsydian.app.ui.theme.ElectricBlueGlow
import com.obsydian.app.ui.theme.GraphiteBorder
import com.obsydian.app.ui.theme.GraphiteCard
import com.obsydian.app.ui.theme.GraphiteDarkest
import com.obsydian.app.ui.theme.GraphiteSurface
import com.obsydian.app.ui.theme.TitaniumBorder
import com.obsydian.app.ui.theme.TitaniumLight
import com.obsydian.app.ui.theme.TitaniumWhite

@Composable
fun ObsydianPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .shadow(
                elevation = if (enabled) 6.dp else 0.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = ElectricBlueGlow,
                spotColor = ElectricBlue
            ),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ElectricBlue,
            contentColor = GraphiteDarkest,
            disabledContainerColor = GraphiteCard,
            disabledContentColor = TitaniumBorder
        ),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = if (enabled) GraphiteDarkest else TitaniumBorder
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                letterSpacing = 0.5.sp
            )
        }
    }
}

@Composable
fun ObsydianOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            1.dp,
            if (enabled) TitaniumBorder else GraphiteBorder
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = GraphiteSurface,
            contentColor = TitaniumLight,
            disabledContainerColor = GraphiteDarkest,
            disabledContentColor = TitaniumBorder
        ),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = if (enabled) ElectricBlue else TitaniumBorder
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ObsydianIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = TitaniumLight,
    backgroundColor: Color = GraphiteCard,
    borderColor: Color = GraphiteBorder,
    size: Int = 42
) {
    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .border(1.dp, borderColor, CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = ElectricBlue),
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size((size * 0.52).dp),
            tint = tint
        )
    }
}

@Composable
fun ObsydianMasterPlayButton(
    isPlaying: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(68.dp)
            .shadow(
                elevation = 10.dp,
                shape = CircleShape,
                ambientColor = ElectricBlueGlow,
                spotColor = ElectricBlue
            )
            .clip(CircleShape)
            .background(
                Brush.radialGradient(
                    colors = listOf(ElectricBlue, ElectricBlueDim)
                )
            )
            .border(2.dp, TitaniumWhite.copy(alpha = 0.4f), CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = GraphiteDarkest),
                onClick = onToggle
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isPlaying) "❚❚" else "▶",
            color = GraphiteDarkest,
            fontSize = 22.sp,
            fontWeight = FontWeight.Black
        )
    }
}
