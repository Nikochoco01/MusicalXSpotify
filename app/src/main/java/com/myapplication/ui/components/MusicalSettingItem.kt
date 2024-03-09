package com.myapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicalSettingItem(
	modifier: Modifier,
	textItem: Int,
	textColor: Color,
	containerColor: Color,
	icon: ImageVector,
	iconDescription: Int,
	onClickable: () -> Unit
){
	ListItem(
		modifier = modifier
			.clip(RoundedCornerShape(16.dp))
			.clickable { onClickable.invoke() },
		headlineText = { Text(text = stringResource(textItem), color = textColor) },
		colors = ListItemDefaults.colors(
			containerColor = containerColor
		),
		trailingContent = {
			Icon(imageVector = icon,
				contentDescription = stringResource(iconDescription),
				tint = textColor
			)
		}
	)
}